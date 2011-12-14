package cn.com.widemex.service.system;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.system.SmOrgDao;
import cn.com.widemex.domain.system.SmOrg;

/**
 * 组织机构管理
 *
 * @author 张中原
 *
 */
@Service("smOrgService")
public class SmOrgService {
	/**组织结构dao*/
	private SmOrgDao smOrgDao;


	/**
	 * 查询组织机构
	 * @param page 页面信息
	 * @return
	 */
	public PageVO list(PageVO page){
		String id = page.getParams().getString("id", null);
		
		String status = page.getParams().getString("status", "");
		if(!"".equals(status)){
			status = " and a.status =" + status;
		}
		
		if(id == null){
			page.setWhere("select a from SmOrg a  left join fetch a.smOrgType where a.smOrg is null " + status);
			
		}else{
			page.setWhere("select a from SmOrg a  left join fetch a.smOrgType where a.smOrg.id=? " + status);
			page.setWhereParams(new Object[]{id});
		}
		
		return this.smOrgDao.findPage( page );
	}
	
	/**
	 * 保存或者更新组织机构
	 * @param org
	 * @return
	 */
	public SmOrg save(SmOrg org){
		this.smOrgDao.saveOrUpdate(org);
		return org;
	}
	
	/**
	 * 删除组织机构
	 * @param org
	 * @return
	 */
	public SmOrg remove(SmOrg org){
		org = this.smOrgDao.get(org.getId());
		this.smOrgDao.remove(org);
		return org;
	}
	
	/**
	 * 是否已经存在当中新增的记录id
	 * @param id
	 * @return
	 */
	public boolean isHasRecord(String id){
		SmOrg org = this.smOrgDao.get(id);
		return org != null;
	}
	
	
	public SmOrgDao getSmOrgDao() {
		return smOrgDao;
	}

	public void setSmOrgDao(SmOrgDao smOrgDao) {
		this.smOrgDao = smOrgDao;
	}

}
