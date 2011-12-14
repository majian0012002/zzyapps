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
import javax.persistence.Table;

/**
 * SmUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_user", catalog = "wm_sys")
public class SmUser implements java.io.Serializable {

	// Fields

	private String id;
	private SmOrg smOrg;
	private String acc;
	private String pwd;
	private String name;
	private String code;
	private String type;
	private String phone;
	private String email;
	private String remark;
	private Short status;
	private Set<SmRole> smRoles = new HashSet<SmRole>(0);

	// Constructors

	/** default constructor */
	public SmUser() {
		//this.smRoles.add(new SmRole());
	}

	/** minimal constructor */
	public SmUser(String id, SmOrg smOrg, String acc, String pwd, String name,
			String type, Short status) {
		this.id = id;
		this.smOrg = smOrg;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.type = type;
		this.status = status;
	}

	/** full constructor */
	public SmUser(String id, SmOrg smOrg, String acc, String pwd, String name,
			String code, String type, String phone, String email,
			String remark, Short status) {
		this.id = id;
		this.smOrg = smOrg;
		this.acc = acc;
		this.pwd = pwd;
		this.name = name;
		this.code = code;
		this.type = type;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.status = status;
//		this.smUserRoleMaps = smUserRoleMaps;
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
	@JoinColumn(name = "ORG_ID", nullable = false)
	public SmOrg getSmOrg() {
		return this.smOrg;
	}

	public void setSmOrg(SmOrg smOrg) {
		this.smOrg = smOrg;
	}

	@Column(name = "ACC", nullable = false, length = 40)
	public String getAcc() {
		return this.acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	@Column(name = "PWD", nullable = false, length = 128)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "NAME", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TYPE", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "REMARK", length = 200)
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
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)   
	@JoinTable(name = "SM_USER_ROLE_MAP", catalog = "wm_sys", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	public Set<SmRole> getSmRoles() {
		return smRoles;
	}

	public void setSmRoles(Set<SmRole> smRoles) {
		this.smRoles = smRoles;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smUser")
//	public Set<SmUserRoleMap> getSmUserRoleMaps() {
//		return this.smUserRoleMaps;
//	}
//
//	public void setSmUserRoleMaps(Set<SmUserRoleMap> smUserRoleMaps) {
//		this.smUserRoleMaps = smUserRoleMaps;
//	}

}