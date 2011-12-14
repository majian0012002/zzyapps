package cn.com.widemex.domain.system;

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
 * SmOrgType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_org_type", catalog = "wm_sys")
public class SmOrgType implements java.io.Serializable {

	// Fields

	private String orgTypeId;
	private String typeName;
	private Integer orgLevel;
	private Integer status;
	private Set<SmOrg> smOrgs = new HashSet<SmOrg>(0);

	// Constructors

	/** default constructor */
	public SmOrgType() {
	}

	/** minimal constructor */
	public SmOrgType(String orgTypeId, String typeName, Integer orgLevel,
			Integer status) {
		this.orgTypeId = orgTypeId;
		this.typeName = typeName;
		this.orgLevel = orgLevel;
		this.status = status;
	}

	/** full constructor */
	public SmOrgType(String orgTypeId, String typeName, Integer orgLevel,
			Integer status, Set<SmOrg> smOrgs) {
		this.orgTypeId = orgTypeId;
		this.typeName = typeName;
		this.orgLevel = orgLevel;
		this.status = status;
		this.smOrgs = smOrgs;
	}

	// Property accessors
	@Id
	@Column(name = "ORG_TYPE_ID", unique = true, nullable = false, length = 32)
	public String getOrgTypeId() {
		return this.orgTypeId;
	}

	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	@Column(name = "TYPE_NAME", nullable = false, length = 40)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "ORG_LEVEL", nullable = false)
	public Integer getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smOrgType")
	public Set<SmOrg> getSmOrgs() {
		return this.smOrgs;
	}

	public void setSmOrgs(Set<SmOrg> smOrgs) {
		this.smOrgs = smOrgs;
	}

}