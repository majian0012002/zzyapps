package cn.com.widemex.service.demo;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.demo.DemoDeptDao;
import cn.com.widemex.domain.demo.DemoDept;

/**
 * 部门维护 service
 *
 * @author 张中原
 *
 */
@Service("demoDeptService")
public class DemoDeptService {
	private DemoDeptDao demoDeptDao; 

	/**
	 * 查询结果
	 * @param page 页面信息
	 * @return
	 */
	public PageVO list(PageVO page){
		String id = page.getParams()==null ? null : page.getParams().getString("id", null);
		
		// 如果为空，则是加载根节点
		if(id == null){
			return this.demoDeptDao.findPage(" where pDept.id is null");
		}else{
			return this.demoDeptDao.findPage(" where pDept.id='" + id + "'");
		}
	}
	
	/**
	 * 保存部门信息
	 * @param dept 部门信息
	 * @return
	 */
	public DemoDept save(DemoDept dept){
		
		dept.setpDept(this.demoDeptDao.get(dept.getParentId()));
		
		this.demoDeptDao.saveOrUpdate(dept);
		
		return dept;
	}
	
	/**
	 * 删除部门信息
	 * @param dept 部门信息
	 * @return
	 */
	public DemoDept delete(DemoDept dept){
		
		this.demoDeptDao.remove(this.demoDeptDao.get(dept.getId()));
		
		return dept;
	}
	
	
	public DemoDeptDao getDemoDeptDao() {
		return demoDeptDao;
	}

	public void setDemoDeptDao(DemoDeptDao demoDeptDao) {
		this.demoDeptDao = demoDeptDao;
	}
	
}
