package com.grocerystore.grocerystoregg.service;

import com.grocerystore.grocerystoregg.exception.UserException;
import com.grocerystore.grocerystoregg.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJWT(String jwt) throws UserException;

}
