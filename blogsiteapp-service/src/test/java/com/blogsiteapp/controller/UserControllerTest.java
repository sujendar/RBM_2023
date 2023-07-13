package com.blogsiteapp.controller;


import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogsiteapp.entity.BlogDetails;
import com.blogsiteapp.entity.User;
import com.blogsiteapp.service.BlogServices;
import com.blogsiteapp.service.UserService;
import com.blogsiteapp.utill.JwtUtil;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UserController.class)
@EnableWebMvc
public class UserControllerTest {
	
private MockMvc mockMVC;	
@Autowired	
private WebApplicationContext context;

@MockBean
private UserService userServices;
@MockBean
private JwtUtil jwtUtil;
@MockBean
private AuthenticationManager manager;


@Before
public void setup() {
	mockMVC=MockMvcBuilders.webAppContextSetup(context).build();
}
@Test
public void mocksaveUser() throws UnsupportedEncodingException, Exception {
	Mockito.when(userServices.saveUser(getUserModel())).thenReturn(getUserModel());
	mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/blogsite/user/register").content(asJsonString())
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
}
@Test
public void mocksigninUser() throws UnsupportedEncodingException, Exception {
	Mockito.when(userServices.findByUserName("lokesh")).thenReturn(getUserModel());
	mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/blogsite/signin/lokesh/lokesh").content(asJsonString())
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
}

private String asJsonString() {
  String data="{\r\n"
  		+ "    \"blogName\":\"Blog Details Blog 1\",\r\n"
  		+ "    \"category\":\"Blog Details Blog category1\",\r\n"
  		+ "    \"article\":\"Blog Details its is the description about blog article1\"\r\n"
  		+ "}";
	return data;
}
private User getUserModel() {
	Gson gson=new Gson();
	  String data="{\r\n"
	  		+ "    \"userName\":\"lokesh\",\r\n"
	  		+ "    \"password\":\"lokesh\",\r\n"
	  		+ "    \"email\":\"lokesh@gmail.com\"\r\n"
	  		+ "}";
	  User user=gson.fromJson(data,User.class);
		return user;
	}

}
