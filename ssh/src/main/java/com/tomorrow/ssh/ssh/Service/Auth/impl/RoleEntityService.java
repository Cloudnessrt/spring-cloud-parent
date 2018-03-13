package com.tomorrow.ssh.ssh.Service.Auth.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tomorrow.common.monitorcommon.Entity.Auth.RoleEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.ValidEnum;
import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import com.tomorrow.common.monitorcommon.VO.PageInfo;
import com.tomorrow.ssh.ssh.Dao.Mapper.Auth.IRoleEntityMapper;
import com.tomorrow.ssh.ssh.VO.Auth.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
@Service
public class RoleEntityService {


    @Autowired
    private IRoleEntityMapper iRoleEntityMapper;

    /**
     * 信息分页
     * @param pageInfo 分页信息
     * @param entity 查询实体
     * @return 分页
     */
    public PageInfo<RoleEntity> getPage(PageInfo pageInfo, RoleVo entity){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<RoleEntity> pageData =iRoleEntityMapper.getPage(entity);
        return new PageInfo<>(pageData);
    }

    /**
     * 根据实体查询用户信息列表
     * @param entity 用户信息
     * @return 列表
     */
    public List<RoleEntity> getList(RoleVo entity){
        return iRoleEntityMapper.getList(entity);
    }

    /**
     * 查询单条数据
     * @param id 查询ID
     * @return 实体
     */
    public RoleEntity getOne(String id){
        return iRoleEntityMapper.getOne(id);
    }

    /**
     * 更新
     * @param entity 实体
     * @return 反馈
     */
    public ExectueResult updateEntity( RoleEntity entity){
        ExectueResult result=judgeDataValid(entity);//唯一性判断
        if(result.isResult()){
            iRoleEntityMapper.updateEntity(entity);
        }
        return result;
    }

    /**
     * 删除
     * @param entity 删除实体
     * @return 反馈
     */
    public ExectueResult deleteEntity(RoleEntity entity){
        entity.setValid(ValidEnum.False);
        return updateEntity(entity);
    }

    /**
     * 新增
     * @param entity 实体
     * @return 反馈
     */
    public ExectueResult insertEntity(RoleEntity entity){
        ExectueResult result=judgeDataValid(entity);//唯一性判断
        if(result.isResult()){
            iRoleEntityMapper.insertEntity(entity);
        }
        return result;
    }

    /**
     * 唯一性鉴别
     * @param entity 实体
     * @return 反馈
     */
    private ExectueResult judgeDataValid(RoleEntity entity){
        Map<String,Long> map=iRoleEntityMapper.judgeDataValid(entity);
        if(map==null){
            return new ExectueResult();
        }
        String errorStr=null;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if(entry.getValue()!=null && entry.getValue() >0L){
                switch (entry.getKey()){
                    case "roleName": errorStr="角色名重复";break;
                }
                break;
            }
        }
        if(StringUtils.isEmpty(errorStr)){
            return  new ExectueResult();
        }else{
            return ExectueResult.createError(errorStr);
        }

    }
}
