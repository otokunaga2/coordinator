package jp.kobe_u.cs27.memory.coordinator.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;

import jp.kobe_u.cs27.memory.coordinator.dao.DBUtil;

public class DBUtilTest {
	private DBUtil db;
	@Before
	public void setUp() throws Exception {
		db = DBUtil.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		db.drop();/*事後処理*/
	}

	@Test
	public void testGetInstance() {
		
		assertNotNull(db);
	}

	@Test
	public void testDrop() {
		
	}

	@Test
	public void testDropString() {
		
	}

	@Test
	public void testRemove() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testGetCollectionNames() {
		
	}

	@Test
	public void testDump() {
		
	}

	@Test
	public void testAdd() {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.append("testKey", "testVal");
		db.add("test", dbObject);
		
	}

	@Test
	public void testFindStringBasicDBObject() {
		
	}

	@Test
	public void testFindStringObjectId() {
		fail("Not yet implemented");
	}

}
