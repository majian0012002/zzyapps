package cn.com.widemex.core.db;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.com.widemex.core.utils.data.StrHelper;
import cn.com.widemex.core.utils.data.Type;
import cn.com.widemex.core.utils.reflection.GenericsUtils;
import cn.com.widemex.core.vo.PageVO;
import cn.com.widemex.core.vo.SortVO;

/**
 * DAO基本实现类
 * @author 张中元
 *
 * @param <T> pojo名称
 * @param <ID> 主键类型
 */
public class HibernateDao<T> extends HibernateDaoSupport{
	/**实体类型*/
	protected Class<T> entityClass;
	/**基本hql语句*/
	public String entityHql;
    
	/**
	 * 实例化dao
	 */
	public HibernateDao() {
    	//获得范型参数的类型
        this.entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
        //根据范型参数初始化HQL
        this.entityHql = "from " + this.entityClass.getName() + " ";
    }
    
///////////////////////////////查询---start/////////////////////////////////////////////////////   
    /**
     * 根据ID得到po
     * @param id
     * @return
     */
    public T get(Serializable id) {
        if (id == null) {
            return null;
        } else {
            return this.getHibernateTemplate().get(this.entityClass, id);
        }
    }
    
    /**
     * 根据ID得到po(from 缓存)
     * @param id
     * @return
     */
    public T load(Serializable id) {
        return this.getHibernateTemplate().load(this.entityClass, id);
    }
    
    /**
     * 根据hql查询
     * @param hql
     * @param params
     * @return
     */
    public List<T> find(String hql, Object... params) {
        return createQuery(hql, params).list();
    }
//    public List<T> find(String hql, boolean isHql, Object... params) {
//        return createQuery(hql, isHql, params).list();
//    }
    /**
     * 根据hql语句获取第一条记录
     */
    public T findUnique(String hql, Object... params) {
        return (T) createQuery(hql, params).uniqueResult();
    }
    
	/**
	 * 是否唯一 
	 * @param COL=? ...
	 * @param 查询参数
	 * @return
	 */
    public boolean isUnique(String hql, Object... params){
    	return this.getCount(hql, params) == 0;
    }
    
	/**
	 * 分页查询
	 * @param page 分页对象
	 * @return
	 */
    public PageVO findPage(PageVO page) {
    	Object[] params = page.getWhereParams();
    	int count = 0;
    	String hql = page.getWhere();
    	if(hql.toUpperCase().indexOf("SELECT") != 0){
    		if(this.entityHql.indexOf("where") > 0){
    			hql = hql.replace("where", "and");
    		}
    		hql = this.entityHql + hql;
    	}
    	
    	// 处理排序
    	if(page.getSortList()!=null && page.getSortList().size()>0){
    		hql += " ORDER BY ";
    		
    		List<SortVO> sortList = page.getSortList();
    		
    		for (int i = 0; i < sortList.size(); i++) {
    			SortVO sortVO = sortList.get(i);
    			hql += sortVO.getProperty() + "  " + sortVO.getDirection() + ((i+1) == sortList.size() ? " " : ",  ");
			}
    		
    	}
    	
    	Query query = createQuery(hql, params);
    	if(page.getLimit() != 0){
    		count = this.getCount(page.getWhere(), params);
	    	query = query.setFirstResult(page.getStart());
	    	query = query.setMaxResults(page.getLimit());
    	}
    	List<T> results = query.list();
        page.setTotal(count);
        page.setRows(results);
        return page;
    }
    
    /**
     * 分页查询
     * @param start 开始记录
     * @param limit 最大行数
     * @param where where条件
     * @param params where条件参数
     * @return
     */
    public PageVO findPage(int start, int limit, String where, Object... params) {
        return findPage(new PageVO(start, limit, where, params));
    }

    /**
     * 分页查询
     * @param start 开始记录
     * @param limit 最大行数
     * @param where 查询条件
     * @return
     */
    public PageVO findPage(int start, int limit, String where) {
        return findPage(new PageVO(start, limit, where));
    }
    
    /**
     * 分页查询
     * @param where 查询条件
     * @param params 动态参数
     * @return
     */
    public PageVO findPage(String where, Object... params) {
        return findPage(new PageVO(0, 0, where, params));
    }
    
