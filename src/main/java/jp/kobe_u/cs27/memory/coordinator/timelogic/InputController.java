package jp.kobe_u.cs27.memory.coordinator.timelogic;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;

import com.google.gson.Gson;

import jp.kobe_u.cs27.memory.coordinator.dao.ActionDAO;
import jp.kobe_u.cs27.memory.coordinator.dao.CareECADAO;
import jp.kobe_u.cs27.memory.coordinator.model.AbstractEvent;
import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;

public class InputController {
	private CareECADAO ecaDAO = null;
	private TimeContextController timeCtxController = null;
	private Gson gson = null;
	ConcurrentHashMap<Long, DateTime> actionManegementMap = null;/* スレッドセーフなhashmap */
	ConcurrentHashMap<String, Boolean> firstFilterResult = null;
	private ActionDAO actionDAO = null;
	public InputController() {
		actionDAO = new ActionDAO();
		ecaDAO = new CareECADAO();
		timeCtxController = new TimeContextController();
		gson = new Gson();
		actionManegementMap = new ConcurrentHashMap<Long, DateTime>();
		firstFilterResult = new ConcurrentHashMap<String, Boolean>();
	}

	public boolean saveECA(String prop, String val, String from, String to){
		CareECA eca = new CareECA();
		eca.setProperty(prop);
		eca.setValue(val);
		TimeCondition timeCond = new TimeCondition(from,to);
		String jsonConvrtedTimeCond = gson.toJson(timeCond);
		eca.setTimeCondition(jsonConvrtedTimeCond);
		String result = ecaDAO.createECA(eca);
		if(result != null){
			return true;
		}else{
			return false;
		}
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

	public boolean isAction(List<CareECA> eventList) {
		boolean result = false;
		for (CareECA targetCare : eventList) {
			// 時間の条件のチェック
			boolean timeExecutionValidator = checkTimeCondition(
					targetCare);/*
								 * 時間の条件をひとまずチェック（後から条件増えるかもしれんので。ここはもうちょい抽象化したい
								 */

		}
		return result;
	}
	private boolean checkInvokeTime(long arg){
		DateTime lastExecution = actionManegementMap.get(arg);
		//時間のバリデーションをDBから取得
		return false;
	}
	private boolean checkTimeCondition(CareECA targetCare) {
		String cond = targetCare.getTimeCondition();
		/* jsonからPojoへマッピング */
		TimeCondition timeCond = gson.fromJson(targetCare.getTimeCondition(), TimeCondition.class);
		boolean result = timeCtxController.evaluate(timeCond);
		return result;
	}

	public ConcurrentHashMap<Long, DateTime> updateActionResult(long actionId) {
		actionManegementMap.put(actionId, timeCtxController.getCurrentDateTime().now());/* 最終時刻を更新 */
		return actionManegementMap;
	}

}
