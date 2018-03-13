package com.tomorrow.ssh.ssh.Dao.Mapper.User;

import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.ValidEnum;
import com.tomorrow.ssh.ssh.VO.Auth.UserVo;
import org.springframework.util.StringUtils;

/**
 * 用户crud
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
public class UserMapperProvide {
    //基本查询
    private String baseSql=" select * from users where 1=1 and isValid='"+ ValidEnum.True+"'";

    //动态的条件查询
    private void addConditionSql(UserVo entity, StringBuffer sql){
        if(entity!=null){
            if(!StringUtils.isEmpty(entity.getAccount())){
                sql.append(" and account= #{account} ");
            }
            if(!StringUtils.isEmpty(entity.getMD5())){
                sql.append(" and MD5= #{MD5} ");
            }
            if(!StringUtils.isEmpty(entity.getName())){
                sql.append(" and name like \"%\"#{name}\"%\" ");
            }
            if(!StringUtils.isEmpty(entity.getCompanyId())){
                sql.append(" and companyId = #{companyId}");
            }
            if(entity.getSexEnum()!=null){
                sql.append(" and sexEnum = #{sexEnum}");
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
    public String getList(UserVo entity){
        StringBuffer sql=new StringBuffer(baseSql);
        addConditionSql(entity,sql);
        return sql.toString();
    }

    /**
     *查询符合该条件的实体的个数
     * @return sql
     */
    public String judgeDataValid(UserEntity entity){
        StringBuffer sql=new StringBuffer();
        sql.append("select  ");
        sql.append("   ( select count(*) from users where id!=#{id} and isValid='"+ ValidEnum.True+"' and account=#{account} ) as account,");//账户名重复
        sql.append("   ( select count(*) from users where id!=#{id} and isValid='"+ ValidEnum.True+"' and creditNum=#{creditNum} ) as creditNum");//员工编号重复
        sql.append("        ");
        return  sql.toString();
    }

}
