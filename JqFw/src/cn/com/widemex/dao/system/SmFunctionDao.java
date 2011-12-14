package cn.com.widemex.dao.system;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.system.SmFunction;
/**
 * 功能 dao
 *
 * @author 张中原
 *
 */
@Repository("smFunctionDao")
public class SmFunctionDao extends HibernateDao<SmFunction> {

}
