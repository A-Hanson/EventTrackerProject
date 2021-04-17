package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;

public interface TvWatchingSessionService {
	List<TvWatchingSession> allTvWatchingSessions();
	List<TvWatchingSession> allActiveTvWatchingSessions(Boolean deleted);
	List<TvWatchingSession> allActiveSessionsByUser(String username);
	TvWatchingSession retrieveSession(int sessionId);
	TvWatchingSession retrieveSessionByUser(int sessionId, String username);
	TvWatchingSession addSession(TvWatchingSession session, String username);
	TvWatchingSession updateSession(int id, TvWatchingSession session);
	boolean softDeleteSession(int id);
}
