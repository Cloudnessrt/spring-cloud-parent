package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 角色组与角色关联
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleGroupRoleRelation extends BaseEntity {

    //角色ID
    @NotNull(message = "角色ID不能为空")
    private String roleId;

    //角色组ID
    @NotNull(message = "角色组ID不能为空")
    private String groupId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
