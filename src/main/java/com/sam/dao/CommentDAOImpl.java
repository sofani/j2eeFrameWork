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
public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public void addComment(Integer articleId, Comment p) {
		Session session = this.sessionFactory.getCurrentSession();		
		Article existingArticle = (Article)session.get(Article.class, articleId);				
		existingArticle.getComments().add(p);
		p.setArticle(existingArticle);
		session.save(existingArticle);
		session.save(p);
		logger.info("Comment saved successfully, Comment Details="+p);
		
	}

	@Override
	public void updateComment(Comment p) {
		Session session = this.sessionFactory.getCurrentSession();
		Comment existingComment = (Comment)session.get(Comment.class, p.getId());				
		existingComment.setComment(p.getComment());
		session.save(existingComment);
		logger.info("Comment updated successfully, Comment Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> listComments() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Comment> CommentsList = session.createQuery("from Comment").list();
		for(Comment p : CommentsList){
			logger.info("Comment List::"+p);
		}
		return CommentsList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> listArticleComments(int articleId) {		
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from Article as a where a.id="+articleId);
		Article article = (Article) query.uniqueResult();
		List<Comment> CommentsList =article.getComments();
		for(Comment p : CommentsList){
			logger.info("Comment List::"+p);
		}
		return CommentsList;
	}

	@Override
	public Comment getCommentById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Comment p = (Comment) session.load(Comment.class, new Integer(id));
		logger.info("Comment loaded successfully, Comment details="+p);
		return p;
	}

	@Override
	public void removeComment(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Comment p = (Comment) session.load(Comment.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Comment deleted successfully, Comment details="+p);
	}

}
