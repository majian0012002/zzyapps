<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/org/org.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 菜单区 -->
		<div region="north" style="padding:5 10 5 10px;" border=false >
			<table class="easyui-tbar">
				<tr>
					<td>
						<a id="search" href="#" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
						<a id="add" href="#" class="easyui-linkbutton"  iconCls="icon-add">新增</a>
						<a id="update" href="#" class="easyui-linkbutton"  iconCls="icon-update">修改</a>
						<a id="remove" href="#" class="easyui-linkbutton"  iconCls="icon-delete">删除</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- 内容区 -->
		<div region="center" style="padding:5 10 5 10px;"  border=false>
			<table id="orgTable" class="easyui-treegrid"
				idField="id"
				treeField="name"
				fitColumns="true"
				fit=true
				url="sys/org"
				>
				<!--				root="{id:0, name:'芜湖卷烟厂', state:'closed', expand:true}"-->
				<thead>
					<tr>
						<th field="name" width="100">名称</th>
						<th field="code" width="80">编号</th>
						<th field="fullName" width="120">全称</th>
						<th field="smOrgType" width="60" formatter="Fmt.showFun('typeName')">类型</th>
						<th field="status" width="50" formatter="Fmt.status">状态</th>
						<th field="remark" width="150">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- form表单 -->
		<div id="formWin" class="easyui-window" 
			closed="true" 
			modal="true" 
			title="维护表单" 
			resizable=false
			style="width:550px;height:300px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" style="padding:10px;">
					<!-- 表单 -->
					<form id="orgForm">
						<table class="form-content" height="185">
							<tr>
								<!-- 机构id -->
								<td colspan="4"><input type="hidden" name="id"/></td>
							</tr>
							<tr>
								<td width="80">机构编号：</td>
								<td><input class="easyui-validatebox" type="text" name="code"  validType="length[1,32]" required="true"/></td>
								<td width="95">父机构名称：</td>	
								<td><input name="parentName"  type="text" disabled="true"/> </td>
							</tr>
							<tr>
								<td>机构名称：</td>
								<td><input class="easyui-validatebox" type="text"  name="name" validType="length[1,32]" required="true"/></td>
								<td>机构全称：</td>
								<td><input name="fullName"  class="easyui-validatebox" type="text" validType="length[1,100]" required="true"/> </td>
							</tr>
							<tr>
								<td>机构类型：</td>
								<td>
									<select class="easyui-combobox"
										id="smOrgTypeCombobox" 
										name="smOrgType.orgTypeId"
										list="sys/orgType/list4ValidData"
										valueField="orgTypeId"
										textField="typeName" 
										required="true">
									</select>		
								 </td>
							
								<td>使用状态：</td>
								<td>
									<input name="status" value=1 type="radio" checked=true/><span class="blue">有效</span>
									<input name="status" value=0 type="radio"/><span class="red">无效</span>
								 </td>
							</tr>
							<tr>
								<td>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
								<td colspan="3">
									<textarea class="easyui-validatebox" 
										name="remark"
										validType="length[0,1000]"
										style="height:35px;width:100%">
									</textarea>
								</td>
							</tr>
						</table>
						
					</form>	
	
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
	
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" form="orgForm" id="save">保存</a>
	
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" id="cancel">取消</a>
	
				</div>

			</div>
		</div>
		
	</body>

</html>