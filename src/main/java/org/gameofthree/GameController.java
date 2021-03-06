package org.gameofthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/gameofthree")
public class GameController {
	
	Map<String, String> players = new HashMap<>();
	Map<String, Float> currentStatus = new HashMap<>(); 
	List<String> logs = new ArrayList<>();
	String resultMessage = new String("none");
	GameStatus gStatus = new GameStatus();
	int randNumber;
	float result = 0;
	
	@RequestMapping(value="/checkstatus", method = RequestMethod.GET)
	public Integer checkStatus(){
		return players.size();
	}
	
	@RequestMapping(value="/join/{name}", method = RequestMethod.GET)
	public Player join(@PathVariable("name") String name){
		if(players.size() == 0){
			players.put("p1", name);
			Player p1 = new Player();
			p1.setId("p1");
			p1.setName(name);
			
			return p1;
		}
		else if(players.size() == 1){
			players.put("p2", name);
			Player p2 = new Player();
			p2.setId("p2");
			p2.setName(name);
			return p2;
		}
		else{
			return new Player();
		}
	}
	
	@RequestMapping(value="/play/{playerid}/{value}", method = RequestMethod.GET)
	public void play(@PathVariable("playerid") String playerid, @PathVariable("value") Integer value ){
		
		if(currentStatus.size() == 0){
			Random r = new Random();
			int Low = 100;
			int High = 300;
			randNumber = r.nextInt(High-Low) + Low;
			logs.add("Starting number : "+randNumber);
		
			logs.add(players.get(playerid)+"["+value+"] : "+(randNumber+value)+"/3 = "+(randNumber+value)/3);
			
			result = (randNumber+value)/3;
			
			if( result == 1){
				resultMessage = "Game over "+players.get(playerid)+" won !!";
			}
			else {
				currentStatus.put(playerid, result);
			}
		}
		else if(currentStatus.size() == 1){
			if(currentStatus.get(playerid) == null){
				for(Map.Entry<String, Float> entry : currentStatus.entrySet()){
					result = entry.getValue();
				}
				 
				logs.add(players.get(playerid)+"["+value+"] : "+(result+value)+"/3 = "+(result+value)/3);
				
				result = (result+value) / 3;
				
				if(result == 1){
					resultMessage = "Game over "+players.get(playerid)+" won !!";
				}
				else{
					currentStatus.clear();
					
					currentStatus.put(playerid, result);
				}
			}
		}
	}
	
	@RequestMapping(value="/getstatus", method = RequestMethod.GET)
	public GameStatus getGameStatus(){
		
		
		gStatus.setCurrentStatus(currentStatus);
		gStatus.setResult(resultMessage);
		gStatus.setLogs(logs);
		
		return gStatus;
	}
	
	@RequestMapping(value="/reset", method = RequestMethod.GET)
	public void reset(){
		currentStatus.clear(); 
		gStatus.setCurrentStatus(new HashMap<>());
		logs.clear();
		gStatus.setLogs(new ArrayList<>());
		resultMessage = "none";
		gStatus.setResult(resultMessage);
		randNumber =0;
	}
}
