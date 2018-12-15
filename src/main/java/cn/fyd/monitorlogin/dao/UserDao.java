package cn.fyd.monitorlogin.dao;

import cn.fyd.monitorlogin.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 操作User表Dao
 * @author fanyidong
 * @date 2018-12-11
 */
@Repository
public interface UserDao {

    /**
     * 插入数据
     * @param user
     * @return 返回插入数
     */
    int addUser(User user);

    /**
     * 查询用户数量
     * @param user
     * @return 返回查询结果数量
     */
    int countUser(User user);

    /**
     * 根据条件查询用户
     * @param user
     * @return 用户list
     */
    User queryBySelective(User user);

    /**
     * 根据条件编辑用户
     * @param user
     * @return 返回编辑成功数量
     */
    int editBySelective(User user);

    /**
     * 根据主键修改
     * @param user
     * @return
     */
    int editByUserId(User user);

    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    User queryByEmail(@Param("email") String email);
}
