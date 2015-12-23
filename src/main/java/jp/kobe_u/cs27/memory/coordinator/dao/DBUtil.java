package jp.kobe_u.cs27.memory.coordinator.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DBUtil {
	private static Mongo m;
	private static DB db;
	private static String ip = "localhost";
	//private static String ip = "localhost";

	private static final String dbName = "coordinatordb";

	
	// for singleton
    private static DBUtil singleton = new DBUtil();

    public static DBUtil getInstance() {
        return singleton;
    }

    // invisible constructor
	private DBUtil() {
		try {
			m = new MongoClient(ip,27017 );
			db = m.getDB(dbName);
			System.out.println("[DBUtil] connect mongoDB. mongoDB Server IP : " + ip + " DB Name : "+ dbName);
		} catch (UnknownHostException | MongoException e) {
			System.out.println("[DBUtil] ERROR in constructor");
			// 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	
	public DBCollection getCollection(String collectionName){
		return db.getCollection(collectionName);
	}
	
	/***
	 * 全コレクションを削除する
	 */
	public static void drop() {
		for (String collectionName : db.getCollectionNames()) {
			drop(collectionName);
		}
	}

	/***
	 * 指定コレクションを削除する
	 * @param collectionName
	 */
	public static void drop(String collectionName) {
		DBCollection coll = db.getCollection(collectionName);
		coll.drop();
	}

	/***
	 * コレクション内の指定データを削除する
	 * @param collectionName
	 * @param dbobject
	 */
	public static boolean remove(String collectionName,BasicDBObject dbobject) {
		DBCollection coll = db.getCollection(collectionName);
		coll.remove(dbobject);
		return true;
	}

	/***
	 * コレクション内の指定データを更新する
	 * @param collectionName
	 * @param dbobject
	 */
	public static Object update(String collectionName,BasicDBObject dbobjectBf,BasicDBObject dbobjectAf) {
		DBCollection coll = db.getCollection(collectionName);
		coll.update(dbobjectBf, dbobjectAf);
		return dbobjectAf;
	}

	public static List<String> getCollectionNames() {
		List<String> list = new ArrayList<String>();
		for (String collectionName : db.getCollectionNames()) {
			list.add(collectionName);
		}
		return list;
	}

	/**
	 * 指定コレクション内の全データを返す
	 * @param collectionName
	 * @return
	 */
	public static List<DBObject> dump(String collectionName) {
		List<DBObject> list = new ArrayList<DBObject>();

		DBCollection coll = db.getCollection(collectionName);
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			list.add(obj);
		}
		return list;
	}

	/**
	 * DBへの追加
	 * @param 追加先のコレクション名
	 * @param 追加内容のキー
	 */
	public static Object add(String collectionName, BasicDBObject dbobject) {
		DBCollection coll = db.getCollection(collectionName);
		coll.insert(dbobject);
		return dbobject.get("_id");
	}


	/**
	 * DB内へのfind()のラッパーメソッド
	 *
	 * @param collectionName
	 * @param query
	 * @return
	 */
	public static DBCursor find(String collectionName, BasicDBObject query) {
		DBCollection coll = db.getCollection(collectionName);
		return coll.find(query);
	}
	
	/**
	 * DBを更新する際に、
	 * @param collectionName
	 * @param query
	 * @return
	 */
	public BasicDBObject incrementNumberId(String collection){
		DBCollection col = this.getCollection("test");
		BasicDBObject incremenStatement = new BasicDBObject("number",col.count()+1);
		return incremenStatement;
		
	}
	
	/**
	 * DB内へのfind()のラッパーメソッド
	 * _id から一つだけ取り出す
	 *
	 * @param collectionName
	 * @param objectId
	 * @return
	 */
	public static DBCursor find(String collectionName, ObjectId objectId) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", objectId);
		return find(collectionName, query);
	}

}