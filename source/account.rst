平台账户
----------------

.. _create_user:

注册用户
^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /user

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
username              是               新建用户名
password              是               登录密码
mobile                是               手机号码
email                 是               邮箱
captcha               是               验证码
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 40000,
        "message": "Required String parameter 'username' is not present"
    }

.. code-block:: json

    {
        "code": 0,
        "message": "success",
        "user": {
            "username": "zhoufeilong",
            "mobile": "18368404991",
            "email": "18368404991@163.com",
            "org": 0
        },
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzNDAwNzAiLCJzY29wZSI6W10sImlzcyI6ImFwaS5zb25tYS5uZXQifQ.Df7QHG85CGZQqYwGVzD4HdNlL_j39-fD74iUVV8sJJs"
    }

.. _check_user:

预校验
^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:get:: /user/check/{resource:^username|email|mobile$}

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
resource           是               校验资源：用户名，邮箱或手机号码。
value              是               校验值
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success",
        "match": true,
        "exist": false
    }

.. _create_captcha_image:

随机生成验证码图片
^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:get:: /captcha/image

.. _verify:

填写收到的注册验证码
^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /auth/image/verify

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
captcha            是               验证码
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }

.. _login:

用户登录
^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /session

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
username            是               用户名
password            是               密码
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success",
        "user": {
            "username": "zhoufeilong",
            "mobile": "18368404991",
            "email": "18368404991@163.com",
            "roles": [],
            "permissions": [],
            "org": 0
        },
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIzNDAwNzAiLCJzY29wZSI6W10sImlzcyI6ImFwaS5zb25tYS5uZXQifQ.Df7QHG85CGZQqYwGVzD4HdNlL_j39-fD74iUVV8sJJs"
    }

.. _query_user:

查询用户信息
^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:get:: /user/{username}

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
username              是               需查询的用户名
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 40300,
        "message": "无权访问"
    }

.. code-block:: json

    {
        "username": "zhoufeilong",
        "mobile": "18368404991",
        "email": "18368404991@163.com",
        "roles": [],
        "permissions": [],
        "org": 0
    }

.. _reset_password:

密码重置
^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:patch:: /user

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
mobile                是               手机号码
password              是               重置的密码
captch                是               验证码
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }

.. _regist_reset_password:

注册账号或找回密码
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /captcha/sms/{action}

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
mobile              是               手机号码
action              是               注册账号或者找回密码的标识(register或forgot)
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }


.. _verify_captcha:

校验验证码与手机号码
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /captcha/sms/verify/{action}

请求参数
~~~~~~~~~~~~~~~~~

============ =============== ========================================================================
参数名称       是否必须          说明
============ =============== ========================================================================
action             是               注册账号或者找回密码的标识(register或forgot)
mobile             是               手机号码
captcha            是               验证码
============ =============== ========================================================================

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 40002,
        "message": "参数过期"
    }

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }

.. _logout:

退出登录
^^^^^^^^^^^

接口地址
~~~~~~~~~~~~~~~~~

.. http:post:: /session

请求参数
~~~~~~~~~~~~~~~~~

无

返回示例
~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "code": 0,
        "message": "success"
    }

