package cn.fyd.monitorlogin.service.impl;

import cn.fyd.monitorlogin.dao.MailDao;
import cn.fyd.monitorlogin.dao.UserDao;
import cn.fyd.monitorlogin.common.MonitorException;
import cn.fyd.monitorlogin.model.Mail;
import cn.fyd.monitorlogin.model.User;
import cn.fyd.monitorlogin.service.MailService;
import cn.fyd.monitorlogin.util.CheckUtils;
import cn.fyd.monitorlogin.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.UUID;

import static cn.fyd.monitorlogin.common.Constant.*;

/**
 * 邮件相关服务层实现类
 * @author fanyidong
 * @date Created in 2018-12-14
 */
@Service
public class MailServiceImpl implements MailService {

    private static Logger logger = LogManager.getLogger(MailServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailDao mailDao;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${fanyidong.context}")
    private String context;
    @Value("${fanyidong.server}")
    private String server;
    @Value("${server.port}")
    private String port;

    @Override
    public void sendEmail(String email) throws MonitorException, ParseException {
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
            // 未过期
            if (!CheckUtils.isEmailOutTime(lastMail.getOutTime())) {
                throw new MonitorException(LINK_NOT_EXPIRED);
            }
        }
        // 生成安全码
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
        // 设置邮件内容
        newMessage.setText(MAIL_MESS1 + secretKey + MAIL_MESS2);
        mailSender.send(newMessage);
        logger.info("日志信息 => 邮件已发送");
    }
}
