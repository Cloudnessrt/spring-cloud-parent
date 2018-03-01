package com.tomorrow.monitorbusi.VO.Auth;

import com.tomorrow.common.monitorcommon.Enum.impl.UseEnum;
import org.hibernate.validator.constraints.Length;

/**
 * 角色查询实体
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleVo {

    private  String id;

    //角色名称
    @Length(max = 50,message = "角色名称不能超过50个字")
    private String roleName;

    //使用状态
    private UseEnum useEnum;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
