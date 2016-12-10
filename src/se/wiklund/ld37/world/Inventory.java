package se.wiklund.ld37.world;

public class Inventory {
	
	private int foodCount, logsCount;
	
	public void addFood(int count) {
		foodCount += count;
	}
	
	public void addLogs(int count) {
		logsCount += count;
	}
	
	public void removeFood(int count) {
		foodCount -= count;
	}
	
	public void removeLogs(int count) {
		logsCount -= count;
	}
	
	public int getFoodCount() {
		return foodCount;
	}
	
	public int getLogsCount() {
		return logsCount;
	}
}
