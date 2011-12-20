package cn.com.widemex.service.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.system.SmFunctionDao;
import cn.com.widemex.domain.system.SmFunction;
import cn.com.widemex.domain.system.SmRole;

/**
 * 功能维护 service
 *
 * @author 张中原
 *
 */
@Service("smFunctionService")
public class SmFunctionService {
	/**功能dao*/
	private SmFunctionDao smFunctionDao;

	/**
	 * 查询
	 * @param page
	 * @return
	 */
	public PageVO list(PageVO page){
		String id = page.getParams().getString("id", "");
		
		String status = page.getParams().getString("status", "");
		if(!"".equals(status)){
			status = " and status="+status;
		}
		
		String hql = "select distinct a from SmFunction a " +
				" left join fetch a.smRoles b " +
				" left join fetch b.smOrg c ";
		
		if("".equals(id)){
			page.setWhere(hql + "where a.parentFun is null" + status);
		}else{
			page.setWhere(hql + "where a.parentFun.id = ?" + status);
			page.setWhereParams(new Object[]{id});
		}
		
		page.setWhere(page.getWhere() + " order by a.orderInd asc");
		
		return this.smFunctionDao.findPage(page);
	}
	
	/**
	 * 一次性查询加载
	 * @return
	 */
	public PageVO listOnce(PageVO page){
		String id = page.getParams().getString("id", "");
		/*
		String status = page.getParams().getString("status", "");
		
		if("".equals(id)){
			page.setWhere("where status=1");
		}else{
			page.setWhere("where parentFun.id = ? and status=1");
			page.setWhereParams(new Object[]{id});
		}*/
		
		List<SmFunction> list = this.smFunctionDao.find(" from SmFunction a where a.status=? order by a.orderInd ASC ", (short)1);
		
		for (SmFunction fun : list) { 
			if(fun.getParentFun() != null){
				fun.setParentId(fun.getParentFun().getId());
			}
//			if(fun.getChildrenFuns().size() == 0){
//				fun.setState("open");
//			}
			
			fun.setLoaded(true);
		}
		page.setRows(list);
		
		return this.smFunctionDao.findPage(page);		
	}
	
	
	
//	/**
//	 * 根据用户获取允许访问的所有菜单
//	 * @param acc 用户账号
//	 * @return
//	 */
//	public List<SmFunction> listFunByUser(String acc){
//		List<SmFunction> funList = new ArrayList<SmFunction>();
//		
//		List<SmRole> roleList = this.smRoleService.listByUser(acc);
//		for (SmRole smRole : roleList) {
//			Set<SmFunction> funSet = smRole.getSmFuns();
//			for (SmFunction smFun : funSet) {
//				funList.add(smFun);
//			}
//		}
//		
//		return funList;
//	}
//	
//	/**
//	 * 根据用户及一级菜单id获取二级菜单信息
//	 * @param acc 当前账号
//	 * @param firstFunId 一级功能菜单Id
//	 * @return
//	 */
//	public List<SmFunction> listBySecondFunUser(String acc, String firstFunId){
//		return this.smFunctionDao.find("select distinct a from SmFunction a " +
//				"left join a.smRoles b " +
//				"left join b.smUsers c " +
//				"left join a.parentFun d " +
//				"where c.acc =? and d.id = ? ", acc, firstFunId);
//	}
	
	
	/**
	 * 保存、更新
	 * @param pojo
	 * @return
	 */
	public SmFunction save(SmFunction pojo){
		this.smFunctionDao.saveOrUpdate(pojo);
		return pojo;
	}
	
	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	public SmFunction remove(SmFunction pojo){
		pojo = this.smFunctionDao.get(pojo.getId());
		this.smFunctionDao.remove(pojo);
		return pojo;
	}
	
	/**
	 * 是否已经存在相同编码的记录
	 * @param pojo
	 * @return
	 */
	public boolean isHasRecord(SmFunction pojo){
		return this.smFunctionDao.get(pojo.getId()) != null;
	}
	
	public SmFunctionDao getSmFunctionDao() {
		return smFunctionDao;
	}

	public void setSmFunctionDao(SmFunctionDao smFunctionDao) {
		this.smFunctionDao = smFunctionDao;
	}
}
