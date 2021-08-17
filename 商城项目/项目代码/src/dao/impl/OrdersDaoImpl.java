package dao.impl;

import dao.OrdersDao;
import entity.Address;
import entity.Item;
import entity.Oders;
import entity.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import util.C3P0Utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public void insertOrder(Oders oders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql ="insert into orders (o_id,a_id,u_id,o_count,o_state,o_time) value(?,?,?,?,?,?)";
        queryRunner.update(sql,oders.getOid(),oders.getAid(),oders.getUid(),oders.getOcount(),oders.getOstate(),oders.getOtime());
    }

    @Override
    public void insertItem(List<Item> items) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //要一次添加多个订单项，使用一个object二维数组进行批量操作
        Object[][] objects = new Object[items.size()][];

        String sql = "insert into item(o_id,p_id,i_count,i_num) value(?,?,?,?)";

        //通过for循环将要填充到sql语句中的值存入数组object中
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            //二维数组只有一个框 对该行赋值 初始化时只指定了行数，动态生成列数
            objects[i] = new Object[]{item.getOid(),item.getPid(),item.getIcount(),item.getInum()};
        }
        queryRunner.batch(sql,objects);
    }

    @Override
    public List<Oders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql ="select o.o_id as oid ,o.u_id as uid, o.a_id as aid, o.o_count as ocount,o.o_time as otime ,o.o_state as ostate," +
                "a.a_name as aname,a.a_phone as aphone,a.a_detail as adetail,a.a_state as astate" +
                " from address a join orders o on a.a_id = o.a_id where o.u_id =?";

        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), uid);

        if (query == null) {
            return null;
        }

        List<Oders> ordersList = new ArrayList<>();

        for (Map<String,Object> map : query) {
            Address address = new Address();
            Oders oders1 = new Oders();

            BeanUtils.populate(oders1,map);
            BeanUtils.populate(address,map);

            oders1.setAddress(address);
            ordersList.add(oders1);



        }

        return ordersList;
    }

    @Override
    public Oders selectOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql ="select o.o_id as oid ,o.u_id as uid, o.a_id as aid, o.o_count as ocount,o.o_time as otime ,o.o_state as ostate," +
                "a.a_name as aname,a.a_phone as aphone,a.a_detail as adetail,a.a_state as astate" +
                " from address a join orders o on a.a_id = o.a_id where o.o_id =?";

        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), oid);

        if (query == null) {
            return null;
        }

            Address address = new Address();
            Oders oders1 = new Oders();

            BeanUtils.populate(oders1,query);
            BeanUtils.populate(address,query);

            oders1.setAddress(address);
            oders1.setAddress(address);

        return oders1;
    }

    @Override
    public List<Item> selectItemByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select p.p_id as pid ,p.t_id as tid, p.p_name as pname,p.p_time as ptime,p.p_image as pimage,p.p_state as pstate,p.p_price as pprice,i.o_id as oid ,i.i_id as iid ,i.i_count as icount ,i.i_num as inum" +
                " from product p join item i on p.p_id = i.p_id where i.o_id=?;";

        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);

        if (list == null) {
            return null;
        }

        List<Item> items = new ArrayList<>();

        for (Map<String,Object> map:list) {
            Product product = new Product();
            Item item = new Item();

            BeanUtils.populate(item,map);
            BeanUtils.populate(product,map);

            item.setProduct(product);

            items.add(item);

        }

        return items;
    }
}
