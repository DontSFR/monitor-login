package cn.fyd.monitorlogin.controller;

import cn.fyd.annotation.IsLogin;
import cn.fyd.common.Response;
import cn.fyd.common.ValidFileds;
import cn.fyd.model.LoginDto;
import cn.fyd.model.ResetDto;
import cn.fyd.model.User;
import cn.fyd.monitorlogin.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static cn.fyd.common.Constant.*;

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
     * @param params {"params":{"account":"root","password":"1"}}
     * @param request
     * @return java.lang.String
     */
    @PostMapping("/login")
    public String login(String params, HttpServletRequest request) throws Exception {
        // 方便测试，正式上线时应删除
        request.getSession().setAttribute("ImgCode", "1234");
        LoginDto loginDto = JSON.parseObject(params, LoginDto.class);
        // 验证参数是否为空
        ValidFileds.verificationoColumn(loginDto);
        userService.login(loginDto, request.getSession());
        return Response.success();
    }

    /**
     * 查询用户个人信息
     * @param userId 用户id {"userId":"1"}
     * @return java.lang.String
     * @throws Exception
     */
    @IsLogin
    @PostMapping("/userInfo")
    public String info(@RequestParam("userId") String userId, HttpServletRequest request) throws Exception {
        // 验证参数是否为空
        if (StringUtils.isEmpty(userId)) {
            return Response.failed(WRONG_PARAMS);
        }
        User userBean = (User) request.getSession().getAttribute(USER_BEAN);
        if (!userId.equals(userBean.getUserId())) {
            return Response.failed(WRONG_USER);
        }
        return Response.success(userService.getUserInfo(userId));

    }


    @PostMapping("/apply")
    @Transactional(rollbackFor = Exception.class)
    public String apply(String params, HttpServletRequest request) throws Exception{
        User newUser = JSON.parseObject(params, User.class);
        User userBean = (User) request.getSession().getAttribute(USER_BEAN);
        userService.applyUser(newUser, userBean);
        return Response.success();
    }

    @PostMapping("/reset")
    @Transactional(rollbackFor = Exception.class)
    public String reset(String params) throws Exception {
        ResetDto dto = JSON.parseObject(params, ResetDto.class);
        // 验证参数是否为空
        ValidFileds.verificationoColumn(dto);
        userService.reset(dto);
        return Response.success();
    }
}
