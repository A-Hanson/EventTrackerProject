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
		return repo.findByDeleted(deleted);
	}

	@Override
	public User showUser(int id) {
		User user = null;
		if (repo.findById(id).isPresent()) {
			user = repo.findById(id).get();
			if (user.getDeleted()) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public User addUser(User user) {
		return repo.save(user);
	}

	@Override
	public User updateUser(int id, User user) {
		User userUpdated = null;
		if (repo.findById(id).isPresent()) {
			userUpdated = repo.findById(id).get();
			if (user.getFirstName() != null && user.getFirstName().length() > 0) {
				userUpdated.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null && user.getLastName().length() > 0) {
				userUpdated.setLastName(user.getLastName());
			}
			if (user.getUserName() != null && user.getUserName().length() > 0) {
				userUpdated.setUserName(user.getUserName());
			}
			if (user.getPassword() != null && user.getPassword().length() > 0) {
				userUpdated.setPassword(user.getPassword());
			}
			if (user.getRole() != null && user.getRole().length() > 0) {
				userUpdated.setRole(user.getRole());
			}
		}
		return userUpdated;
	}

	@Override
	public boolean softDeleteUser(int id) {
		boolean deleted = false;
		if (repo.findById(id).isPresent()) {
			User user = repo.findById(id).get();
			user.setDeleted(true);
			deleted = true;
		}
		return deleted;
	}


}
