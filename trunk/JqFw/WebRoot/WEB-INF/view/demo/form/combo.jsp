<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/demo/form/combo.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body>
		<table>
			
			<tr>
				<td>
					<br/>
					<br/>
					简单下拉列表：
					<select id="cc" class="easyui-combobox" name="state" style="width:200px;" required="true">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="WY">Wyoming</option>
						<option value="AK">Alaska</option>
						<option value="AK">Alaska</option>
						<option value="AK">Alaska</option>
					</select>	
				</td>
				<td>
					<br/>
					<br/>
					后台下拉列表：
					<select id="datacombo" name="state" style="width:200px;" required="true">
					</select>
					<a href="#" id="datacobo_btn" class="easyui-linkbutton" iconCls="icon-ok">获取值</a>
					<a href="#" id="datacobo_btn_setVal" class="easyui-linkbutton" iconCls="icon-ok" >设置值</a>
					<a href="#" id="datacobo_btn_setBeanVal" class="easyui-linkbutton" iconCls="icon-ok">设置对象值</a>
				</td>
			</tr>
			
			<tr>
				<td>
					<br/>
					<br/>
					下拉表格：
					<select id="datagrid"></select>	
					<a href="#" id="datagrid_btn" class="easyui-linkbutton" iconCls="icon-ok">获取值</a>
				</td>
				<td>
					<br/>
					<br/>
					后台下拉列表：
					<select class="easyui-combogrid"
						id="combogrid_cfg"
						url="demo/combo"
						value='006'
						idField='id'
						textField='typeName'
						columns="[[{field:'code',title:'编码',width:60},{field:'typeName',title:'名称',width:60}]]"
						>
					</select>
					
					<a href="#" id="combogrid_cfg_btn" class="easyui-linkbutton" iconCls="icon-ok">获取值</a>
					<a href="#" id="combogrid_btn_setVal" class="easyui-linkbutton" iconCls="icon-ok" >设置值</a>
					<a href="#" id="combogrid_btn_setBeanVal" class="easyui-linkbutton" iconCls="icon-ok">设置对象值</a>
				</td>
			</tr>	
			
			<tr>
				<td>
					<br/>
					<br/>
					下拉单选树：
					<select class="easyui-combotree"
						id="datatree"
						url="demo/pojo/treegrid" 
						textField="name"
						idField="id"
						style="width:200px;">
					</select>	
					<a href="#" id="datatree_btn" class="easyui-linkbutton" iconCls="icon-ok">获取值</a>
				</td>
				<td>
					<br/>
					<br/>
					后台下拉多选树：
					<select class="easyui-combotree" 
						id="datatree_cfg" 
						list="demo/pojo/treegrid/list" 
						multiple="true" 
						cascadeCheck="true" 
						textField="name"
						idField="id"
						style="width:200px;"
						queryParams="function(){return {aaa: 1111};}">
					</select>	
					<a href="#" id="datatree_cfg_btn" class="easyui-linkbutton" iconCls="icon-ok">获取值</a>
					<a href="#" id="datatree1_btn_setVal" class="easyui-linkbutton" iconCls="icon-ok" >设置值</a>
					<a href="#" id="datatree1_btn_setBeanVal" class="easyui-linkbutton" iconCls="icon-ok">设置对象值</a>
				</td>
			</tr>
								
		</table>	
	</body>

</html>