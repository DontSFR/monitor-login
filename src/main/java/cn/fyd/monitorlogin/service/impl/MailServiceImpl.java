package cn.fyd.monitorlogin.service.impl;

import cn.fyd.monitorlogin.dao.MailDao;
import cn.fyd.monitorlogin.dao.UserDao;
import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.model.Mail;
import cn.fyd.monitorlogin.model.User;
import cn.fyd.monitorlogin.service.MailService;
import cn.fyd.monitorlogin.util.CheckUtils;
import cn.fyd.monitorlogin.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static cn.fyd.monitorlogin.common.Constant.*;

/**
 * 邮件相关服务层实现类
 * @author fanyidong
 * @date Created in 2018-12-14
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailDao mailDao;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String email, HttpServletRequest request) throws MonitorException, ParseException {
        // 验证邮箱格式
        if (!StringUtils.isEmpty(email)&&!CheckUtils.checkEmail(email)) {
            throw new MonitorException(WRONG_MAIL);
        }
        // 验证邮箱是否存在
        User resUser = userDao.queryByEmail(email);
        if (resUser == null) {
            throw new MonitorException(MAIL_NOT_REGIST);
        }
        // 验证上一封邮件是否存在或过期
        Mail lastMail = mailDao.queryByUserIdOrderByOutTime(resUser.getUserId());
        if (lastMail != null) {
            // 获取过期日期
            String lastMailOutTime = lastMail.getOutTime();
            Date lastMailOutTimeDate = DateUtils.StringToDate(lastMailOutTime, DATE_FOEMAT_TO_SECOND);
            // 若过期时间在当前时间之后，不发送
            if (lastMailOutTimeDate.after(new Date())) {
                throw new MonitorException(LINK_NOT_EXPIRED);
            }
        }
        // 生成密钥
        String secretKey = UUID.randomUUID().toString();
        // 新建Mail对象
        Mail mail = new Mail();
        mail.setUserId(resUser.getUserId());
        mail.setValidationCode(secretKey);
        Calendar now = Calendar.getInstance();
        // 设置创建时间
        mail.setCreateTime(DateUtils.DateToString(now.getTime(), DATE_FOEMAT_TO_SECOND));
        // 设置邮件到期时间为半个小时
        now.add(Calendar.MINUTE, 30);
        mail.setOutTime(DateUtils.DateToString(now.getTime(), DATE_FOEMAT_TO_SECOND));
        // 插入数据库
        mailDao.saveMail(mail);

        // 发送邮件，新建邮件对象
        SimpleMailMessage newMessage = new SimpleMailMessage();
        // 设置发件人
        newMessage.setFrom(MONITOR + " <" + sender + ">");
        // 设置收件人
        newMessage.setTo(email);
        // 设置邮件主题
        newMessage.setSubject(MAIL_SUBJECT);
        // 获取协议名
        String path = request.getContextPath();
        // 获取主机名及端口号
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        // 设置修改密码地址(需修改为前端地址，并利用sid做校验)
        String resetPasswordHref = basePath + "reset/byEmail?validationCode=" + secretKey + "&account=" + resUser.getAccount();
        // 设置邮件内容
        newMessage.setText(MAIL_MESS1 + resetPasswordHref + MAIL_MESS2);
        mailSender.send(newMessage);
    }
}
