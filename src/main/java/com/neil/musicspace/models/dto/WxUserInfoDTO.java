package com.neil.musicspace.models.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description UserDTO
 * @Author neil
 * @Date 2021/7/9 13:45
 * @Version 1.0
 **/
public class WxUserInfoDTO {
    @ApiModelProperty("用户昵称")
    public String nickName;

    @ApiModelProperty("用户头像url")
    public String avatarUrl;

    @ApiModelProperty("用户性别 0-未知 1-男性 2-女性")
    public Integer gender;

    @ApiModelProperty("国家")
    public String country;

    @ApiModelProperty("省份")
    public String province;

    @ApiModelProperty("城市")
    public String city;

    @ApiModelProperty("显示 country，province，city 所用的语言")
    public String language;
}
