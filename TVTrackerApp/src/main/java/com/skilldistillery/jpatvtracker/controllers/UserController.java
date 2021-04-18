package com.skilldistillery.jpatvtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jpatvtracker.entities.User;
import com.skilldistillery.jpatvtracker.services.UserService;

@CrossOrigin({"*", "http://localhost:4300"})
@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService svc;
	
	@GetMapping("users")
	public List<User> listUsers(){
//		Users that have not been deleted
		return svc.allActiveUsers(false);
	}
	
	@GetMapping("users/{id}")
	public User showUser(@PathVariable int id,
			HttpServletResponse response) {
		User user = svc.showUser(id);
		if (user == null) {
			response.setStatus(404);
		}
		return user;
	}
	
	@PostMapping("users")
	public User addUser(@RequestBody User user,
			HttpServletResponse response,
			HttpServletRequest request){
		try {
			svc.addUser(user);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(user.getId());
			response.setHeader("location", url.toString());	
		}
		catch (Exception e) {
			System.err.println(e);
			response.setStatus(404);
			user = null;
		}
		return user;
	}
	
	@PutMapping("users/{id}")
	public User updateUser(@PathVariable int id,
			@RequestBody User user,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			user = svc.updateUser(id, user);
			if (user == null) {
				response.setStatus(404);
			}
			response.setStatus(200);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(user.getId());
			response.setHeader("location", url.toString());	
		}
		catch (Exception e) {
			System.err.println(e);
			response.setStatus(404);
			user = null;
		}
		return user;
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id,
			HttpServletResponse response) {
		try {
			boolean deleted = svc.softDeleteUser(id);
			
			if (deleted) {
				response.setStatus(204);
			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			System.err.println(e);
			response.setStatus(400);
		}
	}

}
