package com.neil.musicspace.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.neil.musicspace.models.dao.UserMapperEx;
import com.neil.musicspace.service.user.UserService;
import com.neil.musicspace.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description UserServiceImpl
 * @Author neil
 * @Date 2021/7/9 15:34
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapperEx userMapperEx;
    @Autowired
    private WeixinUtil weixinUtil;

    @Override
    public String bindPhone(HttpServletRequest request, String encryptedData, String iv, String sessionKey) {
        // 解密数据
        JSONObject data = weixinUtil.wxDecrypt(encryptedData, sessionKey, iv);
        return null;
    }
}
