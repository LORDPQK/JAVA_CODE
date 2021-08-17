package service.impl;

import dao.TypeDao;
import dao.impl.TypeDaoImpl;
import entity.Type;
import service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {

    @Override
    public List<Type> findAll() throws SQLException {
        TypeDao typeDao = new TypeDaoImpl();

        List<Type> list = typeDao.selectAll();
        return list;
    }
}
