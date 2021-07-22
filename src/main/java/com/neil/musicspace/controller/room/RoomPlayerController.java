package com.neil.musicspace.controller.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description RoomPlayerController
 * @Author neil
 * @Date 2021/7/2 9:45
 * @Version 1.0
 **/
@Controller
@RequestMapping("/room/player")
public class RoomPlayerController {

    @GetMapping("/index")
    public String player() {
        return "player";
    }
}
