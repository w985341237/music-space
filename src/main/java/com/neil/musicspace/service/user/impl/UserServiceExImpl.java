package com.neil.musicspace.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neil.musicspace.models.dao.UserMapperEx;
import com.neil.musicspace.models.entity.User;
import com.neil.musicspace.service.user.UserServiceEx;
import org.springframework.stereotype.Service;

/**
 * @Description UserServiceExImpl
 * @Author neil
 * @Date 2021/9/3 10:08
 * @Version 1.0
 **/
@Service
public class UserServiceExImpl extends ServiceImpl<UserMapperEx, User> implements UserServiceEx {
}
