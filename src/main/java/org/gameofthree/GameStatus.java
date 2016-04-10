package org.gameofthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStatus {
	
	Map<String, Integer> currentStatus = new HashMap<>(); 
	List<String> logs = new ArrayList<>();
	String result = new String("none");
	
	public Map<String, Integer> getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Map<String, Integer> currentStatus) {
		this.currentStatus = currentStatus;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
