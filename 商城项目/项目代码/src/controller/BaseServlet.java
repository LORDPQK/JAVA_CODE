package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Constants;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet用于集中处理方法的调用
 * 以及默认页的处理方法
 * 以及返回值处理
 */

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //【1】接收请求的标识符method
        //标识符的规定，当每次请求，用户需要在请求参数申明method参数
        //参数的值就是要调用的方法名
        //   /user?method=login
        String methodStr = req.getParameter(Constants.TAG);

        //【2】如果method没有取到值，默认跳转到首页(标识符异常处理)
        if( methodStr == null && methodStr.equals("")){
            methodStr = Constants.INDEX;
        }

        //【3】反射调用的业务逻辑方法
             // 1获取类的class对象
        Class aClass = this.getClass();
             // 2 获取方法
        try {
            //getMethod 参数1 ：方法名，参数2：可变长参数列表 ...:方法参数的类型
            Method method1 = aClass.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            //3 执行方法 invoke 参数1：要执行方法的对象 参数2...:执行方法传入的参数
            Object result = method1.invoke(this,req,resp);

            //【4】集中处理返回值响应
            if (result != null) {
                //转发  重定向  返回字符
                String str = (String)result;
                if(str.startsWith(Constants.FORWARD)){
                    //转发
                    String path = str.substring(str.indexOf(Constants.FLAG)+1);
                    req.getRequestDispatcher(path).forward(req,resp);
                }else if(str.startsWith(Constants.REDIRECT)){
                    //重定向
                    String path = str.substring(str.indexOf(Constants.FLAG)+1);
                    resp.sendRedirect(path);
                }else {
                    resp.getWriter().println(str);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 当method方法没有值，默认赋值index,访问每个controller的index方法
     * 默认处理：跳转到首页
     * @param request
     * @param response
     * @return
     */
    public String index (HttpServletRequest request,HttpServletResponse response){
        return Constants.FORWARD+"/index.jsp";
    }

}
