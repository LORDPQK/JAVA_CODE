package controller;

import entity.Address;
import entity.Cart;
import entity.Oders;
import entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AddressService;
import service.CartService;
import service.OrdersService;
import service.impl.AddressServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.OrdersServiceImpl;
import util.Constants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrdersController extends BaseServlet{
    /**
     * 订单预览
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String preview(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {

        //收参
        String uid = request.getParameter("uid");
        //调用业务逻辑
        //1 获取购物车数据
        CartService cartService = new CartServiceImpl();
        List<Cart> cartList = cartService.findAll(Integer.parseInt(uid));
        //2 获取地址数据
        AddressService addressService = new AddressServiceImpl();
        List<Address> addresslisyt = addressService.findAddressByUid(Integer.parseInt(uid));
        //保存到共享域
        request.setAttribute("addressList",addresslisyt);
        request.setAttribute("cartList",cartList);
        //转发
        return Constants.FORWARD+"/order.jsp";
    }

    /**
     * 生成订单
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String create(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //获取参数
        String aid = request.getParameter("aid");
        String uid = request.getParameter("uid");
        String sum = request.getParameter("sum");

        //调用业务逻辑
        OrdersService ordersService = new OrdersServiceImpl();
        ordersService.createOrder(aid,uid,sum);


        return Constants.FORWARD+"/order?method=show";
    }

    /**
     * 订单展示
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String show(HttpServletRequest request ,HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            session.setAttribute("msg","登录后可查看订单");
            return Constants.FORWARD+"/login.jsp";
        }

        OrdersService ordersService = new OrdersServiceImpl();
        List<Oders> orderList = ordersService.findOrdersByUid(loginUser.getUid());

        request.setAttribute("orderList",orderList);

        return Constants.FORWARD+"/orderList.jsp";
    }

    public String detail(HttpServletRequest request ,HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {

       String oid = request.getParameter("oid");

        OrdersService ordersService = new OrdersServiceImpl();
        Oders oders = ordersService.findOrderByOid(oid);

        request.setAttribute("order",oders);

        return Constants.FORWARD+"/orderDetail.jsp";
    }
}
