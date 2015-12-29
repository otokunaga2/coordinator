package jp.kobe_u.cs27.memory.coordinator.dao;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

/**
 * @author otokunaga
 *
 */
public class TimeContextDAO {
	private final String collectionName = "";
	private DBUtil dbUtil = null;
	DateTime dt = null;
	public TimeContextDAO() {
		dbUtil = DBUtil.getInstance();
		dt = new DateTime();
	}


	/**
	 * @param timePattern
	 * @return
	 */
	public boolean createSingleEventTime(String timePattern){
		DBCollection col = dbUtil.getCollection("test");
		WriteResult result = col.insert(new BasicDBObject("number",col.count()+1).append("testTime",timePattern));/*auto increment*/
		return result.isUpdateOfExisting();

	}

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean createBetweenTime(String from, String to){
		DBCollection col = dbUtil.getCollection("test");
		WriteResult result = col.insert(new BasicDBObject("number",col.count()+1).append("from","03:23:33").append("to", "09:23:33"));/*auto increment*/
		return result.isUpdateOfExisting();

	}


	public boolean createDailyTime(){
		return false;
	}


	private boolean checkDateFormat(String target){
		DateTimeFormatter date = null;
		try{
			date = DateTimeFormat.forPattern(target);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
