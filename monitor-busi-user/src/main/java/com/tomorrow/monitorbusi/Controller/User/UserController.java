package com.tomorrow.monitorbusi.Controller.User;

import com.tomorrow.common.monitorcommon.Entity.UserEntity;
import com.tomorrow.common.monitorcommon.Enum.impl.SexEnum;
import com.tomorrow.common.monitorcommon.Function.EnumUtil;
import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import com.tomorrow.common.monitorcommon.VO.PageInfo;
import com.tomorrow.monitorbusi.Service.User.IUserEntityService;
import com.tomorrow.monitorbusi.VO.Auth.UserVo;
import com.tomorrow.monitorbusi.VO.test;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

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

    private void addModelAttribute(ModelAndView modelAndView){
        modelAndView.addObject("sexEnum",EnumUtil.enumToMap(SexEnum.MAN));
    }

    /**
     * 获取数据列表
     * @param entity
     * @return
     */
    @ApiOperation(value="用户列表页", notes="查询列表页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "查询实体", required = true, dataType = "UserVo"),
            @ApiImplicitParam(name = "pageInfo", value = "分页信息", required = true, dataType = "PageInfo")
    })
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUsers(UserVo entity, PageInfo pageInfo,Map<String, Object> map) throws Exception {
        try {
            PageInfo<UserEntity> users = userEntityService.getPage(pageInfo, entity);
            map.put("page", users);
            map.put("sexEnum", EnumUtil.enumToMap(SexEnum.MAN));
        }catch (Exception e){
            throw new Exception("发生错误");
        }
        return "users/list";
    }

    @ApiOperation(value="用户新增页", notes="新增页")
    @RequestMapping(value = "/users/handle",method = RequestMethod.GET)
    public ModelAndView toAdd(Map<String, Object> map) {

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("users/userAdd");
        modelAndView.addObject("entity",new UserEntity());
        modelAndView.addObject("actionUrl", "/users");
        modelAndView.addObject("method","POST");
        addModelAttribute(modelAndView);
        return modelAndView;
    }

    /**
     * 添加
     * @param entity 实体
     * @return 结果
     */
    @ApiOperation(value="用户新增", notes="新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "新增实体", required = true, dataType = "UserEntity")
    })
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
    @ApiOperation(value="用户详情页", notes="详情页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public Object getUser(@PathVariable String id) {
        UserEntity users=userEntityService.getOne(id);
        return "user";
    }


    @ApiOperation(value="用户修改页", notes="修改页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改实体ID", required = true, dataType = "String")
    })
    @RequestMapping(value = "/users/handle/{id}",method = RequestMethod.GET)
    public ModelAndView toEdit(Model model,UserEntity userEntity,Map<String, Object> map) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("users/userAdd");
        modelAndView.addObject("entity",userEntity);
        modelAndView.addObject("actionUrl", "/users/"+userEntity.getId());
        modelAndView.addObject("method","PUT");
        addModelAttribute(modelAndView);
        return modelAndView;
    }

    /**
     * 更新
     * @param userEntity 实体
     * @return 结果
     */
    @ApiOperation(value="用户修改", notes="修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "修改实体id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userEntity", value = "修改实体", required = true, dataType = "UserEntity")
    })
    @RequestMapping(value="/users/{id}",method=RequestMethod.PUT )
    @ResponseBody
    public Object update(UserEntity userEntity, BindingResult result){
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
    @ApiOperation(value="用户删除", notes="删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "删除实体ID", required = true, dataType = "String")
    })
    @RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Object delete(UserEntity userEntity){
        ExectueResult exectueResult=userEntityService.deleteEntity(userEntity);
        return exectueResult;
    }

    @RequestMapping(value="/testSecKill",method=RequestMethod.GET)
    @ResponseBody
    public Object testSecKill() {
        int threadCount = 1000;
        int splitPoint = 500;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        CountDownLatch beginCount = new CountDownLatch(1);

        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        //用动态代理的方式调用secKill方法
                        userEntityService.secKill("test1", 10000001L);
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();

        }
        //再起500个线程，秒杀第二件商品
        for (int i = splitPoint; i < threadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        //用动态代理的方式调用secKill方法
                        userEntityService.secKill("test2", 10000002L);
                        //testClass.testFunc("test", 10000001L);
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();

        }


        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
        beginCount.countDown();

        try {
            //主线程等待结束信号量
            endCount.await();
            //观察秒杀结果是否正确

            System.out.println("error count" + test.error);
            System.out.println("total cost " + (System.currentTimeMillis() - startTime));
            System.out.println("1-"+test.inventory.get(10000001L));
            System.out.println("2-"+test.inventory.get(10000002L));
            test.error=0;
            test.inventory.put(10000001L, 10000l);
            test.inventory.put(10000002L, 10000l);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
