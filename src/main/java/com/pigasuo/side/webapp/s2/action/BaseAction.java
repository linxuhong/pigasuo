package com.pigasuo.side.webapp.s2.action;

import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linxuhong on 2017/8/18.
 */

public class BaseAction extends StrutsActionAdapter {
    private static final long serialVersionUID = -5962519873199802327L;


    protected Map<String,Object> jsonData ;
    protected static final String MSG_NOLOGIN = "nologin";
    protected static final String MSG_SUCCESS = "success";
    protected static final String MSG_FAILURE = "failure";


    protected void generateJsonpResult(int code, String message, Object data) {
        jsonData = new HashMap<String,Object>();
        jsonData.put("code", code);
        jsonData.put("message", message);
        jsonData.put("data", data);
    }






    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
