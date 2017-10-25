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

import com.sam.dao.ReplyDAOImpl;
import com.sam.model.Article;
import com.sam.model.Comment;
import com.sam.model.Reply;
import com.sam.service.ArticleService;
import com.sam.service.ReplyService;

@Controller
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOImpl.class);
	private ReplyService replyService;
	
	@Autowired(required=true)
	@Qualifier(value="replyService")
	public void setReplyService(ReplyService ps){
		this.replyService = ps;
	}
	
	@RequestMapping("/list_reply")
    public ModelAndView handleRequest() throws Exception {
        
        ModelAndView model = new ModelAndView("ReplyList");
        model.addObject("replyList", this.replyService.listReply());
        model.addObject("reply", new Reply());
        return model;
    }
     
	@RequestMapping(value = "/new_reply", method = RequestMethod.GET)
    public ModelAndView newReply(@RequestParam("id") Integer commentId) {			
		ModelAndView model = new ModelAndView("ReplyForm");		
		model.addObject("commentId", commentId);		        
        model.addObject("reply", new Reply());
        return model;       
    }
    @RequestMapping(value = "/edit_reply", method = RequestMethod.GET)
    public ModelAndView editReply(@RequestParam("aid") Integer commentId, 
    		@RequestParam("cid") Integer replyId) {        
        Reply reply = this.replyService.getReplyById(replyId);
        ModelAndView model = new ModelAndView("ReplyEdit");
        model.addObject("commentId", commentId);
        model.addObject("reply", reply);
        return model;       
    }
     
    @RequestMapping(value = "/delete_reply", method = RequestMethod.GET)
    public ModelAndView deleteReply(HttpServletRequest request) {
        int replyId = Integer.parseInt(request.getParameter("id"));
        this.replyService.removeReply(replyId);
        return new ModelAndView("redirect:/articles");      
    }
    
     
    
    @RequestMapping(value = "/add_reply", method = RequestMethod.POST)
    public String addReply(@RequestParam("cid") Integer commentId,@RequestParam("aid") Integer articleId, @ModelAttribute("reply") Reply reply, RedirectAttributes redirectAttrs) {
    	    	
			this.replyService.addReply(commentId, reply);
			redirectAttrs.addAttribute("id", articleId).addFlashAttribute("message", "Account created!");
			   return "redirect:/article?id={id}";
    }  
    @RequestMapping(value = "/save_reply", method = RequestMethod.POST)
    public ModelAndView saveReply(@ModelAttribute("reply") Reply reply) {
    	    
			this.replyService.updateReply(reply);
		
        return new ModelAndView("redirect:/articles");
    	
    }
    @RequestMapping(value = "/save_reply1", method = RequestMethod.GET)
    public @ResponseBody String saveReply1(@ModelAttribute Reply reply) {
    	    
			this.replyService.updateReply(reply);
		
			return "Reply " + reply.getReply() + "  was added";
    	
    }
    
    @RequestMapping(value = "/add_reply1", method = RequestMethod.GET)
    public @ResponseBody String addReply1(@RequestParam("commentId") Integer commentId, @ModelAttribute Reply reply) {
    	
    	
			this.replyService.addReply(commentId, reply);
		
    	
			 return "Reply " + reply.getReply() + "  was added";  
    	
    } 

	
}
