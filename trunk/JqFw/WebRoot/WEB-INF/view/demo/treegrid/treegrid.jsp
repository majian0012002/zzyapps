<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/demo/treegrid/treegrid.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body>
		<h2>TreeGrid</h2>
		<div class="demo-info" style="margin-bottom:10px">
			<div class="demo-tip icon-tip"></div>
			<div>Show summary information on treegrid footer.</div>
		</div>
		
		<table id="test1"></table>
		
		<table id="test2"></table>
		
		<table id="test3"></table>
		
	</body>

</html>