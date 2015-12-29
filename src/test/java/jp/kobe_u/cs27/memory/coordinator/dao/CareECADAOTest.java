package jp.kobe_u.cs27.memory.coordinator.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import jp.kobe_u.cs27.memory.coordinator.model.CareECA;
import jp.kobe_u.cs27.memory.coordinator.model.TimeCondition;

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
	public void testCreateECA() {
		eca.setProperty("user.tokunaga");
		eca.setValue("genkan");
		BasicDBObject testTimeConditon = new BasicDBObject();
		testTimeConditon = (BasicDBObject)JSON.parse("{'from':'11:00','to':'12:00'}");
		TimeCondition tContext = new TimeCondition();

		String jsonTiemContext = "{'from':'11:00','to':'12:00'}";
		eca.setTimeCondition(jsonTiemContext);

		BasicDBObject testContext =new BasicDBObject();
		testContext = (BasicDBObject)JSON.parse("{'after':30, 'unit': 'minites'}");

		String timeCtx = "{'after':30, 'unit': 'minites'}";
		eca.setTimeContext(timeCtx);
		Object obj = ecaDAO.createECA(eca);
		assertNotNull(obj);
	}

	@Test
	public void testRemoveECA() {


	}

	@Before
	public void prepareTestFindECAUsingEvent(){



	}


	@Test
	public void testFindECAUsingEvent(){
		CareECA target = new CareECA();
		target.setValue("genkan");
		target.setProperty("user.tokunaga");
		List<CareECA> result = ecaDAO.findECAUsingEvent(target);
		for(CareECA temp: result){
			System.out.println(temp);
		}
		assertNotNull(result);
	}
	@Test
	public void testFindECA() {
//		prepareSampleData();
		//String id = ecaDAO.findECA("567ca8c315c657313e9056c4");
		//assertNotNull(id);
	}
	@Test
	public void testUpdateECA() {
		CareECA afterECA = new CareECA(null/**/, null/**/, null/**/, null/**/);

//		ecaDAO.updateECA(_id, updateECA);
		//ecaDAO.updateECA(_id, updateECA)
	}

}
