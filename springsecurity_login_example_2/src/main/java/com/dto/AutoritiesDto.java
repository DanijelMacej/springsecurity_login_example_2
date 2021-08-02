package com.dto;

public class AutoritiesDto {

	private int id_authorities;
	private int id_user;
	private String roles;

	
	
	

	

	
	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getId_authorities() {
		return id_authorities;
	}

	public void setId_authorities(int id_authorities) {
		this.id_authorities = id_authorities;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	/*
	 * public List<SimpleGrantedAuthority> getRoles() { return roles; } public void
	 * setRoles(List<SimpleGrantedAuthority> roles) { this.roles = roles; }
	 * 
	 */
	/*
	 * public List<GrantedAuthority> getRoles() { return roles; } public void
	 * setRoles(List<GrantedAuthority> roles) { this.roles = roles; }
	 */
	public String getRoles() {
		return roles;
	}

}
