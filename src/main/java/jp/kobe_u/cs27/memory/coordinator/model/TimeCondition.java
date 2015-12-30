package jp.kobe_u.cs27.memory.coordinator.model;

public class TimeCondition extends AbstractCondition{
	private String from;
	private String to;
	public TimeCondition(){

	}
	public TimeCondition(String from, String to){
		this.from = from;
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
