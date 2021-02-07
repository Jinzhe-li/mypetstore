package org.mypetstore.web.servlets;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;
import org.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Created by songtie on 2015/4/27.
 */
public class UpdateCartQuantitiesServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Cart cart;
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account=(Account)session.getAttribute("account");
        cart = (Cart) session.getAttribute("cart");
        String itemId=request.getParameter("itemId");

        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            try {
                if(itemId.equalsIgnoreCase(cartItem.getItem().getItemId())){
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    cart.setQuantityByItemId(itemId, quantity);
                    CartService cartService=new CartService();
                    cartService.updateCart(cartItem,account.getUsername());
                    response.setContentType("text/plain");
                    PrintWriter out=response.getWriter();
                    out.print(cartItem.getTotal());
                    out.flush();
                    out.close();
                    break;
                }
            } catch (Exception e) {
            }
        }
    }
}
