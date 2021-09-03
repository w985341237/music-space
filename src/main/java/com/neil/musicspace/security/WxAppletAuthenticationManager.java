package com.neil.musicspace.security;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neil.musicspace.exception.BasicException;
import com.neil.musicspace.models.entity.Role;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.models.enums.ReturnCode;
import com.neil.musicspace.service.role.RoleService;
import com.neil.musicspace.service.user.UserService;
import com.neil.musicspace.service.user.UserServiceEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description WxAppletAuthenticationManager
 * 真正执行逻辑的manager，{@link WxAppletAuthenticationFilter}会将认证委托给{@link WxAppletAuthenticationManager}来做
 * @Author neil
 * @Date 2021/7/13 14:55
 * @Version 1.0
 **/
@Slf4j
@Component
public class WxAppletAuthenticationManager implements AuthenticationManager {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceEx userServiceEx;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WxAppletAuthenticationToken wxAppletAuthenticationToken = null;
        if (authentication instanceof WxAppletAuthenticationToken) {
            wxAppletAuthenticationToken = (WxAppletAuthenticationToken) authentication;
        }
        Wrapper<User> wrapper = new QueryWrapper<User>().lambda().eq(User::getOpenid, wxAppletAuthenticationToken.getOpenid());
        User user = userServiceEx.getOne(wrapper);
        // 执行注册逻辑
        if (user == null) {
            log.debug("user not exist, began to register. openid is [{}]", wxAppletAuthenticationToken.getOpenid());
            Digester digester = new Digester(DigestAlgorithm.SHA1);
            String data = wxAppletAuthenticationToken.getRawData() + wxAppletAuthenticationToken.getSessionKey();
            String signature = digester.digestHex(data);
            if (!wxAppletAuthenticationToken.getSignature().equals(signature)) {
                log.error("signature is invalid, received:[{}] generated:[{}]", wxAppletAuthenticationToken.getSignature(), signature);
                throw new BasicException(ReturnCode.CLIENT_AUTHENTICATION_FAILED);
            }

            user = JSON.parseObject(wxAppletAuthenticationToken.getRawData(), User.class);
            user.setOpenid(wxAppletAuthenticationToken.getOpenid());
            userServiceEx.saveOrUpdate(user, wrapper);

            Role role = roleService.getRoleById(user.getRoleId());
            List<String> permissions = JSON.parseArray(role.getAuthPermissions(), String.class);
            List<SimpleGrantedAuthority> authorities = permissions.stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toList());
            return new WxAppletAuthenticationToken(user.getOpenid(), wxAppletAuthenticationToken.getSessionKey(), authorities);
        }

        Role role = roleService.getRoleById(user.getRoleId());
        List<String> permissions = JSON.parseArray(role.getAuthPermissions(), String.class);
        List<SimpleGrantedAuthority> authorities = permissions.stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toList());
        return new WxAppletAuthenticationToken(wxAppletAuthenticationToken.getOpenid(), wxAppletAuthenticationToken.getSessionKey(), authorities);
    }
}
