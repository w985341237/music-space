package com.neil.musicspace.models.vo;

import lombok.Data;

/**
 * @Description LoginVO
 * @Author neil
 * @Date 2021/9/3 14:07
 * @Version 1.0
 **/
@Data
public class LoginVO {

    private UserVO userInfo;

    private String accessToken;

    private String sessionKey;
}
