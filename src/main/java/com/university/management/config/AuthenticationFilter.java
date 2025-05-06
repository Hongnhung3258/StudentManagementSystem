package com.university.management.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filter for checking authentication status
 */
@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Get current path
        String path = httpRequest.getRequestURI();
        
        // URLs that don't require authentication
        if (path.equals("/login") || path.startsWith("/css/") || path.startsWith("/js/") || 
            path.startsWith("/images/") || path.equals("/favicon.ico") || 
            path.startsWith("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check if the user is authenticated
        HttpSession session = httpRequest.getSession(false);
        boolean isAuthenticated = session != null && session.getAttribute("username") != null;
        
        if (isAuthenticated) {
            // User is authenticated, proceed with the request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}