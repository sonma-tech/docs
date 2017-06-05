打印模板
================



110mm打印机模板说明
------------------------

支持EJS语法动态模板

标签说明
^^^^^^^^^^^^^^^^^^^

::

    一.模板文件使用标准xml语法与ejs模板语言的方式组合
    二.模板标签的根元素必须是page,其它标签都为二级标签并不允许嵌套
    三.打印机支持的单位有:px, mm,如无特殊说明,默认单位都为mm,(DA200的分辨率为1mm = 8 px)
    四.page标签,page支持的属性有:
        1.width		:决定打印纸张宽度
        2.height	:决定纸张高度
        3.gap		:决定标签纸之间的缝隙;如不为标签纸,请填写0或者不填
        4.offset	:决定打印完成后,继续出纸的长度
    五.text标签,text支持的属性有:
        1.width		:决定元素宽度，用于对齐
        2.height	:决定元素高度，用于对齐
        3.x			:决定元素左上角x轴位置
        4.y			:决定元素左上角y轴位置
        5.font-size	:决定文本字体高度,单位为px
        6.font-name	:决定文本的字体,出厂预烧写的字体有:SimSun.TTF,SimHei.TTF,JXLT.TTF
        7.align		:决定文本的对齐方式，有效值有:left,center,right
    六.line标签,line支持的属性有:
        1.x			:决定线段起点x坐标
        2.y			:决定线段起点y坐标
        3.endx		:决定线段终点x坐标
        4.endy		:决定线段终点y坐标
    七.barcode标签,barcode支持的属性有:
        1.x			:决定元素左上角x轴位置
        2.y			:决定元素左上角y轴位置
        3.width		:决定元素宽度，用于对齐
        4.height	:决定元素高度，用于对齐
        5.scale		:决定条码宽度的放大系数，有效值为1-10的整数
        6.readable-align
                    :决定可读文字在条码中的位置，有效值为:none,left,center,right
        7.align		:决定条码的水平对齐方式，有效值有:left,center,right

    八.qrcode标签,qrcode支持的属性有:
        1.x			:决定元素左上角x轴位置
        2.y			:决定元素左上角y轴位置
        3.width		:决定元素宽度，用于对齐
        4.height	:决定元素高度，用于对齐
        5.align		:决定二维码的水平对齐方式，有效值有:left,center,right
        6.scale		:决定二维码的放大系数，有效值为1-10的整数
    九.image标签,image支持的属性有:
        1.x			:决定元素左上角x轴位置
        2.y			:决定元素左上角y轴位置
        3.width		:决定元素宽度，用于放大
        4.height	:决定元素高度，用于放大
        5.src		:决定图片来源，支持的属性有filename:xxxx.BMP(打印预存在打印机中xxxx.BMP的文件)


模板示例
^^^^^^^^^^^^^^^^^^^

`示例模板 <https://api.sonma.net/template/2002>`_

