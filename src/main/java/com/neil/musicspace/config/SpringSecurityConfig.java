package com.neil.musicspace.config;

import com.neil.musicspace.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description SpringSecurity 配置
 * @Author neil
 * @Date 2021/7/12 10:35
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/index/**",
            "/passport/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //定义验权失败返回格式
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
//                // 配置登出逻辑
//                .and().logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
                .and().authorizeRequests()
                .antMatchers(AUTH_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
