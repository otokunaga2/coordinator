package jp.kobe_u.cs27.memory.coordinator.model;

import org.json.JSONObject;

public class CareECA {
	private String property;
	private String value;
	private String timeCondition;
	private String condition;

	private long actionId;

	/**
	 * @param prop
	 * @param val
	 * @param timeCondition
	 * @param condition
	 */
	public CareECA(String prop, String val, JSONObject timeCondition, JSONObject condition) {

	}

	/**
	 * default constructor
	 */
	public CareECA() {

	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTimeCondition() {
		return timeCondition;
	}

	public void setTimeCondition(String timeCondition) {
		this.timeCondition = timeCondition;
	}

	public String getTimeContext() {
		return condition;
	}

	public void setTimeContext(String timeContext) {
		this.condition = timeContext;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

}
