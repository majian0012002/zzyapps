package cn.com.widemex.domain.system;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SmRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_role", catalog = "wm_sys")
public class SmRole implements java.io.Serializable {

	// Fields

	private String id;
	private SmOrg smOrg;
	private String name;
	private String type;
	private String remark;
	private Short status;
	private Set<SmUser> smUsers = new HashSet<SmUser>(0);
	private Set<SmFunction> smFuns = new HashSet<SmFunction>(0);

	// Constructors


	/** default constructor */
	public SmRole() {
	}

	/** minimal constructor */
	public SmRole(String id, SmOrg smOrg, String name, String type, Short status) {
		this.id = id;
		this.smOrg = smOrg;
		this.name = name;
		this.type = type;
		this.status = status;
	}

	/** full constructor */
	public SmRole(String id, SmOrg smOrg, String name, String type,
			String remark, Short status) {
		this.id = id;
		this.smOrg = smOrg;
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.status = status;
//		this.smUserRoleMaps = smUserRoleMaps;
//		this.smRoleFunMaps = smRoleFunMaps;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "SmRole")    
    @GenericGenerator(name = "SmRole", strategy = "uuid")
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

	@Column(name = "NAME", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", nullable = false, length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "REMARK", length = 160)
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
	
	//@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH},mappedBy="smRoles",targetEntity=SmUser.class)
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "SM_USER_ROLE_MAP", catalog = "wm_sys", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	public Set<SmUser> getSmUsers() {
		return smUsers;
	}

	public void setSmUsers(Set<SmUser> smUsers) {
		this.smUsers = smUsers;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smRole")
//	public Set<SmUserRoleMap> getSmUserRoleMaps() {
//		return this.smUserRoleMaps;
//	}
//
//	public void setSmUserRoleMaps(Set<SmUserRoleMap> smUserRoleMaps) {
//		this.smUserRoleMaps = smUserRoleMaps;
//	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "sm_role_fun_map", catalog = "wm_sys", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "FUN_ID"))
	public Set<SmFunction> getSmFuns() {
		return smFuns;
	}

	public void setSmFuns(Set<SmFunction> smFuns) {
		this.smFuns = smFuns;
	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smRole")
//	public Set<SmRoleFunMap> getSmRoleFunMaps() {
//		return this.smRoleFunMaps;
//	}
//
//	public void setSmRoleFunMaps(Set<SmRoleFunMap> smRoleFunMaps) {
//		this.smRoleFunMaps = smRoleFunMaps;
//	}

}