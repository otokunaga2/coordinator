package jp.kobe_u.cs27.memory.coordinator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import jp.kobe_u.cs27.memory.coordinator.model.CareECA;

public class CareECADAO {
	private static final String PROPERTY = "property";
	private static final String VALUE = "value";
	private static final String TIMECONDITION = "timeCondition";
	private static final String TIMECONTEXT = "timeContext";
	private static final String LASTINVOCATION = "lastInvocation";
	private static final String UNIQUE_ID = "uniquie_id";
	private static final String ACTION_ID = "actionid";
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
		insertObj.append(TIMECONDITION, ecaObj.getTimeCondition());
		insertObj.append(TIMECONTEXT, ecaObj.getTimeContext());
		insertObj.append(ACTION_ID, ecaObj.getActionId());
		Object obj =db.add(this.collectionName, insertObj);
		return obj.toString();
	}

	public boolean removeECA(String _id){



//		db.find(this.collectionName, );

		return true;
	}

	public boolean deleteECA(){

		return true;
	}
	/**
	 * トリガーイベントを利用して、データを検索
	 * @param t_event
	 * @return
	 */
	public List findECAUsingEvent(CareECA eca){
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.append(PROPERTY,eca.getProperty());
		queryObj.append(VALUE, eca.getValue());
		queryObj.append(TIMECONDITION, new BasicDBObject("$ne","{}"));/*データの比較emptyではないものを選択*/
		queryObj.append(TIMECONTEXT, new BasicDBObject("$ne","{}"));
		DBCursor cursor = db.find(collectionName, queryObj);

		List careECAList = new ArrayList<CareECA>();
		CareECA tempECAPojo = null;
		while(cursor.hasNext()){
			try{
				BasicDBObject obj = (BasicDBObject) cursor.next();
				tempECAPojo = new CareECA();
				String prop = obj.getString(PROPERTY);
				tempECAPojo.setProperty(prop);
				String val = obj.getString(VALUE);
				String timeCondition = obj.getString(TIMECONDITION);
				String timeContext = obj.getString(TIMECONTEXT);
				String actionId = obj.getString(ACTION_ID);
				tempECAPojo.setTimeCondition(timeCondition);
				tempECAPojo.setTimeContext(timeContext);
				careECAList.add(tempECAPojo);
			}catch(NoSuchElementException e){
				e.printStackTrace();
			}finally{
				try{
					cursor.next();
				}catch(NoSuchElementException ne){

				}finally{

				}

			}
		}
		cursor.close();

		return careECAList;
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
