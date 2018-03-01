package com.tomorrow.common.monitorcommon.VO;

/**
 * 错误信息反馈
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-27
 **/
public class ExectueResult {
    //错误信息
    private String errorStr;
    //执行结果
    private boolean result;
    //实体
    private Object obj;

    public ExectueResult(){
        result=true;
    }

    /**
     * 创建错误信息
     * @param errorStr 错误信息
     * @param obj 实体
     * @return 反馈
     */
    public static ExectueResult createError(String errorStr,Object obj){
        ExectueResult exectueResult=new ExectueResult();
        exectueResult.setErrorStr(errorStr);
        exectueResult.setResult(false);
        exectueResult.setObj(obj);
        return exectueResult;
    }

    /**
     * 创建错误信息
     * @param errorStr 错误信息
     * @return 反馈
     */
    public static ExectueResult createError(String errorStr){
        ExectueResult exectueResult=new ExectueResult();
        exectueResult.setErrorStr(errorStr);
        exectueResult.setResult(false);
        return exectueResult;
    }

    public String getErrorStr() {
        return errorStr;
    }

    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
