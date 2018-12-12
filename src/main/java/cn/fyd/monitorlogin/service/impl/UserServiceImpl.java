package cn.fyd.monitorlogin.service.impl;

import cn.fyd.monitorlogin.dao.UserDao;
import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.model.LoginDto;
import cn.fyd.monitorlogin.model.User;
import cn.fyd.monitorlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int login(HttpSession session, LoginDto loginDto) throws MonitorException {
        // 验证参数是否为空

        // 验证验证码

        // 验证用户是否存在

        // 验证密码

        // 存session
        return 0;
    }

    @Override
    public void applyUser(User newUser) throws MonitorException {
        // 验证参数是否为空

        // 验证邮箱格式
//        if (newUser.getEmail()!=null&&!CheckUtil.checkEmail(newUser.getEmail())) {
//            throw new MonitorException(WRONG_MAIL);
//        }
//        if (newUser.getEmail()!=null&&userMapper.queryUserDetailByEmail(newUser.getEmail())!=null) {
//            throw new MonitorException(MAIL_EXIST);
//        }
//        int userNum = userMapper.countUser(newUser);
//        //新增用户信息
//        if (newUser.getUser_id()==null||"".equals(newUser.getUser_id())) {
//            if (userNum>0) {
//                throw new MonitorException(USER_EXIST);
//            }
//            newUser.setUser_id(UUID.randomUUID().toString());
//            userMapper.addUser(newUser);
//        } else {//修改用户信息
//            if (userNum==0) {
//                throw new MonitorException(USER_NOT_EXIST);
//            }
//            //如邮箱为空是修改密码接口
//            if (newUser.getEmail()==null){
//                if (userMapper.editUserById(newUser) == 0) {
//                    throw new MonitorException(EDIT_PASSWORD_FAILED);
//                }
//            } else {
//                if (userMapper.editUserById(newUser) == 0) {
//                    throw new MonitorException(EDIT_USER_MESSAGE_FAILED);
//                }
//            }
//        }
    }

    @Override
    public User getUserInfo(String id) throws MonitorException {
        // 验证参数

        // 验证查询结果是否为空
        return null;
    }
}
