package cn.fyd.monitorlogin.common;

/**
 * 常量接口类
 * @author fanyidong
 * @date 2018-12-11
 */
public interface Constant {

    String USER_BEAN = "userBean";

    String SUCCESS = "success";

    String FAILED = "failed";

    String MONITOR = "Monitor服务监控平台";

    String QUARTZ_ERROR = "定时任务出错，请联系管理员";

    String MONITOR_EXIST = "相同监控已存在，不要重复提交啦";

    String MONITOR_NOT_EXIST = "该监控不存在";

    String WRONG_MONITOR_URL = "监控地址格式错误";

    String MONITOR_ADD_FAILED = "新建监控项目失败";

    String MONITOR_SAVE_FAILED = "保存监控项目失败";

    String NO_EDIT_RIGHT = "没有编辑权限";

    String TWO_PASSWORD_NOT_SAME = "两次密码输入不一致";

    String SEND_MAIL_SUCCESS = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";

    String MAIL_NOT_REGIST = "该邮箱尚未注册";

    String MAIL_SUBJECT = "Monitor服务监控平台 - 找回密码";

    String MAIL_MESS1 = "请勿回复本邮件.点击下面的链接,重设密码\n";

    String MAIL_MESS2 = "\n注意:本链接超过30分钟将会失效，需要重新申请找回密码";

    String LINK_PARAMS_EMPTY = "链接参数为空，请重新申请";

    String CAN_NOT_FIND_USER = "无法找到匹配用户,请重新申请";

    String LINK_EXPIRED = "链接已过期,请重新申请";

    String WRONG_LINK = "链接不正确,请重新申请";

    String USER_NOT_EXIST = "该用户不存在";

    String WRONG_PASSWORD = "密码错误";

    String WRONG_CAPTCHA = "验证码错误";

    String NO_CAPTCHA = "验证码不存在";

    String WRONG_MAIL = "邮箱格式错误";

    String MAIL_EXIST = "该邮箱已被注册";

    String USER_EXIST = "用户已存在";

    String EDIT_PASSWORD_FAILED = "修改密码失败";

    String EDIT_USER_MESSAGE_FAILED = "修改用户信息失败";

    String REGIST_FAILED = "注册失败";

    String WRONG_PARAMS = "参数错误";

    String WRONG_DATA = "数据错误，请联系管理员";

    String USER_NOT_LOGIN = "用户未登录";

    String CONNECT_TIME_OUT = "连接超时";

    String CLOSE_RESOURCE_FAILED = "关闭资源出错";

    String SYSTEM_ERROR = "系统错误，请联系系统管理员";

    String VERIFY_OBJECT_CAN_NOT_BE_EMPTY = "校验对象不能为空";

    Integer CAPTCHA_LENGTH = 4;

    Integer CAPTCHA_BACKGROUND = 120;
}
