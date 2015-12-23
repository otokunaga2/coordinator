package jp.kobe_u.cs27.memory.coordinator.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

import jp.kobe_u.cs27.memory.coordinator.model.CareECA;

public class CareECADAO {
	private static final String collection = "care_eca";
	private DBUtil db = DBUtil.getInstance();
	DBCollection dbCollection;
	public CareECADAO(){
		dbCollection = db.getCollection(collection);	
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
	public boolean update(String _id, CareECA ecaObj){
		BasicDBObject query = new BasicDBObject();
		query.append("eca_id", _id);
		BasicDBObject insertObj = new BasicDBObject();
//		private String property;
//		private String value;
//		private DateTime timeCondition;
//		private JsonObject timeContext;
//		private JsonObject actionCondition;
//		private String currentCareId;/*care id*/
		insertObj.append("property", ecaObj.getProperty());
		insertObj.append("value", ecaObj.getValue());
		insertObj.append("timeCondition",ecaObj.getTimeCondition().toString());
		WriteResult result = dbCollection.update(query, insertObj,true/*もしデータがなければ作成する*/,true/**/);
		return result.isUpdateOfExisting();
	}
}
