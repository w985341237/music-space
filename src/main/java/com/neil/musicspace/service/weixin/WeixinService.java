package com.neil.musicspace.service.weixin;

import com.neil.musicspace.models.dto.WxCode2SessionDTO;

/**
 * @Description WeixinService
 * @Author neil
 * @Date 2021/7/13 15:50
 * @Version 1.0
 **/
public interface WeixinService {

    /**
     * auth.code2Session
     * 通过code获得openid
     * @param code
     * @return
     */
    WxCode2SessionDTO code2Session(String code);
}
