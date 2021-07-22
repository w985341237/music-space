package com.neil.musicspace.service.role.impl;

import com.neil.musicspace.models.dao.RoleMapper;
import com.neil.musicspace.models.entity.Role;
import com.neil.musicspace.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description RoleServiceImpl
 * @Author neil
 * @Date 2021/7/13 15:22
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
