package com.update.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.update.entity.Signup;
import com.update.service.ServiceUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class ControllerUser {
	@Autowired
	public ServiceUser serviceUser;
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String,Object>> signup(@RequestBody Signup signup){
		Map<String,Object> response=new HashMap<>();
		Signup saveUser=serviceUser.signupUser(
				signup.getName(),
				signup.getPhNo(),
				signup.getEmail(),
				signup.getPassWord()
		);
		if(saveUser==null) {
			response.put("UserId:", null);
			response.put("Status:", "Signup Failed");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response) ;
		}else {
			response.put("UserId:", saveUser.getId());
			response.put("Status:", "Signup Successful");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> getUser(@PathVariable @Valid Long id){
		Signup user=serviceUser.getUserById(id);
		Map<String,Object> m=new HashMap<>();
		if(user==null) {
			m.put("Status:", "User Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
			
		}else {
			m.put("UserId:", user.getId());
			m.put("Name:", user.getName());
			m.put("Phone NUmber:", user.getPhNo());
			m.put("Email:", user.getEmail());
			return ResponseEntity.ok(m);
			
		}
		
		
	}
	//check is balance
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateUser(
	        @PathVariable Long id,
	        @RequestBody Signup updatedUser) {

	    Map<String, Object> response = new HashMap<>();
	    Signup savedUser = serviceUser.updateUser(id, updatedUser);

	    if (savedUser == null) {
	        response.put("status", "User not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    response.put("userId", savedUser.getId());
	    response.put("name", savedUser.getName());
	    response.put("email", savedUser.getEmail());
	    response.put("phoneNumber", savedUser.getPhNo());
	    response.put("status", "User updated successfully");

	    return ResponseEntity.ok(response);
	}
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Signup signup) {
	    Map<String, Object> response = new HashMap<>();
	    
	    Signup loggedInUser = serviceUser.loginUser(signup.getEmail(), signup.getPassWord());

	    if (loggedInUser != null) {
	        response.put("status", "Login successful");
	        response.put("userId", loggedInUser.getId());
	        response.put("name", loggedInUser.getName());
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("status", "Invalid email or password");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	    }
	}
	
}
