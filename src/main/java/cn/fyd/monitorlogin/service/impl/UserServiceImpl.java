package cn.fyd.monitorlogin.service.impl;

import cn.fyd.annotation.IsLogin;
import cn.fyd.common.MonitorException;
import cn.fyd.model.LoginDto;
import cn.fyd.model.Mail;
import cn.fyd.model.ResetDto;
import cn.fyd.model.User;
import cn.fyd.monitorlogin.dao.MailDao;
import cn.fyd.monitorlogin.dao.UserDao;
import cn.fyd.monitorlogin.service.UserService;
import cn.fyd.util.CheckUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import static cn.fyd.common.Constant.*;

/**
 * User表相关的服务接口实现类
 * @author fanyidong
 * @date 2018-12-11
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailDao mailDao;

    @Override
    public void login(LoginDto loginDto, HttpSession session) throws MonitorException {
        // 从session中获取验证码
        Object imgCodeObject = session.getAttribute("ImgCode");
        if (imgCodeObject == null) {
            throw new MonitorException(NO_CAPTCHA);
        }
        // 转化为小写字母
        String imgCode = imgCodeObject.toString().toLowerCase();
        // 验证验证码
        if (!imgCode.equals(loginDto.getCaptcha().toLowerCase())) {
            throw new MonitorException(WRONG_CAPTCHA);
        }
        //查询结果对象
        User resUser = userDao.queryByAccountOrEmail(loginDto.getAccount());
        // 验证用户是否存在
        if (resUser == null) {
            throw new MonitorException(USER_NOT_EXIST);
        }
        // 验证密码
        if (!resUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            throw new MonitorException(WRONG_PASSWORD);
        }
        // 存session
        session.setAttribute(USER_BEAN, resUser);
        logger.info("日志信息 => 登录成功 ***** " + resUser.toString());
    }

    @Override
    public void applyUser(User newUser, User userBean) throws MonitorException {
        String email = newUser.getEmail();
        if (!StringUtils.isEmpty(email)&&!CheckUtils.checkEmail(email)) {
            throw new MonitorException(WRONG_MAIL);
        }
        if (!StringUtils.isEmpty(email)&&userDao.queryByEmail(email)!=null) {
            throw new MonitorException(MAIL_EXIST);
        }
        User existUserSelective = new User();
        existUserSelective.setAccount(newUser.getAccount());
        // 根据account查询该用户是否存在
        int existNum = userDao.countUser(existUserSelective);
        if (StringUtils.isEmpty(newUser.getUserId())) {
            // 注册
            if(addUser(newUser,existNum) == 0) {
                throw new MonitorException(REGIST_FAILED);
            }
            logger.info("日志信息 => 注册成功");
        } else {
            // 验证是否修改本人信息
            if (!newUser.getUserId().equals(userBean.getUserId())) {
                throw new MonitorException(WRONG_USER);
            }
            // 修改信息
            if (editUser(newUser, existNum) == 0) {
                throw new MonitorException(EDIT_USER_MESSAGE_FAILED);
            }
            logger.info("日志信息 => 修改个人信息成功");
        }
    }

    @Override
    public User getUserInfo(String userId) throws MonitorException {
        User selective = new User();
        selective.setUserId(userId);
        // 验证查询结果是否为空
        User resUser = userDao.queryBySelective(selective);
        if (resUser == null) {
            throw new MonitorException(USER_NOT_EXIST);
        }
        logger.info("日志信息 => 用户个人信息 ***** " + resUser.toString());
        return resUser;
    }

    @Override
    public void reset(ResetDto dto) throws MonitorException, ParseException {
        User user = userDao.queryByEmail(dto.getEmail());
        if (user == null) {
            throw new MonitorException(CAN_NOT_FIND_USER);
        }
        Mail mail = mailDao.queryByUserIdOrderByOutTime(user.getUserId());
        if (mail == null) {
            throw new MonitorException(CAN_NOT_FIND_MAIL);
        }
        if (CheckUtils.isEmailOutTime(mail.getOutTime())) {
            throw new MonitorException(LINK_EXPIRED);
        }
        if (!dto.getSecretKey().equals(mail.getValidationCode())) {
            throw new MonitorException(WRONG_LINK);
        }
        User editUserParam = new User();
        editUserParam.setUserId(user.getUserId());
        editUserParam.setEmail(dto.getEmail());
        editUserParam.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        if (editUser(editUserParam, 1) == 0) {
            throw new MonitorException(EDIT_USER_MESSAGE_FAILED);
        }
        logger.info("日志信息 => 重置密码成功");
    }

    public int addUser(User newUser, Integer existNum) throws MonitorException {
        if (existNum > 0) {
            throw new MonitorException(USER_EXIST);
        }
        // 设置主键uuid
        newUser.setUserId(UUID.randomUUID().toString());
        // 密码使用md5加密
        newUser.setPassword(DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes()));
        // 创建时间
        newUser.setCreateTime(new Date());
        // 新增用户
        return userDao.addUser(newUser);
    }

    @IsLogin
    private int editUser(User newUser, Integer existNum) throws MonitorException {
        if (existNum == 0) {
            throw new MonitorException(USER_NOT_EXIST);
        }
        // 修改信息
        return userDao.editByUserId(newUser);
    }
}
