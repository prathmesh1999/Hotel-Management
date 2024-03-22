package com.user.service.UserService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.service.UserService;
import com.user.service.UserService.serviceImpl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger= LoggerFactory.getLogger(UserController.class);

	
	//create
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	int retryCount=1;
	
	//get single user
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
//	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback" )
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id){
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
		
	}
	
	
	// creating fallback method for circuitBreaker
	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex){
//		logger.info("Fallback is executed becoz service is down",ex.getMessage());
		
		User user = new User();
		user.setEmail("dummy@gmail.com");
		user.setName("dummy");
		user.setAbout("This user is created becoz some services are down");
		user.setUserId("88485");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}

}
