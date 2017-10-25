package com.sam.service;

import java.util.List;

import com.sam.model.Article;

public interface ArticleService {

	public void addArticle(Article p);
	public void updateArticle(Article p);
	public List<Article> listArticles();
	public Article getArticleById(int id);
	public void removeArticle(int id);
	public List<Article> userArticles(String username);
	
}
