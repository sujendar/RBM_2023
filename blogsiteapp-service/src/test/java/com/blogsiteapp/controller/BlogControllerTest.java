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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogsiteapp.entity.BlogDetails;
import com.blogsiteapp.service.BlogServices;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BlogController.class)
@EnableWebMvc
public class BlogControllerTest {
	
private MockMvc mockMVC;	
@Autowired	
private WebApplicationContext context;

@MockBean
private BlogServices blogServices;

@Before
public void setup() {
	mockMVC=MockMvcBuilders.webAppContextSetup(context).build();
}
@Test
public void mockCreateBlog() throws UnsupportedEncodingException, Exception {
	Mockito.when(blogServices.createBlog(getBlogModel())).thenReturn(null);
	mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/blogsite/users/blogs/add").content(asJsonString())
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
}
@Test
public void mockgetAllBlog() throws UnsupportedEncodingException, Exception {
	Mockito.when(blogServices.getAllBlog()).thenReturn(null);
	mockMVC.perform(MockMvcRequestBuilders.get("/api/v1/blogsite/user/getall")
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
}
@Test
public void mockDeleteByBlogName() throws UnsupportedEncodingException, Exception {
	Mockito.when(blogServices.deleteByBlogname("blogname")).thenReturn(null);
	mockMVC.perform(MockMvcRequestBuilders.delete("/api/v1/blogsite/user/delete/blogname")
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
}
@Test
public void mockgetAllbyCategory() throws UnsupportedEncodingException, Exception {
	Mockito.when(blogServices.getAllByCategory("category")).thenReturn(null);
	mockMVC.perform(MockMvcRequestBuilders.get("/api/v1/blogsite/blogs/info/category")
	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
}
@Test
public void mockgetAllbyCategoryAndDuration() throws UnsupportedEncodingException, Exception {
	Mockito.when(blogServices.getAllByCategoryAndDuration("category","24-12-2022","26-12-2022")).thenReturn(null);
	mockMVC.perform(MockMvcRequestBuilders.get("/api/v1/blogsite//blogs/get/category/24-12-2022/26-12-2022")
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
private BlogDetails getBlogModel() {
	Gson gson=new Gson();
	  String data="{\r\n"
	  		+ "    \"blogName\":\"Blog Details Blog 1\",\r\n"
	  		+ "    \"category\":\"Blog Details Blog category1\",\r\n"
	  		+ "    \"article\":\"Blog Details its is the description about blog article1\"\r\n"
	  		+ "}";
	  BlogDetails blog=gson.fromJson(data,BlogDetails.class);
		return blog;
	}

}
