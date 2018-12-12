#monitor——登录服务

##1. 登录
* url地址：http://39.108.52.40:8002/user/login
* 请求方式：post
* 参数：{"params":{"account": "root","password": "1", "captcha": "6s8q"}}
* 返回格式：{"result":"success","message":null,"data":null}

##2. 获取验证码
* url地址：http://39.108.52.40:8002/RandomCode
* 请求方式：get
* 参数：无
* 返回格式：JPEG格式图片(60*22)