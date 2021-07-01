package com.neil.musicspace.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 微信 工具
 * @Author neil
 * @Date 2021/6/29 13:26
 * @Version 1.0
 **/
@Service
public class WeixinUtil {

    @Value("wechat-appId")
    private String appId;

    @Value("wechat-appSecret")
    private String appSecret;

    public Map<String, String> getWechatConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("appId", appId);
        map.put("appSecret", appSecret);
        return map;
    }

}
