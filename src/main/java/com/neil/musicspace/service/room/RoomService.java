package com.neil.musicspace.service.room;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neil.musicspace.models.dto.RoomDTO;
import com.neil.musicspace.models.dto.RoomSearchDTO;
import com.neil.musicspace.models.vo.RoomDeatilVO;
import com.neil.musicspace.models.vo.RoomVO;

import java.util.List;

/**
 * @Description RoomManager
 * @Author neil
 * @Date 2021/7/1 16:31
 * @Version 1.0
 **/
public interface RoomService {

    /**
     * 创建房间
     *
     * @param roomDTO
     * @return
     */
    RoomVO createRoom(RoomDTO roomDTO);

    /**
     * 更新房间信息
     *
     * @param roomDTO
     * @return
     */
    RoomVO updateRoom(RoomDTO roomDTO);

    /**
     * 删除房间
     *
     * @param roomId
     */
    void delRoom(Long roomId);

    /**
     * 分页获取房间列表
     *
     * @param roomSearchDTO
     * @return
     */
    Page<RoomVO> getRoomPageList(RoomSearchDTO roomSearchDTO);

    /**
     * 获取房间详细信息
     *
     * @param roomId
     * @return
     */
    RoomDeatilVO getRoomDetail(Integer roomId);
}
