package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entity.PageBean;
import entity.Product;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public PageBean<Product> findpage(String tid, int page, int pageSize) throws SQLException {

        ProductDao productDao = new ProductDaoImpl();
        long count = productDao.selectCountById(tid);       //获取总条数
        List<Product> list =  productDao.selectProductByPage(page,pageSize,tid);  //查询当前页对应的商品
        return new PageBean<Product>(list,page,pageSize,count);
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.selectProductByPid(pid);
        return product;
    }
}
