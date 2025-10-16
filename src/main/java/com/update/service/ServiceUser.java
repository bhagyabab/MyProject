package com.update.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.update.entity.Signup;
import com.update.repository.RepositoryUser;
@Service
public class ServiceUser {
	Signup s=new Signup();
	@Autowired 
	private RepositoryUser repositoryUser;
	public Signup signupUser(String name, String phNo, String email, String password) {
		s.setName(name);
		s.setPhNo(phNo);
		s.setEmail(email);
		s.setPassWord(password);
		return repositoryUser.save(s);
	}
	public Signup getUserById(Long id) {
		return repositoryUser.findById(id).orElse(null);
	}
	public Signup updateUser(Long id, Signup updateUser) {
		Optional<Signup> existUseropt=repositoryUser.findById(id);
		if(existUseropt.isPresent()) {
			Signup eu=existUseropt.get();
			eu.setName(updateUser.getName());
			eu.setPhNo(updateUser.getPhNo());
			eu.setEmail(updateUser.getEmail());
			eu.setPassWord(updateUser.getPassWord());
			
			return repositoryUser.save(eu);
		}
		return null;
	}
	public Signup loginUser(String email, String password) {
	    Optional<Signup> userOptional = repositoryUser.findByEmail(email);

	    if (userOptional.isPresent()) {
	        Signup user = userOptional.get();
	        if (user.getPassWord().equals(password)) {
	            return user; // Successful login
	        }
	    }
	    return null; // Invalid email or password
	}

	
}
