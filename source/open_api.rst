开放接口
------------------------------------------------------------------------------

.. _api:

::

    release:    https://api.sonma.net
    beta:       http://api-beta.sonma.net

.. warning::

    数据中包含敏感信息请务必使用https。

.. _token:

获取Token
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

调用API时需要使用token或者请求头进行鉴权,token存在泄漏风险,外部调用务必使用https

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:get:: /v1/auth/access_token


请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
scope        是               权限范围: * 表示账户下所有打印机 ,[xxx,xxxx,...]表示指定打印机
exp          否               过期时间,10位Unix时间戳,最长时间为1年,不传该参数生成的token,30分钟内无请求则被吊销
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "code": 0,
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzNDAwNjkiLCJzY29wZSI6WyIqIl0sImlzcyI6ImFwaS5zb25tYS5uZXQiLCJleHAiOjE0OTc1MTkzNDJ9.PwlIwY9IzqYEM4NTnKofLz9TbmEfHmxbjmrOnOA9ciA"
    }

.. code-block:: json

    {
      "code":40030,
      "message":"过期时间不能早于Thu Jun 15 07:37:45 UTC 2017"
    }


.. _revoke_token:

吊销token
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
当token泄漏时,吊销该token

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:delete:: /auth/access_token/{token}

.. important::
    必须使用请求头鉴权

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }

烧写图片
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
向打印机中写入图片文件

.. note::
    当图片需要频繁使用时需要缓存到打印机中,使用别名打印

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:post:: /v1/print/image

请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================
参数名称       是否必须          说明
============ =============== ====================================================================
token        否               鉴权方式自选
sn           是               打印机串号
file         是               图片文件
name         否               图片名称(不带后缀名),如果未传该参数则取 file part 中filename作为图片名
width        否               指定图片宽度(单位像素,最大800)
height       否               指定图片高度(单位像素,最大800)
threshold    否               图片黑白处理阀值(0~255),数值越大图片越黑
scale        否               缩放比例,默认为1
============ =============== ====================================================================

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


.. code-block:: json

    {
        "code": 0,
        "message": "logo.BMP"
    }


图片测试模板

.. code-block:: xml

    <?xml version="1.0" encoding="UTF-8"?>
    <page width="100" height="100" >
        <image x="0" y="0" width="100" height="100" src="logo.BMP"/>
    </page>

.. note::

    烧写后打印机中图片别名为 <图片名>.BMP



获取打印机状态(在线)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:get:: /printer/{sn}/status


请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================
参数名称       是否必须          说明
============ =============== ====================================================================
token        否               鉴权方式自选
============ =============== ====================================================================

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


.. code-block:: json

    {
        "code": 0,
        "online": true
    }

获取打印机状态(详细)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:get:: /printer/{sn}


请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================
参数名称       是否必须          说明
============ =============== ====================================================================
token        否               鉴权方式自选
============ =============== ====================================================================

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


.. code-block:: json

    {
        "sn": 1002123456,
        "name": "测试打印机",
        "type": "TSCDA200",
        "online": false,
        "status": 0,
        "queue": 0
    }

打印机状态码(status)说明
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

=========== ================================================
status         说明
=========== ================================================
00          Normal
01          Head opened
02          Paper Jam
03          Paper Jam and head opened
04          Out of paper
05          Out of paper and head opened
08          Out of ribbon
09          Out of ribbon and head opened
0A          Out of ribbon and paper jam
0B          Out of ribbon, paper jam and head opened
0C          Out of ribbon and out of paper
0D          Out of ribbon, out of paper and head opened
10          Pause
20          Printing
80          Other error
=========== ================================================



.. _print:

打印
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:post:: /v1/print

请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================================================
参数名称       是否必须          说明
============ =============== ====================================================================================================
content      是               可以是模板数据混合的形式(xml)或者json数据(需要传template)
token        否               鉴权方式自选
sn           否               打印机唯一编号
template     否               可以是模板编号、URL(仅支持菜鸟模板链接)或者模板内容(xml)
============ =============== ====================================================================================================

.. note::

    调试模板的时候可以通过template上传模板内容,无需频繁修改模板使用id


返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "code": 0,
      "message": "success"
    }

.. code-block:: json

    {
        "code": 202,
        "message": "打印机离线,已加入打印队列"
    }

.. _queue_clear:

清空打印队列
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:delete:: /printer/{sn}/queue

请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================================================
参数名称       是否必须          说明
============ =============== ====================================================================================================
sn           否               打印机唯一编号
============ =============== ====================================================================================================

返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "code": 0,
      "message": "success"
    }

.. include:: return_code.rst



面单打印
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
未正式发布,请使用api-beta,进行调试

