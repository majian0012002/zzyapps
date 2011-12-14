<%@ page language="java" import="java.util.*,cn.com.widemex.domain.system.SmFunction,cn.com.widemex.core.utils.reflection.Bean" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<title>北京万维美思轻量级开发平台</title>
		${jqFW }
		
		<!-- 自定义JS -->
		<script type="text/javascript" src="<c:url value="/view/home/left.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/home/top.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/home/main.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/home/home.js" />"></script>
		
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- Top布局 -->
		<div region="north" style="height:70px;" id="layout-top" class="top">
			<div >
				<div class="logo"></div>
            </div>
            
        	<div class="ad" >
        		<div id="top-info-div" style="text-align:right;padding:3px;" class="toolbar">
        			<a href="#" class="easyui-linkbutton"  iconCls="icon-home" id="deskBtn">桌面</a>
<%--        			<a href="#" class="easyui-linkbutton"  iconCls="icon-skin" id="skinBtnX">皮肤</a>--%>
        			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#skinmb" plain="false" iconCls="icon-skin">皮肤</a>
        			<div id="skinmb" style="width:100px;">
						<div id="skin-blue">默认</div>
						<div id="skin-pink">红色</div>
						<div id="skin-orange">橘色</div>
						<div id="skin-gray">灰色</div>
						<div id="skin-green">绿色</div>
					</div>
					<a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" id="quitSysBtn" >退出系统</a>
        		</div>
        		
        		<div id="top-info-div" style="text-align:right;padding:3px;" class="toolbar">
        			当前用户：${USER_INFO.acc }【${USER_INFO.name }】
        			<a href="#" class="easyui-linkbutton" id="modiPwd" iconCls="icon-pwd" title="修改个人信息！"></a>
        		</div>
        		
        		<!-- 
				<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="620" height="60">
				    <param name="movie" value="<c:url value="/resource/themes/custom/huangshan.swf"/>"/>
				    <param name="quality" value="high"/>
				 	<param name="menu" value="false"/>
				 	<param name="wmode" value="Opaque"/>
				    <embed 
				    	src="<c:url value="/resource/themes/custom/huangshan.swf"/>" 
				    	quality="high" 
				    	pluginspage="http://www.macromedia.com/go/getflashplayer" 
				    	type="application/x-shockwave-flash" 
				    	width="530" 
				    	height="63"
				    	wmode="Opaque">
				    </embed>
				</object>   
				 -->    	
        	</div>
		</div>
		

		<!-- 左菜单布局 -->
		<div region="west" split="true" title="功能菜单"
			headerCls="main-left-header"
			style="width: 280px; padding1: 1px; overflow: hidden;">
			<div class="easyui-accordion" fit="true" border="false">
				<%
					List<SmFunction> funList = (List<SmFunction>)session.getAttribute("USER_FUN_LIST");
					for(int index=0; index<funList.size(); index++){
						SmFunction fun = funList.get(index);
						out.println("<div title='"+ fun.getName() +"'>\n " +
								"<ul class='easyui-tree' id='FunTree-"+ fun.getId() +"' animate='true' textField='name' other='Wide.Left.funEvent'>\n " +
								"</ul> \n"+
								"</div>\n");					
					}
					
					// 赋值给JS公共变量
					out.println("<script type=\"text/javascript\" > "+
								"USER_FUN_LIST= " +Bean.toJson(funList).replaceAll("childrenFuns", "children")+ "; "+
								"USER_INFO=" +Bean.toJson(session.getAttribute("USER_INFO"))+
								"</script>");
				%>
			</div>
		</div>



		<!-- 主显示区内容布局 -->
		<div region="center" style="overflow: hidden;">
			<div class="easyui-tabs" id="mainTabs" fit="true" border="false">
				<div title="首页" closable="false" >
					<iframe src="" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>
				<!-- 
				<div title="组织机构类型维护" closable="true" >
					<iframe src="/JqFw/sys/orgType" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>
				<div title="组织机构维护" style="overflow:hidden;">
					<iframe src="/JqFw/sys/org" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>
				 <div title="用户信息维护" icon="icon-reload" closable="true"
					style="overflow: hidden; padding: 5px;">
					<iframe src="/JqFw/sys/user" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>
				<div title="角色维护" icon="icon-reload" closable="true"
					style="overflow: hidden; padding: 5px;">
					<iframe src="/JqFw/sys/role" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>
				<div title="功能维护" icon="icon-reload" closable="true"
					style="overflow: hidden; padding: 5px;">
					<iframe src="/JqFw/sys/fun" style="width:100%;height:100%;" frameborder="no" scrolling="auto"></iframe>
				</div>-->
			</div>
		</div>
		
		<!-- 用户修改form表单 -->
		<div id="userFormWin" class="easyui-window"
			closed="true" 
			modal="true" 
			title="个人信息修改" 
			resizable=false
			style="width:600px;height:300px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" style="padding:5px;">

					<!-- 表单 -->
					<form id="userForm">
						<table class="form-content"  height="205">
							<tr>
								<!-- 机构id -->
								<td colspan="4"><input type="hidden" name="id"/></td>
							</tr>
							<tr>
								<td width="80">登录账号：</td>
								<td><input class="easyui-validatebox" type="text" name="acc" disabled="disabled" validType="length[1,40]" required="true"/></td>
								<td width="95">员工姓名：</td>	
								<td><input class="easyui-validatebox" type="text" name="name"  validType="length[1,40]" required="true"/> </td>
							</tr>
							<tr>
								<td>登录密码：</td>
								<td><input class="easyui-validatebox" type="password"  name="pwd" id="pwd" validType="length[1,32]" required="true"/></td>
								<td>确认密码：</td>
								<td><input name="pwd2"  class="easyui-validatebox" type="password" validType="pwdTow['pwd']" required="true"/> </td>
							</tr>
							<tr>
								<td>员工编号：</td>
								<td><input class="easyui-validatebox" type="text"  disabled="disabled" name="code" validType="length[1,10]" required="true"/></td>
								<td>联系电话：</td>
								<td><input name="phone"  class="easyui-validatebox" type="text" validType="length[0,40]" /> </td>
							</tr>
							<tr>
								<td>电子邮件：</td>
								<td><input class="easyui-validatebox" type="text"  name="email" validType="email;length[0,80]"/></td>
								<td>所属机构：</td>
								<td><input name="smOrgText"  class="easyui-validatebox" type="text" required="true" disabled="true"/> </td>
							</tr>
							<tr>
								<td>员工类型：</td>
								<td>
									<input name="type" value=1 type="radio" checked=true disabled="disabled" /><span class="blue">普通用户</span>
									<input name="type" value=0 type="radio" disabled="disabled" /><span class="red">系统用户</span>
								 </td>
							
								<td>使用状态：</td>
								<td>
									<input name="status" value=1 type="radio" checked=true disabled="disabled" /><span class="blue">有效</span>
									<input name="status" value=0 type="radio" disabled="disabled" /><span class="red">无效</span>
								 </td>
							</tr>
							<tr>
								<td>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
								<td colspan="3">
									<textarea class="easyui-validatebox" 
										name="remark"
										validType="length[0,200]"
										style="height:55px;width:400">
									</textarea>
								</td>
							</tr>
						</table>
						
					</form>
					
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="userSaveBtn" form="userForm">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#userFormWin').window('close')">取消</a>
				</div>

			</div>
		</div>
		


	</body>

</html>