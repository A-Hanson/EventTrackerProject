package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;
import com.skilldistillery.jpatvtracker.entities.User;
import com.skilldistillery.jpatvtracker.repositories.PlatformRepository;
import com.skilldistillery.jpatvtracker.repositories.TvWatchingSessionRepository;
import com.skilldistillery.jpatvtracker.repositories.UserRepository;

@Service
@Transactional
public class TvWatchingSessionServiceImpl implements TvWatchingSessionService {

	@Autowired
	private TvWatchingSessionRepository repo;
	
	@Autowired
	private PlatformRepository platformRepo;
	
	@Autowired
	private UserRepository userRepo;
		
	@Override
	public List<TvWatchingSession> allTvWatchingSessions() {
		return repo.findAll();
	}
	
	@Override
	public List<TvWatchingSession> allActiveTvWatchingSessions(Boolean deleted){
		return repo.findByDeleted(deleted);
	}
	
	@Override
	public List<TvWatchingSession> allActiveSessionsByUser(String username) {
		return repo.findByDeletedFalseAndUser_UserName(username);
	}

	@Override
	public TvWatchingSession retrieveSession(int sessionId) {
		TvWatchingSession session = null;
		if (repo.findById(sessionId).isPresent()) {
			session = repo.findById(sessionId).get();
			if (session.getDeleted()) {
				session = null;
			}
		}
		return session;
	}
	
	@Override
	public TvWatchingSession retrieveSessionByUser(int sessionId, String username) {
		return repo.findByIdAndUser_UserName(sessionId, username);
	}

	@Override
	public TvWatchingSession addSession(TvWatchingSession session, String username) {
		User user = userRepo.findByUserName(username);
		if (user != null) {
			session.setUser(user);
			repo.saveAndFlush(session);
		} else {
			session = null;
		}
		return session;
	}

	@Override
	public TvWatchingSession updateSession(int id, TvWatchingSession session, String username) {
		TvWatchingSession sessionUpdated = repo.findByIdAndUser_UserName(id, username);
		if (sessionUpdated != null) {
			sessionUpdated = repo.findById(id).get();
			if (session.getStart() != null) {
				sessionUpdated.setStart(session.getStart());
			}
			if (session.getStop() != null) {
				sessionUpdated.setStop(session.getStop());				
			}
			if (session.getPlatform() != null) {
				if (platformRepo.findById(session.getPlatform().getId()).isPresent()) {
					sessionUpdated.setPlatform(platformRepo.findById(session.getPlatform().getId()).get());
				}
				
			}
			if (session.getUser() != null) {
				if (userRepo.findById(session.getUser().getId()).isPresent()) {
					sessionUpdated.setUser(userRepo.findById(session.getUser().getId()).get());
				}
				
			}
		}
		
		return sessionUpdated;
	}

	@Override
	public boolean softDeleteSession(int id, String username) {
		boolean deleted = false;
		if (repo.findByIdAndUser_UserName(id, username) != null) {
			TvWatchingSession session = repo.findById(id).get();
			session.setDeleted(true);
			deleted = true;
		}
		return deleted;
	}

}
