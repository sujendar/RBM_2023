package com.blogsiteapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blogsiteapp.entity.BlogDetails;
import com.blogsiteapp.entity.ResponseParms;
import com.blogsiteapp.entity.User;
import com.blogsiteapp.interfaces.UserServiceConstants;
import com.blogsiteapp.repo.BlogRepo;
import com.blogsiteapp.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogServices {

	
	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo userRepo;

	public ResponseParms createBlog(BlogDetails blog) {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  createBlog name :"+token.getName());
		User user=userRepo.findByUserName(token.getName());
		blog.setUserId(user.getUserId());
		blog.setPublisheddate(new Date());
		blogRepo.save(blog);
		ResponseParms params=new ResponseParms();
  	  params.setErrorcode(UserServiceConstants.SUCCESS_CODE.toString());
  	  params.setErrormessage(UserServiceConstants.SUCCESS_MSG.toString());
		return params;
	}

	public List<BlogDetails> getAllBlog() {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  getallBlog name :"+token.getName());
		User user=userRepo.findByUserName(token.getName());
		return blogRepo.findByUserId(user.getUserId());
	}

	public List<BlogDetails> getAllByCategory(String category) {
		// TODO Auto-generated method stub
		return blogRepo.findByCategory(category);
	}

	public ResponseParms deleteByBlogname(String blogname) {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  delete Blog name :"+token.getName());
		User user=userRepo.findByUserName(token.getName());
		BlogDetails blog=blogRepo.findByBlogNameAndUserId(blogname,user.getUserId());
		ResponseParms params=new ResponseParms();
		if(blog!=null) {
		blogRepo.delete(blog);
		
	  	  params.setErrorcode(UserServiceConstants.SUCCESS_CODE.toString());
	  	  params.setErrormessage(UserServiceConstants.SUCCESS_DEL_MSG.toString());
		}else {
		  	  params.setErrorcode(UserServiceConstants.SUCCESS_CODE.toString());
		  	  params.setErrormessage("You are not authorised to delete this blog");
		}
			return params;
	}

	public List<BlogDetails> getAllByCategoryAndDuration(String category, String fromdate, String todate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<BlogDetails> blogDetails=blogRepo.getAllByCategoryAndDuration(category,formatter.parse(fromdate),formatter.parse(todate));
		return blogDetails;
	}

}
