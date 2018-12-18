# monitor——登录服务
登录服务包含的功能有：登录、注册、修改个人信息、通过邮件找回密码、获取个人信息

## 1. 获取验证码
* url地址：/RandomCode
* 请求方式：get
* 参数：无
* 返回格式：JPEG格式图片(60*22)

## 2. 登录
* url地址：/user/login
* 请求方式：post
* 参数：
```$xslt
{
	"params": {
		"account": "root",
		"password": "1",
		"captcha": "6s8q"
	}
}
```  
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": null
}
```

## 3. 注册
* url地址：/apply
* 请求方式：post
* 参数：
```$xslt
{
	"params": {
		"account": "dd",
		"password": "1",
		"email": "123@qq.com",
		"mobile": "10086"
	}
}
```
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": null
}
```

## 4. 获取个人信息
* url地址：/userInfo
* 请求方式：post
* 参数：
```$xslt
{
	"userId": "bf6eb812-4b38-4965-a121-bf297d58447c"
}
```
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": {
		"userId": "bf6eb812-4b38-4965-a121-bf297d58447c",
		"account": "dd",
		"password": "1",
		"email": "123@qq.com",
		"mobile": "10086"
	}
}
```

## 5. 修改个人信息
* url地址：/apply
* 请求方式：post
* 参数：
```$xslt
{
	"params": {
		"userId": "bf6eb812-4b38-4965-a121-bf297d58447c",
		"account": "dd",
		"password": "1",
		"email": "123@qq.com",
		"mobile": "10086"
	}
}
```
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": null
}
```

## 6. 发送邮件
* url地址：/sendEmail
* 请求方式：post
* 参数：
```$xslt
{
	"email": "123@email.com"
}
```
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": null
}
```

## 7. 修改密码
* url地址：/reset
* 请求方式：post
* 参数：
```$xslt
{
	"params": {
		"secretKey": "60baf240-559a-4775-9b12-23894b377e35",
		"email": "fanyidong@biosan.cn",
		"password": "1"
	}
}
```
* 返回格式：
```$xslt
{
	"result": "success",
	"message": null,
	"data": null
}
```