package com.pigasuo.side.webapp.s2.action;


import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URLDecoder;

/**
 * Created by linxuhong on 2017/8/18.
 */
public abstract class StrutsActionAdapter
        extends ActionSupport implements MessageSourceAware, Serializable {

    public static String GO_BACK = "goBack";
    public static String RESULT_MESSAGE = "resultMessage";
    public static String BASE_ERROR = "baseError";
    public static String BACKEND_LOGIN = "backendLogin";

    protected final Logger log = LoggerFactory.getLogger(StrutsActionAdapter.class);

    protected String backUrl="";//璺宠浆鐩爣url

    protected String resultMessage;//鎿嶄綔鎻愮ず娑堟伅

    protected Boolean isEdit = false;

    protected Boolean isSave =false;

    protected Boolean isCopy = false;

    public String baseList;

    public static final String CONTENT_TYPE = "text/html;charset=utf-8";

    private MessageSource messageSource;

    public static final String UTF8 = "UTF-8";

    protected String serverIp = "";

    //鎿嶄綔鏍囧織锛屾瘮濡傛柊澧炪�鍒犻櫎銆佹煡璇㈢瓑锛屽彲鑷畾涔夊�
    protected Integer actionFlag;

    protected String password;





    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getBackUrl() {
        if(backUrl != null){
            try {
                //			return URLEncoder.encode(backUrl, "UTF8").replaceAll("%2F","/").replaceAll("%3D", "=").replaceAll("%3F","?").replaceAll("%26","&");
                return URLDecoder.decode(backUrl.trim(),"UTF-8");
            } catch(Exception e) {
                log.error("StrutsActionAdapter.getBackUrl ERROR;",e);
            }
        }
        return backUrl;
    }

    public void ajaxPrintPage(Object obj) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding(UTF8);
        PrintWriter writer = null;
        try {
            try {
                writer = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("obj", obj);
                writer.print(obj.toString());
            } catch (IOException e) {

            }
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }


    public void ajaxPrintPageForGrid(Object obj) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding(UTF8);
        PrintWriter writer = null;
        try {
            try {
                writer = response.getWriter();
                //JSONObject json = new JSONObject();
                //json.put("obj", obj);
                writer.print(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     *
     * @author hyq
     * @param content
     */
    public void returnMessageByJavascript(String content) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType( "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            StringBuffer info = new StringBuffer();
            info.append("<script language='javascript'>");
            info.append("alert('"+content+"');");
            info.append("window.opener=null;");
            info.append("window.open('','_self','');");
            info.append("window.close();");
            info.append("</script>");
            os.write(info.toString().getBytes());
            os.flush();
        } catch (IOException e) {
            log.error("returnMessageByJavascript error;content is " + content, e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBackUrlValue() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Boolean getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(Boolean isCopy) {
        this.isCopy = isCopy;
    }

    public Boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(Boolean isSave) {
        this.isSave = isSave;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getBaseList() {
        return baseList;
    }

    public void setBaseList(String baseList) {
        this.baseList = baseList;
    }

    public Integer getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(Integer actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getServerIp() {
        return serverIp;
    }
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

