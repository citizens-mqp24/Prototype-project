package com.citizensmqp.backend.filters;
import com.citizensmqp.backend.ValueObjects.HttpRouteVO;
import com.citizensmqp.backend.services.userService;
import com.citizensmqp.backend.controller.UnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final userService userService;
    private static final HttpRouteVO[] filteredRoutes = {
            new HttpRouteVO("/api/msg", HttpMethod.POST),
            new HttpRouteVO("/api/msg/likes/*", HttpMethod.POST),
            new HttpRouteVO("/api/users/info", HttpMethod.GET),
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.info(request.getRequestURI());
        for(HttpRouteVO routes : filteredRoutes) {
            if(routes.matchRequest(request)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            response.sendError(400,"you are not logged in or un unauthorized");
            return;
        }

        Optional<Cookie> authCookie = Arrays.stream(cookies)
                .filter(cookie -> { return cookie.getName().equals("access_token");}).findFirst();


        try {
            //try to fetch user info and see if it matches
            userService.fetchUserInfo(authCookie.orElseThrow().getValue());
        } catch (NoSuchElementException e) {
            response.sendError(400,"you are not logged in or un unauthorized");
            return;
        }

        chain.doFilter(request,response); // pass the request down the chain
    }

}

