package org.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SkipServlet extends HttpServlet {
    private String jsp;
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String HELP = "/WEB-INF/jsp/help.html";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignOnForm.jsp";
    private static final String EDITACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        jsp = request.getParameter("jsp");
        if(jsp.equals("1")){
            request.getRequestDispatcher(SIGNONFORM).forward(request,response);
        }
        else if(jsp.equals("2")){
            request.getRequestDispatcher(HELP).forward(request,response);
        }
        else if(jsp.equals("3")){
            request.getRequestDispatcher(NEWACCOUNTFORM).forward(request,response);
        }
        else if(jsp.equals("4")){
            HttpSession session = request.getSession();
            session.removeAttribute("account");
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
        else if(jsp.equals("5")){
            request.getRequestDispatcher(EDITACCOUNT).forward(request,response);
        }
    }
}
