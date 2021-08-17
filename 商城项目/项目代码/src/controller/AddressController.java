package controller;

import entity.Address;
import entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import service.AddressService;
import service.impl.AddressServiceImpl;
import util.Constants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/address")
public class AddressController extends BaseServlet{

    /**
     * 地址的展示
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String show(HttpServletRequest request , HttpServletResponse response) throws SQLException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            session.setAttribute("msg","需要先登录");
            return Constants.FORWARD+"login.jsp";
        }

        int uid = loginUser.getUid();

        AddressService addressService = new AddressServiceImpl();
        List<Address> addressList = addressService.findAddressByUid(uid);

        request.setAttribute("list",addressList);

        return Constants.FORWARD+"self_info.jsp";
    }

    /**
     * 添加地址
     * @param request
     * @param response
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {

        Map<String, String[]> map = request.getParameterMap();

        Address address = new Address();
        BeanUtils.populate(address,map);

        AddressService addressService = new AddressServiceImpl();
        addressService.saveAddress(address);

        return Constants.FORWARD+"/address?method=show";
    }

    /**
     * 删除地址
     * @param request
     * @param response
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {

        String aid = request.getParameter("aid");

        AddressService addressService = new AddressServiceImpl();
        addressService.deleteAddress(aid);

        return Constants.FORWARD+"/address?method=show";
    }

    /**
     * 设置默认地址
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String setDefault(HttpServletRequest request,HttpServletResponse response) throws SQLException {

        String aid = request.getParameter("aid");
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            session.setAttribute("msg","需要先登录");
            return Constants.FORWARD+"login.jsp";
        }

        int uid = loginUser.getUid();

        AddressService addressService = new AddressServiceImpl();
        addressService.setAddressToDefault(aid,uid);

        return Constants.FORWARD+"address?method=show";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {

        Map<String, String[]> map = request.getParameterMap();

        Address address = new Address();
        BeanUtils.populate(address,map);

        AddressService addressService = new AddressServiceImpl();
        addressService.updateByAid(address);

        return Constants.FORWARD+"/address?method=show";
    }
}
