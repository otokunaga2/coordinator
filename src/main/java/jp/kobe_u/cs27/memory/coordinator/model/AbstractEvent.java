package jp.kobe_u.cs27.memory.coordinator.model;

public abstract class AbstractEvent {
	private String property;
	private String value;
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
}
