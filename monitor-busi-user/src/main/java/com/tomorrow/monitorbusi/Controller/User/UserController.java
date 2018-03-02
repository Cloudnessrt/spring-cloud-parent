package com.tomorrow.monitorbusi.Controller.User;

import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import com.tomorrow.common.monitorcommon.VO.PageInfo;
import com.tomorrow.monitorbusi.Service.User.IUserEntityService;
import com.tomorrow.monitorbusi.VO.Auth.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

import static com.tomorrow.common.monitorcommon.Function.CommonUtil.controllerCheck;

/**
 * 测试
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
@Controller
public class UserController {
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
    public void getEntity(@PathVariable(value="id",required = false)String id, Map<String,Object> map){
        if(!StringUtils.isEmpty(id)){
           UserEntity use= userEntityService.getOne(id);
           map.put("userEntity",use);
        }
    }

    /**
     * 获取数据列表
     * @param entity
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUsers(UserVo entity, PageInfo pageInfo,Map<String, Object> map) {
        PageInfo<UserEntity> users=userEntityService.getPage(pageInfo,entity);
        map.put("page",users);
        return "users/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Map<String, Object> map) {
        map.put("entity",new UserEntity());
        map.put("actionUrl", "/users");
        map.put("method","POST");
        return "users/userAdd";
    }

    /**
     * 添加
     * @param entity 实体
     * @return 结果
     */
    @RequestMapping(value="/users",method=RequestMethod.POST)
    @ResponseBody
    public Object add(@Valid UserEntity entity, BindingResult result){
        ExectueResult exectueResult=controllerCheck(result);
        if(exectueResult.isResult()){
            exectueResult=userEntityService.insertEntity(entity);
        }
        return exectueResult;
    }

    /**
     * 获取数据详细
     * @return 路由
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public Object getUser(@PathVariable String id) {
        UserEntity users=userEntityService.getOne(id);
        return "user";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,UserEntity userEntity,Map<String, Object> map) {
        map.put("entity", userEntity);
        map.put("actionUrl", "/users/"+userEntity.getId());
        map.put("method","PUT");
        return "users/userAdd";
    }

    /**
     * 更新
     * @param userEntity 实体
     * @return 结果
     */
    @RequestMapping(value="/users/{id}",method=RequestMethod.PUT )
    @ResponseBody
    public Object update(UserEntity userEntity, MultipartFile multipartFile, BindingResult result){
        ExectueResult exectueResult=controllerCheck(result);
        if(exectueResult.isResult()){
            exectueResult=userEntityService.updateEntity(userEntity);
        }
        return exectueResult;
    }

    /**
     * 删除员工信息
     * @param userEntity 删除实体
     * @return
     */
    @RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Object delete(UserEntity userEntity){
        ExectueResult exectueResult=userEntityService.deleteEntity(userEntity);
        return exectueResult;
    }
}
