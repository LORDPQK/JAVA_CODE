package dao.impl;

import dao.UserDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.C3P0Utils;
import util.Constants;
import util.SslUtils;

import javax.management.Query;
import java.sql.SQLException;

/**
 * 数据库访问实现类
 */
public class UserDaoImpl implements UserDao {

    /**
     *
     * @param username 查询的条件
     * @return
     */
    @Override
    public User selectUserByUname(String username) throws Exception {
        //创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //执行mysql语句
        String sql = "select u_id as uid,u_name as username,u_password as upassword" +
                ",u_sex as usex,u_status as ustatus,u_code as ucode,u_email as email" +
                ",u_role as urole from user where u_name = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),username);
        return user;
    }

    @Override
    public int insertUser(User user) throws SQLException {
        //创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //执行mysql语句
        String sql = "insert into user (u_name,u_password,u_email,u_sex,u_status,u_code,u_role)value(?,?,?,?,?,?,?)";

        int rows = queryRunner.update(sql,user.getUsername(),user.getUpassword(),user.getEmail(),user.getUsex(),user.getUstatus(),user.getCode(),user.getUrole());

        return rows;
    }

    @Override
    public User selectUserByCode(String code) throws SQLException {
        //创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //执行mysql语句
        String sql = "select u_id as uid,u_name as username,u_password as upassword" +
                ",u_sex as usex,u_status as ustatus,u_code as ucode,u_email as email" +
                ",u_role as urole from user where u_code = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),code);
        return user;

    }

    @Override
    public int updateStatusByUid(int uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update user set u_status = ? where u_id =?";
        int row = queryRunner.update(sql, Constants.USER_ACTIVE, uid);
        return row;
    }


}
