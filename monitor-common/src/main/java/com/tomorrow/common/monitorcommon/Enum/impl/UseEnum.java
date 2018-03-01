package com.tomorrow.common.monitorcommon.Enum.impl;

import com.tomorrow.common.monitorcommon.Enum.IEnum;

/**
 * 使用情况枚举
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public enum UseEnum implements IEnum {

    useing   (1,"使用"),
    stopuser (2,"禁用");

    private Integer key;
    private String text;

    private  UseEnum(Integer key,String text){
        this.key=key;
        this.text=text;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getText() {
        return text;
    }
}
