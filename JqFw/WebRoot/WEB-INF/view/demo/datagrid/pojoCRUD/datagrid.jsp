<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/demo/datagrid/pojoCRUD/datagrid.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 工具栏区 -->
		<div region="north" style="padding:5 10 5 10px;" border=false >
			<table class="easyui-tbar">
				<tr>
					<td>
						<a id="add" href="#" class="easyui-linkbutton"  iconCls="icon-add">新增</a>
						<a id="eidt" href="#" class="easyui-linkbutton"  iconCls="icon-update">修改</a>
						<a id="save" href="#" class="easyui-linkbutton"  iconCls="icon-save">保存</a>
						<a id="delete" href="#" class="easyui-linkbutton"  iconCls="icon-delete">删除</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- 内容区 -->
		<div region="center" style="padding:5 10 5 10px;"  border=false>
			<table id="datagridDIV" fit=true></table>
		</div>
		
	</body>

</html>