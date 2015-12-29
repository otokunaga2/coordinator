package jp.kobe_u.cs27.memory.coordinator.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
public class TriggerEvent {

	private String property;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
