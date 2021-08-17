package service.impl;

import dao.CartDao;
import dao.ProductDao;
import dao.impl.CartDaoImpl;
import dao.impl.ProductDaoImpl;
import entity.Cart;
import entity.Product;
import service.CartService;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        // 1 判断商品是否已经存在
        CartDao cartDao = new CartDaoImpl();
        Cart cart = cartDao.hasCart(uid,pid);

        if(cart!=null){
            //商品已经添加到购物车过
            cart.setCnum(cart.getCnum()+1);
            cartDao.upadteCart(cart);
        }else{
            //商品不存在购物车中添加即可
            ProductDao productDao = new ProductDaoImpl();
            Product product = productDao.selectProductByPid(pid);

            cart = new Cart();
            cart.setCnum(1);
            cart.setPid(Integer.parseInt(pid));
            cart.setProduct(product);
            cart.setUid(uid);
            cartDao.insertCart(cart);
        }
    }

    @Override
    public List<Cart> findAll(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        CartDao cartDao = new CartDaoImpl();
        List<Cart> cartList = cartDao.selectCartByUid(uid);
        return cartList;
    }

    @Override
    public void delectCartById(String cid) throws SQLException {
        CartDao cartDao = new CartDaoImpl();
        cartDao.delectCartById(cid);
    }

    @Override
    public void updateCartById(String cid, String cnum, String price) throws SQLException {
        BigDecimal cnumbig = new BigDecimal(cnum);
        BigDecimal pricebig = new BigDecimal(price);

        BigDecimal count = pricebig.multiply(cnumbig);

        CartDao cartDao = new CartDaoImpl();
        cartDao.updateByCid(count,cnumbig,cid);
    }

    @Override
    public void clearCart(String uid) throws SQLException {
        CartDao cartDao = new CartDaoImpl();
        cartDao.clearCardByUid(uid);
    }
}
