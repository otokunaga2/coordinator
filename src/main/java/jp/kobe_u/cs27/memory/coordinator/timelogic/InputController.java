package jp.kobe_u.cs27.memory.coordinator.timelogic;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import com.google.gson.Gson;

import jp.kobe_u.cs27.memory.coordinator.dao.CareECADAO;
import jp.kobe_u.cs27.memory.coordinator.model.AbstractEvent;
import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;

public class InputController {
	private CareECADAO ecaDAO = null;
	private TimeContextController timeCtxController = null;
	private Gson gson = null;
	HashMap<String, DateTime> actionManegementMap = null;
	HashMap<String,Boolean> firstFilterResult = null;
	public InputController() {
		ecaDAO = new CareECADAO();
		timeCtxController = new TimeContextController();
		gson = new Gson();
		actionManegementMap = new HashMap<String, DateTime>();
		firstFilterResult = new HashMap<String,Boolean>();
	}

	/**
	 * イベントの条件をもとに、実行可能なケアを検索するためのメソッド
	 *
	 * @param tEven
	 * @return
	 */
	public List<CareECA> findConditionUsingEvent(AbstractEvent tEvent) {
		List<CareECA> eventList = null;
		String currentProp = tEvent.getProperty();
		String currentVal = tEvent.getValue();
		CareECA careECA = new CareECA();
		careECA.setProperty(currentProp);
		careECA.setValue(currentVal);
		/* 時間の条件をもとに検索する */
		eventList = ecaDAO.findECAUsingEvent(careECA);
		return eventList;
	}

	public boolean doAction(AbstractEvent tEvent) {
		List<CareECA> eventList = this.findConditionUsingEvent(tEvent);
		for(CareECA targetCare: eventList){
			boolean result = checkTimeCondition(targetCare);
			if(result == true){
				updateActionResult(targetCare.getActionId());
			}
		}
		return true;
	}

	private boolean checkTimeCondition(CareECA targetCare) {
		String cond = targetCare.getTimeCondition();
		/*jsonからPojoへマッピング*/
		TimeCondition timeCond = gson.fromJson(targetCare.getTimeCondition(), TimeCondition.class);
		boolean result = timeCtxController.evaluate(timeCond);
		return result;
	}

	public HashMap<String, DateTime> updateActionResult(String actionId) {
		actionManegementMap.put(actionId, timeCtxController.getCurrentDateTime().now());/*最終時刻を更新*/

		return actionManegementMap;
	}

}
