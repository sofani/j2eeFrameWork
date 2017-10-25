package com.sam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "REPLY")
public class Reply implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	private String reply;
	private String username;
	private Date date;
	public String getUsername() {
		return username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@ManyToOne()
	@JoinColumn(name = "comment_id")
	private Comment comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
