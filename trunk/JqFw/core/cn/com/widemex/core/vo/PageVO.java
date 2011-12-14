package cn.com.widemex.core.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import cn.com.widemex.core.data.MyHashMap;
import cn.com.widemex.core.data.MyMap;
import cn.com.widemex.core.utils.web.WebUtils;

/**
 * 分页值对象
 * 用于存放一页数据以及关于该页的相关信息
 * @author 张中原
 *
 */
@JsonIgnoreProperties(value={"where", "whereParams", "sortList"})
public class PageVO extends MsgVO{
	/**树节点*/
//	private String node;

	/**记录总数*/
    private int total;

	/**行数*/
    private int limit;
    
	/** 起始行 */
	private int start;
	
	/** 查询条件 */
	private String where = "";
	
	/** 查询条件的参数 数组*/
	private Object[] whereParams = new Object[]{}; 
	
	/**排序的信息集合*/
	private List<SortVO> sortList;
	
//	/**定制参数*/
//	private MyMap myParams;
	
	/**构造函数：空*/
    public PageVO(){}
    
	/**
	 * 有参构造函数：请求对象转换成参数
	 * @param request 请求对象
	 */
	public PageVO(HttpServletRequest request){
		super(request);
//		super.setParams(WebUtils.getParamsMap(request));
	}
    
	/**
	 * 获取MsgVO对象
	 * @param request 请求对象
	 * @return
	 */
	public static PageVO valueOf(HttpServletRequest request){
		PageVO vo = new PageVO( request );
		
		// 参数map
		MyMap map = vo.getParams(); 
		
		// 处理页面大小
		if(map.get("limit") != null){
			vo.setLimit(map.getInt("limit", 0));
			map.remove("limit");	
		}
		
		// 处理页面
		if(map.get("start") != null){
			vo.setStart(map.getInt("start", 0));
			map.remove("start");
		}
		
		// 处理where语句
		if(map.get("where") != null){
			vo.setWhere(map.getString("where", ""));
			map.remove("where");
		}
		
		// 处理排序信息
		if(map.get("sort") != null){
			List<SortVO> list = new ArrayList<SortVO>();
			list.add(new SortVO(map.getString("sort"), map.getString("order", "ASC")));
			vo.setSortList(list);
			
			map.remove("sort");
			map.remove("order");
		}
		
		
		
//		if(map.getString("sort") != null){
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				vo.setSortList((List<SortVO>) mapper.readValue(map.getString("sort"), TypeFactory.collectionType(ArrayList.class, SortVO.class)));
//				map.remove("sort");
//			} catch (Exception e) {
//				System.err.println("排序参数转换成集合时报错：：：" + e.toString());
//			}	
//		}
		
		// 获取定制参数信息
//		if(map.getString("myParams") != null){
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				vo.setMyParams((MyMap) mapper.readValue(map.getString("myParams"), TypeFactory.mapType(MyHashMap.class, String.class, String.class)));
//				map.remove("myParams");
//			} catch (Exception e) {
//				System.err.println("定制参数转换成Map时报错：：：" + e.toString());
//			}	
//		}
		
//		// 获取树节点
//		if(map.getString("node") != null){
//			vo.setNode(map.getString("node"));
//		}
		
		return vo;
	}

    /**
     * 构造
     * @param result
     * @param totalCount
     */
    public PageVO(Object result, int totalCount) {
        this.setRows(result);
        this.total = totalCount;
    }

    /**
     * 构造
     * @param start
     * @param limit
     * @param where
     */
    public PageVO(int start, int limit, String where) {
        this.start = start;
        this.limit = limit;
        this.where = where;
    }

    /**
     * 构造
     * @param start
     * @param limit
     * @param where
     * @param params
     */
    public PageVO(int start, int limit, String where, Object... params) {
        this.start = start;
        this.limit = limit;
        this.where = where;
    }
    
//
//	/**
//	 * @return the totalCount
//	 */
//	public int getTotalCount() {
//		return totalCount;
//	}
//
//	/**
//	 * @param totalCount the totalCount to set
//	 */
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(String where) {
		this.where = where;
	}
	
	public List<SortVO> getSortList() {
		return sortList;
	}

	public void setSortList(List<SortVO> sortList) {
		this.sortList = sortList;
	}
	
	public Object[] getWhereParams() {
		return whereParams;
	}

	public void setWhereParams(Object[] whereParams) {
		this.whereParams = whereParams;
	}
	
    
//	public MyMap getMyParams() {
//		return myParams;
//	}
//
//	public void setMyParams(MyMap myParams) {
//		this.myParams = myParams;
//	}
//	
	
//	public String getNode() {
//		return node;
//	}
//
//	public void setNode(String node) {
//		this.node = node;
//	}
	
    
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
