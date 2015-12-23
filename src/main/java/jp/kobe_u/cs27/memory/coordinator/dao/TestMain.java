package jp.kobe_u.cs27.memory.coordinator.dao;

import jp.kobe_u.cs27.memory.coordinator.model.CareECA;

public class TestMain {
	
	
	private static TimeMongoDao timdao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		timdao = new TimeMongoDao();
		timdao.createSingleEventTime("2015-12-22 23:14:22");
		
		boolean result = timdao.checkDateFormatWrapper("2015-12|||||22 23:14:22222");
		System.out.println(result);
		CareECADAO ecaDao = new CareECADAO();
		CareECA ecaObj = new CareECA();
		ecaDao.update("1",ecaObj);
	}

}
