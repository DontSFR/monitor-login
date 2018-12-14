package cn.fyd.monitorlogin.service;

import cn.fyd.monitorlogin.exception.MonitorException;

import javax.servlet.http.HttpServletRequest;

/**
 * 邮件相关的服务层
 * @author fanyidong
 * @date Created in 2018-12-14
 */
public interface MailService {

    /**
     * 发送邮件方法
     * @param email 邮箱地址
     * @param request
     * @throws MonitorException
     */
    void sendEmail(String email, HttpServletRequest request) throws MonitorException;
}
