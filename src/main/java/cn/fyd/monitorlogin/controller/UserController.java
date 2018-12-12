package cn.fyd.monitorlogin.controller;

import cn.fyd.monitorlogin.common.Response;
import cn.fyd.monitorlogin.model.LoginDto;
import cn.fyd.monitorlogin.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User控制层
 * @author fanyidong
 * @date Created in 2018-12-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param params {"account":"root","password":"1"}
     * @param request
     * @return Response
     */
    @PostMapping("/login")
    public Response login(String params, HttpServletRequest request) {
        try {
            LoginDto loginDto = JSON.parseObject(params, LoginDto.class);
            userService.login(loginDto, request.getSession());
            return Response.success();
        } catch (Exception e) {
            return Response.failed(e.getMessage());
        }
    }
}
