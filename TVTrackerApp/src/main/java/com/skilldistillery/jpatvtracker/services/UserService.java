package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.User;

public interface UserService {
	List<User> allUsers();
	List<User> allActiveUsers(Boolean deleted);
	User showUser(int id);
	User addUser(User user);
	User updateUser(int id, User user);
	boolean softDeleteUser(int id);
}
