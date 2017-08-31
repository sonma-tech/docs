打印模板
================================================================================

模板是指依据(菜鸟打印标记语言规范)设计出来的模板。为了更加快速的进行模板设计与生成，可采用菜鸟官方提供的模板编辑器（菜鸟模板编辑器）。

模板内容
--------------------------------------------------------------------------------
模板内容分为两种：静态内容和动态内容。

* 静态内容：在模板绘制的过程中，值就确定的内容我们称之为静态内容。在编辑器中进行模板设计的时候就可以将内容输入进去。
* 动态内容：在绘制模板的过程中，我们经常希望只放入一个占位符，其内容将在实际打印时才能确定，我们称之为动态内容。为了支持这种动态内容，
  在模板中可以通过编写ECMAScript(参考链接)脚本来达到生成动态内容的目的。同时对 `ECMAScript <http://www.ecmascript.org/>`_ 的解析采用了
  目前业界最先进的V8引擎，具有极高的性能，在打印服务生成打印文件前，会对ECMAScript的代码进行解析并替换为实际值。

动态内容的编写
--------------------------------------------------------------------------------

动态内容可以嵌入类似js的代码，在模板中可以通过如下的方式嵌入动态代码:

* 动态语句: 以"<%"开始，以"%>"结束，如<% if(true) %>。
* 变量引用: 以"<%="开始，以"%>"结束，如"<%=data.address%>"，表示将会用data.address的实际值在模板中进行填充。允许使用自定义的变量。可参考：(链接)。
* 如果内容中要输出"<%"或者"<%="，需要添加"\"做转义，也即"<\%"。
* 内置变量：_data为内置变量,表示打印数据的内容,即打印接口中的content数据


动态代码示例如下，这个例子是将获得的打印数据（data）中的所有对象依次输出。

.. code-block:: xml

    <layout left="6" top="5" width="20" height="20" style="borderStyle:solid;">
        <text width="" value="动态数据显示" />
            <% for(i=0;i++;i<_data.arrayObject.lenth) {%>
                <text width="" value="<%=_data.arrayObject[i].value%>" style="fontSize:12"/>
            <%}%>
    </layout>


数据：


.. code-block:: json

    [
        ["收件人"],
        ["发件人"],
        [ "收货地址"]
    ]

动态代码解析之后的静态代码为：

.. code-block:: xml

    <layout left="6" top="5" width="20" height="20" style="borderStyle:solid;">
        <text width="" value="动态数据显示" />
        <text width="" value="收件人" style="fontSize:12"/>
        <text width="" value="发件人" style="fontSize:12"/>
        <text width="" value="收货地址" style="fontSize:12"/>
    </layout>

.. note::

    _开头的变量名都保留给系统使用，请不要在模板中定义_开头的变量名字。


标记语言规范
--------------------------------------------------------------------------------

 打印机支持的单位有:px, mm,如无特殊说明,默认单位都为mm,(200dpi的打印机1mm = 8 px),坐标系以左上角为起点（0,0), page 为根节点.

标签说明
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

page 根节点
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
======== ============= ===================================
属性      类型           说明
======== ============= ===================================
width    dimension     打印区域宽度
height   dimension     打印区域高度
gap      dimension     纸张间隙(标签纸、面单纸需要设置)
offset   dimension     打印完后走纸距离
tear     boolean       撕纸模式
shift-x  dimension     x轴偏移量
shift-y  dimension     y轴偏移量
======== ============= ===================================



layout 布局节点
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
======== ============= ===================================
属性      类型           说明
======== ============= ===================================
width    dimension     宽度
height   dimension     高度
left     dimension     与上一节点x轴相对位置
top      dimension     与上一节点y轴相对位置
======== ============= ===================================


line 直线
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
======== ============= ===================================
属性      类型           说明
======== ============= ===================================
startX   dimension
startY   dimension
endX     dimension
endY     dimension
======== ============= ===================================


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

支持模板数据分离

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
换行            ``..<BR>``
============== ========================== ===================



.. _template:

动态模板(数据分离)
^^^^^^^^^^^^^^^^^^^^^

模板
::

    <CB><%=shopName%></CB><BR>
    <C><%=shopAddress%></C><BR>
    单号:<%=:orderNumber | left_align:18%> 时间:<%=date%><BR>
    客户:<%=:customerName | left_align:18 %> 员工:<%=staffName%><BR>
    ------------------------------------------------<BR>
    <%=:'货号' | left_align:9 %><%=:'名称' | left_align:9 %><%=:'数量' | left_align:10 %><%=:'单价' | left_align:10 %><%=:'小计' | right_align:10 %><BR>
    ------------------------------------------------<BR>
    <% for(var item in order)
        {%><%=:order[item].ref | left_align:9 %><%=:order[item].name | left_align:9 %><%=:order[item].num | left_align:10 %><%=:order[item].price | left_align:10 %><%=:order[item].total | right_align:10 %><BR><%}
    %>
    ------------------------------------------------<BR>
    数量:                                       2<BR>
    总计:                                       1000<BR>
    ------------------------------------------------<BR>
    <B>微信:500</B><BR><B>未付:500</B><BR>
    ------------------------------------------------<BR>
    农行卡：6228 4800 8207 8306 717<BR>
    工行卡：6222 0236 0202 3368 921<BR>
    户名：杭州胜马科技有限公司<BR>
    温馨提示：如发现质量问题，凭此开单票据，本市的三天内，外地七日内调换，若人为损坏，开不退换！<BR>
    ------------------------------------------------<BR>
    单据打印时间:                   2016-07-13 13:34<BR>
    ------------------------------------------------<BR>
    技术支持(全国):0571-85353593           胜马科技<BR><BR>
    <C><QRCODE>http://weixin.qq.com/r/Xo7WzpnEacQWrd2t99tM</QRCODE></C><BR><BR>
    <CUT>

数据

::

    {
      "shopName": "ShopName",
      "shopAddress": "ShopAddress",
      "orderNumber": "OrderNumber",
      "date": "2017/06/13 15:00:00",
      "customerName": "gao",
      "staffName": "min",
      "order": [
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        },
        {
          "name": "1",
          "ref": "N1001",
          "num": 8,
          "price": "8.88",
          "total": 99
        }
      ]
    }


模板数据混合
^^^^^^^^^^^^

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

