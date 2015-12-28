package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import java.sql.Time;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.mongodb.util.TimeConstants;

import jp.kobe_u.cs27.memory.coordinator.eca.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeContext;

public class CareECADAOTest {

	private CareECADAO ecaDAO;
	private CareECA eca;
	private DateTime dt;
	private CareECA sampleECA;
	private static Gson gson = new Gson();
	@Before
	public void setUp() throws Exception {
		ecaDAO = new CareECADAO();
		eca = new CareECA();
		sampleECA = new CareECA();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCareECADAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateECA() {
		
		eca.setProperty("user.tokunaga");
		eca.setValue("genkan");
		BasicDBObject testTimeConditon = new BasicDBObject();
		testTimeConditon = (BasicDBObject)JSON.parse("{'from':'11:00','to':'12:00'}");
		TimeContext tContext = new TimeContext();
		String jsonTiemContext = gson.toJson(tContext);
		eca.setTimeCondition(jsonTiemContext);
	
		BasicDBObject testContext =new BasicDBObject();
		testContext = (BasicDBObject)JSON.parse("{'after':30, 'unit': 'minites'}");
		
		
		eca.setTimeContext(jsonTiemContext);
		Object obj = ecaDAO.createECA(eca);
		System.out.println(obj);
		assertNotNull(obj);
	}

	@Test
	public void testRemoveECA() {
		fail("Not yet implemented");
	}
	@Test
	public void testFindECA() {
//		prepareSampleData();
		String id = ecaDAO.findECA("567ca8c315c657313e9056c4");
		assertNotNull(id);
	}

	@Test
	public void testUpdateECA() {
		CareECA afterECA = new CareECA(null/**/, null/**/, null/**/, null/**/);
		
//		ecaDAO.updateECA(_id, updateECA);
		//ecaDAO.updateECA(_id, updateECA)
	}

}
