package com.sam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.ArticleDAO;
import com.sam.model.Article;


@Service
public class ArticleServiceImpl implements ArticleService {
	
	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	@Transactional
	public void addArticle(Article p) {
		this.articleDAO.addArticle(p);
	}

	@Override
	@Transactional
	public void updateArticle(Article p) {
		this.articleDAO.updateArticle(p);
	}

	@Override
	@Transactional
	public List<Article> listArticles() {
		return this.articleDAO.listArticles();
	}

	@Override
	@Transactional
	public Article getArticleById(int id) {
		return this.articleDAO.getArticleById(id);
	}

	@Override
	@Transactional
	public void removeArticle(int id) {
		this.articleDAO.removeArticle(id);
	}

	@Override
	@Transactional
	public List<Article> userArticles(String username) {
		return this.articleDAO.userArticles(username);
	}

}
