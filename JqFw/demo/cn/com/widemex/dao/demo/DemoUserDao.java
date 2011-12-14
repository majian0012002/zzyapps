package cn.com.widemex.dao.demo;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.demo.DemoUser;
/**
 * 用户dao 
 * @author 张中原
 *
 */
@Repository("demoUserDao")
public class DemoUserDao extends HibernateDao<DemoUser> {

}
