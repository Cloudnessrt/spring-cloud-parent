package com.tomorrow.common.monitorcommon.Entity;

import com.tomorrow.common.monitorcommon.Enum.impl.SexEnum;
import com.tomorrow.common.monitorcommon.Enum.impl.UseEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


/**
 * 用户
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
public class UserEntity extends BaseEntity {
    //账户
    @NotEmpty(message = "账户不能为空")
    @Length(min = 2,max = 10,message = "账户最多输入10位最小2位")
    private String account;
    //md5
    @NotEmpty(message = "密码不能为空")
    private String MD5;
    //用户名称
    @NotEmpty(message = "名称不能为空")
    @Length(min = 1,max = 20,message = "名称不能小于1位字符不能超过20位字符")
    private String name;
    //证件号
    @Length(max = 20,message = "证件号不能超过20位字符")
    private String creditNum;
    //性别
    private SexEnum sexEnum;
    //性别的描述列 虚拟的
    private String sexEnumName;

    //生日
    private Date birthday;
    //公司号
    @NotEmpty(message = "公司不能为空")
    @Length(max = 32,message = "公司最大32位")
    private String companyId;

    //使用状态
    private UseEnum useEnum;
    //使用状态的描述列 虚拟的
    private String useEnumName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(String creditNum) {
        this.creditNum = creditNum;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public UseEnum getUseEnum() {
        return useEnum;
    }

    public void setUseEnum(UseEnum useEnum) {
        this.useEnum = useEnum;
    }

    public UserEntity() {
        super();
    }

    public String getSexEnumName() {
        if(sexEnum!=null){
            return sexEnum.getText();
        }
        return null;
    }

    public void setSexEnumName(String sexEnumName) {

    }

    public String getUseEnumName() {
        if(useEnum!=null){
            return useEnum.getText();
        }
        return null;
    }

    public void setUseEnumName(String useEnumName) {

    }
}
