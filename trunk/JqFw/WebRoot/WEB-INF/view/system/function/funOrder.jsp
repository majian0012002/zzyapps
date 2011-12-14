<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/function/funOrder.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 菜单区 -->
		<div region="north" style="padding:5 10 5 10px;" border=false >
			<table class="easyui-tbar">
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<a id="showRoleBtn" href="${path}/sys/fun/" class="easyui-linkbutton"  iconCls="icon-add">返回功能维护</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- 内容区 -->
		<div region="center" style="padding:5 10 5 10px;"  border=false>
			<ul id="funTable" class="easyui-tree"
				idField="id"
				textField="name"
				fitColumns="true"
				fit=true
				dnd=true
				url="sys/fun"
				>
			</ul>
		</div>
	</body>

</html>