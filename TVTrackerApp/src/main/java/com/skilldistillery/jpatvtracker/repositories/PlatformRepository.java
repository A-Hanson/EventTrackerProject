package com.skilldistillery.jpatvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jpatvtracker.entities.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
	List<Platform> findByDeleted(Boolean deleted);
}
