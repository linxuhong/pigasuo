# 前台应用公用 后台管理

## 1 项目结构  (springmvc struts均支持)

1. src
    1. main
    1. java
         1. com.yhd.detail
             1. domain   ---领域对象,用于db--JavaBean映射
             1. persistence --mybatis dao接口层
             1. service /service/imp  -- service 层
             1. webapp
                 1. controller    
                     *配置 spring mvc controller 对象  .mvc请求
                 1. s2/action    
                     *配置 struts2  action对象          .do请求
             
     1. resources
         1.   resources\com\yhd\detail\   
              *配置persistence对应的mapper,即mybatis sql xml文件
              *Mapper.java类中的方法名，为mapp.xml中的sql xml节点id名相对应
              参照AccountMapper.xml.AccountMapper.java
         
## 2 视图层（ftl, JSP）
  1. resources  首先找 /web-inf/views/xx.ftl ,如果没有再找  /web-inf/pages/xx.jsp     

## 3 EasyUi还未编纂 
  >后续再加

 


