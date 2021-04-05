package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpatvtracker.entities.User;
import com.skilldistillery.jpatvtracker.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> allUsers() {
		return repo.findAll();
	}
	
	@Override
	public List<User> allActiveUsers(Boolean deleted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean softDeleteUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}


}
