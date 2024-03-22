package com.user.service.UserService.service;

import java.util.List;

import com.user.service.UserService.entities.User;


public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String id);

}
