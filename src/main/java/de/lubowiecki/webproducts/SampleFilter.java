package de.lubowiecki.webproducts;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class SampleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println("REQ: " + req.getRequestURI());
        // Login prüfen oder Co...
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
