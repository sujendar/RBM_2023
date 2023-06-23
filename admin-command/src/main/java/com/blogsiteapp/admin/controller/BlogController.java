package com.blogsiteapp.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogsiteapp.admin.model.BlogDetails;
import com.blogsiteapp.admin.service.BlogService;


@RestController
@RequestMapping("/api/v1/blogsite")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	@PostMapping(value="/users/blogs/add")
	public ResponseEntity<BlogDetails> addCourse(@Valid @RequestBody BlogDetails blog) {
		return blogService.addCourse(blog);
	}
	
	
	@PostMapping(value="/users/blogs/update")
	public ResponseEntity<BlogDetails> updateCourse( @Valid @RequestBody BlogDetails blog) {
		return blogService.updateCourse(blog);
	}
	



	
}
