package servletProject.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import servletProject.dao.AdminDao;
import servletProject.entity.Admin;
import servletProject.utils.DbUtils;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner queryRunner= new QueryRunner();

    @Override
    public int insert(Admin admin) {
        return 0;
    }

    @Override
    public int delete(String uesrname) {
        return 0;
    }

    @Override
    public int update(Admin admin) {
        return 0;
    }

    @Override
    public Admin select(String username) {
        try {
            Admin admin = queryRunner.query(DbUtils.getConncetion(), "select * from admin where username =?;", new BeanHandler<Admin>(Admin.class),username);
            return admin;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> selectAll() {
        try {
            List<Admin> admin= queryRunner.query(DbUtils.getConncetion(),"select * from admin ",new BeanListHandler<Admin>(Admin.class));
            return admin;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
