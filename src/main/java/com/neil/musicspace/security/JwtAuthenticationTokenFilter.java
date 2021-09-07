package com.neil.musicspace.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neil.musicspace.models.dao.RoleMapperEx;
import com.neil.musicspace.models.dao.UserMapperEx;
import com.neil.musicspace.models.entity.Role;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.models.enums.ReturnCode;
import com.neil.musicspace.models.vo.ResultData;
import com.neil.musicspace.utils.jwt.JwtUtil;
import com.neil.musicspace.utils.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description JwtAuthenticationTokenFilter
 * @Author neil
 * @Date 2021/7/12 13:21
 * @Version 1.0
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RoleMapperEx roleMapperEx;
    @Resource
    private UserMapperEx userMapperEx;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("authorization");
        response.setCharacterEncoding("utf-8");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authToken = authHeader.substring("Bearer ".length());

        Claims claims = JwtUtil.parseToekn(authToken);
        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String openid = (String) claims.get("openid");

        if (redisUtil.exists(authToken)) {
            return;
        }

        if (openid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            HashMap<String, Object> map = (HashMap<String, Object>) redisUtil.get(authToken);
            User user = userMapperEx.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenid, openid));
            Role role = roleMapperEx.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, user.getRoleId()));
            List<String> permissions = JSON.parseArray(role.getAuthPermissions(), String.class);
            List<SimpleGrantedAuthority> authorities = permissions.stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toList());
            SecurityContextHolder.getContext().setAuthentication(new WxAppletAuthenticationToken(openid, authorities));
        }

        filterChain.doFilter(request, response);
    }
}
