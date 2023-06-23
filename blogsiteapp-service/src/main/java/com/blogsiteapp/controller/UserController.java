package com.blogsiteapp.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogsiteapp.entity.AuthRequest;
import com.blogsiteapp.entity.User;
import com.blogsiteapp.service.UserService;
import com.blogsiteapp.utill.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/blogsite/")
@Slf4j
@CrossOrigin("*")
public class UserController {
	 @Autowired
	private JwtUtil jwtUtil;
	 @Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@PostMapping("user/register")
	public User saveUser(@Valid @RequestBody User user) {
		log.info("inside saveuser in user controller");
		return userService.saveUser(user);
	}
	
	@PostMapping("/signin/{username}/{password}")
	public ResponseEntity<?> signinUser(@PathVariable String username,@PathVariable String password) {
		log.info("inside saveuser in user controller");
		User userda =userService.findByUserName(username);
    	if(userda.getPassword().equals(password)) {
    		return ResponseEntity.ok(userda);
    	}else {
    		return (ResponseEntity<?>) ResponseEntity.internalServerError(); 
    	}
	}
	  @GetMapping("/")
	    public String welcome() {
	    	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list=  	token.getAuthorities();
	       for (GrantedAuthority grantedAuthority : list) {
			System.out.println(grantedAuthority.getAuthority());
		}
	        return "Welcome to javatechie !!";
	    }
	   @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getUserName());
	    }
	 
}
