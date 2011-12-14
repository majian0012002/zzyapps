package cn.com.widemex.domain.demo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.widemex.core.vo.TreeVO;

/**
 * DemoDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_dept", catalog = "wm_demo")
public class DemoDept extends TreeVO implements java.io.Serializable {

	// Fields

	private String id;
	private DemoDept pDept;
	private String code;
	private String name;
	private Short status;
	private Timestamp createTime;
	private Set<DemoUser> demoUsers = new HashSet<DemoUser>(0);
	private Set<DemoDept> demoDepts = new HashSet<DemoDept>(0);

	// Constructors

	/** default constructor */
	public DemoDept() {
	}

	/** minimal constructor */
	public DemoDept(String id, DemoDept demoDept, String code, String name,
			Short status, Timestamp createTime) {
		this.id = id;
//		this.demoDept = demoDept;
		this.code = code;
		this.name = name;
		this.status = status;
		this.createTime = createTime;
	}


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		// 初始化父节点
		this.setParentId(this.getpDept()!=null ? this.getpDept().getId() : null);
		
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "P_ID", nullable = false)
//	public DemoDept getDemoDept() {
//		return this.demoDept;
//	}
//
//	public void setDemoDept(DemoDept demoDept) {
//		this.demoDept = demoDept;
//	}

	@Column(name = "code", nullable = false, length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME", nullable = false, length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "demoDept")
	public Set<DemoUser> getDemoUsers() {
		return this.demoUsers;
	}

	public void setDemoUsers(Set<DemoUser> demoUsers) {
		this.demoUsers = demoUsers;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "pDept")
	public Set<DemoDept> getDemoDepts() {
		return this.demoDepts;
	}

	public void setDemoDepts(Set<DemoDept> demoDepts) {
		this.demoDepts = demoDepts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ID", nullable = true)
	public DemoDept getpDept() {
		return pDept;
	}

	public void setpDept(DemoDept pDept) {
		this.pDept = pDept;
	}
}