package service.impl;

import dao.CartDao;
import dao.OrdersDao;

import dao.impl.CartDaoImpl;
import dao.impl.OrdersDaoImpl;
import entity.Cart;
import entity.Item;
import entity.Oders;
import service.CartService;
import service.OrdersService;
import util.RandomUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    @Override
    public void createOrder(String aid, String uid, String sum) throws SQLException, InvocationTargetException, IllegalAccessException {

        //创建一个订单项  订单的主键是一个varchar类型 在生成是手动赋值 好处：每次的主键创建时已明确，在创建items时可以关联到主键
        Oders oders = new Oders();
        String orderId = RandomUtils.createOrderId();
        BigDecimal bigDecimal = new BigDecimal(sum);
        oders.setOid(orderId);
        oders.setAid(Integer.parseInt(aid));
        oders.setUid(Integer.parseInt(uid));
        oders.setOcount(bigDecimal);
        oders.setOtime(new Date());
        oders.setOstate(1);

        //保存订单
        OrdersDao ordersDao = new OrdersDaoImpl();
        ordersDao.insertOrder(oders);

        //将购物车转成订单项
        CartDao cartDao = new CartDaoImpl();
        List<Cart> carts = cartDao.selectCartByUid(Integer.parseInt(uid));

        List<Item> items = new ArrayList<>();
        for (Cart c:carts) {
            Item item = new Item();
            item.setOid(orderId);
            item.setPid(c.getPid());
            item.setInum(c.getCnum());
            item.setIcount(c.getCcount());
            items.add(item);
        }

        //保存订单对应的订单项
        ordersDao.insertItem(items);
        //清空购物车
        cartDao.delectCartById(uid);
    }

    @Override
    public List<Oders> findOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Oders> list =  ordersDao.selectOrdersByUid(uid);

        return list;
    }

    @Override
    public Oders findOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {

        //1 oid查询订单和订单地址信息
        OrdersDao ordersDao = new OrdersDaoImpl();
        Oders oders = ordersDao.selectOrderByOid(oid);

        //2 oid对应的订单项和商品信息
        List<Item> items = ordersDao.selectItemByOid(oid);

        //3 订单项集合设置给订单对象
        oders.setItems(items);
        return oders;
    }
}
