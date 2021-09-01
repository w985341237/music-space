package com.neil.musicspace.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neil.musicspace.models.dao.UserMapperEx;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description UserServiceImpl
 * @Author neil
 * @Date 2021/7/9 15:34
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapperEx userMapperEx;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOrUpdateByOpenId(String openId, User user) {
        User record = userMapperEx.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenid, openId));
        if (record == null) {
            userMapperEx.insert(user);
        } else {
            userMapperEx.updateById(user);
        }
    }

    @Override
    public User getUserByOpenid(String openid) {
        return userMapperEx.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenid, openid));
    }
}
