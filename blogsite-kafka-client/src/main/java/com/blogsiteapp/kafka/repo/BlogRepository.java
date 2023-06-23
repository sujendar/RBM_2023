package com.blogsiteapp.kafka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogsiteapp.kafka.model.BlogDetails;


@Repository
public interface BlogRepository extends JpaRepository<BlogDetails, Long>{

}
