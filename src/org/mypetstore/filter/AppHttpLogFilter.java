package org.mypetstore.filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class AppHttpLogFilter implements Filter {

    public void destroy() {
        /*销毁时调用*/
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String ip = httpReq.getRemoteAddr();
        String path = httpReq.getServletPath();
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        FileOutputStream fileOutputStream = null;
        System.out.println("A");
        File file = new File("D:\\log.txt");
            if (!file.exists()) {
                if(file.createNewFile()) System.out.println("b");
            }
            fileOutputStream=new FileOutputStream(file);
            String log=new String("IP:"+ip+"于"+new Date()+"访问"+path);
            fileOutputStream.write(log.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        chain.doFilter(req, resp);//交给下一个过滤器或servlet处理
    }

    public void init(FilterConfig config) throws ServletException {

        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/

    }
}