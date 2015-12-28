package jp.kobe_u.cs27.memory.coordinator.eca;

import org.joda.time.DateTime;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class CareECA {
	private String property;
	private String value;
	private String timeCondition;
	private String condition;

	private String[] actionId;/* CareActionのobjectidを参照するように設計 */

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

	public String[] getActionId() {
		return actionId;
	}

	public void setActionId(String[] actionId) {
		this.actionId = actionId;
	}

}
