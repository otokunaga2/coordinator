package jp.kobe_u.cs27.memory.coordinator.time;

import org.joda.time.DateTime;

public class TimeController {

	private static DateTime time = new DateTime();
	
	/**
	 * コンテキストの情報から、適切な時間かどうかを判定するメソッド
	 * @return
	 */
	public static boolean judgeTimeWithContextInfo(DateTime current,DateTime compared){
		return true;
	}
	
	/**
	 * 過去のログ情報から、直近で送っていないかどうかを判定するロジック
	 * @return
	 */
	public static boolean judgeLogTime(DateTime past){
		
		return false;
	}
	public static DateTime getCurrentTime(){
		return time.now();
	}
	
}
