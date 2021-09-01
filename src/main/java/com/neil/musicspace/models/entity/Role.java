package com.neil.musicspace.models.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("ms_role")
public class Role implements Serializable {
    private Long id;

    private String roleName;

    private String authPermissions;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    public Role(Long id, String roleName, String authPermissions, Boolean isDelete) {
        this.id = id;
        this.roleName = roleName;
        this.authPermissions = authPermissions;
        this.isDelete = isDelete;
    }

    public Role() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getAuthPermissions() {
        return authPermissions;
    }

    public void setAuthPermissions(String authPermissions) {
        this.authPermissions = authPermissions == null ? null : authPermissions.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", authPermissions=").append(authPermissions);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}