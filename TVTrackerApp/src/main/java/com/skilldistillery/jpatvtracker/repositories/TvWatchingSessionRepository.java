package com.skilldistillery.jpatvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jpatvtracker.entities.TvWatchingSession;

public interface TvWatchingSessionRepository extends JpaRepository<TvWatchingSession, Integer> {
	List<TvWatchingSession> findByDeleted(Boolean deleted);
}
