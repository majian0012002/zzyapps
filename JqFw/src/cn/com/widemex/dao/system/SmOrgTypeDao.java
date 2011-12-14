package cn.com.widemex.dao.system;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.system.SmOrgType;
/**
 * 组织机构类型dao
 *
 * @author 张中原
 *
 */
@Repository("smOrgTypeDao")
public class SmOrgTypeDao extends HibernateDao<SmOrgType> {

}
