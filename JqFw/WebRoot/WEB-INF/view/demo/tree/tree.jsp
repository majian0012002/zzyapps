<%@ page language="java" import="java.lang.*,java.util.*,cn.com.widemex.domain.demo.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

	<!-- 表头区 -->
	<head>
		<!-- jq框架 -->
		${jqFW}
		<!-- 自定义js -->
		<script type="text/javascript" src="<c:url value="/view/demo/tree/tree.js" />"></script>
	</head>

	<!-- 内容区 -->
	<body>
		<table>
			<tr>
				<td>
					jsp渲染树：<br/>
					<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
						<%! 
							public String toTree(Collection<DemoDept> deptList){
								String str = "";
							
								for(DemoDept dept : deptList){
									
									if(dept.getDemoDepts()!=null && dept.getDemoDepts().size()>0){
										str += "<li state='closed'> \n";	
									}else{
										str += "<li> \n";
									}
									
									str += "	<span>" + dept.getName() + "</span> \n";
									
									if(dept.getDemoDepts()!=null && dept.getDemoDepts().size()>0){
										str += "   <ul state='closed'> \n";
										str += toTree(dept.getDemoDepts());
										str += "   </ul> \n";
									}
									
									str += "   </li> \n";
									
								}
								
								return str;
							}
						
						%>
						
						<%= toTree((List<DemoDept>)request.getAttribute("deptList")) %>
					</ul>
					
					
				</td>
				
				<td width=200>

				</td>
					
				<td>
					ajax异步渲染树：<br/>
					
					<ul id="ajaxTree" class="easyui-tree" 
						url="demo/pojo/treegrid" 
						idField = 'id'
						textField='name'
						animate="true"
						url="xxx">
					</ul>
					
				</td>
			</tr>
		</table>
	</body>

</html>