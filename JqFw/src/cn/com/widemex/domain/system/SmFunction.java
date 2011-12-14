package cn.com.widemex.domain.system;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import cn.com.widemex.core.vo.TreeVO;

/**
 * SmFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_function", catalog = "wm_sys")
public class SmFunction extends TreeVO implements java.io.Serializable {

	// Fields

	private String id;
	@JsonBackReference
	private SmFunction parentFun;
	private String code;
	private String name;
	private String type;
	private String funPath;
	private Integer orderInd;
	private String iconCls;
	private String remark;
	private Short status;
	private String uiPath;
	private Set<SmRole> smRoles = new HashSet<SmRole>(0);
	private Set<SmFunction> childrenFuns = new HashSet<SmFunction>(0);

	// Constructors

	/** default constructor */
	public SmFunction() {
	}

	/** minimal constructor */
	public SmFunction(String id, String code, String name, String type,
			Short status) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.status = status;
	}

	/** full constructor */
	public SmFunction(String id, SmFunction smFunction, String code,
			String name, String type, String funPath, Integer orderInd,
			String iconCls, String remark, Short status, String uiPath,
			Set<SmFunction> smFunctions) {
		this.id = id;
//		this.smFunction = smFunction;
		this.code = code;
		this.name = name;
		this.type = type;
		this.funPath = funPath;
		this.orderInd = orderInd;
		this.iconCls = iconCls;
		this.remark = remark;
		this.status = status;
		this.uiPath = uiPath;
//		this.smRoleFunMaps = smRoleFunMaps;
//		this.smFunctions = smFunctions;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ID")
	public SmFunction getParentFun() {
		return parentFun;
	}

	public void setParentFun(SmFunction parentFun) {
		this.parentFun = parentFun;
	}

	@Column(name = "CODE", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", nullable = false, length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "FUN_PATH", length = 2000)
	public String getFunPath() {
		return this.funPath;
	}

	public void setFunPath(String funPath) {
		this.funPath = funPath;
	}

	@Column(name = "ORDER_IND")
	public Integer getOrderInd() {
		return this.orderInd;
	}

	public void setOrderInd(Integer orderInd) {
		this.orderInd = orderInd;
	}

	@Column(name = "ICON_CLS", length = 20)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "REMARK", length = 400)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "UI_PATH", length = 2000)
	public String getUiPath() {
		return this.uiPath;
	}

	public void setUiPath(String uiPath) {
		this.uiPath = uiPath;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)   
	@JoinTable(name = "sm_role_fun_map", catalog = "wm_sys", joinColumns = @JoinColumn(name = "FUN_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	public Set<SmRole> getSmRoles() {
		return smRoles;
	}

	public void setSmRoles(Set<SmRole> smRoles) {
		this.smRoles = smRoles;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smFunction")
//	public Set<SmRoleFunMap> getSmRoleFunMaps() {
//		return this.smRoleFunMaps;
//	}
//
//	public void setSmRoleFunMaps(Set<SmRoleFunMap> smRoleFunMaps) {
//		this.smRoleFunMaps = smRoleFunMaps;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentFun")
	@OrderBy(value="orderInd asc")
	public Set<SmFunction> getChildrenFuns() {
		return childrenFuns;
	}

	public void setChildrenFuns(Set<SmFunction> childrenFuns) {
		this.childrenFuns = childrenFuns;
	}

}