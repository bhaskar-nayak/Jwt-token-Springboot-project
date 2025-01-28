package com.learningJwt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000",  allowCredentials = "true")
public class AccessController {
	@Value("${AWS_ACCESS_KEY}")
	private String accessKey;
	@Value("${AWS_SECRET_KEY}")
	private String secretKey;
	@GetMapping("/getKeys")
	public String getKeys() {
		System.out.println("accesskey:"+accessKey +"secretKey"+secretKey);
		return "Aws Access Key:"+accessKey + "Aws secretkey:"+secretKey;
	}
}
