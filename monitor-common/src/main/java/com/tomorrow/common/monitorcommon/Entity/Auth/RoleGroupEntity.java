package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.UseEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色组
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleGroupEntity extends BaseEntity {

    //角色组名称
    @NotNull(message = "角色组名称不能为空")
    @Length(max = 50,message = "角色组名称不能超过50个字")
    private String groupName;

    //组排序
    private int sort;

    //使用状态
    private UseEnum useStatus;

    //角色组
    private List<RoleEntity> roleEntity;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public UseEnum getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(UseEnum useStatus) {
        this.useStatus = useStatus;
    }

    public RoleGroupEntity(String groupName, int sort, UseEnum useStatus, List<RoleEntity> roleEntity) {
        this.groupName = groupName;
        this.sort = sort;
        this.useStatus = useStatus;
        this.roleEntity = roleEntity;
    }

    public List<RoleEntity> getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(List<RoleEntity> roleEntity) {
        this.roleEntity = roleEntity;
    }
}
