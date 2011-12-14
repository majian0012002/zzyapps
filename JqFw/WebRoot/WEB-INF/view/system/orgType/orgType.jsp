<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/orgType/orgType.js" />"></script>
	</head>


	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 工具栏区 -->
		<div region="north" style="padding:5 10 5 10px;" border=false >
			<table class="easyui-tbar">
				<tr>
					<td>
						<a id="search" href="#" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
						<a id="add" href="#" class="easyui-linkbutton"  iconCls="icon-add">新增</a>
						<a id="update" href="#" class="easyui-linkbutton"  iconCls="icon-update">修改</a>
						<a id="remove" href="#" class="easyui-linkbutton"  iconCls="icon-remove">删除</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- 内容区 -->
		<div region="center" style="padding:5 10 5 10px;"  border=false>
			<table id="datagridDIV" class="easyui-datagrid"
				singleSelect="true"
				idField="id" 
				fit=true
				url="sys/orgType"
				>
				<thead>
					<tr>
						<th field="ck" width="40" checkbox=true></th>
						<th field="orgTypeId" width="200">类型编号</th>
						<th field="typeName" width="200">类型名称</th>
						<th field="orgLevel" width="200">机构级别</th>
						<th field="status" width="200" formatter="Formatter.status">使用状态</th>
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
			style="width:400px;height:250px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" style="padding:10px;">
					<!-- 表单 -->
					<form id="orgTypeForm">
						<table class="form-content" height="145">
							<tr>
								<td width="100">类型编号：</td>
								<td><input class="easyui-validatebox" type="text" name="orgTypeId"  validType="length[1,32]" required="true"/></td>
							</tr>
							<tr>
								<td>类型名称：</td>
								<td><input class="easyui-validatebox" type="text"  name="typeName" validType="length[1,32]" required="true"/></td>
							</tr>
							<tr>
								<td>机构级别：</td>
								<td><input name="orgLevel"  class="easyui-numberbox" type="text" min="0" max="20" required="true"/> </td>
							</tr>
							<tr>
								<td>使用状态：</td>
								<td>
									<input name="status" value=1 type="radio" checked=true/><span class="blue">有效</span>
									<input name="status" value=0 type="radio"/><span class="red">无效</span>
								 </td>
							</tr>
						</table>
						
					</form>	
	
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
	
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="save">保存</a>
	
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" id="cancel">取消</a>
	
				</div>

			</div>
		</div>
		
	</body>

</html>