package com.sam.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sam.model.Article;
import com.sam.model.Comment;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addArticle(Article p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("User saved successfully, User Details="+p);
	}

	@Override
	public void updateArticle(Article p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("User updated successfully, User Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> listArticles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articlesList = session.createQuery("from Article").list();
		for(Article p : articlesList){			
			logger.info("Article List::"+p);
		}
		return articlesList;
	}

	@Override
	public Article getArticleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Article p = (Article) session.load(Article.class, new Integer(id));
		logger.info("User loaded successfully, Article details="+p);
		return p;
	}

	@Override
	public void removeArticle(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Article p = (Article) session.load(Article.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("User deleted successfully, Article details="+p);
	}

	@Override
	public List<Article> userArticles(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> userArticles = session.createQuery("from Article as a where a.username='"+username+"'").list();
				
		return userArticles;
	}

	

}
