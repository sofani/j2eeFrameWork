package com.sam.service;

import java.util.List;

import com.sam.model.Reply;

public interface ReplyService {

	public void addReply(Integer articleId,Reply p);
	public void updateReply(Reply p);
	public List<Reply> listReply();
	public Reply getReplyById(int id);
	public void removeReply(int id);
	public List<Reply> listArticleReply(int personId);
	
}
