package controller;

import entity.PageBean;
import entity.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import service.impl.ProductServiceImpl;
import util.Constants;

import java.sql.SQLException;

@WebServlet(value = "/product")
public class ProductController extends BaseServlet{
    /**
     * 商品的展示方法
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.接收请求参数tid [类别参数id] 从前端获取当前页数和页容量
        String tid = request.getParameter("tid");
        int pageSize = 6;//页容量
        String currentPage = request.getParameter("currentPage");
        int page = 1;//当前页数
        if(currentPage!=null){
            page = Integer.parseInt(currentPage);
        }
        //2.调用业务逻辑得到前端需要的PageBean对象
        ProductService productService = new ProductServiceImpl();
        PageBean<Product> pageBean = productService.findpage(tid,page,pageSize);
        //3.响应
        request.setAttribute("pageBean",pageBean);

        return Constants.FORWARD+"goodsList.jsp";

    }

    public String detail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //请求参数
        String pid = request.getParameter("pid");

        //调用业务逻辑
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);

        //响应
        request.setAttribute("product",product);

        return Constants.FORWARD+"goodsDetail.jsp";
    }

}
