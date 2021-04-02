package com.skilldistillery.jpatvtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
