package org.mypetstore.web.servlets;

import org.mypetstore.domain.Product;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AutoCompleteServlet extends HttpServlet {
    public String keyword;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");

        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        if(productList.size()==0){
            out.print("not");
        }
        else {
            for (int i=0;i<productList.size();i++){
                out.print(productList.get(i));
                out.print(",");
            }
        }
        out.flush();
        out.close();
    }
}
