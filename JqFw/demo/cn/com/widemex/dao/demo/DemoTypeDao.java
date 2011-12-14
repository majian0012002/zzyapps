package cn.com.widemex.dao.demo;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.demo.DemoType;
/**
 * 类型dao
 * @author 张中原
 *
 */
@Repository("demoTypeDao")
public class DemoTypeDao extends HibernateDao<DemoType> {

}
