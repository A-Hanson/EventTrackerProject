package com.skilldistillery.jpatvtracker.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Platform {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="platform")
	private List<TvWatchingSession> tvWatchingSessions;
	
//	Constructor
	public Platform() {}

//	Methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public List<TvWatchingSession> getTvWatchingSessions() {
		return tvWatchingSessions;
	}

	public void setTvWatchingSessions(List<TvWatchingSession> tvWatchingSessions) {
		this.tvWatchingSessions = tvWatchingSessions;
	}
	
	public void addTvWatchingSession(TvWatchingSession tvWatchingSession) {
		if (tvWatchingSessions == null) {
			tvWatchingSessions = new ArrayList<>();
		}
		if (!tvWatchingSessions.contains(tvWatchingSession)) {
			tvWatchingSessions.add(tvWatchingSession);
			tvWatchingSession.setPlatform(this);
		}
	}
	
	public void removeTvWatchingSession(TvWatchingSession tvWatchingSession) {
		if (tvWatchingSessions != null && tvWatchingSessions.contains(tvWatchingSession)) {
			tvWatchingSessions.remove(tvWatchingSession);
			tvWatchingSession.setPlatform(null);
		}
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
