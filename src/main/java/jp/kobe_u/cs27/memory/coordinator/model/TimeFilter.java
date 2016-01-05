package jp.kobe_u.cs27.memory.coordinator.model;

public interface TimeFilter {
	public void waitForNextProcessing(long sleep);
	
}
