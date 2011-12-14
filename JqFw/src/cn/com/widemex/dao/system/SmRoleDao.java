package cn.com.widemex.dao.system;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.system.SmRole;
/**
 * 角色dao
 *
 * @author 张中原
 *
 */
@Repository("smRoleDao")
public class SmRoleDao extends HibernateDao<SmRole> {

}
