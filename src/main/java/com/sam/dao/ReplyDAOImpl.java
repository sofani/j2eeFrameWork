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
import com.sam.model.Reply;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public void addReply(Integer commentId, Reply p) {
		Session session = this.sessionFactory.getCurrentSession();		
		Comment existingComment = (Comment)session.get(Comment.class, commentId);				
		existingComment.getReply().add(p);
		p.setComment(existingComment);
		session.save(existingComment);
		session.save(p);		
					
		logger.info("Reply saved successfully, Reply Details="+p);
		
	}

	@Override
	public void updateReply(Reply p) {
		Session session = this.sessionFactory.getCurrentSession();
		Reply existingReply = (Reply)session.get(Reply.class, p.getId());				
		existingReply.setReply(p.getReply());
		session.save(existingReply);
		logger.info("Reply updated successfully, Reply Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> listReply() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Reply> ReplysList = session.createQuery("from Reply").list();
		for(Reply p : ReplysList){
			logger.info("Reply List::"+p);
		}
		return ReplysList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reply> listCommentReply(int commentId) {		
		Session session = this.sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from Article as a where a.id="+commentId);
		Comment comment = (Comment) query.uniqueResult();
		List<Reply> ReplyList =comment.getReply();
		for(Reply p : ReplyList){
			logger.info("Reply List::"+p);
		}
		return ReplyList;
	}

	@Override
	public Reply getReplyById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Reply p = (Reply) session.load(Reply.class, new Integer(id));
		logger.info("Reply loaded successfully, Reply details="+p);
		return p;
	}

	@Override
	public void removeReply(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Reply p = (Reply) session.load(Reply.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Reply deleted successfully, Reply details="+p);
	}

}
