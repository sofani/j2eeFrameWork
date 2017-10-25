package com.sam.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sam.model.Article;
import com.sam.model.Authorities;
import com.sam.model.Users;;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorityDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public void addAuthority(Integer userId, Authorities authority) {
		Session session = this.sessionFactory.getCurrentSession();		
		Users existingUser = (Users)session.get(Users.class, userId);				
		existingUser.getAuthorities().add(authority);
		authority.setUser(existingUser);
		session.save(existingUser);
		session.save(authority);
		logger.info("Authorites saved successfully, Authorites Details="+authority);		
	}

	@Override
	public void updateAuthority(Authorities p) {
		Session session = this.sessionFactory.getCurrentSession();
		Authorities existingAuthorites = (Authorities)session.get(Authorities.class, p.getId());				
		existingAuthorites.setAuthority(p.getAuthority());
		session.save(p);
		logger.info("Authorites updated successfully, Authorites Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Authorities> listAuthority() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Authorities> AuthoritessList = session.createQuery("from authorities").list();
		for(Authorities p : AuthoritessList){
			logger.info("Authorites List::"+p);
		}
		return AuthoritessList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Authorities> listUserAuthority(int userId) {		
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from Users as a where a.id="+ userId);
		Users user = (Users) query.uniqueResult();
		List<Authorities> AuthorityList =user.getAuthorities();
		for(Authorities p : AuthorityList){
			logger.info("Authorites List::*********************"+p.getAuthority());
		}
		return AuthorityList;
	}

	@Override
	public Authorities getAuthorityById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Authorities p = (Authorities) session.load(Authorities.class, new Integer(id));
		logger.info("Authorites loaded successfully, Authorites details="+p);
		return p;
	}

	@Override
	public void removeAuthority(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Authorities p = (Authorities) session.load(Authorities.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Authorites deleted successfully, Authorites details="+p);
	}

}
