package cn.com.widemex.action.demo.jdbcCRUD;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DEMO：datagrid 通过JDBC方式对表格进行CRUD
 * @author 张中原
 *
 */
@Controller
@RequestMapping("demo/datagrid/jdbc")
public class DemoJdbcDataGridController {

	/**
	 * 初始化界面
	 */
	public String init(){
		return "demo/datagrid/datagrid";
	}
	
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody Object list(){
		return null;
	}
	
	/**
	 * 更新、保存
	 * @return
	 */
	@RequestMapping("save")
	public @ResponseBody Object save(){
		return null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("delete")
	public @ResponseBody Object delete(){
		return null;
	}	
	
}
