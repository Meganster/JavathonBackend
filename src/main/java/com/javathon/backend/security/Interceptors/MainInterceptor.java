package com.javathon.backend.security.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class MainInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String token;

    public MainInterceptor() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] allCookies = request.getCookies();
        if (allCookies != null) {
            Cookie session = Arrays.stream(allCookies).filter(x -> x.getName().equals("Auth-Token"))
                    .findFirst().orElse(null);
            if (session == null) {
                return true;
            }
            setToken(session.getValue());
        }
        return true;
    }
}
