package jp.kobe_u.cs27.memory.coordinator.timelogic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import jp.kobe_u.cs27.memory.coordinator.dao.CareECADAO;
import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;
import jp.kobe_u.cs27.memory.coordinator.model.TriggerEvent;

public class InputControllerTest {
	private InputController inputCtroller;
	private CareECADAO daoController;
	TriggerEvent tEvent = new TriggerEvent();

	@Before
	public void setUp() throws Exception {
		inputCtroller = new InputController();
		/*検索対象のdummyデータの登録をしておく*/
		daoController = new CareECADAO();
		CareECA eca = new CareECA();
		eca.setActionId("-1");
		eca.setValue("dummyVal");
		eca.setProperty("dummyProp");
		eca.setTimeContext("{timeValidation:  }");
		eca.setTimeCondition("{'from':'10:00:00', 'to': '12:00:00'}");
		daoController.createECA(eca);
		tEvent.setProperty("dummyProp");
		tEvent.setValue("dummyVal");

	}

	@After
	public void tearDown() throws Exception {

	}
	@Test
	public void testCreateECA(){
		String prop = "testPropVal";
		String val = "tstVal";
		TimeCondition cond = new TimeCondition("10:00:00","12:00:00");
		Gson gson = new Gson();
		String convTimeCond = gson.toJson(cond);
		boolean actual = inputCtroller.saveECA(prop, val,"10:00:00", "12:00:00");
		boolean expected = true;
		assertEquals(expected,actual);
	}
	@Test
	public void testAnalyzeInputEventData() {
		List<CareECA> expectedNotNullList = inputCtroller.findConditionUsingEvent(tEvent);

		assertNotNull(expectedNotNullList);
	}
	@Test
	public void testDoAction() {
//		fail("まだ実装されていません");
//		inputCtroller.isAction(tEvent);

	}

}
