package service;

import entity.Oders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrdersService {
    void createOrder(String aid, String uid, String sum) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Oders> findOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    Oders findOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
}
