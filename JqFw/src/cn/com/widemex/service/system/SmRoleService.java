package cn.com.widemex.service.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.system.SmRoleDao;
import cn.com.widemex.dao.system.SmUserDao;
import cn.com.widemex.domain.system.SmOrg;
import cn.com.widemex.domain.system.SmRole;
import cn.com.widemex.domain.system.SmUser;

/**
 * 角色维护service
 *
 * @author 张中原
 *
 */

@Service("smRoleService")
public class SmRoleService {
	/**角色dao **/
	private SmRoleDao smRoleDao;
	/**用户dao*/
	private SmUserDao smUserDao;
	
	/**
	 * 查询
	 * @param page
	 * @return
	 */
	public PageVO list(PageVO page){
		String orgId = page.getParams().getString("orgId", null);
		
		String status = page.getParams().getString("status", "");
		if(!"".equals(status)){
			status = " and a.status = "+status;
		}
		
		if(orgId != null){
			page.setWhere("select distinct a " +
					"	from SmRole a " +
					"	left join fetch a.smOrg b " +
					"	left join fetch a.smUsers c " +
					"	left join fetch c.smOrg d " +
					"	left join fetch a.smFuns e " +
					"	where b.id=? " + status);
			page.setWhereParams(new Object[]{orgId});
		}
		
		return this.smRoleDao.findPage(page);
	}
	
	/**
	 * 根据账号，获取相关角色，包括相关公共角色
	 * @param acc 账号
	 * @return
	 */
	public List<SmRole> listByUser(String acc){
		List<SmRole> list = new ArrayList<SmRole>();
		
		SmUser user = this.smUserDao.get(acc);
		
		// 当前用户拥有的普通角色
		Set<SmRole> roleSet = user.getSmRoles();
		for (SmRole smRole : roleSet) {
			list.add(smRole);
		}
		
		// 当前用户拥有的公共角色
		//TODO...
		
		return list;
		
	}
	
//	/**
//	 * 获取当前用户部门的所有公共角色，包括父部门
//	 * @param org 部门
//	 * @param roleList 角色集合
//	 * @return
//	 */
//	private List<SmRole> getPublicRoleByOrg(SmOrg org, List<SmRole> roleList){
//		Set<SmRole> roleSet = org.getSmRoles();
//		for (SmRole smRole : roleSet) {
//			if(smRole.getType().equals("PUB")){
//				
//			}
//		}
//	}
	
	/**
	 * 保存、更新
	 * @param pojo
	 * @return
	 */
	public SmRole save(SmRole pojo){
//		for (SmFunction fun : pojo.getSmFuns()) {
//			Bean.printValues(pojo);
//		}
		
		this.smRoleDao.saveOrUpdate(pojo);
		return pojo;
	}

	/**
	 * 删除
	 * @param pojo
	 * @return
	 */
	public SmRole remove(SmRole pojo){
		pojo = this.smRoleDao.get(pojo.getId());
		this.smRoleDao.remove(pojo);
		return pojo;
	}
	
	public SmRoleDao getSmRoleDao() {
		return smRoleDao;
	}

	public void setSmRoleDao(SmRoleDao smRoleDao) {
		this.smRoleDao = smRoleDao;
	}

	public SmUserDao getSmUserDao() {
		return smUserDao;
	}

	public void setSmUserDao(SmUserDao smUserDao) {
		this.smUserDao = smUserDao;
	}
	
	
	
}
