package dao.impl;

import dao.CartDao;
import entity.Cart;
import entity.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import util.C3P0Utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    @Override
    public Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql="select p.p_name as pname,p.p_id as pid,p.t_id as tid," +
                "p.p_time as ptime,p.p_image as pimage,p.p_state as state," +
                "p.p_info as pinfo ,p.p_price as pprice, c.c_id as cid,c.u_id as uid ,c.c_count as ccount," +
                "c.c_num as cnum from product as p join cart as c on p.p_id=c.p_id where c.u_id = ? and c.p_id = ?";

        //不能用BeanHandler因为无法对pname等属于product的属性赋值，
        //Cart --->Product --->pname无法进行越级赋值
        Map<String, Object> map = queryRunner.query(sql, new MapHandler(), uid, pid);

        if(map == null)
        {
            return null;
        }

        //这里的cart指的是购物车的一条购物车数据，该条购物车数据要连接到商品表
        Cart cart = new Cart();
        Product product = new Product();
        //BeanUtils.populate(a,bmap)将bmap集合中与a中名字相同的熟悉赋值
        BeanUtils.populate(cart,map);
        BeanUtils.populate(product,map);

        cart.setProduct(product);

        return cart;
    }

    @Override
    public void upadteCart(Cart cart) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update cart set c_num=?,c_count=? where c_id=?";
        queryRunner.update(sql,cart.getCnum(),cart.getCcount(),cart.getCid());

    }

    @Override
    public void insertCart(Cart cart) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql ="insert into cart (u_id,p_id,c_num,c_count) value(?,?,?,?)";

        queryRunner.update(sql,cart.getUid(),cart.getPid(),cart.getCnum(),cart.getCcount());

    }

    @Override
    public List<Cart> selectCartByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        //查询购物车中的一条数据cart需关联到商品表，cart--->product 多表查询 链接查询
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql="select p.p_name as pname,p.p_id as pid,p.t_id as tid," +
                "p.p_time as ptime,p.p_image as pimage,p.p_state as state," +
                "p.p_info as pinfo ,p.p_price as pprice, c.c_id as cid,c.u_id as uid ,c.c_count as ccount," +
                "c.c_num as cnum from product as p join cart as c on p.p_id=c.p_id where c.u_id = ?";

        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), uid);

        if(query==null){
            return null;
        }

        List<Cart> carts = new ArrayList<>();
        for (Map<String,Object> map : query) {
            Cart cart = new Cart();
            Product product = new Product();

            BeanUtils.populate(cart,map);
            BeanUtils.populate(product,map);

            cart.setProduct(product);
            carts.add(cart);
        }
        

        return carts;
    }

    @Override
    public void delectCartById(String uid) throws SQLException {
            QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
            String sql ="delete from cart where u_id =?;";
            queryRunner.update(sql,uid);
    }

    @Override
    public void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql ="update cart set c_count =? ,c_num=? where c_id=?";
        queryRunner.update(sql,count,cnumbig,cid);
    }

    @Override
    public void clearCardByUid(String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql="delete from cart where u_id=?;";
        queryRunner.update(sql,uid);
    }
}
