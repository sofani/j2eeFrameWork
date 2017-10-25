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
import org.springframework.web.servlet.ModelAndView;

import com.sam.dao.AuthorityDAOImpl;
import com.sam.dao.AuthorityDAOImpl;
import com.sam.model.Article;
import com.sam.model.Authorities;
import com.sam.service.ArticleService;
import com.sam.service.AuthorityService;
import com.sam.service.AuthorityService;

@Controller
public class AuthorityController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorityDAOImpl.class);
	private AuthorityService authorityService;
	
	@Autowired(required=true)
	@Qualifier(value="authorityService")
	public void setAuthorityService(AuthorityService ps){
		this.authorityService = ps;
	}
	
	@RequestMapping("/list_authority")
    public ModelAndView handleRequest() throws Exception {
        
        ModelAndView model = new ModelAndView("AuthorityList");
        model.addObject("AuthorityList", this.authorityService.listAuthority());
        model.addObject("authority", new Authorities());
        return model;
    }
     
	@RequestMapping(value = "/new_authority", method = RequestMethod.GET)
    public ModelAndView newAuthority(@RequestParam("id") Integer userId) {			
		ModelAndView model = new ModelAndView("AuthorityForm");		
		model.addObject("userId", userId);		        
        model.addObject("authority", new Authorities());
        return model;       
    }
    @RequestMapping(value = "/edit_authority", method = RequestMethod.GET)
    public ModelAndView editAuthority(@RequestParam("aid") Integer userId, 
    		@RequestParam("cid") Integer AuthorityId) {        
        Authorities Authority = this.authorityService.getAuthorityById(AuthorityId);
        ModelAndView model = new ModelAndView("AuthorityEdit");
        model.addObject("userId", userId);
        model.addObject("authority", Authority);
        return model;       
    }
     
    @RequestMapping(value = "/delete_authority", method = RequestMethod.GET)
    public ModelAndView deleteAuthority(HttpServletRequest request) {
        int AuthorityId = Integer.parseInt(request.getParameter("id"));
        this.authorityService.removeAuthority(AuthorityId);
        return new ModelAndView("redirect:/users");      
    }
   
    @RequestMapping(value = "/add_authority", method = RequestMethod.POST)
    public ModelAndView addAuthority(@RequestParam("id") Integer userId, @ModelAttribute("authority") Authorities authority) {
    	   	
			this.authorityService.addAuthority(userId, authority);		
    	
        return new ModelAndView("redirect:/users");
    	
    }  
   
    @RequestMapping(value = "/save_authority", method = RequestMethod.POST)
    
    public ModelAndView saveAuthority(@ModelAttribute("authority") Authorities authority) {
    	
			this.authorityService.updateAuthority(authority);
		
			 return new ModelAndView("redirect:/users");
    	
    }  

	
}
