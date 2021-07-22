package com.neil.musicspace.controller.room;

import com.neil.musicspace.models.dto.RoomDTO;
import com.neil.musicspace.models.vo.RoomVO;
import com.neil.musicspace.service.room.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @Description RoomController
 * @Author neil
 * @Date 2021/7/1 14:05
 * @Version 1.0
 **/
@RestController()
@RequestMapping("/room")
@Api("房间控制器")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @ApiOperation("创建房间")
    @ApiImplicitParam(name = "room", value = "房间信息", required = true, dataType = "RoomDTO", paramType = "body")
    @PostMapping("/create")
    public RoomVO create(@ApiIgnore @Valid @RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }
}
