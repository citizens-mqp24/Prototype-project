package com.citizensmqp.backend.ValueObjects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
@RequiredArgsConstructor
public class HttpRouteVO {
    private String routePattern;
    private HttpMethod method;

    public HttpRouteVO(String path, HttpMethod httpMethod) {
        this.routePattern = path;
        this.method = httpMethod;
    }
    public boolean matchRequest(HttpServletRequest request) {
        //there may be a better way to do this but this is the best I can think of
        return matchUrl(request.getRequestURI()) && request.getMethod().equals(method.name());
    }
    public boolean matchUrl(String targetUrl) {
        //there may be a better way to do this but this is the best I can think of
        if(this.routePattern.contains("*")) {
            return targetUrl.startsWith(targetUrl.replace("/*",""));
        }
        return this.routePattern.equals(targetUrl);
    }
}

