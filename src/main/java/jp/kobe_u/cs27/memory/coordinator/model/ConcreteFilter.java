package jp.kobe_u.cs27.memory.coordinator.model;

/**
 * @author otokunaga
 *
 */
public class ConcreteFilter implements TimeFilter {

	/**
	 * 次の処理を待つためのメソッド（wait中にイベントを食った時の挙動をどうするか
	 */
	@Override
	public void waitForNextProcessing(long sleep) {
		// TODO Auto-generated method stub
		try {
			System.out.println("start");
			Thread.sleep(sleep);
			System.out.println("end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
