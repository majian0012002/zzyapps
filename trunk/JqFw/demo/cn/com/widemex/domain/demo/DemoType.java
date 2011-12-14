package cn.com.widemex.domain.demo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DemoType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_type", catalog = "wm_demo")
public class DemoType implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String typeName;
	private Short status;
	private Timestamp createTime;
	private Set<DemoUser> demoUsers = new HashSet<DemoUser>(0);

	// Constructors

	/** default constructor */
	public DemoType() {
	}

	/** minimal constructor */
	public DemoType(String id, String code, String typeName, Short status,
			Timestamp createTime) {
		this.id = id;
		this.code = code;
		this.typeName = typeName;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public DemoType(String id, String code, String typeName, Short status,
			Timestamp createTime, Set<DemoUser> demoUsers) {
		this.id = id;
		this.code = code;
		this.typeName = typeName;
		this.status = status;
		this.createTime = createTime;
		this.demoUsers = demoUsers;
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

	@Column(name = "CODE", nullable = false, length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TYPE_NAME", nullable = false, length = 32)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "demoType")
	public Set<DemoUser> getDemoUsers() {
		return this.demoUsers;
	}

	public void setDemoUsers(Set<DemoUser> demoUsers) {
		this.demoUsers = demoUsers;
	}

}