    /**
     * 分页查询
     * @param where 查询条件
     * @return
     */
    public PageVO findPage(String where) {
        return findPage(new PageVO(0, 0, where));
    }
    
    /**
     * 返回记录条数
     * @param where 查询条件
     * @param params 查询参数
     * @return
     */
    public int getCount(String where, Object... params) {
    	return this.getCount(where, true, params);
    }
    
    /**
     * 返回记录条数
     * @param where 查询条件
     * @param isHql 是否是HQL
     * @param params 查询参数
     * @return
     */
    public int getCount(String where, boolean isHql, Object... params) {
    	int i = (where.toUpperCase()).indexOf("ORDER BY");
    	if(i >= 0) where = where.substring(0, i);
    	if(where.toUpperCase().indexOf("WHERE") < 0 && params.length > 0) where = " where " + where;
    	String hql = "";
		if(where.toUpperCase().indexOf("SELECT COUNT(1)") != 0){
	    	if(where.toUpperCase().trim().indexOf("SELECT") != 0 && where.toUpperCase().trim().indexOf("FROM") != 0){
	    		if(this.entityHql.toUpperCase().indexOf("WHERE") > 0){
	    			where = where.replace("where", "and");
	    		}
	    		hql = "select count(*) " + this.entityHql + where;
	    	}
	    	else{
	    		hql = "select count(*) " + where.substring(where.toUpperCase().indexOf("FROM"));
	    	}
		}
		else{
			hql = where;
		}
		
		hql = hql.replaceAll("fetch", "");
		hql = hql.replaceAll("FETCH", "");
		
    	return Type.toInt(createQuery(hql, isHql, params).uniqueResult());
    }
    
///////////////////////////////查询---end/////////////////////////////////////////////////////   
    
    
    
    
    
///////////////////////////////保存、更新---start////////////////////////////////////////////////

    /**
     * 保存或者更新pojo
     * @param entity pojo对象
     */
    public boolean saveOrUpdate(T entity) {
		return this.saveOrUpdate(entity, 0);
    }
    
    /**
     * 保存或更新pojo集合
     * @param entities 集合
     */
    public boolean saveOrUpdateAll(Collection<T> entities){
        return this.saveOrUpdate(entities, 3);
    }
    
    /**
     * 保存pojo
     * @param entity pojo对象
     */
    public boolean save(T entity){
        return this.saveOrUpdate(entity, 1);
    }
    
    /**
     * 更新pojo
     * @param entity pojo对象
     */
    public boolean update(T entity) {
		return this.saveOrUpdate(entity, 2);
    }
    
    /**
     * 根据标识来判断是保存、更新、集合保存更新
     * @param entity pojo对象
     * @param flag	标识     0：保存或更新 ； 1：保存； 2：更新； 3：保存或者更新集合
     * @return
     */
    private boolean saveOrUpdate(Object entity, int flag){
		switch (flag) {
    		case 0:
    			this.getHibernateTemplate().saveOrUpdate((T) entity);
    			break;
    		case 1:
    			this.getHibernateTemplate().save((T)entity);
    			break;
    		case 2:
    			this.getHibernateTemplate().update((T)entity);
    			break;
    		case 3:
    			this.getHibernateTemplate().saveOrUpdateAll((Collection<T>)entity);
    			break;
		}
		return true;
    }
///////////////////////////////保存、更新---end////////////////////////////////////////////////////////////////    
    
    
    
    
///////////////////////////////删除---start////////////////////////////////////////////////////////////////
    /**
     * 删除pojo
     * @param entity pojo对象
     */
    public boolean remove(T entity) {
    	return this.remove(null, entity);
    }
    
    /**
     * 根据主键删除
     * @param id 主键
     */
    public boolean remove(Serializable id) {
    	return this.remove(id, null);
    }    
    
    /**
     * 删除全部
     * @return
     */
    public boolean removeAll() {
        return this.removeAll(this.getAll());
    }
    
