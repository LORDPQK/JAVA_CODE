package service;

import entity.PageBean;
import entity.Product;

import java.sql.SQLException;

public interface ProductService {
    PageBean<Product> findpage(String tid,int page,int pageSize) throws SQLException;

    Product findProductByPid(String pid) throws SQLException;
}
