package com.javathon.backend.security;

import com.javathon.backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Главный конфиг системы безопасности
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthTokenFilter authTokenFilter;

    @Autowired
    public WebSecurityConfig(UserDao userDao) {
        authTokenFilter = new AuthTokenFilter(userDao);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public FilterRegistrationBean<AuthTokenFilter> FilterRegistration() {
        FilterRegistrationBean<AuthTokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(authTokenFilter);
        registration.setOrder(Integer.MIN_VALUE);
        registration.addUrlPatterns("/rest/*");
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4, null);
    }

}
