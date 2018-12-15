package cn.fyd.monitorlogin.controller;

import cn.fyd.monitorlogin.common.Response;
import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author fanyidong
 * @date Created in 2018-12-14
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    @Transactional(rollbackFor = Exception.class)
    public Response sendEmail(String email, HttpServletRequest request) {
        try {
            mailService.sendEmail(email, request);
            return Response.success();
        } catch (MonitorException | ParseException e) {
            return Response.failed(e.getMessage());
        }
    }
}
