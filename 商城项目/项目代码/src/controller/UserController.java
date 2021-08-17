package controller;

import entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Base64Utils;
import util.Constants;
import util.MD5Utils;
import util.RandomUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(value = "/user")
public class UserController extends BaseServlet {



    public String check(HttpServletRequest request,HttpServletResponse response) throws Exception {
        // 1.获取用户名
        String username = request.getParameter("username");
        if(username==null){
            return Constants.HAS_USER;//不能注册
        }
        // 2.调用业务逻辑判断用户名是否存在
        UserService userService =new UserServiceImpl();
        boolean b = userService.checkedUser(username);
        // 3.响应字符串 1 存在 0 不存在
        if(b){
            return Constants.HAS_USER;
        }
        return Constants.NOT_HAS_USER;
        /*

    public String login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //登录业务逻辑
        //1。收参 req
        //2。调用业务逻辑方法
        //3。响应（转发，重定向，字符串(json),返回字节）resp
        response.getWriter().println("登录业务逻辑");
        //转发 forward:/xxx
        //重定向 redirect:/xxx
        //返回字符串 {}即可 return "{name:''}"
        //返回字节文件 方法返回值类型void 使用resp对象手动返回 resp.getOutputStream().write();
        return "{name:'xxx'}";
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("注册业务逻辑");
        response.getOutputStream().write(null);
    }

    */
    }

    /**
     * 注册方法
     * @param request
     * @param response
     * @return
     */
    public String register(HttpServletRequest request,HttpServletResponse response)  {
        // 【1】获取用户信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 【2】 对未赋值的属性进行赋值
        user.setUstatus(Constants.USER_NOT_ACTIVE);
        user.setUrole(Constants.ROLE_CUSTOMER);
        user.setCode(RandomUtils.createActive());

        // 【3】 需要对属性进行处理例如密码加密
        user.setUpassword(MD5Utils.md5(user.getUpassword()));

        // 【4】调用注册的业务逻辑方法
        UserService userService = new UserServiceImpl();
        try {
            userService.registerUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            request.setAttribute("registerMsg","注册失败");
            return Constants.FORWARD+"/register.jsp";
        }
        //【5】 响应
        return Constants.FORWARD+"/registerSuccess.jsp";
    }

    /**
     * 激活方法
     * @param request
     * @param response
     * @return
     */
    public String active(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        // 1 获取激活码
        String c = request.getParameter("c");
        //base64翻转
        String decode = Base64Utils.decode(c);
        // 2 调用激活的业务逻辑
        UserService userService = new UserServiceImpl();
        int i = userService.activeUser(decode);
        // 3 响应（激活失败（code没有找到） 已经激活 激活成功）
        if (i==Constants.ACTIVE_FAIL){
            request.setAttribute("msg","未激活成功");
        }else if(i==Constants.ACTIVE_SUCCESS)
        {
            request.setAttribute("msg","激活成功");
        }else if (i==Constants.ACTIVE_ALREADY){
            request.setAttribute("msg","已激活");
        }
        return Constants.FORWARD+"/message.jsp";
    }


    /**
     * 登录方法（包含自动登录功能 AutoFilter中具体实现）
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户名 密码 验证码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String auto = request.getParameter("auto");

        //获取正确的验证码
        HttpSession session = request.getSession();
        String code1 = (String) session.getAttribute("code");

        //与用户输入的验证码进行比较
        if(code1==null||!code.equalsIgnoreCase(code1)){
            session.setAttribute("msg","验证码错误");
            return Constants.FORWARD+"/login.jsp";
        }

        //调用业务逻辑判断账号密码
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);

        //响应
        if(user == null){
            session.setAttribute("msg","账号或密码错误");
            return Constants.FORWARD+"/login.jsp";
        }
        if(user.getUstatus().equals(Constants.USER_NOT_ACTIVE)){
            session.setAttribute("msg","用户未激活");
        }

        session.setAttribute("loginUser",user);

        //判断是否勾选自动登录
        if(auto==null){
            //没有勾选，将本地浏览器的cookies清空
            Cookie cookie = new Cookie(Constants.AUTO_NAME,"");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }else{
            //勾选 保存两周cookies
            String content = username+Constants.FLAG+password;
            content = Base64Utils.encode(content);
            Cookie cookie = new Cookie(Constants.AUTO_NAME,content);
            cookie.setPath("/");
            cookie.setMaxAge(14*24*60*60);
            response.addCookie(cookie);
        }
        return Constants.REDIRECT+"/index.jsp";
    }


    /**
     * 注销方法（清空数据 跳转到主页面）
     * @param request
     * @param response
     * @return
     */
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        // 1 清空session中的用户数据
            HttpSession session = request.getSession();
            session.removeAttribute("loginUser");
        // 2 清空和覆盖cookies储存的自动登录信息
            Cookie cookie = new Cookie(Constants.AUTO_NAME,"");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        // 3 转发到登录页面
            request.setAttribute("msg","注销登录成功");
            return Constants.FORWARD+"/index.jsp";
    }
}
