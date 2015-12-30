package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeContextDAOTest {
	private TimeContextDAO timeDao;
	@Before
	public void setUp() throws Exception {
		timeDao = new TimeContextDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateSingleTime() {
		
		timeDao.createSingleEventTime("");
		
		
//		fail("Not yet implemented");
	}

	@Test
	public void testCreateDailyTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecurrenceTime() {
		fail("Not yet implemented");
	}

}