接口地址
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. http:post:: /v1/waybill/print

请求参数
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

============ =============== ====================================================================================================
参数名称       是否必须          说明
============ =============== ====================================================================================================
content      是               面单打印所需的JSON数据
token        否               鉴权方式自选
sn           是               打印机唯一编号
template     否               可以不指定模板打印,默认使用菜鸟标准快递模板进行打印
============ =============== ====================================================================================================

content 参数说明
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

+------------------------+------------+--------------------+----------+
| 参数                   | 类型       | 说明               | 是否必须 |
+========================+============+====================+==========+
| uuid                   |  String    | 唯一编号           | 是       |
+------------------------+------------+-----+--------------+----------+
| type                   |  String    |中通 |ZTO           | 是       |
|                        |            +-----+--------------+          |
|                        |            |韵达 |YUNDA         |          |
+------+-----------------+------------+-----+--------------+----------+
| ext  |companyCode      |  String    | 集团客户的编号     |中通必传  |
|      +-----------------+------------+--------------------+----------+
|      |startTime        |  Timestamp |取件起始时间        |          |
|      |                 |            |(13位Unix时间戳)    | 否       |
+      +-----------------+------------+--------------------+----------+
|      |endTime          |  Timestamp |取件截止时间        |          |
|      |                 |            |(13位Unix时间戳)    | 否       |
+------+-----------------+------------+--------------------+----------+
| auth |partnerID        |  String    | 合作商ID           |是        |
+      +-----------------+------------+--------------------+----------+
|      |password         |  String    | 客户密码           |韵达必传  |
+------+-----------------+------------+--------------------+----------+
| remark                 |  String    | 备注信息,会被打印  | 是       |
+-----------+------------+------------+--------------------+----------+
|           | name       |  String    | 收件人姓名         | 是       |
|           +------------+------------+--------------------+----------+
| receiver  | company    |  String    | 收件人公司名       | 否       |
|           +------------+------------+--------------------+----------+
|           | mobile     |  String    | 收件人手机号码     |          |
|           +------------+------------+--------------------+ 至少一个 |
|           | phone      |  String    | 收件人固定电话     |          |
|           +------------+------------+--------------------+----------+
|           | province   |  String    | 收件人省           | 是       |
|           +------------+------------+--------------------+----------+
|           | city       |  String    | 收件人市           | 是       |
|           +------------+------------+--------------------+----------+
|           | district   |  String    | 收件人区           | 是       |
|           +------------+------------+--------------------+----------+
|           | detail     |  String    | 收件人详细地址     | 是       |
+-----------+------------+------------+--------------------+----------+
|           | name       |  String    | 发件人姓名         | 是       |
|           +------------+------------+--------------------+----------+
| sender    | company    |  String    | 发件人公司名       | 否       |
|           +------------+------------+--------------------+----------+
|           | mobile     |  String    | 发件人手机号码     |          |
|           +------------+------------+--------------------+ 至少一个 |
|           | phone      |  String    | 发件人固定电话     |          |
|           +------------+------------+--------------------+----------+
|           | province   |  String    | 发件人省           | 是       |
|           +------------+------------+--------------------+----------+
|           | city       |  String    | 发件人市           | 是       |
|           +------------+------------+--------------------+----------+
|           | district   |  String    | 发件人区           | 是       |
|           +------------+------------+--------------------+----------+
|           | detail     |  String    | 发件人详细地址     | 是       |
+-----------+------------+------------+--------------------+----------+


返回示例
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "content": {
          "recipient": {
            "address": {
              "city": "北京市",
              "detail": "花家地社区卫生服务站三层楼我也不知道是哪儿了",
              "district": "朝阳区",
              "province": "北京",
              "town": "望京街道"
            },
            "mobile": "1326443654",
            "name": "张三",
            "phone": "057123222"
          },
          "routingInfo": {
            "consolidation": {
              "name": "杭州",
              "code": "hangzhou"
            },
            "origin": {
              "code": "POSTB"
            },
            "sortation": {
              "name": "杭州"
            },
            "routeCode": "380D-56-04"
          },
          "sender": {
            "address": {
              "city": "北京市",
              "detail": "花家地社区卫生服务站二层楼我也不知道是哪儿了",
              "district": "朝阳区",
              "province": "北京",
              "town": "望京街道"
            },
            "mobile": "1326443654",
            "name": "秦疏",
            "phone": "057123222"
          },
          "waybillCode": "9890000160004"
        },
      "template": "http://api.sonma.net/template/101"
    }


.. note::

    如需重打,请使用 :ref:`打印接口 <print>`。
    测试需要使用 :ref:`BETA服务器 <api>`。