package com.htic.hticarq.people.model;

import com.htic.hticarq.core.model.CodeDesc;

public class PostalAddress {

	private long id;

	private CodeDesc country;
	private CodeDesc region;
	private CodeDesc province;
	private CodeDesc locality;


	//Getters && Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CodeDesc getCountry() {
		return country;
	}
	public void setCountry(CodeDesc country) {
		this.country = country;
	}
	public CodeDesc getRegion() {
		return region;
	}
	public void setRegion(CodeDesc region) {
		this.region = region;
	}
	public CodeDesc getProvince() {
		return province;
	}
	public void setProvince(CodeDesc province) {
		this.province = province;
	}
	public CodeDesc getLocality() {
		return locality;
	}
	public void setLocality(CodeDesc locality) {
		this.locality = locality;
	}
}