    /**
     * 删除集合里的所有信息
     */
    public boolean removeAll(Collection<T> list) {
    	boolean success = false;
 		for (T obj : list) {
 			success = this.remove(null, obj);
 			if(!success) return false;
      	}
    	return success;
    }
    /**
     * 删除记录：优先根据id删除，如果id在数据库中不存在，则根据pojo对象删除
     * @param id 主键
     * @param entity pojo对象
     * @return
     */
    private boolean remove(Serializable id, T entity) {
    	Object obj = id == null ? entity : this.get(id);
    	this.getHibernateTemplate().delete(obj);
    	return true;
    }
    
    /**
     * 删除记录：根据hql语句及其参数
     */
	public boolean remove(String hql, Object... params) {
		return this.executeUpdate(hql, params) > 0;
	}

///////////////////////////////删除---end////////////////////////////////////////////////////////////////
	
	
    /**
     * 根据QBC条件创建Criteria
     * @param criterions
     * @return
     */
    private Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }
    private Criteria createCriteria(String sort, boolean isAsc, Criterion... criterions) {
        Criteria criteria = this.createCriteria(criterions);
        if(sort != null){
            if (isAsc) {
                criteria.addOrder(Order.asc(sort));
            }
            else {
                criteria.addOrder(Order.desc(sort));
            }
        }
        return criteria;
    }
    
    /**
     * 单条件QBC查询
     * @param name
     * @param value
     * @return
     */
    public List<T> findBy(String name, Object value) {
        return createCriteria(Restrictions.eq(name, value)).list();
    }
    public List<T> findBy(String name, Object value, String sort, boolean isAsc) {
        return createCriteria(sort, isAsc, Restrictions.eq(name, value)).list();
    }
    public List<T> findByLike(String name, Object value) {
        return createCriteria(Restrictions.like(name, value)).list();
    }
    public List<T> findByLike(String name, Object value, String sort, boolean isAsc) {
        return createCriteria(sort, isAsc, Restrictions.like(name, value)).list();
    }
    public T findUniqueBy(String name, Object value) {
        return (T) createCriteria(Restrictions.eq(name, value))
                       .setMaxResults(1).uniqueResult();
    }
    
    /**
     * QBC排序、返回所有记录
     * @param sort
     * @param isAsc
     * @return
     */
    public List<T> getAll(String sort, boolean isAsc) {
    	Criteria criteria = createCriteria();
		if ((sort == null) || sort.trim().equals("")) {
			///
		} 
		else {
			if (isAsc) {
				criteria.addOrder(Order.asc(sort));
			} else {
				criteria.addOrder(Order.desc(sort));
			}
		}
		return criteria.list();
	}
    
    public List<T> getAll(){
    	return this.getAll(null, false);
    }
    
    /**
     * executeUpdate
     * @param hql
     * @param isHql
     * @param params
     */
    public int executeUpdate(String hql, boolean isHql, Object... params){
    	Query query = this.createQuery(hql, isHql, params);
    	return query.executeUpdate();
    }
    public int executeUpdate(String hql, Object... params){
    	return this.executeUpdate(hql, true, params);
    }
    
    /**
     * 根据hql、params返回query
     * @param hql
     * @param params
     * @return
     */
    public Query createQuery(String hql, boolean isHql, Object... params) {
        Query query = null;
        Session session = this.getSession();
        if(isHql){
        	if(hql.indexOf("from") < 0 && hql.indexOf("where") < 0) hql = this.entityHql + " where " + hql;
        	query = session.createQuery(hql);
        }
        else{
        	query = session.createSQLQuery(hql);
        }
        if(params != null){
        	int pLen = 0;
	        for (int i = 0; i < params.length; i++) {
	        	Object v = params[i];
	        	if(StrHelper.isEmpty(v + "") && 
	        			hql.toUpperCase().indexOf("WHERE") > 0 && 
	        			hql.toUpperCase().indexOf("UPDATE") < 0){
	        		continue;
	        	}
	        	if(v != null && v.getClass().isArray()){
	        		query.setParameterList("value" + i, (Object[]) params[i]);
	        	}
	        	else{
	        		query.setParameter(pLen++, params[i]);
	        	}
	        }
        }
        return query;
    }
    
    
    public Query createQuery(String hql, Object... params) {
        return this.createQuery(hql, true, params);
    }
}
