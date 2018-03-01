package com.tomorrow.common.monitorcommon.Entity.Auth;

import com.tomorrow.common.monitorcommon.Entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 资源
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
public class RecourseEntity extends BaseEntity {

    //资源名
    @NotNull(message = "资源名不能为空")
    @Length(max = 100,message = "资源名长度不能超过100")
    private String resourceName;
    //资源路径
    @NotNull(message = "资源路径不能为空")
    @Length(max = 1000,message = "资源路径长度不能超过1000")
    private String resourceUrl;

    //排序
    private int sort;

    //是否是菜单
    private boolean isMenu;

    //是否是功能点（按钮、超链接等）
    private boolean isButton;

    //父节点
    private String parentId;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isButton() {
        return isButton;
    }

    public void setButton(boolean button) {
        isButton = button;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
