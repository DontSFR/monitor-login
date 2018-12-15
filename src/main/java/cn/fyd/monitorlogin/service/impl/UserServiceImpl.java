package cn.fyd.monitorlogin.service.impl;

import cn.fyd.monitorlogin.dao.UserDao;
import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.model.LoginDto;
import cn.fyd.monitorlogin.model.User;
import cn.fyd.monitorlogin.service.UserService;
import cn.fyd.monitorlogin.util.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.UUID;

import static cn.fyd.monitorlogin.common.Constant.*;

/**
 * User表相关的服务接口实现类
 * @author fanyidong
 * @date 2018-12-11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
        // 查询条件对象
        User selectiveUser = new User();
        selectiveUser.setAccount(loginDto.getAccount());
        //查询结果对象
        User resUser = userDao.queryBySelective(selectiveUser);
        // 验证用户是否存在
        if (resUser == null) {
            throw new MonitorException(USER_NOT_EXIST);
        }
        // 验证密码
        if (!resUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            throw new MonitorException(WRONG_PASSWORD);
        }
        // 存session
        session.setAttribute("userBean", resUser);
    }

    @Override
    public void applyUser(User newUser) throws MonitorException {
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
        } else {
            // 修改信息
            if (editUser(newUser, existNum) == 0) {
                throw new MonitorException(EDIT_USER_MESSAGE_FAILED);
            }
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
        return resUser;
    }

    public int addUser(User newUser, Integer existNum) throws MonitorException {
        if (existNum > 0) {
            throw new MonitorException(USER_EXIST);
        }
        // 设置主键uuid
        newUser.setUserId(UUID.randomUUID().toString());
        // 密码使用md5加密
        newUser.setPassword(DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes()));
        // 新增用户
        return userDao.addUser(newUser);
    }

    private int editUser(User newUser, Integer existNum) throws MonitorException {
        if (existNum == 0) {
            throw new MonitorException(USER_NOT_EXIST);
        }
        // 修改信息
        return userDao.editByUserId(newUser);
    }
}
