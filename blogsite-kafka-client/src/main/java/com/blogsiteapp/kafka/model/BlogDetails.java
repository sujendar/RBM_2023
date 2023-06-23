package com.blogsiteapp.kafka.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Blog_DETAILS_BACKUP")
@Data
public class BlogDetails {
	@Id
	private Long blogId;
	private String  blogName;
	private String category;	
	private Date publisheddate;
	@Column(length = 4000)
	private String  article;
	private Long userId;
}
