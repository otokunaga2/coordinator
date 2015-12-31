package jp.kobe_u.cs27.memory.coordinator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeCondition extends AbstractCondition{
	private String from;
	private String to;
	public TimeCondition(){

	}
	public TimeCondition(String from, String to){
		boolean conditionFrom = checkDateFormat(from);
		boolean conditionTo = checkDateFormat(to);
		if(!conditionFrom){
			fillMissingDigit(from);
		}
		if(!conditionTo){
			fillMissingDigit(to);
		}
	}
	private void fillMissingDigit(String from) {
		boolean checkMissingFormat = isMissingDigit(from);
		if(checkMissingFormat){
			String replaced = replaceMissingFormat(from);
			this.from = replaced;
		}else{
			this.from = from;
		}
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
	public boolean checkDateFormat(String datePattern){
		Pattern pattern  = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
		Matcher res = pattern.matcher(datePattern);
		return res.find();
	}

	public boolean isMissingDigit(String datePattern){
		Pattern pattern = Pattern.compile("^\\d{2}:\\d{2}$");
		Matcher res = pattern.matcher(datePattern);
		return res.find();
	}
	/**/
	public String replaceMissingFormat(String missingDate){
		String replaced  = missingDate+":00";
		return replaced;
	}
}
