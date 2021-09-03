package com.neil.musicspace.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.neil.musicspace.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description MyMetaObjectHandler
 * @Author neil
 * @Date 2021/9/3 11:24
 * @Version 1.0
 **/

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("addTime", DateUtil.getDateline(), metaObject);
        this.setFieldValByName("lastLoginTime", DateUtil.getDateline(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("lastLoginTime", DateUtil.getDateline(), metaObject);
    }
}
