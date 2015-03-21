package us.renedo.find.hibu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "domain", uniqueConstraints = {
		@UniqueConstraint(columnNames = "domain") })
public class Domain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "domain", unique = true, nullable = false, columnDefinition="LONGTEXT")
	private String domain;

	@Column(columnDefinition="LONGTEXT")
	private String telf;

	@Column(columnDefinition="LONGTEXT")
	private String dir;
	
	@Column(columnDefinition="LONGTEXT")
	private String name;
	
	@Column(columnDefinition="LONGTEXT")
	private String whois;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWhois() {
		return whois;
	}

	public void setWhois(String whois) {
		this.whois = whois;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
