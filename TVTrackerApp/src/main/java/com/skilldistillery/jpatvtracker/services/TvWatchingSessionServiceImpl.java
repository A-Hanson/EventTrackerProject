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
		TvWatchingSession session = null;
		if (repo.findById(sessionId).isPresent()) {
			session = repo.findById(sessionId).get();
		}
		return session;
	}

	@Override
	public TvWatchingSession addSession(TvWatchingSession session) {
		try {
			session = repo.save(session);
		}
		catch (IllegalArgumentException e) {
			session = null;
			System.err.println(e.getMessage());
		}
		return session;
	}

	@Override
	public TvWatchingSession updateSession(int id, TvWatchingSession session) {
		TvWatchingSession sessionUpdated = null;
		if (repo.findById(id).isPresent()) {
			sessionUpdated = repo.findById(id).get();
			sessionUpdated.setStart(session.getStart());
			sessionUpdated.setStop(session.getStop());
		}
		
		return sessionUpdated;
	}

	@Override
	public boolean softDeleteSession(int id) {
		boolean deleted = false;
		if (repo.findById(id).isPresent()) {
			TvWatchingSession session = repo.findById(id).get();
			session.setDeleted(true);
			deleted = true;
		}
		return deleted;
	}

}
