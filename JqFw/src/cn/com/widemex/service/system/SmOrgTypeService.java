package cn.com.widemex.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.system.SmOrgTypeDao;
import cn.com.widemex.domain.system.SmOrgType;

/**
 * 组织机构类型维护
 *
 * @author 张中原
 *
 */
@Service("smOrgTypeService")
public class SmOrgTypeService {
	/**组织结构类型 dao*/
	private SmOrgTypeDao smOrgTypeDao;
	
	/**
	 * 查询
	 * @param page
	 * @return
	 */
	public PageVO list(PageVO page){
		return this.smOrgTypeDao.findPage(page);
	}
	
	/**
	 * 查询有效数据
	 * @return
	 */
	public List<SmOrgType> list4ValidData(){
		return this.smOrgTypeDao.findBy("status", 1);
	}
	
	/**
	 * 保存
	 * @param orgType
	 * @return
	 */
	public SmOrgType save(SmOrgType orgType){
		this.smOrgTypeDao.saveOrUpdate(orgType);
		return orgType;
	}
	
	/**
	 * 删除
	 * @param orgType
	 * @return
	 */
	public SmOrgType remove(SmOrgType orgType){
		this.smOrgTypeDao.remove(orgType);
		
		return orgType;
	}
	
	/**
	 * 是否存在记录
	 * @param id 主键
	 * @return
	 */
	public boolean isHasRecord(String id){
		SmOrgType type = this.smOrgTypeDao.get(id);
		return type!=null;
	}
	

	public SmOrgTypeDao getSmOrgTypeDao() {
		return smOrgTypeDao;
	}

	public void setSmOrgTypeDao(SmOrgTypeDao smOrgTypeDao) {
		this.smOrgTypeDao = smOrgTypeDao;
	}
	
}
