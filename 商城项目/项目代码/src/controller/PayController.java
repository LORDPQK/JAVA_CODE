package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Constants;

@WebServlet("/Pay")
public class PayController extends BaseServlet{

    public String showQRcode(HttpServletRequest request, HttpServletResponse response){
        String QRcode ="/image/QRCODE.png";
        request.setAttribute("qrcode",QRcode);
        return Constants.FORWARD+"QRshow.jsp";
    }
}
