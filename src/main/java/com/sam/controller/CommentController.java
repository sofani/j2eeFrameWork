package com.sam.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sam.dao.CommentDAOImpl;
import com.sam.model.Article;
import com.sam.model.Comment;
import com.sam.service.ArticleService;
import com.sam.service.CommentService;

@Controller
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);
	private CommentService commentService;
	
	@Autowired(required=true)
	@Qualifier(value="commentService")
	public void setCommentService(CommentService ps){
		this.commentService = ps;
	}
	
	@RequestMapping("/list_comment")
    public ModelAndView handleRequest() throws Exception {
        
        ModelAndView model = new ModelAndView("CommentList");
        model.addObject("commentList", this.commentService.listComments());
        model.addObject("comment", new Comment());
        return model;
    }
     
	@RequestMapping(value = "/new_comment", method = RequestMethod.GET)
    public ModelAndView newComment(@RequestParam("id") Integer articleId) {			
		ModelAndView model = new ModelAndView("CommentForm");		
		model.addObject("articleId", articleId);		        
        model.addObject("comment", new Comment());
        return model;       
    }
    @RequestMapping(value = "/edit_comment", method = RequestMethod.GET)
    public ModelAndView editComment(@RequestParam("aid") Integer articleId, 
    		@RequestParam("cid") Integer commentId) {        
        Comment comment = this.commentService.getCommentById(commentId);
        ModelAndView model = new ModelAndView("CommentEdit");
        model.addObject("articleId", articleId);
        model.addObject("comment", comment);
        return model;       
    }
     
    @RequestMapping(value = "/delete_comment", method = RequestMethod.GET)
    public ModelAndView deleteComment(HttpServletRequest request) {
        int commentId = Integer.parseInt(request.getParameter("id"));
        this.commentService.removeComment(commentId);
        return new ModelAndView("redirect:/articles");      
    }
     
    @RequestMapping(value = "/add_comment", method = RequestMethod.POST)
    public String addComment(@RequestParam("id") Integer articleId, @ModelAttribute("comment") Comment comment,RedirectAttributes redirectAttrs) {
    	
    	
			this.commentService.addComment(articleId, comment);
			redirectAttrs.addAttribute("id", articleId).addFlashAttribute("message", "Account created!");
			   return "redirect:/article?id={id}";
			
    	
    }  
    @RequestMapping(value = "/save_comment", method = RequestMethod.POST)
    public ModelAndView saveComment(@ModelAttribute("comment") Comment comment) {
    	    
			this.commentService.updateComment(comment);
		
        return new ModelAndView("redirect:/articles");
    	
    } 
    
    @RequestMapping(value = "/add_comment1", method = RequestMethod.GET)
    public @ResponseBody String addComment1(@RequestParam("articleId") Integer articleId, @ModelAttribute Comment comment) {
    	
    	
			this.commentService.addComment(articleId, comment);
		
    	
			 return "Comment " + comment.getComment() + "  was added";  
    	
    } 

	
}
