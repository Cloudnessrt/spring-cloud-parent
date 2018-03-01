package com.tomorrow.common.monitorcommon.Function;

import com.tomorrow.common.monitorcommon.VO.ExectueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 公共方法
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-18
 **/
public class CommonUtil {

    private static Logger logger= LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 后台对于参数的检验
     * @param result
     * @return
     */
    public  static ExectueResult controllerCheck(BindingResult result){
            if(result!=null && result.hasErrors()){
                for (ObjectError error:result.getAllErrors()){
                    return ExectueResult.createError(error.getDefaultMessage());
                }
            }
            return  new ExectueResult();
        }
}
