package com.neil.musicspace.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neil.musicspace.exception.BasicException;
import com.neil.musicspace.models.dao.UserMapperEx;
import com.neil.musicspace.models.dto.LoginDTO;
import com.neil.musicspace.models.dto.WxCode2SessionDTO;
import com.neil.musicspace.models.dto.WxUserInfoDTO;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.models.enums.ReturnCode;
import com.neil.musicspace.models.vo.UserVO;
import com.neil.musicspace.service.user.UserLoginService;
import com.neil.musicspace.service.user.UserService;
import com.neil.musicspace.utils.WeixinUtil;
import com.neil.musicspace.utils.jwt.JwtUtil;
import com.neil.musicspace.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description 用户登录Service
 * @Author neil
 * @Date 2021/6/29 14:15
 * @Version 1.0
 **/
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private UserMapperEx userMapperEx;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WeixinUtil weixinUtil;

    @Override
    public UserVO miniProgramLogin(HttpServletResponse response, LoginDTO loginDTO, WxCode2SessionDTO content) {
        String openId = content.getOpenid();

        if (!weixinUtil.checkSignature(loginDTO.rawData, content.getSessionKey(), loginDTO.getSignature())) {
            log.error("signature is invalid, signature: [{}]", loginDTO.getSignature());
            throw new BasicException(ReturnCode.CLIENT_AUTHENTICATION_FAILED);
        }

        WxUserInfoDTO wxUserInfo = JSON.parseObject(loginDTO.getUserInfo(), WxUserInfoDTO.class);

        // 解密数据
        JSONObject data = weixinUtil.wxDecrypt(loginDTO.getEncryptedData(), content.getSessionKey(), loginDTO.getIv());

        User user = new User();
        BeanUtils.copyProperties(wxUserInfo, user);
        user.setOpenid(data.getString("openId"));
        user.setUnionid(data.getString("unionId") == null ? "" : data.getString("unionId"));
        user.setAddTime(new Date());

        userService.insertOrUpdateByOpenId(openId, user);

        // 下发token
        String token = JwtUtil.createToken(user, content.getSessionKey());
        response.setHeader("authorization", token);

        User model = userService.getUserByOpenid(openId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(model, userVO);

        return userVO;
    }
}
