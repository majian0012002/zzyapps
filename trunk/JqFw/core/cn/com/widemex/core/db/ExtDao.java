package cn.com.widemex.core.db;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.com.widemex.core.vo.PageVO;


/**
 * 通用DAO接口
 * @author 张中元
 *
 * @param <T> pojo类
 * @param <ID> 主键类型
 */
public interface ExtDao<T>/*<T, ID extends Serializable> extends GenericDAO<T, ID> */ {
	
    /**
     * 根据ID得到po
     * @param id
     * @return
     */
	public T get(Serializable id);
	
	/**
	 * 查询
	 * 默认为HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, Object... params);
	
	/**
	 * 查询
	 * 允许设置是否是HQL查询
	 * @param hql
	 * @param isHql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, boolean isHql, Object... params);
	
	/**
	 * 查询
	 * 根据字段、值返回符合条件的记录集
	 * @param name
	 * @param value
	 * @return
	 */
	public List<T> findBy(String name, Object value);
	
	/**
	 * 查询
	 * 根据字段、值返回符合条件的记录集
	 * 允许设置排序
	 * @param name
	 * @param value
	 * @param sort
	 * @param isAsc
	 * @return
	 */
	public List<T> findBy(String name, Object value, String sort, boolean isAsc);
	
	/**
	 * 查询
	 * 返回符合条件的第一条记录
	 * @param name
	 * @param value
	 * @return
	 */
	public T findUniqueBy(String name, Object value);
	
    /**
     * 分页查询
     * @param start
     * @param limit
     * @param where
     * @param params
     * @return
     */
	public PageVO findPage(int start, int limit, String where, Object... params);
	
	/**
	 * 分页
	 * @param page 分页对象
	 * @return
	 */
    public PageVO findPage(PageVO page);
	
	/**
	 * 保存
	 * 新增或更新
	 * @param entity
	 * @return
	 */
	public boolean saveOrUpdate(T entity);
	
	/**
	 * 批量保存
	 * 新增或更新
	 * @param entities
	 * @return
	 */
	public boolean saveOrUpdateAll(Collection<T> entities);
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public boolean save(T entity);
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	public boolean update(T entity);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean remove(T id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean remove(Serializable id);
	
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	public boolean removeAll(Collection<T> list);
	
	/**
	 * 删除
	 * @param hql
	 * @param params
	 * @return
	 */
	public boolean remove(String hql, Object... params);
	
	/**
	 * 删除
	 * @param hql
	 * @param params
	 * @param types
	 * @return
	 */
	public boolean remove(String hql, Object[] params, int[] types);
	
	/**
	 * 是否唯一 
	 * @param COL=? ...
	 * @param 查询参数
	 * @return
	 */
	public boolean isUnique(String hql, Object... params);
	
}
