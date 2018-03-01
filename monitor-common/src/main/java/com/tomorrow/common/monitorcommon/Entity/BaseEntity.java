package com.tomorrow.common.monitorcommon.Entity;

import com.tomorrow.common.monitorcommon.Enum.impl.ValidEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 实体基类
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-14
 **/
public class BaseEntity implements Serializable {
    private static final long serivalVersionUID=1L;

    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSSZ")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSSZ")
    private Date lastChangeDate;
    private String createUserName;
    private String createUser;
    private String lastChangeUserName;
    private String lastChangeUser;
    private ValidEnum isValid;

    /**
     * 构造函数 生成主键
     */
    public BaseEntity() {
            this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 更新实体操作时对于基本信息的维护
     * @param user 操作人
     */
    public void updateBase(UserEntity user){
        lastChangeDate=new Date();
        lastChangeUser=user.getId();
        lastChangeUserName=user.getName();
    }

    /**
     * 新增实体操作时对于基本信息的维护
     * @param user 操作人
     */
    public void insertBase(UserEntity user){
        isValid=ValidEnum.True;//新增默认有效数据
        createDate=new Date();
        createUser=user.getId();
        createUserName=user.getName();
        lastChangeDate=new Date();
        lastChangeUser=user.getId();
        lastChangeUserName=user.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getLastChangeUser() {
        return lastChangeUser;
    }

    public void setLastChangeUser(String lastChangeUser) {
        this.lastChangeUser = lastChangeUser;
    }

    public ValidEnum isValid() {
        return isValid;
    }

    public void setValid(ValidEnum valid) {
        isValid = valid;
    }
}
