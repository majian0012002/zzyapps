package cn.com.widemex.dao.demo;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.demo.DemoDept;

/**
 * 部门DAO
 * @author 张中原
 *
 */
@Repository("demoDeptDao")
public class DemoDeptDao extends HibernateDao<DemoDept>{
	
}
