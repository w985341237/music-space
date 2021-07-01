package com.neil.musicspace.service.passport.impl;

import com.neil.musicspace.service.passport.WechatLoginManager;
import com.neil.musicspace.utils.WeixinUtil;
import io.netty.util.internal.StringUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @Description TODO
 * @Author neil
 * @Date 2021/6/29 10:50
 * @Version 1.0
 **/
@Service
public class WechatLoginManagerImpl implements WechatLoginManager {

    @Autowired
    private WeixinUtil weixinUtil;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String code2Session(String code) {
        try {
            Map<String, String> weixinConfigMap = weixinUtil.getWechatConfig();
            String appId = weixinConfigMap.get("appId");
            String appSecret = weixinConfigMap.get("appSecret");

            if (StringUtil.isNullOrEmpty(appId) || StringUtil.isNullOrEmpty(appSecret)) {
                throw new RuntimeException("微信配置填写错误");
            }

            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=" + appId +
                    "&secret=" + appSecret +
                    "&js_code=" + code +
                    "&grant_type=" + "authorization_code";

            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            return Objects.requireNonNull(response.body()).toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
