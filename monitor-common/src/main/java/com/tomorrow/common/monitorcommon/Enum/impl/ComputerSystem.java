package com.tomorrow.common.monitorcommon.Enum.impl;

import com.tomorrow.common.monitorcommon.Enum.IEnum;

/**
 * 操作系统枚举
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-16
 **/
public enum ComputerSystem implements IEnum {

    windows(1,"windows系统"),
    Linux(2,"Linux系统");

    private Integer key;
    private String text;

    private ComputerSystem(Integer key,String text){
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
