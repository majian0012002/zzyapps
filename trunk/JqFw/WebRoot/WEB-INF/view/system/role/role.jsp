<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/role/role2User.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/system/role/role2Fun.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/system/role/role.js" />"></script>
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
				other="Role.orgTreeEvent"
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="showFunBtn" href="#" class="easyui-linkbutton"  iconCls="icon-setting">设置功能</a>
							<a id="showUserBtn" href="#" class="easyui-linkbutton"  iconCls="icon-setting">用户授权</a>
							
						</td>
					</tr>
				</table>		
			</div>
			<div region="center" border="false">
				<table id="roleGrid" class="easyui-datagrid"
					singleSelect="true"
					idField="id" 
					fit=true
					pagination=true
					autoLoad=false
					queryParams="Role.queryParams"
					url="sys/role">
					<thead>
						<tr>
							<th field="ck" width="40" checkbox=true></th>
							<th field="name" width="80">角色名称</th>
							<th field="smOrg" formatter="Fmt.showFun('name')" width="80">所属机构</th>
							<th field="type" width="80" formatter="Fmt.roleType">角色类型</th>
							<th field="status" width="60" formatter="Formatter.status">使用状态</th>
							<th field="remark" width="150">角色描述</th>
						</tr>
					</thead>
				</table>			
			</div>
		</div>
		
		
		
		<!-- 维护表单form -->
		<div id="formWin" class="easyui-window" 
			closed="true" 
			modal="true" 
			title="维护表单" 
			resizable=false
			style="width:650px;height:320px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" >
					<!-- 表单 -->
					<form id="form">
						<table class="form-content" height="200">
							<tr>
								<!-- 机构id -->
								<td colspan="4"><input type="hidden" name="id"/></td>
							</tr>
							<tr>
								<td width="80">角色名称：</td>
								<td><input class="easyui-validatebox" type="text" name="name"  validType="length[1,60]" required="true"/></td>
							</tr>
							<tr>
								<td width="95">所属机构：</td>	
								<td><input class="easyui-validatebox" type="text" name="smOrg" disabled="disabled" /> </td>
							</tr>
							<tr>
								<td>角色类型</td>
								<td>
									<input name="type" value=GR type="radio" checked=true/>
									<span class="blue">正常角色（可授予当前及其下级机构的所有用户，并且不能拥有系统管理功能）</span><br/>
									<input name="type" value=PR type="radio"/>
									<span class="red">公共角色（当前及其下级机构的所有用户将默认拥有该角色，并且不能拥有系统管理功能）</span><br/>
								</td>
							</tr>
							<tr>
								<td>使用状态：</td>
								<td>
									<input name="status" value=1 type="radio" checked=true/><span class="blue">有效</span>
									<input name="status" value=0 type="radio"/><span class="red">无效</span>
								 </td>
							</tr>
							<tr>
								<td>角色描述：</td>
								<td colspan="1">
									<textarea class="easyui-validatebox" 
										name="remark"
										validType="length[0,200]"
										style="height:35px;width:100%">
									</textarea>
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
		
		
		
		<!-- 设置功能window -->
		<div id="funWin" class="easyui-window" 
			title="设置功能" 
			closed="true"
			style="width:450px;height:290px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" >
					<!-- 内容区 -->
					<div region="center"   border=false>
						<ul id="funTree" class="easyui-tree" 
							list="sys/fun/listOnce" 
							idField = 'id'
							textField='name'
							animate="false"
							checkbox="true"
							style="width:400px;height:220px"
							>
						</ul>						
						
					
<!--						<table id="funTree" class="easyui-tree"-->
<!--							idField="id"-->
<!--							treeField="name"-->
<!--							list="sys/fun/listOnce"-->
<!--							border=false-->
<!--							singleSelect=false-->
<!--							style="width:436px;height:220px"-->
<!--							>-->
<!--							<thead>-->
<!--								<tr>-->
<!--									<th field="name" width="100" formatter="Fmt['renderCheckbox']">名称</th>-->
<!--									<th field="code" width="80">编号</th>-->
<!--									<th field="type" width="120" formatter="Fmt.funsType">类型</th>-->
<!--								</tr>-->
<!--							</thead>-->
<!--						</table>-->
					</div>					
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="funOKBtn">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#funWin').window('close')">取消</a>
				</div>

			</div>
		</div>
		
		
		<!-- 用户维护window -->
		<div id="userWin" class="easyui-window" 
			closed="true" 
			modal="true" 
			title="角色跟用户关联" 
			resizable=false
			style="width:650px;height:380px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false"  >
					<table>
						<tr>
							<td >
								<div style="width:180px;height:300px;">
									<table id="userOrgTree" class="easyui-treegrid"
										idField="id"
										treeField="name"
										fitColumns="true"
										fit=true
										border=true
										url="sys/org"
										other="Role2User.orgTreeEvent"
										queryParams="$.statusParam"
										>
										<thead>
											<tr>
												<th field="name" width="140">名称</th>
												<th field="code" width="40">编号</th>
											</tr>
										</thead>
									</table>	
								</div>				
							</td>
							<td >
								<div style="width:445px;height:300px;">
									<!-- 维护用户-->
									<div  class="easyui-tabs" fit="true">
										<div title="用户" border="false">
											<table id="userGrid" class="easyui-datagrid"
												singleSelect="false"
												idField="id" 
												pagination=true
												queryParams="Role2User.queryUserParams"
												autoLoad="false"
												url="sys/user"
												fit=true
												border=false
												other="Role2User.userGridEvent"
												sytle="width:200px;height:240px;">
												<thead>
													<tr>
														<th field="ck"  checkbox=true width="40">账号</th>
														<th field="acc" width="80">账号</th>
														<th field="name" width="80">姓名</th>
														<th field="code" width="80">编号</th>
														<th field="phone" width="50">电话</th>
														<th field="type" width="50">类型</th>
													</tr>
												</thead>
											</table>				
										</div>
										
										<div title="已选" border="false">
											<textarea  id="selctedUser" rows="17" cols="60"></textarea>
										</div>
										
									</div>									
								</div>
							
							</td>
						</tr>
					</table>					
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="userOKBtn">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#userWin').window('close')">取消</a>
				</div>

			</div>
		</div>			
		
	</body>

</html>