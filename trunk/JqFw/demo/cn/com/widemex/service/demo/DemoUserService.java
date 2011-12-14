package cn.com.widemex.service.demo;

import org.springframework.stereotype.Service;

import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.dao.demo.DemoUserDao;
import cn.com.widemex.domain.demo.DemoUser;

/**
 * 用户维护 service
 *
 * @author 张中原
 *
 */
@Service("demoUserService")
public class DemoUserService {
	private DemoUserDao demoUserDao;
	
	/**
	 * 查询
	 * @param page
	 * @return
	 */
	public PageVO list(PageVO page){
		return this.demoUserDao.findPage(page);
	}
	
	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public DemoUser save(DemoUser user){
		this.demoUserDao.save(user);
		return user;
	}
	
	/**
	 * 删除
	 * @param user
	 * @return
	 */
	public DemoUser remove(DemoUser user){
		this.demoUserDao.remove(user);
		return user;
	}
	
	
	public DemoUserDao getDemoUserDao() {
		return demoUserDao;
	}

	public void setDemoUserDao(DemoUserDao demoUserDao) {
		this.demoUserDao = demoUserDao;
	}
	
}
