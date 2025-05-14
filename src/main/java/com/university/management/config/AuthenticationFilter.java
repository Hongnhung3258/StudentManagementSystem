package com.university.management.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // Cho phép truy cập các tài nguyên công khai
        if (requestURI.equals("/login") || requestURI.startsWith("/css/") || requestURI.startsWith("/js/")
                || requestURI.startsWith("/images/") || requestURI.equals("/favicon.ico") || requestURI.startsWith("/h2-console/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Kiểm tra phiên đăng nhập
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            filterChain.doFilter(request, response);
        } else {
            // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
            response.sendRedirect("/login");
        }
    }
}