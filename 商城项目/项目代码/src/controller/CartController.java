package controller;

import entity.Cart;
import entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CartService;
import service.impl.CartServiceImpl;
import util.Constants;
import util.RandomUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;


@WebServlet(value = "/cart")
public class CartController extends BaseServlet{

    /**
     * 生成购物车中的一条数据
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String create(HttpServletRequest request , HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //判断是否已经登录
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("msg","添加购物车必须先登录");
            return Constants.FORWARD+"login.jsp";
        }
        //获得商品的id和用户的id
        int uid = loginUser.getUid();
        String pid = request.getParameter("pid");

        CartService cartService = new CartServiceImpl();
        cartService.createCart(uid,pid);

        return Constants.FORWARD+"cartSuccess.jsp";
    }

    /**
     * 购物车数据的展示模块
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String show(HttpServletRequest request,HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {

        // 1 判断是否已经登录
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser==null){
            session.setAttribute("msg","添加购物车必须先登录");
            return Constants.FORWARD+"login.jsp";
        }

        // 2 获取参数
        int uid = loginUser.getUid();

        // 3 调用业务逻辑进行数据查询
        CartService cartService = new CartServiceImpl();
        List<Cart> list = cartService.findAll(uid);//查询到的一条条的购物车数据存放在carts

        request.setAttribute("list",list);

        return Constants.FORWARD+"/cart.jsp";
    }


    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //获取cid
        String cid = request.getParameter("cid");

        //调用业务逻辑删除
        CartService service = new CartServiceImpl();
        service.delectCartById(cid);
        return Constants.FORWARD+"/cart?method=show";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //获取cid
        String cid = request.getParameter("cid");
        String cnum = request.getParameter("cnum");
        String price =request.getParameter("price");
        //调用业务逻辑
        CartService service = new CartServiceImpl();
        service.updateCartById(cid,cnum,price);
        return Constants.FORWARD+"/cart?method=show";
    }

    public String clear(HttpServletRequest request , HttpServletResponse response) throws SQLException {
        //获取id
        String uid = request.getParameter("uid");
        //调用业务逻辑
        CartService cartService = new CartServiceImpl();
        cartService.clearCart(uid);
        //转发
        return Constants.FORWARD+"/cart?method=show";
    }
}
