package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import com.skilldistillery.jpatvtracker.entities.Platform;

public interface PlatformService {
	List<Platform> allPlatforms();
	List<Platform> allActivePlatforms(boolean deleted);
	Platform showPlatform(int id);
	Platform addPlatform(Platform platform);
	Platform updatePlatform(int id, Platform platform);
	boolean softDeletePlatform(int id);
}
