<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
	</head>

	<!-- 内容区 -->
	<body>
		<table>
			<tr>
				<td>
					<div class="easyui-panel" title = 'Tree、TreeGrid组件：'  style="height:200px;width:400px;">
						<br/>
						<a href="/JqFw/demo/pojo/treegrid/initTree" class="easyui-linkbutton" iconCls="icon-add">树操作</a>
						
						<br/>
						<a href="/JqFw/demo/pojo/treegrid/init" class="easyui-linkbutton" iconCls="icon-add">基于hibernate的CRUD</a>
					</div>
				</td>
				
				<td>
					<div class="easyui-panel" title = 'DataGrid组件：'  style="height:200px;width:400px;">
						<br/>
						<a href="/JqFw/demo/pojo/datagrid/init" class="easyui-linkbutton" iconCls="icon-add">基于hibernate的CRUD</a>
					</div>				
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-panel" title = 'FORM组件：'  style="height:200px;width:400px;">
						<br/>
						<a href="/JqFw/demo/combo/init" class="easyui-linkbutton" iconCls="icon-add">下拉框信息</a>
					</div>				
				</td>
			</tr>
		</table>
		
	</body>

</html>