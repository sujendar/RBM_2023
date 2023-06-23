package com.blogsiteapp.admin.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.blogsiteapp.admin.exception.ApiException;
import com.blogsiteapp.admin.model.BlogDetails;
import com.blogsiteapp.admin.repo.BlogRepository;


@Service
public class BlogService {
	
	@Autowired
    private KafkaTemplate<String, BlogDetails> kafkaTemplateCourse;
	
	@Value("${kafka.course.topic}")
	private String courseTopic;
	

	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BlogRepository blogRepository;
	
	
	public ResponseEntity<BlogDetails> addCourse(BlogDetails blog) {
		blog.setPublisheddate(new Date());
		BlogDetails blogDetails=blogRepository.save(blog);
			kafkaTemplateCourse.send(courseTopic, blogDetails);
			return new ResponseEntity<BlogDetails>(blogDetails, HttpStatus.OK);
	}


	
	

	
	public ResponseEntity<BlogDetails> updateCourse(BlogDetails blog) {
		Optional<BlogDetails> opt = blogRepository.findById(blog.getBlogId());
		blog.setPublisheddate(new Date());
		if(opt.isPresent()) {
			BlogDetails entity=opt.get();
			blog.setBlogId(entity.getBlogId());
			BlogDetails blogDetails=blogRepository.save(blog);
			kafkaTemplateCourse.send(courseTopic, blogDetails);
			return new ResponseEntity<BlogDetails>(blogDetails, HttpStatus.OK);
		}
		throw new ApiException("Invalid Course Id", HttpStatus.NOT_FOUND);
	}
}
