package com.tomorrow.common.monitorcommon.Enum.impl;

import com.tomorrow.common.monitorcommon.Enum.IEnum;

/**
 * 性别枚举
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-14
 **/
public enum SexEnum implements IEnum {
	MAN   (1,"男"),
	WOMAN (2,"女");

	private Integer key;
	private String text;

	private  SexEnum(Integer key,String text){
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
