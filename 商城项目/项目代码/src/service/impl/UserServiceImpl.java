package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import util.C3P0Utils;
import util.Constants;
import util.EmailUtils;
import util.MD5Utils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean checkedUser(String username) throws Exception {
        // 1 创建dao对象
        UserDao userDao = new UserDaoImpl();
        // 2 执行结果
        User user = userDao.selectUserByUname(username);
        // 3 处理返回值
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public int registerUser(User user) throws SQLException {
        // 1 用户保存到数据库
        UserDao userDao = new UserDaoImpl();
        int row = userDao.insertUser(user);
        // 2 发送一封邮件
        EmailUtils.sendEmail(user);
        return row;
    }

    @Override
    public int activeUser(String code) throws SQLException {
        // 1  根据激活码寻找用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByCode(code);
        // 2  判断用户是否激活
        if(user==null){
            return Constants.ACTIVE_FAIL; //激活失败
        }
        if(user.getUstatus().equals(Constants.USER_ACTIVE)){
            return Constants.ACTIVE_ALREADY; //已激活
        }
        // 3 进行激活操作
        int i = userDao.updateStatusByUid(user.getUid());

        if(i>0){
            return Constants.ACTIVE_SUCCESS; //激活成功
        }

        return 0;
    }

    @Override
    public User login(String username, String password) throws Exception {
        //用md5处理密码
        String md5password = MD5Utils.md5(password);

        //根据用户名查找密码
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByUname(username);
        if(user!=null && user.getUpassword().equals(md5password)){
            return user;
        }
        return null;
    }

}
