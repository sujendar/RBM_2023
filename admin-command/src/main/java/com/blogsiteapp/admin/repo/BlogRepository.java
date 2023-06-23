package com.blogsiteapp.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogsiteapp.admin.model.BlogDetails;


@Repository
public interface BlogRepository extends JpaRepository<BlogDetails, Long>{

}
