package com.blogsiteapp.kafka.model;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "blog_details" )
@Data
public class BlogDetails {
	@Id
	private Long blogId;
	private String  blogName;
	private String category;	
	private Date publisheddate;
	private String  article;
	private Long userId;
}
