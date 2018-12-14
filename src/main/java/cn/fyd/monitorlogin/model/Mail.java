package cn.fyd.monitorlogin.model;

/**
 * 发送邮件找回密码 邮件信息存储表 实体类
 * @author fanyidong
 * @date Created in 2018-12-13
 */
public class Mail {

    /**
     * mail表id
     */
    private String mailId;

    /**
     * user表id
     */
    private String userId;

    /**
     * md5加密后的值
     */
    private String validationCode;

    /**
     * 邮件过期时间
     */
    private String outTime;

    /**
     * 创建时间
     */
    private String createTime;

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
