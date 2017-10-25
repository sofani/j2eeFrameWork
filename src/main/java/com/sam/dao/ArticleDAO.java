package com.sam.dao;



import java.util.List;

import com.sam.model.Article;
import com.sam.model.Comment;

public interface ArticleDAO {

	public void addArticle(Article article);
	public void updateArticle(Article articId);
	public List<Article> listArticles();
	public List<Article> userArticles(String username);
	public Article getArticleById(int id);
	public void removeArticle(int id);
	
}
