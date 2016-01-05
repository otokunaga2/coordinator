package jp.kobe_u.cs27.memory.coordinator.timelogic;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;

import com.google.gson.Gson;

import jp.kobe_u.cs27.memory.coordinator.action.FiredAction;
import jp.kobe_u.cs27.memory.coordinator.dao.ActionDAO;
import jp.kobe_u.cs27.memory.coordinator.dao.CareECADAO;
import jp.kobe_u.cs27.memory.coordinator.model.AbstractEvent;
import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeIntervalCondition;

public class InputController {
	private CareECADAO ecaDAO = null;
	private TimeContextController timeCtxController = null;
	private Gson gson = null;
	ConcurrentHashMap<Long, DateTime> actionManegementMap = null;/* スレッドセーフなhashmap */
	ConcurrentHashMap<String, Boolean> firstFilterResult = null;
	private ActionDAO actionDAO = null;
	private FiredAction firedAction = null;
	public InputController() {
		firedAction = new FiredAction();
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

		TimeIntervalCondition timeCond = new TimeIntervalCondition(from,to);
		String jsonConvrtedTimeCond = gson.toJson(timeCond);
		eca.setTimeCondition(jsonConvrtedTimeCond);
		String result = ecaDAO.createECA(eca);
		if(result != null){
			return true;
		}else{
			return false;
		}
	}
	public boolean saveECA(String prop, String val, String from, String to, String timeCtx, long actionId){
		CareECA eca = new CareECA();
		eca.setProperty(prop);
		eca.setValue(val);
		eca.setTimeContext(timeCtx);
		eca.setActionId(actionId);
		System.out.println("actionId is"+actionId);
		TimeIntervalCondition timeCond = new TimeIntervalCondition(from,to);
		String jsonConvrtedTimeCond = gson.toJson(timeCond);
		eca.setTimeCondition(jsonConvrtedTimeCond);
		System.out.println(jsonConvrtedTimeCond);
		String result = ecaDAO.createECA(eca);
		if(result != null){
			return true;
		}else{
			return false;
		}
	}

	public boolean invokeAction(long actionId){
		boolean result = firedAction.getAndInvokeActionFromId(actionId);
		if(result == true){
			actionDAO.updateAction(actionId);
		}
		return false;
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

	public boolean isValidEventCondition(List<CareECA> eventList) {
		boolean result = false;
		for (CareECA targetCare : eventList) {
			// 時間の条件のチェック
			boolean timeExecutionValidator = checkTimeCondition(
					targetCare);/*
								 * 時間の条件をひとまずチェック（後から条件増えるかもしれんので。ここはもうちょい抽象化したい
								 */
			System.out.println("judge reesult"+timeExecutionValidator);

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
		TimeIntervalCondition timeCond = gson.fromJson(targetCare.getTimeCondition(), TimeIntervalCondition.class);
		boolean result = timeCtxController.isWithinTimeInterval(timeCond);
		return result;
	}

	public ConcurrentHashMap<Long, DateTime> updateActionResult(long actionId) {
		actionManegementMap.put(actionId, timeCtxController.getCurrentDateTime().now());/* 最終時刻を更新 */
		return actionManegementMap;
	}

}
