package cn.com.widemex.domain.demo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DemoUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_user", catalog = "wm_demo")
public class DemoUser implements java.io.Serializable {

	// Fields

	private String id;
	private DemoType demoType;
	private DemoDept demoDept;
	private String acc;
	private String pwd;
	private Short status;
	private String email;
	private String name;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public DemoUser() {
	}

	/** minimal constructor */
	public DemoUser(String id, DemoType demoType, DemoDept demoDept,
			String acc, String pwd, Short status, String name,
			Timestamp createTime) {
		this.id = id;
		this.demoType = demoType;
		this.demoDept = demoDept;
		this.acc = acc;
		this.pwd = pwd;
		this.status = status;
		this.name = name;
		this.createTime = createTime;
	}

	/** full constructor */
	public DemoUser(String id, DemoType demoType, DemoDept demoDept,
			String acc, String pwd, Short status, String email, String name,
			Timestamp createTime) {
		this.id = id;
		this.demoType = demoType;
		this.demoDept = demoDept;
		this.acc = acc;
		this.pwd = pwd;
		this.status = status;
		this.email = email;
		this.name = name;
		this.createTime = createTime;
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
	@JoinColumn(name = "TYPE_ID", nullable = false)
	public DemoType getDemoType() {
		return this.demoType;
	}

	public void setDemoType(DemoType demoType) {
		this.demoType = demoType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = false)
	public DemoDept getDemoDept() {
		return this.demoDept;
	}

	public void setDemoDept(DemoDept demoDept) {
		this.demoDept = demoDept;
	}

	@Column(name = "ACC", nullable = false, length = 32)
	public String getAcc() {
		return this.acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	@Column(name = "PWD", nullable = false, length = 32)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "STATUS", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "EMAIL", length = 62)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CREATE_TIME", nullable = false, length = 0)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}