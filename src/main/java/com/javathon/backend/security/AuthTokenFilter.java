package com.javathon.backend.security;

import com.javathon.backend.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


/**
 * Главный фильтр, отвечающий за url { /rest }
 * Кастомно реализован секьюрити
 */
public class AuthTokenFilter extends GenericFilterBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;

    public AuthTokenFilter(UserDao userDao) {
        this.userDao = userDao;
    }


    //Filter for field_login and get cookie session
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //TODO Здесь вытаскиваем токен и проверяем совпадает ли он с пользователем из бд по id, imei.
        Cookie[] allCookies = ((HttpServletRequest) request).getCookies();
        if (allCookies != null) {
            Cookie session = Arrays.stream(allCookies).filter(x -> x.getName().equals("Auth-Token"))
                    .findFirst().orElse(null);
            if (session != null) {
                try {
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    logger.error("Failure in autoLogin", e);
                }
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
