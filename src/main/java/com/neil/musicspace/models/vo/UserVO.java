package com.neil.musicspace.models.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description UserVO
 * @Author neil
 * @Date 2021/7/9 16:32
 * @Version 1.0
 **/
@Data
public class UserVO {

    private Integer id;

    private String nickName;

    private Byte gender;

    private String avatarUrl;

    private String phoneNumber;

    private Date addTime;

    private String sessionKey;
}
