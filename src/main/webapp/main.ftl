<#include  "/WEB-INF/views/common/tags.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="/static/css/thrid_part/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/css/thrid_part/easyui/themes/icon.css">
    <script type="text/javascript" src="/static/js/third_part/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/third_part/easyui/jquery.easyui.min.js"></script>



</head>


<body class="easyui-layout">
<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
<div data-options="region:'south',title:'South Title',split:true" style="height:20px;"></div>
<div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
<div data-options="region:'west',title:'West',split:true" style="width:200px;">


    <div style="margin-bottom:10px">
        <a href="#" class="easyui-linkbutton" onclick="addTab('google','http://www.google.com')">google</a>
        <a href="#" class="easyui-linkbutton" onclick="addTab('jquery','http://jquery.com/')">jquery</a>
        <a href="#" class="easyui-linkbutton" onclick="addTab('easyui','http://jeasyui.com/')">easyui</a>
    </div>
    <ul id="mytree" class="easyui-tree" url="tree_data.json"></ul>
    <ul id="tree" class="easyui-tree">
        <li>
            <span>Folder</span>
            <ul>
                <li>
                    <span>Sub Folder 1</span>
                    <ul>
                        <li><span><a href="#">File 11</a></span></li>
                        <li><span>File 12</span></li>
                        <li><span>File 13</span></li>
                    </ul>
                </li>
                <li><span>File 2</span></li>
                <li><span>File 3</span></li>
            </ul>
        </li>
        <li><span>File21</span></li>
    </ul>

</div>
<div data-options="region:'center',title:'center title'"
     style="padding:10px;background:#eee;">

    <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
        <div title="Home">
        </div>
    </div>
</div>
</body>
<script>

    function addTab(title, url){
        if ($('#tt').tabs('exists', title)){
            $('#tt').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
            $('#tt').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    }


    var treeData = [{
        "id":1,
        "text":"My Documents",
        "children":[{
            "id":22,
            "text":"Photos",
            "state":"closed",
            "children":[{
                "id":111,
                "text":"Friend"
            },{
                "id":112,
                "text":"Wife"
            },{
                "id":113,
                "text":"Company"
            }]
        },{
            "id":12,
            "text":"Program Files",
            "children":[{
                "id":121,
                "text":"Intel"
            },{
                "id":122,
                "text":"Java",
                "attributes":{
                    "p1":"Custom Attribute1",
                    "p2":"Custom Attribute2"
                }
            },{
                "id":123,
                "text":"Microsoft Office"
            },{
                "id":124,
                "text":"Games",
                "checked":true
            }]
        },{
            "id":13,
            "text":"index.html"
        },{
            "id":14,
            "text":"about.html"
        },{
            "id":15,
            "text":"welcome.html"
        }]
    }];

    $('#mytree').tree('append', {
        parent:node.target,
        data:treeData
    });
</script>

</html>