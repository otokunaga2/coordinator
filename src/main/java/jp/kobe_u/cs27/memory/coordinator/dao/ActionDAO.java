package jp.kobe_u.cs27.memory.coordinator.dao;

import org.joda.time.DateTime;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ActionDAO {
	private DBUtil db = DBUtil.getInstance();
	private static final String actionCollectionName = "care_action";
	private static final String ACTIONID = "unique_id";
	private DateTime dateTime;

	public ActionDAO(){
		dateTime = new DateTime();
	}

	public String updateAction(){
		DBCollection collection = db.getCollection(actionCollectionName);
		return "";
	}
	public DBObject findAction(long actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		DBObject actionObject = collection.findOne(query);
		return actionObject;
	}

	public DBObject deleteAction(String actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		return null;
	}

	public Object createAction(String description, String url){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject document = new BasicDBObject();
		document.append("description", description);
		document.append("url", url);
		document.append("lastInvocatin", dateTime.now().toString("yyyy/MM/dd HH:mm:ss"));
		Object obj = db.add(actionCollectionName, document);
		return obj;
	}




}
