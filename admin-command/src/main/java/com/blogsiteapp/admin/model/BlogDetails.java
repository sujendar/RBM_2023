package com.blogsiteapp.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="Blog_DETAILS")
@Data
public class BlogDetails {
	@Id
	@GeneratedValue
	private Long blogId;
	@Size(min = 20, message = "blogName too short.")
	private String  blogName;
	@Size(min = 20, message = "category too short.")
	private String category;	
	private Date publisheddate;
	@Size(min = 300, message = "article too short.")
	@Column(length = 4000)
	private String  article;
	private Long userId;
}
