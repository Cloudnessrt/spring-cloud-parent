package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 用户和用户组关联
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class UserGroupUserRelation extends BaseEntity {

    //角色ID
    @NotNull(message = "用户ID不能为空")
    private String userId;

    //角色组ID
    @NotNull(message = "用户组ID不能为空")
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
