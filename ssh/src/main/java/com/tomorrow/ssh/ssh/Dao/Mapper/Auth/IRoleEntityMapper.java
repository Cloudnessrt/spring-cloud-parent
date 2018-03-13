package com.tomorrow.ssh.ssh.Dao.Mapper.Auth;

import com.github.pagehelper.Page;
import com.tomorrow.common.monitorcommon.Entity.Auth.RoleEntity;
import com.tomorrow.ssh.ssh.VO.Auth.RoleVo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public interface IRoleEntityMapper {

    /**
     * 信息分页
     * @param entity 查询实体
     * @return
     */
    @SelectProvider(type = RoleEntityMapperProvide.class,method ="getList")
     Page<RoleEntity> getPage(RoleVo entity);

    /**
     * 信息列表
     * @param entity 查询实体
     * @return
     */
    @SelectProvider(type = RoleEntityMapperProvide.class,method ="getList")
     List<RoleEntity> getList(RoleVo entity);

    /**
     * 查询单条数据
     * @param id 查询ID
     * @return
     */
    @Select("SELECT * FROM roleEntity WHERE id = #{id}")
     RoleEntity getOne(String id);

    /**
     * 更新
     * @param entity 实体
     */
    @UpdateProvider(type=RoleEntityMapperProvide.class,method = "updateEntity")
     void updateEntity(RoleEntity entity);

    /**
     * 新增
     * @param entity 实体
     */
    @InsertProvider(type = RoleEntityMapperProvide.class,method = "insertEntity")
     void insertEntity(RoleEntity entity);

    /**
     * 查询符合该条件的实体的个数
     * @param entity 实体
     * @return map
     */
    @SelectProvider(type = RoleEntityMapperProvide.class,method ="judgeDataValid")
     Map judgeDataValid(RoleEntity entity);

}
