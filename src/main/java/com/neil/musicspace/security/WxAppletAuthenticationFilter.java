package com.neil.musicspace.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.neil.musicspace.exception.BasicException;
import com.neil.musicspace.models.dto.WxCode2SessionDTO;
import com.neil.musicspace.models.enums.ReturnCode;
import com.neil.musicspace.service.weixin.WeixinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description WxAppletAuthenticationFilter
 * @Author neil
 * @Date 2021/7/12 14:54
 * @Version 1.0
 **/
@Slf4j
public class WxAppletAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    @Autowired
    private WeixinService weixinService;

    public WxAppletAuthenticationFilter(String defaultFilterProcessUrl) {
        super(defaultFilterProcessUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            log.error("code is null");
            throw new BasicException(ReturnCode.RC400);
        }
        String rawData = request.getParameter("rawData");
        String signature = request.getParameter("signature");
        if (StringUtils.isBlank(rawData) || StringUtils.isBlank(signature)) {
            log.error("rawData or signature is null");
            throw new BasicException(ReturnCode.RC400);
        }

        WxCode2SessionDTO content = weixinService.code2Session(code);
        return this.getAuthenticationManager().authenticate(new WxAppletAuthenticationToken(content.getOpenid(), content.getSessionKey(), rawData, signature));
    }
}
