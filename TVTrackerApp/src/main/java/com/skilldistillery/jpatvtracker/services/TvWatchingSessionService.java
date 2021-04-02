package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;

public interface TvWatchingSessionService {
	List<TvWatchingSession> allTvWatchingSessions();
	TvWatchingSession retrieveSession(int sessionId);
}
