<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/system/function/fun2Role.js" />"></script>
		<script type="text/javascript" src="<c:url value="/view/system/function/fun.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body class="easyui-layout">
		<!-- 菜单区 -->
		<div region="north" style="padding:5 10 5 10px;" border=false >
			<table class="easyui-tbar">
				<tr>
					<td>
						<a id="search" href="#" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
						<a id="add" href="#" class="easyui-linkbutton"  iconCls="icon-add">新增</a>
						<a id="update" href="#" class="easyui-linkbutton"  iconCls="icon-update">修改</a>
						<a id="remove" href="#" class="easyui-linkbutton"  iconCls="icon-delete">删除</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<a id="showRoleBtn" href="#" class="easyui-linkbutton"  iconCls="icon-add">角色授权</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<a id="showRoleBtn" href="${path}/sys/fun/initFunOrder" class="easyui-linkbutton"  iconCls="icon-add">功能排序</a>
					</td>
				</tr>
			</table>		
		</div>
		
		<!-- 内容区 -->
		<div region="center" style="padding:5 10 5 10px;"  border=false>
			<table id="funTable" class="easyui-treegrid"
				idField="id"
				treeField="name"
				fitColumns="true"
				fit=true
				url="sys/fun"
				other="Fun.funEvent"
				>
				<thead>
					<tr>
						<th field="name" width="100">名称</th>
						<th field="code" width="80">编号</th>
						<th field="type" width="120" formatter="Fmt.funsType">类型</th>
						<th field="status" width="50" formatter="Fmt.status">状态</th>
						<th field="remark" width="150">备注</th>
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
			style="width:550px;height:320px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false" style="padding:10px;">
					<!-- 表单 -->
					<form id="funForm">
						<table class="form-content" height="200">
							<tr>
								<!-- 机构id -->
								<td colspan="4"><input type="hidden" name="id"/></td>
							</tr>
							<tr>
								<td width="80">功能编号：</td>
								<td><input class="easyui-validatebox" type="text" name="code"  validType="length[1,20]" required="true"/></td>
								<td width="95">功能名称：</td>	
								<td><input class="easyui-validatebox" type="text" name="name"  validType="length[1,40]" required="true"/></td>
							</tr>
							<tr>
								<td>菜单图标：</td>
								<td>
									<input id="iconClsCombo" name="iconCls">
								</td>
								<td>上级菜单：</td>
								<td><input class="easyui-validatebox" type="text"  name="parentName"/></td>
							</tr>
							<tr>
								
								<td>菜单路径：</td>
								<td colspan="3"><input class="easyui-validatebox" type="text"  name="uiPath" validType="length[1,500]" required="true" style="width:300px;"/></td>
							</tr>
							<tr>
								<td>功能类型：</td>
								<td>
									<input name="type" value="BASE" type="radio" checked=true/><span class="blue">基本功能</span>
									<input name="type" value="SUBSYS" type="radio"/><span class="red">子系统</span>	
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
										validType="length[0,2000]"
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
		
		
		<!-- 角色维护window -->
		<div id="roleWin" class="easyui-window" 
			closed="true" 
			modal="true" 
			title="功能到角色关联" 
			resizable=false
			style="width:650px;height:380px;">
			
			<div class="easyui-layout" fit="true">
				
				<div region="center" border="false"  >
					<table>
						<tr>
							<td >
								<div style="width:180px;height:300px;">
									<table id="roleOrgTree" class="easyui-treegrid"
										idField="id"
										treeField="name"
										fitColumns="true"
										fit=true
										border=true
										url="sys/org"
										other="Fun2Role.orgTreeEvent"
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
									<!-- 维护角色-->
									<div  class="easyui-tabs" fit="true">
										<div title="角色" border="false">
											<table id="roleGrid" class="easyui-datagrid"
												singleSelect="false"
												idField="id" 
												pagination=true
												queryParams="Fun2Role.queryRoleParams"
												autoLoad="false"
												url="sys/role"
												fit=true
												border=false
												other="Fun2Role.roleGridEvent"
												sytle="width:200px;height:240px;">
												<thead>
													<tr>
														<th field="ck"  checkbox=true width="40">账号</th>
														<th field="name" width="80">角色名称</th>
														<th field="smOrg" formatter="Fmt.showFun('name')" width="80">所属机构</th>
														<th field="type" width="80" formatter="Fmt.roleType">角色类型</th>
														<th field="status" width="60" formatter="Formatter.status">使用状态</th>
														<th field="remark" width="150">角色描述</th>
													</tr>
												</thead>
											</table>				
										</div>
										
										<div title="已选" border="false">
											<textarea  id="selctedRole" rows="17" cols="60"></textarea>
										</div>
										
									</div>									
								</div>
							
							</td>
						</tr>
					</table>					
				</div>
				<!-- 按钮区 -->
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="roleOKBtn">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="$('#roleWin').window('close')">取消</a>
				</div>

			</div>
		</div>		
		
	</body>

</html>