<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员管理系统</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5.2/themes/icon.css" />
<script type="text/javascript" src="/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<div>
    <table class="easyui-datagrid" id="userList" title="会员列表" 
	       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'post',pageSize:5,toolbar:toolbar,pageList:[2,5,10]">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'id',width:60">ID</th>
	            <th data-options="field:'userName',width:200">用户名</th>
	            <th data-options="field:'name',width:100">姓名</th>
	            <th data-options="field:'age',width:100">年龄</th>
	            <th data-options="field:'sex',width:80,align:'right',formatter:formatSet">性别</th>
	            <th data-options="field:'birthday',width:80,align:'right',formatter:formatBirthday">出生日期</th>
	            <th data-options="field:'created',width:130,align:'center',formatter:formatDate">创建日期</th>
	            <th data-options="field:'updated',width:130,align:'center',formatter:formatDate">更新日期</th>
	        </tr>
	    </thead>
	</table>
	</div>
<div id="userAdd" class="easyui-window" title="新增会员" data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,href:'/user/page/user-add'" style="width:600px;height:400px;padding:10px;">
        
</div>
<div id="userEdit" class="easyui-window" title="编辑会员" data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false" style="width:600px;height:400px;padding:10px;">
	<form id="editContent" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td>
		          	<input type="hidden" id="editId" name="id"/> 
	            	<input class="easyui-textbox" type="text" id="editUserName" name="userName" data-options="required:true" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type="password" id="editPassword" name="password" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>姓名:</td>
	            <td><input class="easyui-textbox" name="name" id="editName" data-options="validType:'length[0,150]',required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>年龄:</td>
	            <td><input class="easyui-numberbox" type="text" id="editAge" name="age" data-options="min:1,max:100,precision:0,required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>性别:</td>
	            <td>
	            	<input class="easyui-radio" type="radio" name="sex" value="1" checked="checked"/> 男
	            	<input class="easyui-radio" type="radio" name="sex" value="2"/> 女
	            </td>
	        </tr>
	        <tr>
	            <td>出生日期:</td>
	            <td>
	                <input class="easyui-datebox" id="editBirthday" type="text" name="birthday" data-options="required:true" />
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>  
</div>
<script type="text/javascript">
function formatDate(val,row){
	var now = new Date(val);
	return now.format("yyyy-MM-dd hh:mm:ss");
}
function formatBirthday(val,row){
	var now = new Date(val);
	return now.format("yyyy-MM-dd");
}
function formatSet(val,row){
	if(val == 1){
		return "男";
	}else if(val == 2){
		return "女";
	}else{
		return "未知";
	}
}
function getSelectionsIds(){
	var userList = $("#userList");
	var sels = userList.datagrid("getSelections");
	var ids = [];
	for(var i in sels){
		ids.push(sels[i].id);
	}
	ids = ids.join(",");
	return ids;
}
function getRowDate(){
	var userList = $("#userList");
	var sels = userList.datagrid("getSelections");
	return sels[0];
}
var toolbar = [{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
    	$('#userAdd').window('open');
    }
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = getSelectionsIds();
    	if(ids.length == 0 || ids.length >1){
    		$.messager.alert('提示','请选择一个用户!');
    		return ;
    	}
    	
    	var data = getRowDate();
    	console.log(data);
    	$('#userEdit').window('open');
		debugger;
    	$("#editId").val(data.id);
    	$("#editUserName").textbox("setValue",data.userName);
    	$("#editName").textbox("setValue",data.name);
    	$("#editAge").numberbox("setValue",data.age);
    	$("input[name='sex'][value='"+data.sex+"']").attr("checked", true);
    	$("#editBirthday").datebox("setValue",new Date(data.birthday).format("yyyy-MM-dd"));
    }
},{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
    	var ids = getSelectionsIds();
    	console.log(ids);
    	if(ids.length == 0){
    		$.messager.alert('提示','未选中用户!');
    		return ;
    	}
    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的会员吗？',function(r){
    	    if (r){
            	$.post("/user/delete",{'ids':ids}, function(data){
        			if(data.status == 1){
        				$.messager.alert('提示',data.msg,undefined,function(){
        					$("#userList").datagrid("reload");
        				});
        			}else{
        				$.messager.alert('提示',data.msg);
        			}
        		});
    	    }
    	});
    }
},'-',{
    text:'导出',
    iconCls:'icon-remove',
    handler:function(){
    	var optins = $("#userList").datagrid("getPager").data("pagination").options;
    	var page = optins.pageNumber;
    	var rows = optins.pageSize;
    	var $form = $("<form></form>");
    	$(document.body).append($form);
    	$form.attr({
    		"action":"/user/export/excel",
    		"method":"POST"
    	}).append("<input type='hidden' name='page' value='"+page+"'/>")
    	.append("<input type='hidden' name='rows' value='"+rows+"'/>").submit();
    }
}];

var checkSubmit = false;
function submitForm(){
	if(checkSubmit){
		return;
	}
	if(!$('#editContent').form('validate')){
		$.messager.alert('提示','表单还未填写完成!');
		return ;
	}
	$.post("/user/edit",$("#editContent").serialize(), function(data){
		if(data.status == 1){
			$.messager.alert('提示',data.msg);
			$('#userEdit').window('close');
			$("#userList").datagrid("reload");
			clearForm();
		}else{
			$.messager.alert('提示',data.msg);
		}
	});
	checkSubmit = true;
}
function clearForm(){
	$('#editContent').form('reset');
}

</script>
</body>
</html>