::

    <?xml version="1.0" encoding="UTF-8"?>
    <page width="100" height="180"  shift-x="1" shift-y="-2" offset="1.2" gap="2">
        <barcode x="41" y="112" align="right" width="58" height="11" scale="2"><%=barcodeOne%></barcode>
        <qrcode x="82" y="127" width="16" height="16" align="center" scale="4"><%=qrcodeOne%></qrcode>
        <qrcode x="82" y="92" width="16" height="16" align="center" scale="4"><%=qrcodeTwo%></qrcode>
        <barcode x="0" y="69" align="center" width="100" height="18" scale="2"><%=barcodeTwo%></barcode>
        <barcode x="73" y="27" align="center" width="26" height="5" scale="1" readable-align="none"><%=barcodeOne%></barcode>
        <line x="0.0" y="10.0" endx="100.0" endy="10.0"/>
        <line x="0.0" y="25.0" endx="100.0" endy="25.0"/>
        <line x="0.0" y="35.0" endx="100.0" endy="35.0"/>
        <line x="0.0" y="55.0" endx="80.0" endy="55.0"/>
        <line x="80.0" y="40.0" endx="100.0" endy="40.0"/>
        <line x="0.0" y="65.0" endx="100.0" endy="65.0"/>
        <line x="0.0" y="90.0" endx="100.0" endy="90.0"/>
        <line x="0.0" y="90.0" endx="100.0" endy="90.0"/>
        <line x="0.0" y="110.0" endx="100.0" endy="110.0"/>
        <line x="0.0" y="125.0" endx="100.0" endy="125.0"/>
        <line x="0.0" y="145.0" endx="80.4" endy="145.0"/>
        <line x="0.0" y="155.0" endx="100.2" endy="155.0"/>
        <line x="73.3" y="25.0" endx="73.3" endy="35.0"/>
        <line x="5.0" y="35.0" endx="5.0" endy="65.0"/>
        <line x="80.0" y="35.0" endx="80.0" endy="65.0"/>
        <line x="45.0" y="90.0" endx="45.0" endy="110.0"/>
        <line x="80.0" y="90.0" endx="80.0" endy="110.0"/>
        <line x="80.4" y="125.0" endx="80.4" endy="155.0"/>
        <line x="5.0" y="125.1" endx="5.0" endy="155.1"/>
        <text x="1.15" y="41.8" font-size="14">收</text>
        <text x="1.15" y="44.8" font-size="14">件</text>
        <text x="1.15" y="57.6" font-size="14">寄</text>
        <text x="1.15" y="60.6" font-size="14">件</text>

        <text x="6.5" y="38.4" font-size="14" font-name="JXLT.TTF"><%=shiptoContactName%></text>
        <text x="6.5" y="43.5" font-size="18"  font-name="JXLT.TTF" width="64" height="50"><%=shiptoAddress%></text>
        <text x="6.5" y="56.5" font-size="14"><%=shipFromContactName%></text>
        <text x="6.5" y="60.6" font-size="14"><%=shipFromAddress%></text>

        <text x="0" y="27.5" align="center" font-size="35" width="73" height="40" font-name="JXLT.TTF"><%=bigheadpenName%></text>
        <text x="0" y="13" font-size="72" width="100" height="80" align="center"><%=bigheadpenCode%></text>


        <text x="88.2" y="36.2" font-size="15">服务</text>
        <text x="81.5" y="42.3" font-size="14">付款方式：</text>

        <text x="48.0" y="94.1" font-size="18">签收人：</text>
        <text x="48.0" y="101.0" font-size="18">时间：</text>
        <text x="1.6" y="93.5" font-size="14">快递描述收件人地址，收件人或者寄件人</text>
        <text x="1.6" y="97.0" font-size="14">允许牵手，视为描述：您的签字代表您已</text>
        <text x="1.6" y="100.5" font-size="14">经验收此包裹，并确认商品完好无损，没</text>
        <text x="1.6" y="104.0" font-size="14">有划痕，没有破损等质量问题。</text>


        <text x="89.8" y="176.1" font-size="14">已验视</text>
        <text x="1.25" y="132.2" font-size="14">收</text>
        <text x="1.25" y="135.2" font-size="14">件</text>
        <text x="1.25" y="148.0" font-size="15">寄</text>
        <text x="1.25" y="151.0" font-size="16">件</text>
        <text x="6.5" y="128.8" font-size="14" font-name="JXLT.TTF"><%=shiptoContactName%></text>
        <text x="6.5" y="133.9" font-size="18" font-name="JXLT.TTF" width="64" height="50"><%=shiptoAddress%></text>

        <text x="6.5" y="151.0" font-size="14" ><%=shipFromAddress%></text>
        <text x="6.5" y="146.9" font-size="14"><%=shipFromContactName%></text>



        <text x="92.3" y="42.3" font-size="14"><%=paymentMethod%></text>
        <text x="3.4" y="159.5" font-size="32"><%=shipFromRemark%></text>
        <image x="2" y="0" width="30" height="10" src="filename:800best_s.BMP"/>
        <image x="2" y="111" width="30" height="15" src="filename:800best.BMP"/>
    </page>






80mm打印机模板说明
-------------------

支持EJS语法动态模板


.. _paiban:

标签说明
^^^^^^^^^^^^

============== ========================== ===================
功能            标签格式                     说明
============== ========================== ===================
加粗            ``<B>..</B>``
居中            ``<C>..</C>``
加宽            ``<W>..</W>``
加高            ``<L>..</L>``
居中加粗         ``<CB>..</CB>``
加粗大字         ``<DB>..</DB>``
居中加粗大字      ``<CDB>..</CDB>``
换行            ``..<BR>`` or ``\n``       不支持``\r``换行
============== ========================== ===================



.. _template:

模板示例
^^^^^^^^^^^^^^

::

    <CB>胜马旗舰店</CB>
    <C>江虹国际创意园6E1201</C>
    单号:1002325            时间:2016-07-13 13:24
    客户:0013               员工:1605
    ------------------------------------------------
    货号        名称              数量  单价    小计
    ------------------------------------------------
    XY80        80打印机            2   500    1000
    ------------------------------------------------
    数量:                            2
    总计:                                        1000
    ------------------------------------------------
    <B>微信:500</B>
    <B>未付:500</B>
    ------------------------------------------------
    农行卡：6228 4800 8207 8306 717
    工行卡：6222 0236 0202 3368 921
    户名：杭州胜马科技有限公司
    温馨提示：如发现质量问题，凭此开单票据，本市的三天内，外地七日内调换，若人为损坏，开不退换！
    ------------------------------------------------
    单据打印时间:                   2016-07-13 13:34
    ------------------------------------------------
    技术支持(全国):0571-85353593           胜马科技

