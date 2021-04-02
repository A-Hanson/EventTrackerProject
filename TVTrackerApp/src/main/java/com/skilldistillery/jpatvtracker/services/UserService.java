package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.User;

public interface UserService {
	List<User> allUsers();
	User showUser(int id);
}
