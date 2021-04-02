package com.skilldistillery.jpatvtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jpatvtracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
