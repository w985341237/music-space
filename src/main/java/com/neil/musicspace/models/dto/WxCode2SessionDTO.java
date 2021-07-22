package com.neil.musicspace.models.dto;

import lombok.Data;

/**
 * @Description WxCode2SessionDTO
 * @Author neil
 * @Date 2021/7/12 15:32
 * @Version 1.0
 **/
@Data
public class WxCode2SessionDTO {
    public String openid;
    public String sessionKey;
    public String unionid;
    public Integer errcode;
    public String errmsg;
}
