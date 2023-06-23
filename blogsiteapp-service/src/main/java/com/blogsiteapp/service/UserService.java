package com.blogsiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.blogsiteapp.entity.User;
import com.blogsiteapp.repo.UserRepo;


@Service
public class UserService {
  @Autowired
	private UserRepo userRepo;

  
  public User saveUser(User user) {
	  return userRepo.save(user);
  }

public User findByUserName(String userName) {
	return userRepo.findByUserName(userName);
}

public ResponseEntity<?> allSubscribedBook(String emailId) {
	// TODO Auto-generated method stub
	return null;
}
  

}
