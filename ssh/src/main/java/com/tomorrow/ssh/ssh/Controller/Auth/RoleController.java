package com.tomorrow.ssh.ssh.Controller.Auth;

import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.ssh.ssh.Controller.User.UserController;
import com.tomorrow.ssh.ssh.Service.User.IUserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 角色
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-28
 **/
@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private IUserEntityService userEntityService;

    //日志
    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    /**
     * 根据ID获取实体并且注入变动属性的值
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getEntity(@RequestParam(value="id",required = false)String id, Map<String,Object> map){
        if(!StringUtils.isEmpty(id)){
            UserEntity use= userEntityService.getOne(id);
            map.put("userEntity",use);
        }
    }
}
