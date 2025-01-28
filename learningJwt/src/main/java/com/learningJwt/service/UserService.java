package com.learningJwt.service;


import com.learningJwt.model.User;
import com.learningJwt.utils.Response;

public interface UserService {
	public Response registerUser(User user);
	public Response loginUser(User user);
}
