/**
 *
 */
package jp.kobe_u.cs27.memory.coordinator.timelogic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;

/**
 * @author otokunaga
 *
 */
public class TimeContextControllerTest {

	private TimeContextController timeController;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		timeController = new TimeContextController();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link jp.kobe_u.cs27.memory.coordinator.timelogic.TimeContextController#TimeContextController()} のためのテスト・メソッド。
	 */
	@Test
	public void testTimeContextController() {

	}

	/**
	 * {@link jp.kobe_u.cs27.memory.coordinator.timelogic.TimeContextController#evaluate(jp.kobe_u.cs27.memory.coordinator.model.TimeCondition)} のためのテスト・メソッド。
	 */
	@Test
	public void testEvaluate() {
		TimeCondition testCond = new TimeCondition();
		testCond.setFrom("12:10:00");
		testCond.setTo("23:51:00");
		boolean expected = true;
		boolean actual = timeController.evaluate(testCond);
		assertEquals(expected, actual);
	}
	@Test
	public void alwaysFailTestEvaluate(){
		TimeCondition testCond = new TimeCondition();
		testCond.setFrom("23:59:00");
		testCond.setTo("23:59:00");
		boolean expected = false;
		boolean actual = timeController.evaluate(testCond);
		assertEquals(expected, actual);
	}

	@Test
	public void maybeWellDoneEvaludate(){
		TimeCondition testCond = new TimeCondition();
		testCond.setFrom("12:00:00");
		testCond.setTo("23:59:00");
		boolean expected = true;
		boolean actual = timeController.evaluate(testCond);
		assertEquals(expected, actual);

	}

}
