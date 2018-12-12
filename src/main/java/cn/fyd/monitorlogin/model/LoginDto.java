package cn.fyd.monitorlogin.model;

import cn.fyd.monitorlogin.annotation.IsEmpty;

/**
 * 登录dto
 * @author fanyidong
 * @date Created in 2018-12-11
 */
public class LoginDto {

    /**
     * 账号
     */
    @IsEmpty(name = "账号")
    private String account;

    /**
     * 密码
     */
    @IsEmpty(name = "密码")
    private String password;

    /**
     * 验证码
     */
    @IsEmpty(name = "验证码")
    private String captcha;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
