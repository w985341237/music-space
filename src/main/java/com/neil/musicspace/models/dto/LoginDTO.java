package com.neil.musicspace.models.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @Description 登录验证DTO
 * @Author neil
 * @Date 2021/7/9 13:44
 * @Version 1.0
 **/
@Data
@ApiModel("登录DTO")
public class LoginDTO {
    @ApiModelProperty("微信临时登录凭证")
    @NotNull
    public String code;

    @ApiModelProperty("用户信息对象")
    public String userInfo;

    @ApiModelProperty("不包括敏感信息的原始数据字符串，用于计算签名")
    public String rawData;

    @ApiModelProperty("签名")
    public String signature;

    @ApiModelProperty("包括敏感数据在内的完整用户信息的加密数据")
    public String encryptedData;

    @ApiModelProperty("加密算法的初始向量")
    public String iv;

}
