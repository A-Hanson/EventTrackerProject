package com.skilldistillery.jpatvtracker.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tv_watching_session")
public class TvWatchingSession {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime start;
	
	private LocalDateTime stop;
	
	
//	TODO: User and Platform
	
//	Constructor
	public TvWatchingSession() {}

//	Methods
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getStop() {
		return stop;
	}

	public void setStop(LocalDateTime stop) {
		this.stop = stop;
	}
	

	@Override
	public String toString() {
		return "TvWatchingSession [id=" + id + ", start=" + start + ", stop=" + stop + "]";
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
		TvWatchingSession other = (TvWatchingSession) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
