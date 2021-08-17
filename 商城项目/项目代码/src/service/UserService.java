package service;

import entity.User;

import java.sql.SQLException;

/**
 * 用户模块对应的业务逻辑接口
 */
public interface UserService {
    /**
     *检测用户名是否存在
     * @param username
     * @return
     */
    boolean checkedUser(String username) throws Exception;

    /**
     * 注册的业务逻辑
     * @param user
     * @return
     */
    int registerUser(User user) throws SQLException;

    /**
     * 激活方法
     * @param code
     * @return
     */
    int activeUser(String code) throws SQLException;

    User login(String username,String password) throws Exception;
}
