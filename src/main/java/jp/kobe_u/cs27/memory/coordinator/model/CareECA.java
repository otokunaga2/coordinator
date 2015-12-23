package jp.kobe_u.cs27.memory.coordinator.model;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;

import org.joda.time.DateTime;

public class CareECA {
	private String property;
	private String value;
	private JsonObject timeCondition;
	private JsonObject condition;
	private DateTime lastInvacation;
	private long actionId;
	private String currentCareId;/*care id*/
	
	
	private DateTime lastInvocation;
	
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
	public JsonObject getTimeCondition() {
		return timeCondition;
	}
	public void setTimeCondition(JsonObject timeCondition) {
		this.timeCondition = timeCondition;
	}
	public JsonObject getTimeContext() {
		return condition;
	}
	public void setTimeContext(JsonObject timeContext) {
		this.condition = timeContext;
	}

	public String getCareId() {
		return currentCareId;
	}
	public void setCareId(String careId) {
		this.currentCareId = careId;
	}
	public DateTime getLastInvacation() {
		return lastInvacation;
	}
	public void setLastInvacation(DateTime lastInvacation) {
		this.lastInvacation = lastInvacation;
	}

	
	
	
}
