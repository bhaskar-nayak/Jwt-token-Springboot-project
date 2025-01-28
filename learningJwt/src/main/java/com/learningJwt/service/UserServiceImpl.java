package com.learningJwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learningJwt.dao.UserDAO;
import com.learningJwt.model.User;
import com.learningJwt.utils.JwtTokenUtil;
import com.learningJwt.utils.Response;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	 @Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Override
	@Transactional
	public Response registerUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.registerUser(user);
	}

	@Override
	public Response loginUser(User user) {
		  Response response = userDAO.loginUser(user);

	        if (response.getOperation()) {
	            String token = jwtTokenUtil.generateToken(user.getEmail());
	            response.setToken(token);
	            System.out.println("JWT Token: " + token);  // Display the token in the console
	        }

	        return response;
}
}