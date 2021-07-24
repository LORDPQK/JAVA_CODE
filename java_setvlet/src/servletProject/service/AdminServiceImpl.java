package servletProject.service;

import servletProject.dao.AdminDao;
import servletProject.dao.impl.AdminDaoImpl;
import servletProject.entity.Admin;
import servletProject.utils.DbUtils;

import java.util.List;

public class AdminServiceImpl implements AdminService{
    @Override
    public Admin login(String username, String password) {
        Admin result=null;
        try {
            DbUtils.begin();
            AdminDao adminDao =new AdminDaoImpl();
            Admin admin = adminDao.select(username);
            if(username!=null){
                    if(admin.getPassword().equals(password)){
                    result =admin;
                }
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Admin> showAllAdmin() {
        List<Admin> admins =null;
        try {
            DbUtils.begin();
            AdminDao adminDao = new AdminDaoImpl();
            admins = adminDao.selectAll();
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }

        return admins;
    }
}
