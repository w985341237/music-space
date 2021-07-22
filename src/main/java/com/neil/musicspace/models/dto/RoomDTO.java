package com.neil.musicspace.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description RoomDTO
 * @Author neil
 * @Date 2021/7/1 14:10
 * @Version 1.0
 **/
@Data
public class RoomDTO implements Serializable {

    private static final long serialVersionUID = 4983584915490619355L;

    @ApiModelProperty(name = "user_id", value = "用户id", required = true)
    private Integer UserId;

    @ApiModelProperty(name = "name", value = "房间名称", required = true)
    @NotEmpty(message = "房间名称不能为空")
    private String Name;

    @ApiModelProperty(name = "room_desc", value = "房间简介")
    private String RoomDesc;

    @ApiModelProperty(name = "room_user_num", value = "房间人数", required = true)
    @Max(value = 10, message = "房间人数不能超过10")
    @Min(value = 1, message = "房间人数不能小于1")
    private Integer RoomUserNum;

//    @ApiModelProperty(name = "use_password", value = "是否使用密码 0-否 1-是", required = true)
//    @Max(value = 1, message = "是否使用密码填写错误")
//    @Min(value = 1, message = "是否使用密码填写错误")
//    private Integer UsePassword;

    @ApiModelProperty(name = "room_password", value = "房间密码")
    @Size(min = 8, max = 20, message = "密码最小8位 最大20位")
    private String RoomPassword;

}
