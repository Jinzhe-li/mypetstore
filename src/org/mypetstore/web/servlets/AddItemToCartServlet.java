package org.mypetstore.web.servlets;

import org.mypetstore.domain.Account;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.Item;
import org.mypetstore.service.CartService;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by songtie on 2015/4/27.
 */
public class AddItemToCartServlet extends HttpServlet {

    //1. 处理完请求后的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //2. 定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;

    //3. 是否需要调用业务逻辑层
    private CatalogService catalogService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String userid = account.getUsername();
        cart = (Cart)session.getAttribute("cart");

        if (cart == null) {
            CartService cartService=new CartService();
            cart = cartService.selectCart(userid);
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock,userid);
        }
        session.setAttribute("cart", cart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
