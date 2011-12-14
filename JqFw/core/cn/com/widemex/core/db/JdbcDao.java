package cn.com.widemex.core.db;
//package cn.com.myext.core.db;
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.jdbc.core.support.JdbcDaoSupport;
//
//import cn.com.myext.core.utils.GenericsUtils;
//import cn.com.myext.core.vo.PageVO;
//
///** 
// * JDBC支持
// * @author 张中原
// * @since 2010-6-26
// * @version ExtFw3.0
// * @param <T>
// */
//public class JdbcSupport<T, ID extends Serializable> extends JdbcDaoSupport implements ExtDao<T, ID >{
////	protected static Log logger = LogFactory.getLog(JdbcSupport.class);
////	protected Class<T> entityClass;
////    public JdbcSupport() {
////    	//获得范型参数的类型
////    	this.entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
////    }
////    
////    /**
////     * 删除
////     */
////	public boolean remove(String sql, Object... params) {
////		if(this.getJdbcTemplate().update(sql, params) > 0){
////			return true;
////		}
////		return false;
////	}
////	public boolean remove(String sql, Object[] params, int[] types) {
////		if(this.getJdbcTemplate().update(sql, params, types) > 0){
////			return true;
////		}
////		return false;
////	}
////	
////	/**
////	 * 
////	 * @param hql
////	 * @param params
////	 * @return
////	 */
////    public int executeUpdate(String sql, Object... params){
////    	return this.getJdbcTemplate().update(sql, params);
////    }
////    public int executeUpdate(String sql, Object[] params, int[] types){
////    	return this.getJdbcTemplate().update(sql, params, types);
////    }
////
////    //待实现======================================
////	public List<T> find(String hql, Object... params) {
////		// TODO Auto-generated method stub
////		//SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
////		return null;
////	}
////
////	public List<T> find(String hql, boolean isHql, Object... params) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public List<T> findBy(String name, Object value) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public List<T> findBy(String name, Object value, String sort, boolean isAsc) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public T findUniqueBy(String name, Object value) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public T get(Serializable id) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public boolean isUnique(String hql, Object... params) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public PageVO pagedQuery(int start, int limit, String where,
////			Object... params) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	public boolean remove(T id) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean remove(Serializable id) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean removeAll(Collection<T> list) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean save(T entity) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean saveOrUpdate(T entity) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean saveOrUpdateAll(Collection<T> entities) {
////		// TODO Auto-generated method stub
////		return false;
////	}
////
////	public boolean update(T entity) {
////		// TODO Auto-generated method stub
////		return false;
////	}
//    
//}
