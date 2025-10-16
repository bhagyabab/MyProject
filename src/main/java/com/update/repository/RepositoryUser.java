package com.update.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.update.entity.Signup;

public interface RepositoryUser extends JpaRepository<Signup,Long>{
	Optional<Signup> findByEmail(String email);
}
