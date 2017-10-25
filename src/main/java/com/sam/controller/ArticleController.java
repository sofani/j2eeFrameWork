package com.sam.controller;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.Blob;
import com.sam.model.Article;
import com.sam.model.Comment;
import com.sam.model.FileValidator;
import com.sam.model.Reply;
import com.sam.model.UploadedFile;
import com.sam.service.ArticleService;
import com.sam.service.CommentService;

@Controller
public class ArticleController {
	
	private ArticleService articleService;
	private CommentService commentService;
	@Autowired
	FileValidator fileValidator;
	@Autowired(required=true)
	@Qualifier(value="articleService")
	public void setarticleService(ArticleService ps){
		this.articleService = ps;
	}
	@Autowired(required=true)
	@Qualifier(value="commentService")
	public void setcommentService(CommentService ps){
		this.commentService = ps;
	}
	@RequestMapping("/getImage/{id}")
	public void getImage(HttpServletResponse response,@PathVariable("id") int id) throws IOException {
	    response.setContentType("image/jpeg");
	    Article article =articleService.getArticleById(id);
	    byte[] imageBytes = article.getImage();
	    response.getOutputStream().write(imageBytes);
	    response.getOutputStream().flush();
	    //<img src="getImage/222" />
	}
	@RequestMapping(value = {"/articles","/"}, method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
		List<Article> articleList = articleService.listArticles();
		FileOutputStream fos = null;
		
		for( Article article : articleList){
        byte[] img = article.getImage();
        
        try{
            
            fos.write(img);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		}      
        ModelAndView model = new ModelAndView("ArticleList");
        model.addObject("articleList", articleList);
        model.addObject("article", new Article());
        return model;
    }
	@RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView handleRequest1(HttpServletRequest request) {
		int articleId = Integer.parseInt(request.getParameter("id"));
        Article article = this.articleService.getArticleById(articleId);  
        article.setComments(commentService.listArticleComments(articleId));        
        
        ModelAndView model = new ModelAndView("Article");
        model.addObject("article", article);
        model.addObject("comment", new Comment());
        model.addObject("reply", new Reply());
        
        return model;
    }
	@RequestMapping(value = "/userarticles", method = RequestMethod.GET)
    public ModelAndView userArticles() throws Exception {
		ModelAndView model = new ModelAndView("UserArticles");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = userDetail.getUsername();
			
			List<Article> userArticles = this.articleService.userArticles(username);
			
        model.addObject("userArticles", userArticles);
		}
        return model;
    }
	
	
    @RequestMapping(value = "/edit_article", method = RequestMethod.GET)
    public ModelAndView editArticle(HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("id"));
        Article article = this.articleService.getArticleById(articleId);
        ModelAndView model = new ModelAndView("ArticleForm");         
        model.addObject("article", article);
        return model;       
    }
    
    @RequestMapping(value = "/delete_article", method = RequestMethod.GET)
    public ModelAndView deleteArticle(HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("id"));
        this.articleService.removeArticle(articleId);
        return new ModelAndView("redirect:/articles");      
    }
     
    @RequestMapping(value = "/save_article", method = RequestMethod.POST)
    public ModelAndView saveArticle(@ModelAttribute Article article,
    		@RequestParam("file") MultipartFile file){
    	if(article.getId() == 0){
    		//save image into database
    		InputStream inputStream = null;
    		OutputStream outputStream = null;
    		
    		//File file = new File("C:\\mavan-hibernate-image-mysql.gif");
            byte[] bFile = new byte[(int) file.getSize()];
            
            try {
            	inputStream = file.getInputStream();
    	     //convert file into array of bytes
            	inputStream.read(bFile);
            	inputStream.close();
            } catch (Exception e) {
    	     e.printStackTrace();
            }
            
            
            article.setImage(bFile);
            
			this.articleService.addArticle(article);
		}else{
			//existing person, call update
			this.articleService.updateArticle(article);
		}
    	
        return new ModelAndView("redirect:/articles");
    }  
    @RequestMapping(value = "/save_article1", method = RequestMethod.GET)
    public @ResponseBody String saveArticle1(@ModelAttribute Article article) {
    	if(article.getId() == 0){
			//new person, add it
			this.articleService.addArticle(article);
		}else{
			//existing person, call update
			this.articleService.updateArticle(article);
		}
    	String result ="\nID: " + article.getId() 
        +"\nTitle: " + article.getTitle() 
        +"\nBody: " + article.getBody()  
        + "Name: "+ article.getUsername(); 
        return  result;      
    } 
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
   
}
