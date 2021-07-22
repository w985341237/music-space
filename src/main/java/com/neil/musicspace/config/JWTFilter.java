package com.neil.musicspace.config;

import com.neil.musicspace.utils.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description JWTFilter
 * @Author neil
 * @Date 2021/7/8 22:19
 * @Version 1.0
 **/
@Slf4j
@WebFilter(filterName = "JWTFilter", urlPatterns = "/test/*")
public class JWTFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String token = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (token == null) {
                response.sendError(401,"token不能为空！");
                return;
            }

            final Claims claims = JwtUtil.parseToekn(token);
            if (claims == null) {
                response.sendError(400, "token已经失效，请重新登录");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
