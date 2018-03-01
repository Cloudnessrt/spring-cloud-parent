package com.tomorrow.common.monitorcommon.Enum.impl;
import com.tomorrow.common.monitorcommon.Enum.IEnum;
/**
 * 有效性枚举
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-14
 **/
public enum ValidEnum implements IEnum {
    True    (1,"是"),
    False   (2,"否");

    private Integer key;
    private String text;

    private  ValidEnum(Integer key,String text){
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
