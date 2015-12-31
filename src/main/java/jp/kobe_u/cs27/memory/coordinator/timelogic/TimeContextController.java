package jp.kobe_u.cs27.memory.coordinator.timelogic;

import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;

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


	public boolean evaluate(TimeCondition timeCond) {
		String startTime = timeCond.getFrom();
		String endTime = timeCond.getTo();
		DateTime startDateTime = createDateTimeFormatUsingStr(startTime);
		DateTime endDateTime = createDateTimeFormatUsingStr(endTime);
		System.out.println(startDateTime.toString());
		System.out.println(endDateTime.toString());
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
		System.out.println(dateTime.toString());
		String[] splitedDateTime = pattern.split(dateTime);
		System.out.println(splitedDateTime);
		Integer tempVal = new Integer(0);
		dt = new DateTime(currentDateTime.now().getYear(), currentDateTime.now().getMonthOfYear(),
				currentDateTime.now().getDayOfMonth(), Integer.parseInt(splitedDateTime[0]/*hour*/),
				Integer.parseInt(splitedDateTime[1])/*minite*/, Integer.parseInt(splitedDateTime[2])/*second*/);
		return dt;
	}

}
