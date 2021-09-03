package com.neil.musicspace.service.room.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neil.musicspace.models.dao.RoomMapper;
import com.neil.musicspace.models.dto.RoomDTO;
import com.neil.musicspace.models.dto.RoomSearchDTO;
import com.neil.musicspace.models.entity.Room;
import com.neil.musicspace.models.vo.RoomDeatilVO;
import com.neil.musicspace.models.vo.RoomVO;
import com.neil.musicspace.service.room.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description RoomManager实现类
 * @Author neil
 * @Date 2021/7/1 16:32
 * @Version 1.0
 **/
@Service
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomMapper roomMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomVO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        // 设置房主id
        room.setManagerId(roomDTO.getUserId());

        roomMapper.insert(room);

        RoomVO roomVO = new RoomVO();
        BeanUtils.copyProperties(room, roomVO);
        return roomVO;
    }

    @Override
    public RoomVO updateRoom(RoomDTO roomDTO) {
        return null;
    }

    @Override
    public void delRoom(Long roomId) {
        roomMapper.deleteByPrimaryKey(roomId);
    }

    @Override
    public Page<RoomVO> getRoomPageList(RoomSearchDTO roomSearchDTO) {
        return null;
    }

    @Override
    public RoomDeatilVO getRoomDetail(Integer roomId) {
        return null;
    }
}
