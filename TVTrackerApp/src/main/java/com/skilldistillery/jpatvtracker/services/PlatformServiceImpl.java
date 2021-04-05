package com.skilldistillery.jpatvtracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpatvtracker.entities.Platform;
import com.skilldistillery.jpatvtracker.repositories.PlatformRepository;

@Service
@Transactional
public class PlatformServiceImpl implements PlatformService {
	
	@Autowired
	private PlatformRepository repo;

	@Override
	public List<Platform> allPlatforms() {
		return repo.findAll();
	}

	@Override
	public List<Platform> allActivePlatforms(boolean deleted) {
		return repo.findByDeleted(deleted);
	}

	@Override
	public Platform showPlatform(int id) {
		Platform platform = null;
		if (repo.findById(id).isPresent()) {
			platform = repo.findById(id).get();
			if (platform.getDeleted()) {
				platform = null;
			}
		}
		return platform;
	}

	@Override
	public Platform addPlatform(Platform platform) {
		return repo.save(platform);
	}

	@Override
	public Platform updatePlatform(int id, Platform platform) {
		Platform platformUpdated = null;
		if (repo.findById(id).isPresent()) {
			platformUpdated = repo.findById(id).get();
			if (platform.getName() != null && platform.getName().length() > 0) {
				platformUpdated.setName(platform.getName());
			}
			if (platform.getImageUrl() != null && platform.getImageUrl().length() > 0) {
				platformUpdated.setImageUrl(platform.getImageUrl());
			}
		}	
		return platform;
	}

	@Override
	public boolean softDeletePlatform(int id) {
		boolean deleted = false;
		if (repo.findById(id).isPresent()) {
			Platform platform = repo.findById(id).get();
			platform.setDeleted(true);
			deleted = true;
		}
		return deleted;
	}

}
