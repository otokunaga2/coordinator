package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBObject;

public class ActionDAOTest {
	private ActionDAO actionDAO = null;

	DateTime dt = new DateTime();
	@Before
	public void setUp() throws Exception {
		actionDAO = new ActionDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateAction() {
		fail("まだ実装されていません");
	}

	@Test
	public void testFindAction() {
		String actionId = "1";
		DBObject result = actionDAO.findAction(actionId);
		assertNotNull(result);
	}

	@Test
	public void testDeleteAction() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCreateAction() {
		String actionId = "1";

		actionDAO.createAction("test action", "http://example.com", dt);
	}

}
