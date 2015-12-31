package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
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

	@Test
	public void testUpdateAction() {
		boolean actual = actionDAO.updateAction(15);
		assertTrue(actual);
	}

	@Test
	public void testFindAction() {
		/*sample data insert*/
		actionDAO.createAction("test action", "http://example.com");

		long actionId = 1;
		Action result = actionDAO.findAction(actionId);
		assertNotNull(result);
	}

	@Test
	public void testDeleteAction() {


//		actionDAO.deleteAction(actionId);

		fail("まだ実装されていません");
	}

	@Test
	public void testCreateAction() {
		String actionId = "1xt";
		actionDAO.createAction("test action", "http://example.com");
	}

}
