package com.citizensmqp.backend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Order(2)
public class corsFilter  extends OncePerRequestFilter  {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // this is for dev only do not do this in a prod env set up stuff in same domain behind firewall and loadbalancer
        response.addHeader("Access-Control-Allow-Origin","*");
        chain.doFilter(request,response); // pass the request down the chain
    }
}
