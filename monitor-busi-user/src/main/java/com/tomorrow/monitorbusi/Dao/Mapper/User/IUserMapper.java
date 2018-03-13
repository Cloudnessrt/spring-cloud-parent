package com.tomorrow.monitorbusi.Dao.Mapper.User;

import com.github.pagehelper.Page;
import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.monitorbusi.VO.Auth.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 用户crud
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
public interface IUserMapper {

    /**
     * 信息分页
     * @param entity 用户信息
     * @return
     */
    @SelectProvider(type = UserMapperProvide.class,method ="getList")
    public Page<UserEntity> getPage(UserVo entity);

    /**
     * 信息列表
     * @param entity 用户信息
     * @return
     */
    @SelectProvider(type = UserMapperProvide.class,method ="getList")
    public List<UserEntity> getList(UserVo entity);

    /**
     * 查询单条数据
     * @param id 查询ID
     * @return
     */
    @Select("SELECT * FROM users WHERE id = #{id}")
    public UserEntity getOne(String id);

    /**
     * 更新
     * @param entity 实体
     * @return
     */
    @Update(" update users set account=#{account},MD5=#{MD5},name=#{name},creditNum=#{creditNum},sexEnum=#{sexEnum},birthday=#{birthday},isValid=#{isValid}," +
            "companyId=#{companyId},createDate=#{createDate},createUser=#{createUser},lastChangeDate=#{lastChangeDate},lastChangeUser=#{lastChangeUser}," +
            "lastChangeUserName=#{lastChangeUserName} where id=#{id} ")
    public void updateEntity(UserEntity entity);

    /**
     * 新增
     * @param entity 实体
     * @return
     */
    @Insert(" INSERT INTO `users` (`id`, `account`, `MD5`, `name`, `creditNum`, `sexEnum`, `birthday`, `companyId`, `createDate`, `createUser`, `createUserName`, `lastChangeDate`, `lastChangeUser`, `lastChangeUserName`,isValid) " +
            "VALUES (#{id},#{account}, #{MD5}, #{name},#{creditNum},#{sexEnum},#{birthday},#{companyId},#{createDate},#{createUser},#{createUserName},#{lastChangeDate},#{lastChangeUser},#{lastChangeUserName},#{isValid})")
    public void insertEntity(UserEntity entity);

    /**
     * 查询符合该条件的实体的个数
     * @param entity 实体
     * @return
     */
    @SelectProvider(type = UserMapperProvide.class,method ="judgeDataValid")
    public Map judgeDataValid(UserEntity entity);
}
