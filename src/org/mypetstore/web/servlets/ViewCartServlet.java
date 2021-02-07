package org.mypetstore.web.servlets;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCartServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignOnForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account!=null) {
            String userid = account.getUsername();
            Cart cart = (Cart)session.getAttribute("cart");

            if (cart == null) {
                CartService cartService=new CartService();
                cart = cartService.selectCart(userid);
                session.setAttribute("cart", cart);
            }

            request.getRequestDispatcher(VIEW_CART).forward(request, response);
        }
        else {
            request.getRequestDispatcher(SIGNONFORM).forward(request,response);
        }
    }
}
