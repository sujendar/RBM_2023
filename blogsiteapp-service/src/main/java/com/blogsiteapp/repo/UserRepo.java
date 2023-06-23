package com.blogsiteapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogsiteapp.entity.User;


public interface UserRepo extends JpaRepository<User, Long>{
	User findByUserId(Long userId);

	User findByUserName(String userName);

	User findByEmail(String email);

}
