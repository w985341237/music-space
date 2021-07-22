package com.neil.musicspace.models.vo;

import com.neil.musicspace.models.entity.Room;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description RoomVO
 * @Author neil
 * @Date 2021/7/1 15:05
 * @Version 1.0
 **/
@Data
public class RoomVO extends Room implements Serializable {
    private static final long serialVersionUID = -684085986115636144L;
}
