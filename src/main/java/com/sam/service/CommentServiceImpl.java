package com.sam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.CommentDAO;
import com.sam.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentDAO CommentDAO;

	public void setCommentDAO(CommentDAO CommentDAO) {
		this.CommentDAO = CommentDAO;
	}

	@Override
	@Transactional
	public void addComment(Integer articleId,Comment p) {
		this.CommentDAO.addComment(articleId,p);
	}

	@Override
	@Transactional
	public void updateComment(Comment p) {
		this.CommentDAO.updateComment(p);
	}

	@Override
	@Transactional
	public List<Comment> listComments() {
		return this.CommentDAO.listComments();
	}
	@Override
	@Transactional
	public List<Comment> listArticleComments(int personId){
		return this.CommentDAO.listArticleComments(personId);
	}
	@Override
	@Transactional
	public Comment getCommentById(int id) {
		return this.CommentDAO.getCommentById(id);
	}

	@Override
	@Transactional
	public void removeComment(int id) {
		this.CommentDAO.removeComment(id);
	}

}
