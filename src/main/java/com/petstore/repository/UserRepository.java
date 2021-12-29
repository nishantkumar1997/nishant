package com.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petstore.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
