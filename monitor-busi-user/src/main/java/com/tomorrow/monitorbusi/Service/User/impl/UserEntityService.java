package com.tomorrow.monitorbusi.Service.User.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.ValidEnum;
import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import com.tomorrow.common.monitorcommon.VO.PageInfo;
import com.tomorrow.monitorbusi.Dao.Mapper.User.IUserMapper;
import com.tomorrow.monitorbusi.Service.User.IUserEntityService;
import com.tomorrow.monitorbusi.VO.Auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
@Service
public class UserEntityService implements IUserEntityService {

    @Autowired
    private IUserMapper iuserMapper;

    /**
     * 信息分页
     * @param pageInfo 分页信息
     * @param userEntity 查询实体
     * @return 分页
     */
    public PageInfo<UserEntity> getPage(PageInfo pageInfo, UserVo userEntity){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<UserEntity> pageData =iuserMapper.getPage(userEntity);
        PageInfo<UserEntity> page = new PageInfo<>(pageData);
        return page;
    }

    /**
     * 根据实体查询用户信息列表
     * @param entity 用户信息
     * @return 列表
     */
    public List<UserEntity> getList(UserVo entity){
        return iuserMapper.getList(entity);
    }

    /**
     * 查询单条数据
     * @param id 查询ID
     * @return 实体
     */
    public UserEntity getOne(String id){
        return iuserMapper.getOne(id);
    }

    /**
     * 更新
     * @param entity 实体
     * @return 反馈
     */
    public ExectueResult updateEntity( UserEntity entity){
        ExectueResult result=judgeDataValid(entity);//唯一性判断
        if(result.isResult()){
            iuserMapper.updateEntity(entity);
            result.setObj(entity);
        }
        return result;
    }

    /**
     * 删除
     * @param entity 删除实体
     * @return 反馈
     */
    public ExectueResult deleteEntity(UserEntity entity){
        entity.setValid(ValidEnum.False);
        return updateEntity(entity);
    }

    /**
     * 新增
     * @param entity 实体
     * @return 反馈
     */
    public ExectueResult insertEntity(UserEntity entity){
        ExectueResult result=judgeDataValid(entity);//唯一性判断
        if(result.isResult()){
            iuserMapper.insertEntity(entity);
            result.setObj(entity);
        }
        return result;
    }

    /**
     * 唯一性鉴别
     * @param entity 实体
     * @return 反馈
     */
    private ExectueResult judgeDataValid(UserEntity entity){
        Map<String,Long> map=iuserMapper.judgeDataValid(entity);
        if(map==null){
            return new ExectueResult();
        }
        String errorStr=null;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if(entry.getValue()!=null && entry.getValue() >0L){
                switch (entry.getKey()){
                    case "account": errorStr="账户名重复";break;
                    case "creditNum": errorStr="员工编号重复";break;
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
