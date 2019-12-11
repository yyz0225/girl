package com.yyz.girl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: yyz
 * @Date: 2019/12/10 9:27
 * 通过filter拦截指定的url请求更改后进行转发
 */
@WebFilter(filterName = "WebFilter",urlPatterns = "/web/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request =(HttpServletRequest)req;
            String requestUri = request.getRequestURI();
            if(requestUri.startsWith("/web")){
                String newPath = requestUri.replaceAll("/web","");
                request.getRequestDispatcher(newPath).forward(req,resp);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config){

    }

}
