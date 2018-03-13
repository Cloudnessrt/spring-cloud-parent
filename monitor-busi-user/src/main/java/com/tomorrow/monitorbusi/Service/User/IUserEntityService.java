package com.tomorrow.monitorbusi.Service.User;


import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import com.tomorrow.common.monitorcommon.VO.PageInfo;
import com.tomorrow.monitorbusi.VO.Auth.UserVo;

import java.util.List;

/**
 * 用户
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
public interface IUserEntityService {

    /**
     * 信息分页
     * @param pageInfo 分页信息
     * @param userEntity 查询实体
     * @return
     */

    public PageInfo<UserEntity> getPage(PageInfo pageInfo, UserVo userEntity);

    /**
     * 根据实体查询用户信息列表
     * @param entity 用户信息
     * @return
     */
    public List<UserEntity> getList(UserVo entity);

    /**
     * 查询单条数据
     * @param id 查询ID
     * @return
     */
    public UserEntity getOne(String id);

    /**
     * 删除
     * @param entity 删除实体
     * @return
     */
    public ExectueResult deleteEntity(UserEntity entity);

    /**
     * 更新
     * @param entity 实体
     * @return
     */
    public ExectueResult updateEntity(UserEntity entity);

    /**
     * 新增
     * @param entity 实体
     * @return
     */
    public ExectueResult insertEntity(UserEntity entity);

    //最简单的秒杀方法，参数是用户ID和商品ID。可能有多个线程争抢一个商品，所以商品ID加上LockedObject注解

    public void secKill(String userID,Long commidityID);
}
