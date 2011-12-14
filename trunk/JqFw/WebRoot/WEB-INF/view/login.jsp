<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 校验是否已经登录，如果已经登录则直接跳转到首界面 -->	
<%
	if(session.getAttribute("USER_INFO") != null)
		request.getRequestDispatcher("/sys/home").forward(request, response);
%>
<html>

	<!-- 表头区 -->
	<head>
		<!-- JQ 框架组件 -->
		<link rel="stylesheet" type="text/css" href="<c:url value="/resource/themes/default/easyui.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resource/themes/icon.css"/>" />
		<script type="text/javascript" src="<c:url value="/resource/easyui/jquery-1.6.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resource/wide/Wide.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resource/easyui/easyloader.js" />"></script>
		
		<!-- 自定义JS -->
		<script type="text/javascript">
			if(parent && parent.window && parent.window.USER_INFO){
				parent.window.location.reload();
			}
		</script>
		<script type="text/javascript" src="<c:url value="/view/login/login.js" />"></script>
		
		
		<style type="text/css">
			<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				font-size: 12px;
				background-color: #EEF9FF;
				background-repeat: repeat-x;
				background-position: center top;
				background-image: url(<c:url value="/resource/themes/custom/login-bg2.jpg" />);
			}
			-->
		</style>
		
	</head>

	<!-- 内容区 -->
	<body >
		<table width="735" height="315" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td height="215">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="245" align="right" valign="top"
					background="<c:url value="/resource/themes/custom/login-bg.jpg" />">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="55%">
								&nbsp;
							</td>
							<td width="45%" height="215" align="left" valign="bottom">
								<br />
								<br />
								
								<!-- Form表单 -->
								<form id="loginForm" method="post">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="77" height="38" align="right">
												&nbsp;
											</td>
											<td width="223">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td width="77" height="30" align="right">
												账&nbsp;&nbsp;&nbsp;&nbsp;号：
											</td>
											<td width="223">
												<input name="acc" type="text" class="easyui-validatebox input" size="25" required="true"/>
											</td>
										</tr>
										<tr>
											<td height="30" align="right">
												密&nbsp;&nbsp;&nbsp;&nbsp;码：
											</td>
											<td>
												<input name="pwd" type="password" class="easyui-validatebox input" size="26" required="true"/>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td align="left">
												<label>
													<input type="checkbox" name="savePwd" />
													保存密码
												</label>
											</td>
										</tr>
										<tr>
											<td height="38">
											</td>
											<td>
												<a  href="#" class="easyui-linkbutton"  id="loginBtn" form="loginForm">登录</a>
												<a  href="#" class="easyui-linkbutton"  id="resetBtn" >重置</a>
											</td>
										</tr>
										<tr>
											<td colspan=2 style="text-align:right;">
												<div id="checkLoadingDiv" class="icon-loading30" style="display:none;height:18px;width:130px;">
													     正在校验登录信息...
												</div>
											</td>
										</tr>
									</table>								
								</form>
								<!-- Form表单 -->
								
							</td>
						</tr>
					</table>
					<br />
				</td>
			</tr>
		</table>
		<table width="680" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="fonts1">
					Copyright © 2010 北京万维美思科技. All rights reserved
				</td>
			</tr>
		</table>
		
	</body>

</html>