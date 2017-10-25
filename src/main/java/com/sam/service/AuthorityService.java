package com.sam.service;

import java.util.List;

import com.sam.model.Authorities;

public interface AuthorityService {

	public void addAuthority(Integer userId,Authorities p);
	public void updateAuthority(Authorities p);
	public List<Authorities> listAuthority();
	public Authorities getAuthorityById(int id);
	public void removeAuthority(int id);
	public List<Authorities> listUserAuthority(int id);
	
}
