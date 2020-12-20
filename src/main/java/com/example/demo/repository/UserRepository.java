package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity,Long> {
	   public Optional<UserEntity> getUserByUserName(String userName);

	    public List<UserEntity> getUsersByRoles(String role);
}
