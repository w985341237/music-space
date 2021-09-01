package com.neil.musicspace.models.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("ms_room")
public class Room implements Serializable {
    private Long id;

    private String roomName;

    private String roomDesc;

    private String roomNumber;

    private String roomPassword;

    private Integer managerId;

    private Byte isDelete;

    private static final long serialVersionUID = 1L;

    public Room(Long id, String roomName, String roomDesc, String roomNumber, String roomPassword, Integer managerId, Byte isDelete) {
        this.id = id;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.roomNumber = roomNumber;
        this.roomPassword = roomPassword;
        this.managerId = managerId;
        this.isDelete = isDelete;
    }

    public Room() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword == null ? null : roomPassword.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomName=").append(roomName);
        sb.append(", roomDesc=").append(roomDesc);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", roomPassword=").append(roomPassword);
        sb.append(", managerId=").append(managerId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}