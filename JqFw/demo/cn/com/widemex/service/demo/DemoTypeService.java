package cn.com.widemex.service.demo;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.demo.DemoTypeDao;

/**
 * 类型维护 service
 *
 * @author 张中原
 *
 */
@Service("demoTypeService")
public class DemoTypeService {

	private DemoTypeDao demoTypeDao;

	/**
	 * 查询
	 * @param page 
	 * @return
	 */
	public PageVO list(PageVO page){
		return this.demoTypeDao.findPage(page);
	}
	
	
	public DemoTypeDao getDemoTypeDao() {
		return demoTypeDao;
	}

	public void setDemoTypeDao(DemoTypeDao demoTypeDao) {
		this.demoTypeDao = demoTypeDao;
	}
	
}
