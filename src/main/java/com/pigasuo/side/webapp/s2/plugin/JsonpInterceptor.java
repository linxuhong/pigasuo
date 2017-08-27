package com.pigasuo.side.webapp.s2.plugin;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by linxuhong on 2017/8/18.
 */

public class JsonpInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 4664085834873733483L;
    private static final Log LOG = LogFactory.getLog(JsonpInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            String result = invocation.invokeActionOnly();
            if (Action.NONE.equals(result)) {
                JsonpResult jsonpResult = new JsonpResult();
                jsonpResult.execute(invocation);
            }
        } catch (Exception ex) {
            LOG.error("Action execute failed.", ex);
            ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            HashMap<String,String> resultObject = new HashMap<String,String> ();
            resultObject.put("ERROR", ex.getClass().getName() +": "+ ex.getMessage());

            new JsonpResult().writeToResponse(invocation, resultObject);
        }
        return Action.NONE;
    }

}
