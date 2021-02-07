package org.mypetstore.web.servlets;

import org.mypetstore.domain.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerifyCodeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VerifyCode verifyCode=new VerifyCode();
        response.setContentType("image/jpeg");
        //输出图片
        BufferedImage image=verifyCode.getImage();
        verifyCode.output(image,response.getOutputStream());
        //存入结果
        request.getSession().setAttribute("res",verifyCode.getText());

    }
}
