package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;
import com.skilldistillery.jpatvtracker.repositories.TvWatchingSessionRepository;

@Service
@Transactional
public class TvWatchingSessionServiceImpl implements TvWatchingSessionService {

	@Autowired
	private TvWatchingSessionRepository repo;
		
	@Override
	public List<TvWatchingSession> allTvWatchingSessions() {
		return repo.findAll();
	}

	@Override
	public TvWatchingSession retrieveSession(int sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
