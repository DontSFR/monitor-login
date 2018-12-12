package cn.fyd.monitorlogin.service;

import cn.fyd.monitorlogin.exception.MonitorException;
import cn.fyd.monitorlogin.model.LoginDto;
import cn.fyd.monitorlogin.model.User;

import javax.servlet.http.HttpSession;

/**
 * User表相关的服务接口类
 * @author fanyidong
 * @date 2018-12-11
 */
public interface UserService {

    /**
     * 登录服务接口
     * @param session
     * @param loginDto
     * @return
     * @throws MonitorException
     */
    int login(HttpSession session, LoginDto loginDto) throws MonitorException;

    /**
     * 修改/新增 用户
     * @param user
     * @throws MonitorException
     */
    void applyUser(User user) throws MonitorException;

    /**
     * 获取用户信息
     * @param id 用户id
     * @return
     * @throws MonitorException
     */
    User getUserInfo(String id) throws MonitorException;
}
