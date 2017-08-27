package com.pigasuo.side.webapp.s2.plugin;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;


/**
 * Created by linxuhong on 2017/8/18.
 */
public class JsonpResult implements Result {

    private static final long serialVersionUID = -4927251500050860678L;
    private final static Log logger = LogFactory.getLog(JsonpResult.class);

    private String root = "jsonData";

    private boolean enableGZIP = false;

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "text/json";
    private static final String CONTENT_ENCODING = "UTF-8";

    @Override
    public void execute(ActionInvocation invocation) throws Exception {
        Object rootObject;
        if (this.root != null) {
            ValueStack stack = invocation.getStack();
            rootObject = stack.findValue(this.root);
        } else {
            rootObject = invocation.getAction();
        }

        writeToResponse(invocation, rootObject);
    }

    /**
     * @Title: createJSONString
     * @throws Exception
     */
    private static String createJSONString(Object rootObject) {
        String outJsonString = "";
        JSONObject jsonObject;

        if (rootObject == null){
            outJsonString  = "null";
        }else if (rootObject instanceof String || rootObject instanceof Number) {
            jsonObject = new JSONObject();
            jsonObject.put("value", rootObject);
            outJsonString = jsonObject.toString();

        }else{
            outJsonString = fromObjecToJsonStr(rootObject);
        }
        return outJsonString;
    }

    /**
     * @Title: fromObjecToJsonStr
     * @throws Exception
     */
    private static String fromObjecToJsonStr(Object obj){
        String outJsonStr = "";
        try {
            outJsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("global class JsonpResult error type: JsonGenerationException",e);
        } catch (JsonMappingException e) {
            logger.error("global class JsonpResult error type: JsonMappingException",e);
        } catch (IOException e) {
            logger.error("global class JsonpResult error type: IOException",e);
        }
        return outJsonStr ;
    }

    /**
     * write response
     */
    public void writeToResponse(ActionInvocation invocation, Object rootObject) throws IOException {
        String json = createJSONString(rootObject);

        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

        String callbackName = request.getParameter("callback");
        if ((callbackName != null) && (callbackName.length() > 0)){
            json = callbackName + "(" + json + ")";
        }

        writeJSONToResponse(request, response, json);
    }

    private static boolean isGzipInRequest(HttpServletRequest request) {
        String header = request.getHeader("Accept-Encoding");
        return (header != null) && (header.indexOf("gzip") >= 0);
    }

    private void writeJSONToResponse(HttpServletRequest request, HttpServletResponse response, String json) throws IOException {
        boolean writeGzip = enableGZIP && isGzipInRequest(request);
        boolean isNoCache = true;

        response.setContentType(CONTENT_TYPE + ";charset=" + CONTENT_ENCODING);
        if(isNoCache&&!response.containsHeader("Cache-Control")){
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
        }


        if (writeGzip) {
            response.addHeader("Content-Encoding", "gzip");
            GZIPOutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(json.getBytes());
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                if (in != null){
                    in.close();
                }
                if (out != null) {
                    out.finish();
                    out.close();
                }
            }

        } else {
            byte[] binData = json.getBytes(CONTENT_ENCODING);
            response.setContentLength(binData.length);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                out.write(binData);
                out.flush();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }


    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public boolean isEnableGZIP() {
        return enableGZIP;
    }

    public void setEnableGZIP(boolean enableGZIP) {
        this.enableGZIP = enableGZIP;
    }

}