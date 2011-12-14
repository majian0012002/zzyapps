package cn.com.widemex.dao.system;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.system.SmOrg;
/**
 * 组织机构 dao
 *
 * @author 张中原
 *
 */
@Repository("smOrgDao")
public class SmOrgDao extends HibernateDao<SmOrg> {

}
