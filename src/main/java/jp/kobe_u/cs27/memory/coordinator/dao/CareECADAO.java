package jp.kobe_u.cs27.memory.coordinator.dao;

import java.text.ParseException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

import jp.kobe_u.cs27.memory.coordinator.eca.CareECA;

public class CareECADAO {
	private static final String PROPERTY = "property";
	private static final String VALUE = "value";
	private static final String TIMECONDITION = "timeCondition";
	private static final String TIMECONTEXT = "timeContext";
	private static final String LASTINVOCATIOn = "lastInvocation";
	private static final String UNIQUE_ID = "uniquie_id";
	private final String collectionName = "care_eca";
	private DBUtil db = DBUtil.getInstance();
	DBCollection dbCollection;
	@SuppressWarnings("static-access")
	public CareECADAO(){	
	}
	
	/**
	 * db.collection.update(
   		<query>,
   		<update>,
   		{ upsert: <boolean>, multi: <boolean> }
		)
	 * @return
	 * 
	 */
	@SuppressWarnings("static-access")
	public String createECA(CareECA ecaObj){
		BasicDBObject insertObj = new BasicDBObject();
		insertObj.append(PROPERTY, ecaObj.getProperty());
		insertObj.append(VALUE, ecaObj.getValue());
		insertObj.append(TIMECONDITION, ecaObj.getTimeCondition().toString());
		Object obj =db.add(this.collectionName, insertObj);
		return obj.toString();
	}
	
	public boolean removeECA(String _id){
		
		
		return true;
	}
	
	/**
	 * idをもとに対象のobjectidを検索する関数
	 * @param _id object_id
	 * @return
	 */
	public String findECA(String _id){
		ObjectId objectId = new ObjectId(_id);
		Cursor cursor = db.find(collectionName, objectId);
		
		BasicDBObject resultSet = new BasicDBObject();
		String resultId = null;
		while(cursor.hasNext()){
			resultId = cursor.toString();
			System.out.println(resultId);
			cursor.next();
		}
		return resultId;
	}
	
	
	private BasicDBObject generateBasicDBObjectFromECA(String _id,CareECA eca){
		BasicDBObject gen = new BasicDBObject();
		gen.append(PROPERTY,eca.getProperty() );
		gen.append(VALUE,eca.getValue());
		gen.append(TIMECONDITION, eca.getTimeCondition());
		gen.append(TIMECONTEXT, eca.getTimeContext());
		
		return gen;
	}
	public boolean updateECA(String _id, CareECA updateECA){
		BasicDBObject query = new BasicDBObject();
		query.append("_id", new ObjectId(_id));
		return db.replace(this.collectionName,query,generateBasicDBObjectFromECA(_id, updateECA));
	}
	
}
