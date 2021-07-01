package com.neil.musicspace.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neil.musicspace.mybatis.dao.UserMapper;
import com.neil.musicspace.mybatis.entity.User;
import com.neil.musicspace.mybatis.entity.UserExample;
import com.neil.musicspace.service.user.UserLoginManager;
import com.neil.musicspace.utils.redis.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 用户登录Service
 * @Author neil
 * @Date 2021/6/29 14:15
 * @Version 1.0
 **/
@Service
public class UserLoginManagerImpl implements UserLoginManager {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 小程序登录
     * @param content
     */
    public void miniProgramLogin(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        String openId = jsonObject.getString("openid");
        redisService.set("session_key", jsonObject.getString("session_key"));

        User user = new User();
        BeanUtils.copyProperties(jsonObject, user);
        insertOrUpdateByOpenId(openId, user);

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertOrUpdateByOpenId(String openId, User user) {
        User record = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId));
        if (record == null) {
            // 插入新记录
            userMapper.insertSelective(user);
        } else {
            BeanUtils.copyProperties(record, user);
            userMapper.updateByPrimaryKey(record);
        }
    }
}
