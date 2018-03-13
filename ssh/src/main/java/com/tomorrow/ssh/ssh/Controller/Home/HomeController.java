package com.tomorrow.ssh.ssh.Controller.Home;

import com.tomorrow.ssh.ssh.Service.User.IUserEntityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 测试
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-26
 **/
@Controller
public class HomeController {
    @Autowired
    private IUserEntityService userEntityService;
    //日志
    private static Logger logger= LoggerFactory.getLogger(HomeController.class);

    /**
     * 获取数据列表
     * @return
     */
    @ApiOperation(value="首页", notes="首页")
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "/index";
    }

    @ApiOperation(value="首页", notes="首页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "专栏", required = true, dataType = "String"),
    })
    @RequestMapping("/home/{name}")
    public String test1(@PathVariable String name, Model model){
        model.addAttribute("name",name);
        return "/detail";
    }
}
