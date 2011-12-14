<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/demo/datagrid/datagrid.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body>
		
		<table id="datagridDIV"></table>
		
	</body>

</html>