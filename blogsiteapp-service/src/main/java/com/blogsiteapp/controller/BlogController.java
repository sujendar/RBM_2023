package com.blogsiteapp.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogsiteapp.entity.BlogDetails;
import com.blogsiteapp.entity.ResponseParms;
import com.blogsiteapp.service.BlogServices;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/blogsite")
@Slf4j
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private BlogServices blogServices;
	
	@PostMapping(value="/users/blogs/add")
	public ResponseEntity<?> createBlog(@Valid @RequestBody BlogDetails blog) {
		log.info("its in createBlog in BlogController");
		ResponseParms response=blogServices.createBlog(blog);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.CREATED);
	     
	}
	
	@GetMapping(value="/user/getall")
	public ResponseEntity<?> getAllBlogs() {
		log.info("its in getAllBlogs in BlogController");
		List<BlogDetails> response=blogServices.getAllBlog();
		return new ResponseEntity<List<BlogDetails>>(response,HttpStatus.OK);
	     
	}
	@DeleteMapping(value="/user/delete/{blogname}")
	public ResponseEntity<?> deleteByBlogname(@PathVariable String blogname) {
		log.info("its in deleteByBlogname in BlogController");
		ResponseParms response=blogServices.deleteByBlogname(blogname);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.OK);
	     
	}
	@GetMapping(value="/blogs/info/{category}")
	public ResponseEntity<?> getAllByCategory(@PathVariable String category) {
		log.info("its in getAllByCategory in BlogController");
		List<BlogDetails> response=blogServices.getAllByCategory(category);
		return new ResponseEntity<List<BlogDetails>>(response,HttpStatus.OK);
	     
	}
	@GetMapping(value="/blogs/get/{category}/{fromdate}/{todate}")
	public ResponseEntity<?> getAllByCategoryAndDuration(@PathVariable String category,@PathVariable String fromdate,@PathVariable String todate) throws ParseException {
		log.info("its in getAllByCategoryAndDuration in BlogController");
		List<BlogDetails> response=blogServices.getAllByCategoryAndDuration(category,fromdate,todate);
		return new ResponseEntity<List<BlogDetails>>(response,HttpStatus.OK);
	     
	}
	
}
