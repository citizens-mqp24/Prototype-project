package com.citizensmqp.backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class authController {
    @Value("${OAUTH2_CLIENT_ID}")
    String clientId;

    @Value("${OAUTH2_CLIENT_SECRET}")
    String clientSecret;

    @Value("${OAUTH2_REDIRECT_URI}")
    String redirectUri;

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
    public String handleOAuth2Callback(@RequestParam(name = "code") String authorizationCode) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("code", authorizationCode);
        tokenRequest.put("client_id", clientId);
        tokenRequest.put("client_secret", clientSecret);
        tokenRequest.put("redirect_uri", redirectUri);
        tokenRequest.put("grant_type", grantType);

        Map<String, String> tokenResponse = restTemplate.postForObject(tokenUri, tokenRequest, Map.class);

        assert tokenResponse != null;
        String accessToken = tokenResponse.get("access_token");
        System.out.println("access token: " + accessToken);

        return "access-token-received!!!";
    }
}
