package cn.com.widemex.core.vo;
/**
 * 排序VO
 * @author 张中原
 *
 */
public class SortVO {
	
	/**要排序的字段属性*/
	private String property;
	
	/**排序形式：ASC ,DESC */
	private String direction;
	
	public SortVO(){};
	
	/**
	 * 排序vo构造函数
	 * @param property 排序字段
	 * @param direction 排序方式：ASC,DESC
	 */
	public SortVO(String property, String direction){
		this.property = property;
		
		this.direction = (direction==null || direction.equals("") ) ? "ASC" : direction;
	}
	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
