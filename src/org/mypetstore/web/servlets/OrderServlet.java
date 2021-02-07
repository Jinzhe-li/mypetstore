package org.mypetstore.web.servlets;

import com.sun.jdi.Value;
import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.Order;
import org.mypetstore.service.CartService;
import org.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String NEWORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRMORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String VIEWORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ListOrder = "/WEB-INF/jsp/order/ListOrders.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orders=request.getParameter("orders");
        if(orders!=null){
            if(orders.equals("1")){
                request.getRequestDispatcher(NEWORDER).forward(request, response);
            }
            else if(orders.equals("2")){
                Order order= new Order();
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("account");
                Cart cart = (Cart) session.getAttribute("cart");
                order.initOrder(account,cart);
                order.setCardType(request.getParameter("cardType"));
                order.setExpiryDate(request.getParameter("expiryDate"));
                order.setCreditCard(request.getParameter("creditCard"));
                session.setAttribute("order",order);
                request.getRequestDispatcher(CONFIRMORDER).forward(request, response);
            }
            else if(orders.equals("3")){
                Order order= new Order();
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("account");
                Cart cart = (Cart) session.getAttribute("cart");
                order.initOrder(account,cart);
                order.setCardType(request.getParameter("cardType"));
                order.setExpiryDate(request.getParameter("expiryDate"));
                order.setCreditCard(request.getParameter("creditCard"));
                order.setShipToFirstName(request.getParameter("shipToFirstName"));
                order.setShipToLastName(request.getParameter("shipToLastName"));
                order.setShipAddress1(request.getParameter("shipAddress1"));
                order.setBillAddress2(request.getParameter("shipAddress2"));
                order.setShipCity(request.getParameter("shipCity"));
                order.setShipState(request.getParameter("shipState"));
                order.setShipZip(request.getParameter("shipZip"));
                order.setShipCountry(request.getParameter("shipCountry"));
                session.setAttribute("order",order);
                request.getRequestDispatcher(CONFIRMORDER).forward(request, response);
            }
            else if(orders.equals("4")){
                HttpSession session = request.getSession();
                Order order=(Order) session.getAttribute("order");
                OrderService orderService=new OrderService();
                orderService.insertOrder(order);
                CartService service =new CartService();
                Account account = (Account) session.getAttribute("account");
                service.deleteCart(account.getUsername());
                Cart cart=new Cart();
                session.setAttribute("cart",cart);
                request.getRequestDispatcher(VIEWORDER).forward(request, response);
            }
            else if(orders.equals("5")){
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("account");
                OrderService orderService =new OrderService();
                List<Order>orderList=orderService.selectOrder(account.getUsername());
                session.setAttribute("orderList",orderList);
                request.getRequestDispatcher(ListOrder).forward(request, response);
            }
        }
        else {
            HttpSession session = request.getSession();
            List<Order>orderList=(List<Order>) session.getAttribute("orderList");
            for(int i=0;i<orderList.size();i++){
                Order order= orderList.get(i);
                if(Integer.toString(order.getOrderId()).equals(request.getParameter("orderId"))){
                    session.setAttribute("order",order);
                    break;
                }
            }
            request.getRequestDispatcher(VIEWORDER).forward(request, response);
        }
    }
}
