package cn.com.widemex.domain.system;

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

/**
 * SmOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_org", catalog = "wm_sys")
public class SmOrg implements java.io.Serializable {

	// Fields

	private String id;
	private SmOrgType smOrgType;
	private SmOrg smOrg;
	private String name;
	private String code;
	private String fullName;
	private String remark;
	private Integer status;
	private Set<SmRole> smRoles = new HashSet<SmRole>(0);
	private Set<SmOrg> smOrgs = new HashSet<SmOrg>(0);
	private Set<SmUser> smUsers = new HashSet<SmUser>(0);

	// Constructors

	/** default constructor */
	public SmOrg() {
	}

	/** minimal constructor */
	public SmOrg(String id, SmOrgType smOrgType, String name, Integer status) {
		this.id = id;
		this.smOrgType = smOrgType;
		this.name = name;
		this.status = status;
	}

	/** full constructor */
	public SmOrg(String id, SmOrgType smOrgType, SmOrg smOrg, String name,
			String code, String fullName, String remark, Integer status,
			Set<SmRole> smRoles, Set<SmOrg> smOrgs, Set<SmUser> smUsers) {
		this.id = id;
		this.smOrgType = smOrgType;
		this.smOrg = smOrg;
		this.name = name;
		this.code = code;
		this.fullName = fullName;
		this.remark = remark;
		this.status = status;
		this.smRoles = smRoles;
		this.smOrgs = smOrgs;
		this.smUsers = smUsers;
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
	public SmOrgType getSmOrgType() {
		return this.smOrgType;
	}

	public void setSmOrgType(SmOrgType smOrgType) {
		this.smOrgType = smOrgType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public SmOrg getSmOrg() {
		return this.smOrg;
	}

	public void setSmOrg(SmOrg smOrg) {
		this.smOrg = smOrg;
	}

	@Column(name = "NAME", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "FULL_NAME", length = 200)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "REMARK", length = 160)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smOrg")
	public Set<SmRole> getSmRoles() {
		return this.smRoles;
	}

	public void setSmRoles(Set<SmRole> smRoles) {
		this.smRoles = smRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smOrg")
	public Set<SmOrg> getSmOrgs() {
		return this.smOrgs;
	}

	public void setSmOrgs(Set<SmOrg> smOrgs) {
		this.smOrgs = smOrgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smOrg")
	public Set<SmUser> getSmUsers() {
		return this.smUsers;
	}

	public void setSmUsers(Set<SmUser> smUsers) {
		this.smUsers = smUsers;
	}

}