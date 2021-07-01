package com.neil.musicspace.mybatis.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

@ApiModel("用户实体")
public class User implements Serializable {
    private Integer id;

    private String openId;

    private String unionId;

    private String nickName;

    private Byte gender;

    private String avatarUrl;

    private String phoneNumber;

    private Date addTime;

    private Byte isDelete;

    private static final long serialVersionUID = 1L;

    public User(Integer id, String openId, String unionId, String nickName, Byte gender, String avatarUrl, String phoneNumber, Date addTime, Byte isDelete) {
        this.id = id;
        this.openId = openId;
        this.unionId = unionId;
        this.nickName = nickName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.phoneNumber = phoneNumber;
        this.addTime = addTime;
        this.isDelete = isDelete;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        sb.append(", openId=").append(openId);
        sb.append(", unionId=").append(unionId);
        sb.append(", nickName=").append(nickName);
        sb.append(", gender=").append(gender);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", addTime=").append(addTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}