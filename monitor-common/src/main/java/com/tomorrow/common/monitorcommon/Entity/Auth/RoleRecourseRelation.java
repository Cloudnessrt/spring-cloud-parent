package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 角色与菜单关联
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleRecourseRelation extends BaseEntity {

    //资源id
    @NotNull(message = "资源ID不能为空")
    private String resourceId;
    //角色id
    @NotNull(message = "角色ID不能为空")
    private String roleId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
