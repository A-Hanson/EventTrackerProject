package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;

public interface TvWatchingSessionService {
	List<TvWatchingSession> allTvWatchingSessions();
	List<TvWatchingSession> allActiveTvWatchingSessions(Boolean deleted);
	TvWatchingSession retrieveSession(int sessionId);
	TvWatchingSession addSession(TvWatchingSession session);
	TvWatchingSession updateSession(int id, TvWatchingSession session);
	boolean softDeleteSession(int id);
}
