package cn.com.widemex.dao.system;

import org.springframework.stereotype.Repository;

import cn.com.widemex.core.db.HibernateDao;
import cn.com.widemex.domain.system.SmUser;
/**
 * 用户 dao
 *
 * @author 张中原
 *
 */
@Repository("smUserDao")
public class SmUserDao extends HibernateDao<SmUser> {

}
