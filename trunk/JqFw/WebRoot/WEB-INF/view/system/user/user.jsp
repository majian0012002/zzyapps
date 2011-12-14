<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/user/user.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 部门信息 -->
		<div region="west" border=true split="true"
			title="部门" 
			style="width: 280px; padding: 1px; overflow: hidden;">
			<table id="orgTree" class="easyui-treegrid"
				idField="id"
				treeField="name"
				fitColumns="true"
				fit=true
				border=false
				url="sys/org"
				other="User.orgTreeEvent"
				queryParams="$.statusParam"
				>
				<thead>
					<tr>
						<th field="name" width="180">名称</th>
						<th field="code" width="60">编号</th>
						<th field="smOrgType" width="60" formatter="Fmt.showFun('typeName')">类型</th>
					</tr>
				</thead>
			</table>			
		</div>
		
		<!-- 维护用户-->
		<div region="center"  border=false
			class="easyui-layout"
			fit=true
			>
			<div region="north" border="false">
				<table class="easyui-tbar" style="padding:0px;">
					<tr>
						<td>
							<a id="add" href="#" class="easyui-linkbutton"  iconCls="icon-add">新增</a>
							<a id="update" href="#" class="easyui-linkbutton"  iconCls="icon-update">修改</a>
							<a id="remove" href="#" class="easyui-linkbutton"  iconCls="icon-remove">删除</a>
						</td>
					</tr>
				</table>		
			</div>
			<div region="center" border="false">
				<table id="userGrid" class="easyui-datagrid"
					singleSelect="true"
					idField="id" 
					fit=true
					pagination=true
					queryParams="User.queryParams"
					autoLoad="false"
					url="sys/user">
					<thead>
						<tr>
							<th field="ck" width="40" checkbox=true></th>
							<th field="acc" width="80">账号</th>
							<th field="name" width="80">姓名</th>
							<th field="code" width="80">编号</th>
							<th field="phone" width="50">电话</th>
							<th field="smRoles" formatter="Fmt.hasRoles" width="100">拥有角色</th>
							<th field="type" width="50">类型</th>
							<th field="status" width="50" formatter="Formatter.status">使用状态</th>
						</tr>
					</thead>
				</table>			
			</div>
		</div>
		
		
		
		<!-- form表单 -->
		<div id="formWin" class="easyui-window"
			closed="true" 
			modal="true" 
			title="维护表单" 
			resizable=false
			style="width:600px;height:350px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" >
					<!-- 用户基本信息 -->
					<div class="easyui-tabs" fit="true" border=false>
						<div title="基本信息" style="padding:5px;">
							<!-- 表单 -->
							<form id="form">
								<table class="form-content"  height="225">
									<tr>
										<!-- 机构id -->
										<td colspan="4"><input type="hidden" name="id"/></td>
									</tr>
									<tr>
										<td width="80">登录账号：</td>
										<td><input class="easyui-validatebox" type="text" name="acc"  validType="length[1,40]" required="true"/></td>
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
										<td><input class="easyui-validatebox" type="text"  name="code" validType="length[1,10]" required="true"/></td>
										<td>联系电话：</td>
										<td><input name="phone"  class="easyui-validatebox" type="text" validType="length[0,40]" /> </td>
									</tr>
									<tr>
										<td>电子邮件：</td>
										<td><input class="easyui-validatebox" type="text"  name="email" validType="email;length[0,80]"/></td>
										<td>所属机构：</td>
										<td><input name="smOrg"  class="easyui-validatebox" type="text" required="true" disabled="true"/> </td>
									</tr>
									<tr>
										<td>员工类型：</td>
										<td>
											<input name="type" value=1 type="radio" checked=true/><span class="blue">普通用户</span>
											<input name="type" value=0 type="radio"/><span class="red">系统用户</span>
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
												validType="length[0,200]"
												style="height:55px;width:400">
											</textarea>
										</td>
									</tr>
								</table>
								
							</form>							
						</div>
						
						<div title="设置角色" style="padding:5px;">
							<!-- 表单 -->
								<table class="form-content" style="width:576px;height:225px;">
									<tr>
										<td>
											<table id="roleGrid" class="easyui-datagrid"
												singleSelect="false"
												idField="id" 
												fit=true
												autoLoad="false"
												queryParams="User.queryParams"
												url="sys/role">
												<thead>
													<tr>
														<th field="ck" width="80" checkbox="true"></th>
														<th field="name" width="80">角色名称</th>
														<th field="smOrg" formatter="Fmt.showFun('name')" width="80">所属机构</th>
														<th field="type" width="80" formatter="Fmt.roleType">角色类型</th>
														<th field="status" width="60" formatter="Formatter.status">使用状态</th>
														<th field="remark" width="150">角色描述</th>
													</tr>
												</thead>
											</table>											
										</td>
									</tr>
								</table>
						</div>	
					</div>
					
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="save">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#formWin').window('close')">取消</a>
				</div>

			</div>
		</div>
		
	</body>

</html>