package com.sam.controller;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sam.model.Article;
import com.sam.model.Authorities;
import com.sam.model.Users;
import com.sam.service.AuthorityService;
import com.sam.service.CommentService;
import com.sam.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	private AuthorityService authorityService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService ps){
		this.userService = ps;
	}
	@Autowired(required=true)
	@Qualifier(value="authorityService")
	public void setAuthorityService(AuthorityService ps){
		this.authorityService = ps;
	}
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView upload() {		
		ModelAndView model = new ModelAndView("upload");
		
        return model;       
    }
	
	@RequestMapping("/users")
    public ModelAndView handleRequest() throws Exception {
        
       	List<Users> userList = userService.listUsers();
        List<Users> usersDTO = new ArrayList<Users>();
        for(Users user : userList ){
        	Users dto = new Users();
        	dto.setId(user.getId());
        	dto.setUsername(user.getUsername());
        	dto.setPassword(user.getPassword());
        	dto.setEnabled(user.isEnabled());
        	dto.setAuthorities(authorityService.listUserAuthority(user.getId()));
        	usersDTO.add(dto);
        }
        ModelAndView model = new ModelAndView("UserList");
        model.addObject("userList", userList);
        model.addObject("user", new Users());
        return model;
    }
     
	@RequestMapping(value = "/new_user", method = RequestMethod.GET)
    public ModelAndView newUser() {		
		ModelAndView model = new ModelAndView("UserForm");
		//model.addObject("userList", this.userService.listUsers());        
        model.addObject("user", new Users());
        return model;       
    }
    @RequestMapping(value = "/edit_user", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        Users user = this.userService.getUserById(userId);
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("userList", this.userService.listUsers()); 
        model.addObject("user", user);
        return model;       
    }     
    @RequestMapping(value = "/delete_user", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        this.userService.removeUser(id);
        return new ModelAndView("redirect:/users");      
    }
     
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Users user) {
    	Authorities authority = new Authorities();
    	if(user.getId() == 0){
			//new person, add it
			this.userService.addUser(user);
			this.authorityService.addAuthority(user.getId(), authority);
		}else{
			//existing person, call update
			this.userService.updateUser(user);
		}
    	
    	
        return new ModelAndView("redirect:/login");
    } 
    
    @RequestMapping(value = "/save_map", method = RequestMethod.GET)
    public @ResponseBody String saveMap(@ModelAttribute Users user) {
    	
			this.userService.addUser(user);
		
        return  "";      
    }  
   
   
    @RequestMapping(value = "/chat1", method = RequestMethod.GET)
    public @ResponseBody String sendMessage2(HttpServletRequest request) { 
    	//Random r = new Random();
    	//return "data:" + r.nextInt(10) +"\n\n";
    	//user = userService.listUsers().get(0);
    	String lat = request.getParameter("lat");
    	String lon = request.getParameter("lon");
    	return "data:" + lat+", "+lon +"\n\n";  
            
    }
    @RequestMapping("chat")
    public ModelAndView chat(HttpServletRequest request) {
        
    	ModelAndView model = new ModelAndView("Chat");
        
        
        return model;    
    }
    
     

            

    
    

	
}
