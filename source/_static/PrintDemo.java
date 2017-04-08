package net.sonma.openapi.demo;

import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.*;

/**
 * Description:
 * User: wanhongming
 * Date: 2017-04-07
 * Time: 下午3:42
 */
public class PrintDemo {
    private static final String AK = "123456789";
    private static final String SK = "123456789";
    private static final String PRINTER_SN = "95599998";
    private static final String API = "https://api.sonma.net/v1/print";
    private static final String TEST = " <CB>胜马旗舰店</CB>\n" +
            "<C>江虹国际创意园6E1201</C>\n" +
            "单号:1002325            时间:2016-07-13 13:24\n" +
            "客户:0013               员工:1605\n" +
            "------------------------------------------------\n" +
            "货号        名称              数量  单价    小计\n" +
            "------------------------------------------------\n" +
            "XY80        80打印机            2   500     1000\n" +
            "------------------------------------------------\n" +
            "数量:                           2\n" +
            "总计:                                       1000\n" +
            "------------------------------------------------\n" +
            "<B>微信:500</B>\n" +
            "<B>未付:500</B>\n" +
            "------------------------------------------------\n" +
            "农行卡：6228 4800 8207 8306 717\n" +
            "工行卡：6222 0236 0202 3368 921\n" +
            "户名：杭州胜马科技有限公司\n" +
            "温馨提示：如发现质量问题，凭此开单票据，本市的三天内，外地七日内调换，若人为损坏，开不退换！\n" +
            "------------------------------------------------\n" +
            "单据打印时间:                   2016-07-13 13:34\n" +
            "------------------------------------------------\n" +
            "技术支持(全国):0571-85353593           胜马科技";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("打印内容:");
        StringBuilder content = new StringBuilder();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.equals("test")) {
                new Thread(new Print(TEST)).start();
            } else if (line.equals("send")) {
                new Thread(new Print(content.toString())).start();
                content = new StringBuilder();
            } else {
                content.append(line);
            }
        }
    }


    static class Print implements Runnable {
        String content;

        Print(String content) {
            this.content = content;
        }

        @Override
        public void run() {
            try {
                //请求时间戳
                long timestamp = Instant.now().getEpochSecond();


                //请求参数
                Map<String, String> params = new HashMap<>();
                params.put("printer_sn", PRINTER_SN);
                params.put("content", content);


                //排序
                SortedSet<String> paramNames = Collections.synchronizedSortedSet(new TreeSet<>(params.keySet()));


                StringBuilder queryString = new StringBuilder();

                //创建规范查询字符串CanonicalQueryString,对参数名和值使用URI 编码
                paramNames.forEach(paramName -> {
                    try {
                        if (queryString.length() != 0) {
                            queryString.append("&");
                        }
                        queryString.append(URLEncoder.encode(paramName, "UTF-8")).
                                append("=").
                                append(URLEncoder.encode(params.get(paramName), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });

                System.out.println("规范查询字符串(CanonicalQueryString):" + queryString);
                String hashedQueryString = SignatureUtil.sha1AsHex(queryString.toString());
                System.out.println("规范查询字符串哈希(HashedCanonicalQueryString):" + hashedQueryString);
                String stringToSign = timestamp + "\n" + hashedQueryString;
                System.out.println("待签字符串(StringToSign):" + stringToSign);
                String signature = SignatureUtil.macSignature(stringToSign, SK);
                System.out.println("签名(Signature):" + signature);
                String authorization = Base64.getEncoder().encodeToString(String.format("HMAC-SHA1 %s:%s", AK, signature).getBytes("UTF-8"));
                System.out.println("鉴权字符串(Authorization):" + authorization);


                OkHttpClient client = new OkHttpClient.Builder()
                        .build();


                Request request = new Request.Builder()
                        .url(API)
                        .header("Authorization", authorization)
                        .header("Timestamp", String.valueOf(timestamp))
                        .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), queryString.toString()))
                        .build();

                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    System.out.println("服务器错误" + response);
                } else {
                    System.out.println(response.body().string());
                }
            } catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class SignatureUtil {

        final static char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        static String macSignature(String text, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
            return macSignature(text.getBytes(StandardCharsets.UTF_8), secretKey.getBytes(StandardCharsets.UTF_8));
        }

        static String macSignature(byte[] text, byte[] secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
            return hexEncode(hmac(text, secretKey));
        }

        static byte[] hmac(byte[] text, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
            String algorithm = "HmacSHA1";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(text);
        }


        static String hexEncode(byte[] data) {
            int l = data.length;
            char[] out = new char[l << 1];
            for (int i = 0, j = 0; i < l; i++) {
                out[j++] = HEX[(0xF0 & data[i]) >>> 4];
                out[j++] = HEX[0x0F & data[i]];
            }
            return new String(out);
        }

        static String sha1AsHex(String data) {
            byte[] dataBytes = getDigest("SHA1").digest(data.getBytes(StandardCharsets.UTF_8));
            return hexEncode(dataBytes);
        }


        static MessageDigest getDigest(String algorithm) {
            try {
                return MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException ex) {
                throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
            }
        }


    }
}


