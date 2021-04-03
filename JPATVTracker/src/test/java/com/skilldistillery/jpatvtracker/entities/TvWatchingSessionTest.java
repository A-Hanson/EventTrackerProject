package com.skilldistillery.jpatvtracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TvWatchingSessionTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TvWatchingSession tvWatchingSession;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("TVTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		tvWatchingSession = em.find(TvWatchingSession.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tvWatchingSession = null;
	}

	@Test
	@DisplayName("Test TV watching session mapping")
	void test_1() {
		assertNotNull(tvWatchingSession);
		assertEquals(2021, tvWatchingSession.getStart().getYear());
		assertEquals(4, tvWatchingSession.getStop().getMonthValue());
		assertFalse(tvWatchingSession.getDeleted());
	}
	
	@Test
	@DisplayName("Test TV watching session to User mapping")
	void test_2() {
		assertNotNull(tvWatchingSession);
		assertNotNull(tvWatchingSession.getUser());
		assertEquals("Betty", tvWatchingSession.getUser().getFirstName());
	}
	
	@Test
	@DisplayName("Test TV Watching Session to Platform mapping")
	void test_3() {
		assertNotNull(tvWatchingSession);
		assertNotNull(tvWatchingSession.getPlatform());
		assertEquals("Amazon Video", tvWatchingSession.getPlatform().getName());
	}

}
