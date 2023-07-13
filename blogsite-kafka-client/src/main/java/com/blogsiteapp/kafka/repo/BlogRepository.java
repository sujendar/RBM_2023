package com.blogsiteapp.kafka.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.blogsiteapp.kafka.model.BlogDetails;


@Repository
public interface BlogRepository extends MongoRepository<BlogDetails, Long>{

}
