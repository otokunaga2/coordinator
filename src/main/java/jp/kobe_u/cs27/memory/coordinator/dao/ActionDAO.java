package jp.kobe_u.cs27.memory.coordinator.dao;

import org.joda.time.DateTime;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ActionDAO {
	private DBUtil db = DBUtil.getInstance();
	private static final String actionCollectionName = "care_action";
	private static final String ACTIONID = "actionid";
	public String updateAction(){
		DBCollection collection = db.getCollection(actionCollectionName);
		return "";
	}
	public DBObject findAction(String actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		DBObject actionObject = collection.findOne(actionCollectionName,query);
		return actionObject;
	}

	public DBObject deleteAction(String actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		return null;
	}

	public Object createAction(String description, String url, DateTime dateTime){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject document = new BasicDBObject();
		document.append("description", description);
		document.append("url", url);
		Object obj = db.add(actionCollectionName, document);
		return obj;
	}




}
