package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.UseEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色表
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleEntity extends BaseEntity {

    //角色名称
    @NotNull(message = "角色名称不能为空")
    @Length(max = 50,message = "角色名称不能超过50个字")
    private String roleName;

    //使用状态
    private UseEnum useEnum;

    //角色组
    private List<RoleGroupEntity> roleGroupEntity;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UseEnum getUseEnum() {
        return useEnum;
    }

    public void setUseEnum(UseEnum useEnum) {
        this.useEnum = useEnum;
    }

    public List<RoleGroupEntity> getRoleGroupEntity() {
        return roleGroupEntity;
    }

    public void setRoleGroupEntity(List<RoleGroupEntity> roleGroupEntity) {
        this.roleGroupEntity = roleGroupEntity;
    }
}
