package com.skilldistillery.jpatvtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jpatvtracker.entities.Platform;
import com.skilldistillery.jpatvtracker.services.PlatformService;

@RestController
@RequestMapping("api")
public class PlatformController {
	
	@Autowired
	private PlatformService svc;
	
	@GetMapping("platforms")
	public List<Platform> listPlatforms(){
		return svc.allActivePlatforms(false);
	}
	
	@GetMapping("platforms/{id}")
	public Platform showPlatform(@PathVariable int id,
			HttpServletResponse response) {
		Platform platform = svc.showPlatform(id);
		if (platform == null) {
			response.setStatus(404);
		}
		return platform;
	}
	
	@PostMapping("platforms")
	public Platform addPlatform(@RequestBody Platform platform,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			svc.addPlatform(platform);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(platform.getId());
			response.setHeader("location", url.toString());
			
		}
		catch (Exception e) {
			System.err.println(e);
			response.setStatus(404);
			platform = null;
		}	
		return platform;
	}
	
	@PutMapping("platforms/{id}")
	public Platform updatePlatform(@PathVariable int id,
			@RequestBody Platform platform,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			platform = svc.updatePlatform(id, platform);
			if (platform == null) {
				response.setStatus(404);
			}
			response.setStatus(200);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(platform.getId());
			response.setHeader("location", url.toString());
		}
		catch (Exception e) {
			System.err.println(e);
			response.setStatus(404);
			platform = null;
		}	
		return platform;
	}
	
	@DeleteMapping("platforms/{id}")
	public void deletePlatform(@PathVariable int id,
			HttpServletResponse response) {
		try {
			boolean deleted = svc.softDeletePlatform(id);
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
