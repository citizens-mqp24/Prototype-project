package com.citizensmqp.backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
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

        return fetchUserInfo(accessToken);
    }

    private String fetchUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        URI userInfoUri = URI.create("https://www.googleapis.com/oauth2/v2/userinfo");

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    userInfoUri,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Map.class
            );

            System.out.println(responseEntity);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> userInfo = responseEntity.getBody();
                return "User Info: " + userInfo;
            } else {
                System.err.println("Error fetching user info: " + responseEntity.getStatusCode());
                return "Error: Failed to fetch user info";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to fetch user info";
        }
    }
}
