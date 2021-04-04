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

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;
import com.skilldistillery.jpatvtracker.services.TvWatchingSessionService;

@RestController
@RequestMapping("api")
public class TvWatchingSessionController {

	@Autowired
	private TvWatchingSessionService svc;
	
	@GetMapping("ping")
	public String test() {
		return "pong";
	}
	
	@GetMapping("tv_watching_sessions")
	public List<TvWatchingSession> listSessions(){
		return svc.allTvWatchingSessions();
	}
	
	@GetMapping("tv_watching_sessions/{id}")
	public TvWatchingSession showSession(@PathVariable int id,
			HttpServletResponse response){
		TvWatchingSession session = svc.retrieveSession(id);
		if (session == null) {
			response.setStatus(404);
		}
		return session;
	}
	
	@PostMapping("tv_watching_sessions")
	public TvWatchingSession addSession(@RequestBody TvWatchingSession tvWatchingSession,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			svc.addSession(tvWatchingSession);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(tvWatchingSession.getId());
			response.setHeader("location", url.toString());	
		}
		catch (Exception e) {
			System.err.println(e);
			response.setStatus(404);
			tvWatchingSession = null;
		}
		return tvWatchingSession;
	}
	
	@PutMapping("tv_watching_sessions/{id}")
	public TvWatchingSession updateSession(@PathVariable int id,
			@RequestBody TvWatchingSession tvWatchingSession,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			tvWatchingSession = svc.updateSession(id, tvWatchingSession);
			if (tvWatchingSession == null) {
				response.setStatus(404);
			}
			response.setStatus(200);
			StringBuffer url = request.getRequestURL();			
			url.append("/").append(tvWatchingSession.getId());
			response.setHeader("location", url.toString());	
			
		}
		catch (Exception e){
			System.err.println(e);
			response.setStatus(400);
			tvWatchingSession = null;
		}
		return tvWatchingSession;
	}
	
	@DeleteMapping("tv_watching_sessions/{id}")
	public void updateSession(@PathVariable int id,
			HttpServletResponse response) {
		try {
			boolean deleted = svc.softDeleteSession(id);
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
