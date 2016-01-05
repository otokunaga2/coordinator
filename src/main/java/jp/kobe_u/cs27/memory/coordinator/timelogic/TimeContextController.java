package jp.kobe_u.cs27.memory.coordinator.timelogic;

import java.util.regex.Pattern;

import javax.validation.constraints.Null;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

import jp.kobe_u.cs27.memory.coordinator.model.TimeIntervalCondition;

/**
 *
 * 時間に関するコンテキストを判断するクラス
 *
 * @author otokunaga
 *
 */
public class TimeContextController {
	private DateTime currentDateTime;

	public TimeContextController() {
		currentDateTime = new DateTime();
	}
	public DateTime getCurrentDateTime(){
		return currentDateTime;
	}

	
	public boolean isWithinTimeInterval(TimeIntervalCondition timeCond) {
		String startTime = timeCond.getFrom();
		String endTime = timeCond.getTo();
		if(startTime == null || endTime == null){
			return false;
		}
		DateTime startDateTime = createDateTimeFormatUsingStr(startTime);
		DateTime endDateTime = createDateTimeFormatUsingStr(endTime);
		
		boolean isAfter = false;
		boolean isBefore = false;
		isBefore = endDateTime.isAfterNow();
		isAfter = startDateTime.isBeforeNow();

		if (isAfter && isBefore) {
			return true;
		} else {
			return false;
		}
	}
	


	private DateTime createDateTimeFormatUsingStr(
			String dateTime/* e.g. 12:13:00 caution:if missing time 12:11 then following logic fail*/) {
		Pattern pattern = Pattern.compile(":");
		Property year = currentDateTime.dayOfYear();
		Property month = currentDateTime.dayOfMonth();
		Property day = currentDateTime.dayOfWeek();
		DateTime dt = new DateTime();
		String[] splitedDateTime;
		try{
			splitedDateTime = pattern.split(dateTime);
			System.out.println(splitedDateTime.toString());
		}catch(NullPointerException e){
			return null;
		}
		Integer tempVal = new Integer(0);
		dt = new DateTime(currentDateTime.now().getYear(), currentDateTime.now().getMonthOfYear(),
				currentDateTime.now().getDayOfMonth(), Integer.parseInt(splitedDateTime[0]/*hour*/),
				Integer.parseInt(splitedDateTime[1])/*minite*/, Integer.parseInt(splitedDateTime[2])/*second*/);
		return dt;
	}

}
