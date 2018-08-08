package logParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import logItem.Alarm;
import logItem.LogEvent;
import logItem.Measurement;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JSONParser {
	
	public static void main(String args[]) {
		alarmOutput("alarm_test.json");
		measurementOutput("measurement_values.json");
		eventLogOutput("eventTest.json");
	}
	
	public static void alarmOutput(String jsonFile) {
		try {
			JsonParser 	parser 	= new JsonParser();
			JsonObject 	json	= parser.parse(new FileReader(jsonFile)).getAsJsonObject();
			
			JsonArray 	alarms 	= json.get("alarms").getAsJsonArray();
			Alarm 		singleAlarm = new Alarm();
			JsonObject 	singleItem;
			
			System.out.println("Total number of alarms: " + alarms.size());
			Time cacheTime = new Time();
			for (int i = 0; i < alarms.size(); i++) {
				singleItem = alarms.get(i).getAsJsonObject();
				String timeInString = singleItem.get("creationTime").getAsString();
				cacheTime.setTime(timeInString);
				singleAlarm.setAlarmCount(singleItem.get("count").getAsInt());
				singleAlarm.setCreationTime(cacheTime.outputFullTime());
				singleAlarm.setAlarmID(singleItem.get("id").getAsInt());
				singleAlarm.setSeverity(singleItem.get("severity").getAsString());
				singleAlarm.setDeviceID(((JsonObject) singleItem.get("source")).get("name").getAsString());
				singleAlarm.setAlarmStatus(singleItem.get("status").getAsString());
				singleAlarm.setDescription(singleItem.get("text").getAsString());
				singleAlarm.setAlarmType(singleItem.get("type").getAsString());
				singleAlarm.printAlarm();
				alarmLogger(singleAlarm,"alarmLog.csv");
			} 
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Alarm log not found");
		}
	}
	
	public static void measurementOutput(String jsonFile) {
		try {
			JsonParser 	parser 		= new JsonParser();
			JsonObject 	valueList 	= (JsonObject)parser.parse(new FileReader(jsonFile));
			JsonArray 	recordValue = valueList.get("measurements").getAsJsonArray();
			String[] 	dataTag 	= getDataTagInfo("dataTag.csv");
			
			System.out.println(" ");
			System.out.print("\tTime\t");
			
			int dataTagQuantity = 0;
			while(dataTag[dataTagQuantity] != null) {
				System.out.print("\t" + dataTag[dataTagQuantity] + "\t");
				dataTagQuantity++;
			}
			
			Measurement dataRow = new Measurement(dataTagQuantity);
			
			System.out.println(" ");
			
			for ( int i = 0; i < recordValue.size(); i++ ) {
				String 	timeEntity 	= recordValue.get(i).getAsJsonObject().get("time").getAsString();
				Time 	tagTime 	= new Time();
				tagTime.setTime(timeEntity);
				
				String 	currentTag 	= recordValue.get(i).getAsJsonObject().get("type").getAsString();
				String 	tagIndex 	= String.valueOf(tag2IndexFromCSV(currentTag));
				JsonObject cacheElement = recordValue.get(i).getAsJsonObject().get(currentTag).getAsJsonObject();
				
				double 	dataPoint 	= cacheElement.get(tagIndex).getAsJsonObject().get("value").getAsDouble();

				dataRow.setTimeStamp(tagTime.outputFullTime());
				dataRow.setEntityValue(tag2IndexFromCSV(currentTag), dataPoint);
				
				if(i != 0 && (i+1) % dataTagQuantity == 0) {
					dataRow.printDataRow();
					exportMeasurementData(dataRow,"exportedData.csv");
				}
			}

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Measurement log not found");
		}
	}
	
	public static void eventLogOutput(String jsonFile) {
		try {
			JsonParser 	parser 		= new JsonParser();
			JsonArray 	eventArray 	= parser.parse(new FileReader(jsonFile)).getAsJsonObject().get("events").getAsJsonArray();
			LogEvent 	logEvent 	= new LogEvent();
			JsonObject 	cacheObject;
			
			System.out.println("Total number of events: " + eventArray.size());

			for (int i = 0; i < eventArray.size(); i++) {
				cacheObject = eventArray.get(i).getAsJsonObject();
				String timeInString = cacheObject.get("creationTime").getAsString();
				
				logEvent.setTime(timeInString);
				logEvent.setDescription(cacheObject.get("text").getAsString());
				logEvent.setEventType(cacheObject.get("type").getAsString());
				logEvent.setEventID(cacheObject.get("id").getAsString());
				
				logEvent.printEventDetails();
			} 
			
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Event log not found");
		}	
	}
	
	public static String[] getDataTagInfo(String dataTagFile) {
		String configFile = dataTagFile;
		String csvSplitby = ",";
		String line = "";
		
		int i = 0;
		int maxEntities = 100;
		
		String[] dataTags = new String[maxEntities];
		
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new FileReader(configFile));
			while((line = br.readLine()) != null){ 
				String[] cacheStringArray = line.split(csvSplitby);
				dataTags[i] = cacheStringArray[0];
				i++;
			}

		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if ( br != null ){
				try{
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		
		return dataTags;
	}
	
	public static int tag2IndexFromCSV(String dataTag) {
		int i = 0;
		
		String[] dataTags = getDataTagInfo("dataTag.csv");
		
		while(dataTag.equals(dataTags[i]) == false) {
			i++;
		}
		
		return i;
	}
	
	public static void logFileCreation(String fileName) {
		File f = new File(fileName);
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exportMeasurementData(Measurement m, String fileName) {
		logFileCreation(fileName);
		
		FileWriter writer;
		try {
			writer = new FileWriter(fileName, true);
			writer.append("\r\n");
			writer.append(m.getTimeStamp());
			writer.append(',');
			
			for ( int i = 0; i < m.getEntityCount(); i++) {
				writer.append(String.valueOf(m.getEntityValue(i)));
				writer.append(',');
			}
			
			writer.flush();
			writer.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void alarmLogger(Alarm a, String logFile) {
		logFileCreation(logFile);
		FileWriter writer;
		try {
			writer = new FileWriter(logFile, true);
			writer.append("\r\n");
			
			writer.append(String.valueOf(a.getAlarmID()));
			writer.append(',');
			
			writer.append(a.getAlarmType());
			writer.append(',');
			
			writer.append(a.getCreationTime());
			writer.append(',');
			
			writer.append(a.getAlarmStatus());
			writer.append(',');
			
			writer.append(a.getSeverity());
			writer.append(',');
			
			writer.append(a.getDeviceID());
			writer.append(',');
			
			writer.append(a.getDescription());
			writer.append(',');
			
			writer.append(String.valueOf(a.getAlarmCount()));
			writer.append(',');
			
			writer.flush();
			writer.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
