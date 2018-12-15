package cn.fyd.monitorlogin.controller;

import cn.fyd.monitorlogin.annotation.IsLogin;
import cn.fyd.monitorlogin.common.Response;
import cn.fyd.monitorlogin.common.ValidFileds;
import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.model.LoginDto;
import cn.fyd.monitorlogin.model.ResetDto;
import cn.fyd.monitorlogin.model.User;
import cn.fyd.monitorlogin.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;

import static cn.fyd.monitorlogin.common.Constant.WRONG_PARAMS;

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
            // 验证参数是否为空
            ValidFileds.verificationoColumn(loginDto);
            userService.login(loginDto, request.getSession());
            return Response.success();
        } catch (MonitorException e) {
            return Response.failed(e.getMessage());
        }
    }

    @IsLogin
    @PostMapping("/userInfo")
    public Response info(String userId) {
        // 验证参数是否为空
        if (StringUtils.isEmpty(userId)) {
            return Response.failed(WRONG_PARAMS);
        }
        try {
            return Response.success(userService.getUserInfo(userId));
        } catch (MonitorException e) {
            return Response.failed(e.getMessage());
        }
    }

    @IsLogin
    @PostMapping("/apply")
    @Transactional(rollbackFor = Exception.class)
    public Response apply(String params) {
        try {
            User newUser = JSON.parseObject(params, User.class);
            userService.applyUser(newUser);
            return Response.success();
        } catch (MonitorException e) {
            return Response.failed(e.getMessage());
        }
    }

    @PostMapping("/reset")
    @Transactional(rollbackFor = Exception.class)
    public Response reset(String params) {
        try {
            ResetDto dto = JSON.parseObject(params, ResetDto.class);
            // 验证参数是否为空
            ValidFileds.verificationoColumn(dto);
            userService.reset(dto);
            return Response.success();
        } catch (MonitorException | ParseException e) {
            return Response.failed(e.getMessage());
        }
    }
}
