package com.citizensmqp.backend.controller;

import com.citizensmqp.backend.ValueObjects.googleTokenResponseVO;
import com.citizensmqp.backend.ValueObjects.googleUserInfoVO;
import com.citizensmqp.backend.services.userService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class authController {

    private final userService us;

    @Value("${OAUTH2_CLIENT_ID}")
    String clientId;

    @Value("${OAUTH2_CLIENT_SECRET}")
    String clientSecret;

    @Value("${OAUTH2_REDIRECT_URI}")
    String redirectUri;

    @Value("${HOMEPAGE_URL}")
    String homepageUrl;

    String scope = "openid profile email";
    String tokenUri = "https://oauth2.googleapis.com/token";
    String grantType = "authorization_code";

    @GetMapping()
    public void oauthLogin(HttpServletResponse response) throws IOException {
        String authorizationEndpoint = "https://accounts.google.com/o/oauth2/auth";
        String authorizationUrl = authorizationEndpoint
                + "?response_type=code"
                + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        response.sendRedirect(authorizationUrl);
    }

    @GetMapping("/callback")
    public void handleOAuth2Callback(@RequestParam(name = "code") String authorizationCode,HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("code", authorizationCode);
        tokenRequest.put("client_id", clientId);
        tokenRequest.put("client_secret", clientSecret);
        tokenRequest.put("redirect_uri", redirectUri);
        tokenRequest.put("grant_type", grantType);

        googleTokenResponseVO tokenResponse = restTemplate.postForObject(tokenUri, tokenRequest, googleTokenResponseVO.class);

        if (tokenResponse == null) {
            //TODO respond with error
            response.sendRedirect(homepageUrl);
        }
        System.out.println("access token: " + tokenResponse.access_token);

        //TODO possibly save id token
        //TODO possibly setup new user

        Cookie authCookie = new Cookie("access_token",  tokenResponse.access_token);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(tokenResponse.expires_in);
        response.addCookie(authCookie);
        log.info("starting to redirect to " + homepageUrl);
        response.sendRedirect(homepageUrl);
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie authCookie = new Cookie("access_token",  "");
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(1);
        response.addCookie(authCookie);
        response.sendRedirect(homepageUrl);
    }

}
