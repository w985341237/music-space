package com.neil.musicspace.service.weixin.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neil.musicspace.exception.BasicException;
import com.neil.musicspace.models.dto.WxCode2SessionDTO;
import com.neil.musicspace.models.enums.ReturnCode;
import com.neil.musicspace.service.weixin.WeixinService;
import com.neil.musicspace.utils.WeixinUtil;
import com.neil.musicspace.utils.http.OkHttpUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description WeixinServiceImpl
 * @Author neil
 * @Date 2021/7/13 15:51
 * @Version 1.0
 **/
@Slf4j
@Service
public class WeixinServiceImpl implements WeixinService {
    @Autowired
    private WeixinUtil weixinUtil;

    @Override
    public WxCode2SessionDTO code2Session(String code) {
        String appId = weixinUtil.getWechatConfig().get("appId");
        String appSecret = weixinUtil.getWechatConfig().get("appSecret");
        try {
            if (StringUtil.isNullOrEmpty(appId) || StringUtil.isNullOrEmpty(appSecret)) {
                throw new RuntimeException("微信配置填写错误");
            }

            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=" + appId +
                    "&secret=" + appSecret +
                    "&js_code=" + code +
                    "&grant_type=authorization_code";

            Response response = OkHttpUtil.getInstance().getData(url);
            JSONObject jsonObject = JSON.parseObject(response.body().string());

            WxCode2SessionDTO wxDTO = JSON.toJavaObject(jsonObject, WxCode2SessionDTO.class);

            if (wxDTO.getErrcode() != null) {
                log.error("code2Session fail, errcode:" + wxDTO.getErrcode() + " errmsg:" + wxDTO.getErrmsg());
                throw new BasicException(ReturnCode.CLIENT_AUTHENTICATION_FAILED);
            }

            log.debug("code2session result:" + wxDTO.toString());

            return wxDTO;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
