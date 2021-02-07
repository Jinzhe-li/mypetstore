package org.mypetstore.web.servlets;

import jdk.swing.interop.SwingInterOpUtils;
import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        if(request.getParameter("res").equalsIgnoreCase(session.getAttribute("res").toString())){
            System.out.println(request.getParameter("res"));
            AccountService accountService = new AccountService();
            Account account=accountService.getAccount(request.getParameter("username"),request.getParameter("password"));
            if(account==null){
                session.setAttribute("message", "用户名或密码错误");
                request.getRequestDispatcher(ERROR).forward(request, response);
            }else{
                session.setAttribute("account",account);
                request.getRequestDispatcher(MAIN).forward(request, response);
            }
        }
        else{
            session.setAttribute("message", "验证码错误");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
