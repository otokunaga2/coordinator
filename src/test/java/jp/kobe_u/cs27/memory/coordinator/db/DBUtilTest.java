package jp.kobe_u.cs27.memory.coordinator.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

import jp.kobe_u.cs27.memory.coordinator.dao.DBUtil;

public class DBUtilTest {
	private static DBUtil db;
	private static String collectionName = "test";
	@Before
	public void setUp() throws Exception {
		db = DBUtil.getInstance();
	}

	@SuppressWarnings("static-access")
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetInstance() {
		
		//assertNotNull(db);
	}

	@Test
	public void testDrop() {
		
	}

	@Test
	public void testDropString() {
		
	}

	@Test
	public void testRemove() {
		BasicDBObject removeTarget = new BasicDBObject();
		removeTarget.append("remove", "test");
		Object response = db.add(collectionName,removeTarget);
		assertNotNull(response);
		
		boolean actual = db.remove(collectionName, removeTarget);
		boolean expected = false;
		assertEquals(expected, actual);
		
	}

	@SuppressWarnings("static-access")
	@Test
	public void testUpdate() {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.append("testKey", "testVal");
		Object res = db.add("test", dbObject);
		assertNotNull(res);
	}

	@Test
	public void testGetCollectionNames() {
		
	}

	@Test
	public void testDump() {
		
	}

	@SuppressWarnings("static-access")
	@Test
	public void testAdd() {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.append("testKey", "testVal");
		Object res = db.add("test", dbObject);
		assertNotNull(res);
	}
	
	@SuppressWarnings("static-access")
	@Test 
	public void testFind(){
		BasicDBObject testQuery = new BasicDBObject();
		testQuery.append("unique_id", new BasicDBObject("$gt",0));/**/
		DBCursor cursor = db.find("test",testQuery);
		boolean expected = true;
		long resultNum = cursor.count();
		boolean actual = isGreaterThanOne(resultNum);
		assertEquals(expected, actual);
//		assertNot(expected, actual);
	}
	private boolean isGreaterThanOne(long num){
		if(num>1){
			return true;
		}else{
			return false;
		}
	}
	
	@Test
	public void findTest(){
		BasicDBObject query = new BasicDBObject();
		query.append("unique_id", "32");
		DBCursor cursor = db.find("test", query);
		
		
	}
	@Test
	public void testReplace(){
		BasicDBObject query = new BasicDBObject();
		query.append("unique_id", 68);
		BasicDBObject insertedObj = new BasicDBObject();
		insertedObj.append("replaced_test", "ok");
		Object returnObj = db.update(collectionName, query, insertedObj);
		assertNotNull(returnObj);
	}
	@Ignore
	@Test
	public void testFindStringBasicDBObject() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testFindStringObjectId() {
		fail("Not yet implemented");
	}

}
