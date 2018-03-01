package com.tomorrow.common.monitorcommon.Enum.impl;

import com.tomorrow.common.monitorcommon.Enum.IEnum;

/**
 * 模式
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public enum ModeEnum implements IEnum {
    createDb   (1,"DB"),
    query (2,"query");

    private Integer key;
    private String text;

    private  ModeEnum(Integer key,String text){
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
