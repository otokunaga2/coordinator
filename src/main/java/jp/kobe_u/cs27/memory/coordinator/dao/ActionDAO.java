package jp.kobe_u.cs27.memory.coordinator.dao;

import java.util.NoSuchElementException;

import org.joda.time.DateTime;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import jp.kobe_u.cs27.memory.coordinator.config.ApplicationEnv;
import jp.kobe_u.cs27.memory.coordinator.model.Action;

public class ActionDAO {
	private DBUtil db = DBUtil.getInstance();
	private static final String actionCollectionName = "care_action";
	private static final String ACTIONID = "unique_id";
	private static final String DESCRIPTION = "description";
	private static final String URL = "url";
	private static final String LASTINVOCATION = "lastInvocation";


	private DateTime dateTime;

	public ActionDAO(){
		dateTime = new DateTime();
	}

	public boolean updateAction(long actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		DBObject actionDBObject = collection.findOne(query);
		actionDBObject.removeField(LASTINVOCATION);
		actionDBObject.put(LASTINVOCATION, dateTime.now().toString(ApplicationEnv.dateTimePattern));
		WriteResult result = collection.update(query, actionDBObject);
		return result.isUpdateOfExisting();
	}
	public Action findAction(long actionId){
		DBCollection collection = db.getCollection(actionCollectionName);
		BasicDBObject query = new BasicDBObject();
		query.append(ACTIONID, actionId);
		DBObject actionDBObject = collection.findOne(query);
		Action action = new Action();
		try{
			action.setActionid(Long.parseLong(actionDBObject.get(ACTIONID).toString()));
			action.setDescription(actionDBObject.get(DESCRIPTION).toString());
			action.setUrl(actionDBObject.get(URL).toString());
		}catch(NoSuchElementException e){
			e.printStackTrace();
		}catch(NullPointerException e){
			return null;/*データを発見できないとき*/
		}
		return action;
	}

	public boolean deleteAction(long actionId){
		BasicDBObject query = new BasicDBObject();
		DBCollection collection = db.getCollection(actionCollectionName);
		query.append(ACTIONID, actionId);
		WriteResult result = collection.remove(query);
		return result.isUpdateOfExisting();/*データが*/
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
