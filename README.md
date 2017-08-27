<<<<<<< HEAD
#  

## 

1. src
    1. main
    1. java
         1. com.pigasuo.side
             1. domain   ---领域对象,用于db--JavaBean映射
             1. persistence --mybatis dao接口层
             1. service /service/imp  -- service 层
             1. webapp
                 1. controller    
                     *配置 spring mvc controller 对象  .mvc请求
                 1. s2/action    
                     *配置 struts2  action对象          .do请求
             
     1. resources
         1.   resources\com\pigasuo\side\   
              *配置persistence对应的mapper,即mybatis sql xml文件
              *Mapper.java类中的方法名，为mapp.xml中的sql xml节点id名相对应
              参照AccountMapper.xml.AccountMapper.java
         
## 2 视图层（ftl, JSP）
  1. resources  首先找 /web-inf/views/xx.ftl ,如果没有再找  /web-inf/pages/xx.jsp     

## 3 EasyUi还未编纂 
  >后续再加

 


=======
# pigasuo
>>>>>>> 91d0f6af190d8e8e0f0a0722c89cfc4fb005febf
