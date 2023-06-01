package com.kxnvg.http.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(value = "/*",
        initParams = {
                @WebInitParam(name = "param1", value = "paramValue")
        },
        dispatcherTypes = DispatcherType.REQUEST
)
public class ACharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
