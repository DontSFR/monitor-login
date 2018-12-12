# monitor——登录服务
登录服务包含的功能有：登录、注册、修改个人信息、通过邮件找回密码、获取个人信息

## 1. 登录
* url地址：http://${api-url}:8002/user/login
* 请求方式：post
* 参数：{"params":{"account": "root","password": "1", "captcha": "6s8q"}}
* 返回格式：{"result":"success","message":null,"data":null}

## 2. 获取验证码
* url地址：http://${api-url}:8002/RandomCode
* 请求方式：get
* 参数：无
* 返回格式：JPEG格式图片(60*22)

## 3. 注册
* url地址：http://${api-url}:8002/apply
* 请求方式：post
* 参数：{"params":{"account":"dd","password":"1","email":"123@qq.com","mobile":"10086"}}
* 返回格式：{"result":"success","message":null,"data":null}

## 4. 修改个人信息
* url地址：http://${api-url}:8002/apply
* 请求方式：post
* 参数：{"params":{"id":"bf6eb812-4b38-4965-a121-bf297d58447c","account":"dd","password":"1","email":"123@qq.com","mobile":"10086"}}
* 返回格式：{"result":"success","message":null,"data":null}

## 4. 获取个人信息
* url地址：http://${api-url}:8002/userInfo
* 请求方式：post
* 参数：{"id":"bf6eb812-4b38-4965-a121-bf297d58447c"}
* 返回格式：{"result":"success","message":null,"data":{"id":"bf6eb812-4b38-4965-a121-bf297d58447c","account":"dd","password":"1","email":"123@qq.com","mobile":"10086"}}