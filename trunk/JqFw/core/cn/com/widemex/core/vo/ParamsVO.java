package cn.com.widemex.core.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 参数VO
 * @author 查询参数类
 *
 */
public class ParamsVO {
	/**参数名称*/
	private String name;
	/**参数值*/
	private Object value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	} 
}
