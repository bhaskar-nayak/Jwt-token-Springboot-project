package com.learningJwt.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningJwt.model.User;
import com.learningJwt.service.UserService;
import com.learningJwt.utils.JwtTokenUtil;
import com.learningJwt.utils.Response;



@CrossOrigin(origins = "http://localhost:3000",  allowCredentials = "true")
@RestController
public class UserController {
	@GetMapping("/test")
	public String healthcheck() {
		return "App is working super smooth for portfolio";	
	}
	@Autowired
	private UserService userService;
	
	 @Autowired
	  private JwtTokenUtil jwtTokenUtil;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		
		Response response = userService.registerUser(user);
		return new ResponseEntity(response,response.getOperation()? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);	
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
	    Response response = userService.loginUser(user);

	    if (response.getOperation()) {
	        String token = jwtTokenUtil.generateToken(user.getEmail());
	        response.setToken(token);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}
	    @PostMapping("/logout")
	    public ResponseEntity<Void> logout() {
	        // Invalidate session here if necessary
	        return ResponseEntity.ok().build();
	    }
	}
