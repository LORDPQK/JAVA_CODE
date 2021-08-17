package service;

import entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartService {
    void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Cart> findAll(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void delectCartById(String cid) throws SQLException;

    void updateCartById(String cid, String cnum, String price) throws SQLException;

    void clearCart(String uid) throws SQLException;
}
