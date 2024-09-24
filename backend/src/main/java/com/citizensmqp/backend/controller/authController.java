package com.citizensmqp.backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class authController {
    @Value("${OAUTH2_CLIENT_ID}")
    String clientId;

    @Value("${OAUTH2_REDIRECT_URI}")
    String redirectUri;

    String scope = "openid profile email";

    // Endpoint to trigger the OAuth2 authorization request
    @GetMapping()
    public void oauthLogin(HttpServletResponse response) throws IOException {
        System.out.println("redirect uri: " + redirectUri);
        String authorizationEndpoint = "https://accounts.google.com/o/oauth2/auth";
        String authorizationUrl = authorizationEndpoint
                + "?response_type=code"
                + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&scope=" + URLEncoder.encode(scope, StandardCharsets.UTF_8);

        // Redirect to OAuth2 provider's authorization endpoint
        response.sendRedirect(authorizationUrl);
    }

    // Callback URL to capture authorization code
    @GetMapping("/callback")
    public String oauthCallback(@RequestParam("code") String code) {
        System.out.println("Authorization Code: " + code);
        return "authorization-code-received";
    }
}
