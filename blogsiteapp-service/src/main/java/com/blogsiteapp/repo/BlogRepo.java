package com.blogsiteapp.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogsiteapp.entity.BlogDetails;



public interface BlogRepo extends JpaRepository<BlogDetails, Long>{

	List<BlogDetails> findByUserId(Long userId);

	List<BlogDetails> findByCategory(String category);

	BlogDetails findByBlogName(String blogname);
    @Query("select b from BlogDetails b where b.category=?1 and b.publisheddate between ?2 and ?3")
	List<BlogDetails> getAllByCategoryAndDuration(String category, Date date, Date date2);

	BlogDetails findByBlogNameAndUserId(String blogname, Long userId);
	
}
