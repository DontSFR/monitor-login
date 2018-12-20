package cn.fyd.monitorlogin.controller;

import cn.fyd.monitorlogin.common.Response;
import cn.fyd.monitorlogin.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanyidong
 * @date Created in 2018-12-14
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件
     * @param email
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("/sendEmail")
    @Transactional(rollbackFor = Exception.class)
    public String sendEmail(String email) throws Exception{
        mailService.sendEmail(email);
        return Response.success();
    }
}
