package dao;

import entity.Item;
import entity.Oders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDao {
    void insertOrder(Oders oders) throws SQLException;

    void insertItem(List<Item> items) throws SQLException;

    List<Oders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    Oders selectOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Item> selectItemByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
}
