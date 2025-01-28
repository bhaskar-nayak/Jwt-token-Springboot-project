package com.learningJwt.dao;

import com.learningJwt.model.User;
import com.learningJwt.utils.Response;

public interface UserDAO {
	public Response registerUser(User user);
	public Response loginUser(User user);
	public User findByUsername(String username);
}
