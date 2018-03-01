package com.tomorrow.monitorbusi.Dao.Mapper.Auth;

import com.tomorrow.common.monitorcommon.Entity.Auth.RoleEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.ValidEnum;
import com.tomorrow.monitorbusi.VO.Auth.RoleVo;
import org.springframework.util.StringUtils;

/**
 * 角色实体动态sql
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RoleEntityMapperProvide {

    //基本查询
    private String baseSql=" select * from roleEntity where 1=1 and isValid='"+ ValidEnum.True+"'";

    //动态的条件查询
    private void addConditionSql(RoleVo entity, StringBuffer sql){
        if(entity!=null){
            if(!StringUtils.isEmpty(entity.getRoleName())){
                sql.append(" and roleName= #{roleName} ");
            }
            if(entity.getUseEnum()!=null){
                sql.append(" and useEnum= #{useEnum} ");
            }
        }
    }

    /**
     * 查询集合
     * @return sql
     */
    public String getList(RoleVo entity){
        StringBuffer sql=new StringBuffer(baseSql);
        addConditionSql(entity,sql);
        return sql.toString();
    }

    /**
     * 新增sql
     * @param entity 实体
     * @return sql
     */
    public String insertEntity(RoleEntity entity){
        StringBuffer sql=new StringBuffer(" INSERT INTO roleEntity (id, isValid, roleName, useEnum, createDate, createUser, createUserName, lastChangeDate, lastChangeUser, lastChangeUserName) ");
        sql.append(" VALUES (#{id}, #{isValid}, #{roleName}, #{useEnum}, #{createDate}, #{createUser}, #{createUserName}," +
                " #{lastChangeDate}, #{lastChangeUser}, #{lastChangeUserName}) ");
        return sql.toString();
    }

    /**
     * 更新sql
     * @param entity 实体
     * @return sql
     */
    public String updateEntity(RoleEntity entity){
        StringBuffer sql=new StringBuffer(" UPDATE roleEntity SET isValid=#{isValid}, roleName=#{roleName}, " +
                "useEnum=#{useEnum}, createDate=#{createDate}, createUser=#{createUser}, createUserName=#{createUserName}, " +
                "lastChangeDate=#{lastChangeDate}, lastChangeUser=#{lastChangeUser}, lastChangeUserName=#{lastChangeUserName} WHERE id=#{id}");
        return sql.toString();
    }

    /**
     *查询符合该条件的实体的个数
     * @return sql
     */
    public String judgeDataValid(RoleEntity entity){
        StringBuffer sql=new StringBuffer();
        sql.append("select  ");
        sql.append("   ( select count(*) from users where id!=#{id} and isValid='"+ ValidEnum.True+"' and roleName=#{roleName} ) as roleName");//角色名重复
        sql.append("        ");
        return  sql.toString();
    }
}
