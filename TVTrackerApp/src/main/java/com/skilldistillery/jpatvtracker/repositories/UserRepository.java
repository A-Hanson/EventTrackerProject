package com.skilldistillery.jpatvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jpatvtracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByDeleted(Boolean deleted);
	User findByUserName(String username);
}
