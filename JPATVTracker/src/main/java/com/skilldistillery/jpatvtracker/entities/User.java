package com.skilldistillery.jpatvtracker.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String role;
	
	private Boolean deleted;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<TvWatchingSession> tvWatchingSessions;
	
//	Constructor
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
			if (tvWatchingSession.getUser() != null) {
				tvWatchingSession.getUser().getTvWatchingSessions().remove(tvWatchingSession);
			}
			tvWatchingSession.setUser(this);
		}
	}
	
	public void removeTvWatchingSession(TvWatchingSession tvWatchingSession) {
		if (tvWatchingSessions != null && tvWatchingSessions.contains(tvWatchingSession)) {
			tvWatchingSessions.remove(tvWatchingSession);
			tvWatchingSession.setUser(null);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", deleted=" + deleted + "]";
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
