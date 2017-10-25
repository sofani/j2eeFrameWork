package com.sam.service;

import java.util.List;

import com.sam.model.Comment;

public interface CommentService {

	public void addComment(Integer articleId,Comment p);
	public void updateComment(Comment p);
	public List<Comment> listComments();
	public Comment getCommentById(int id);
	public void removeComment(int id);
	public List<Comment> listArticleComments(int personId);
	
}
