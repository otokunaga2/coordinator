package jp.kobe_u.cs27.memory.coordinator.action;

import java.util.HashMap;

import org.joda.time.DateTime;

public class ActionLogListener {


	/**
	 * hashMapのデータをmongoに書き込むメソッド
	 * @param map
	 */
	public void notify(HashMap<String,DateTime> map){
		for(String key: map.keySet()){
			System.out.println(map.get(key));
		}
	}
}
