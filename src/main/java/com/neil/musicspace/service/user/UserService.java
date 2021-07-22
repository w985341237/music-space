package com.neil.musicspace.service.user;

import com.neil.musicspace.models.entity.User;

/**
 * @Description UserService
 * @Author neil
 * @Date 2021/7/9 15:31
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 插入或更新用户
     *
     * @param user
     */
    void insertOrUpdateByOpenId(String openId, User user);

    /**
     * 根据openid获取用户
     *
     * @param openid
     * @return
     */
    User getUserByOpenid(String openid);
}
