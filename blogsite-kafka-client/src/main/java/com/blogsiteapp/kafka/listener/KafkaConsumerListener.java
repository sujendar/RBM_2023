package com.blogsiteapp.kafka.listener;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.blogsiteapp.kafka.model.BlogDetails;
import com.blogsiteapp.kafka.repo.BlogRepository;

@Service
public class KafkaConsumerListener {
	
	@Autowired
	private BlogRepository blogRepository;
	
    @KafkaListener(topics = "#{'${kafka.course.topic}'}", groupId="group_id", containerFactory = "courseKafkaListenerFactory")
    public void consumeJson(BlogDetails entity) {
    	saveCourseEntity(entity);
        System.out.println("Consumed Course Message: " + entity);
    }
    
 
    
    @Transactional
    private void saveCourseEntity(BlogDetails entity) {
    	Optional<BlogDetails> entityOpt = blogRepository.findById(entity.getBlogId());
    	BlogDetails courseEntityToPersist=null;
    	if(entityOpt.isPresent()) {
    		entity.setBlogId(entityOpt.get().getBlogId());
    	}
    	
    	blogRepository.save(entity);
	}
    
  

	
	
}