package com.sam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sam.model.FileValidator;
import com.sam.model.UploadedFile;

@Controller
public class UploadController {

	@Autowired
	FileValidator fileValidator;
   
	@RequestMapping("/fileUploadForm")
	public ModelAndView getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		return new ModelAndView("uploadForm");
	}
	
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUploaded(HttpServletRequest request,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		//String path = request.getServletContext().getRealPath("/resources/images");
		String path = request.getSession().getServletContext().getRealPath("/resources/images/");
		System.out.println("***********************"+path);
		InputStream inputStream = null;
		OutputStream outputStream = null;		
		
		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);
       
		String fileName = file.getOriginalFilename();
		//file.transferTo(new File(realPath + fileName));
		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}
		try {
			inputStream = file.getInputStream();
			
			File newFile = new File(path+"sam.png");
			//File newFile = new File(realPath+"video_icon.png");
			
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("showFile", "message", fileName);
	}
	@RequestMapping(value = "/downloadForm", method = RequestMethod.GET)
	public String start() throws IOException {
		return "downloadForm";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletResponse response) throws IOException {

		File file = new File("C:/images/dd.png");
		InputStream is = new FileInputStream(file);

		// MIME type of the file
		response.setContentType("application/octet-stream");
		// Response header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}


    
}
