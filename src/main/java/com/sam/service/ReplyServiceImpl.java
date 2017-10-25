package com.sam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.ReplyDAO;
import com.sam.model.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDAO ReplyDAO;

	public void setReplyDAO(ReplyDAO ReplyDAO) {
		this.ReplyDAO = ReplyDAO;
	}

	@Override
	@Transactional
	public void addReply(Integer articleId,Reply p) {
		this.ReplyDAO.addReply(articleId,p);
	}

	@Override
	@Transactional
	public void updateReply(Reply p) {
		this.ReplyDAO.updateReply(p);
	}

	@Override
	@Transactional
	public List<Reply> listReply() {
		return this.ReplyDAO.listReply();
	}
	@Override
	@Transactional
	public List<Reply> listArticleReply(int personId){
		return this.ReplyDAO.listCommentReply(personId);
	}
	@Override
	@Transactional
	public Reply getReplyById(int id) {
		return this.ReplyDAO.getReplyById(id);
	}

	@Override
	@Transactional
	public void removeReply(int id) {
		this.ReplyDAO.removeReply(id);
	}

}
