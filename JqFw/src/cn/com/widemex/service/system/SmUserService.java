package cn.com.widemex.service.system;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.system.SmRoleDao;
import cn.com.widemex.dao.system.SmUserDao;
import cn.com.widemex.domain.system.SmRole;
import cn.com.widemex.domain.system.SmUser;
/**
 * 用户维护service
 *
 * @author 张中原
 *
 */
@Service("smUserService")
public class SmUserService {
	/**用户dao**/
	private SmUserDao smUserDao;
	/**角色dao*/
	private SmRoleDao smRoleDao;
	
	/**
	 * 查询信息
	 * @param page
	 * @return
	 */
	public PageVO list(PageVO page){
		page.setWhere("select  distinct a from SmUser a " +
				"left join fetch a.smRoles b " +
				"left join fetch a.smOrg c " +
				"where a.smOrg.id=? order by a.acc");
		page.setWhereParams(new Object[]{page.getParams().getString("orgId", null)});
		
		return this.smUserDao.findPage(page);   
	}
	
	/**
	 * 保存更新信息
	 * @param pojo
	 * @return
	 */
	public SmUser save(SmUser pojo){
		this.smUserDao.saveOrUpdate(pojo);
		return pojo;
	}
	
	/**
	 * 删除信息
	 * @param pojo
	 * @return
	 */
	public SmUser remove(SmUser pojo){
		pojo = this.smUserDao.get(pojo.getId());
		this.smUserDao.remove(pojo);
		return pojo;
	}
	
	/**
	 * 是否已经存在相同的记录
	 * @param pojo
	 * @return
	 */
	public boolean isHasRecord(String id){
		return this.smUserDao.get(id) != null;
	}

	public SmUserDao getSmUserDao() {
		return smUserDao;
	}

	public void setSmUserDao(SmUserDao smUserDao) {
		this.smUserDao = smUserDao;
	}

	public SmRoleDao getSmRoleDao() {
		return smRoleDao;
	}

	public void setSmRoleDao(SmRoleDao smRoleDao) {
		this.smRoleDao = smRoleDao;
	}

	
	 
}
