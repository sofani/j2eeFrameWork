package com.sam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.AuthorityDAO;
import com.sam.model.Authorities;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	private AuthorityDAO AuthoritiesDAO;

	public void setAuthorityDAO(AuthorityDAO AuthoritiesDAO) {
		this.AuthoritiesDAO = AuthoritiesDAO;
	}

	@Override
	@Transactional
	public void addAuthority(Integer articleId,Authorities p) {
		this.AuthoritiesDAO.addAuthority(articleId,p);
	}

	@Override
	@Transactional
	public void updateAuthority(Authorities p) {
		this.AuthoritiesDAO.updateAuthority(p);
	}

	@Override
	@Transactional
	public List<Authorities> listAuthority() {
		return this.AuthoritiesDAO.listAuthority();
	}
	@Override
	@Transactional
	public List<Authorities> listUserAuthority(int id){
		return this.AuthoritiesDAO.listUserAuthority(id);
	}
	@Override
	@Transactional
	public Authorities getAuthorityById(int id) {
		return this.AuthoritiesDAO.getAuthorityById(id);
	}

	@Override
	@Transactional
	public void removeAuthority(int id) {
		this.AuthoritiesDAO.removeAuthority(id);
	}

}
