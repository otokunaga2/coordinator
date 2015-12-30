package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.kobe_u.cs27.memory.coordinator.model.Action;

public class ActionDAOTest {
	private ActionDAO actionDAO = null;

	DateTime dt = null;
	@Before
	public void setUp() throws Exception {
		actionDAO = new ActionDAO();
		dt = new DateTime();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testUpdateAction() {
		fail("まだ実装されていません");
	}

	@Test
	public void testFindAction() {
		/*sample data insert*/
		actionDAO.createAction("test action", "http://example.com");

		long actionId = 1;
		Action result = actionDAO.findAction(actionId);
		assertNotNull(result);
	}

	@Ignore
	public void testDeleteAction() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCreateAction() {
		String actionId = "1";
		actionDAO.createAction("test action", "http://example.com");
	}

}
