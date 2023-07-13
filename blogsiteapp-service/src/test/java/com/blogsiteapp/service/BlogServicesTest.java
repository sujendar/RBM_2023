package com.blogsiteapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.blogsiteapp.entity.BlogDetails;
import com.blogsiteapp.entity.ResponseParms;
import com.blogsiteapp.entity.User;
import com.blogsiteapp.interfaces.UserServiceConstants;
import com.blogsiteapp.repo.BlogRepo;
import com.blogsiteapp.repo.UserRepo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@SpringBootTest
public class BlogServicesTest {
	@InjectMocks
	private BlogServices blogservice;
	
	@Mock
	private BlogRepo blogRepo;
	
	@Mock
	private UserRepo userRepo;
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@BeforeEach
	public void setUpbefore() {
    Authentication auth=mock(Authentication.class);
		   SecurityContext securityContext = mock(SecurityContext.class);
	        when(securityContext.getAuthentication()).thenReturn(auth);
	         SecurityContextHolder.setContext(securityContext);
	         when(auth.getName()).thenReturn("lokesh");
		
	}
	@Test
	public void mockCreateBlog() {
	    Authentication auth=mock(Authentication.class);
		   SecurityContext securityContext = mock(SecurityContext.class);
	        when(securityContext.getAuthentication()).thenReturn(auth);
	         SecurityContextHolder.setContext(securityContext);
	         when(auth.getName()).thenReturn("lokesh");
		Mockito.when(userRepo.findByUserName("lokesh")).thenReturn(getUserModel());
		Mockito.when(blogRepo.save(getBlogModel())).thenReturn(null);
		ResponseParms parms=blogservice.createBlog(getBlogModel());
		ArgumentCaptor<BlogDetails> captor=ArgumentCaptor.forClass(BlogDetails.class);
		verify(blogRepo,times(1)).save(captor.capture());
		assertEquals(UserServiceConstants.SUCCESS_CODE.toString(), parms.getErrorcode());
		
	}
	@Test
	public void mockgetAllBlog() {
	    Authentication auth=mock(Authentication.class);
		   SecurityContext securityContext = mock(SecurityContext.class);
	        when(securityContext.getAuthentication()).thenReturn(auth);
	         SecurityContextHolder.setContext(securityContext);
	         when(auth.getName()).thenReturn("lokesh");
		Mockito.when(userRepo.findByUserName("lokesh")).thenReturn(getUserModel());
		Mockito.when(blogRepo.findByUserId(getUserModel().getUserId())).thenReturn(getBlogList());
		List<BlogDetails> parms=blogservice.getAllBlog();
		assertEquals(getBlogList().size(), parms.size());
		
	}
	@Test
	public void mockgetAllByCategory() {
		Mockito.when(blogRepo.findByCategory("category")).thenReturn(getBlogList());
		List<BlogDetails> parms=blogservice.getAllByCategory("category");
		assertEquals(getBlogList().size(), parms.size());
		
	}
	@Test
	public void  mockgetAllByCategoryAndDuration() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		when(blogRepo.getAllByCategoryAndDuration("category",formatter.parse("22-04-2021"),formatter.parse("22-05-2021"))).thenReturn(getBlogList());
		List<BlogDetails> parms=blogservice.getAllByCategoryAndDuration("category","22-04-2021","22-05-2021");
		assertEquals(getBlogList().size(), parms.size());
		
		
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
	private List<BlogDetails> getBlogList() {
		Gson gson=new Gson();
		  String data="[{\r\n"
		  		+ "    \"blogName\":\"Blog Details Blog 1\",\r\n"
		  		+ "    \"category\":\"Blog Details Blog category1\",\r\n"
		  		+ "    \"article\":\"Blog Details its is the description about blog article1\"\r\n"
		  		+ "}]";
		  List<BlogDetails> blog=gson.fromJson(data,new TypeToken<List<BlogDetails>>() {
		}.getType());
			return blog;
		}
}
