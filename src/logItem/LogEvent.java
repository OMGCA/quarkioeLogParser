package logItem;

import logParser.Time;

public class LogEvent {
	
	Time creationTime;
	String eventDescription;
	String eventType;
	String eventID;
	
	public LogEvent() {
		this.creationTime = new Time();
		this.eventDescription = " ";
		this.eventType = " ";
		this.eventID = " ";
	}
	
	public void setTime(String newTime) {
		this.creationTime.setTime(newTime);
	}
	
	public Time getTime() {
		return this.creationTime;
	}
	
	public void setDescription(String newDescription) {
		this.eventDescription = newDescription;
	}
	
	public String getDescription() {
		return this.eventDescription;
	}
	
	public void setEventType(String newType) {
		this.eventType = newType;
	}
	
	public String getEventType() {
		return this.eventType;
	}
	
	public void setEventID(String newID) {
		this.eventID = newID;
	}
	
	public String getEventID() {
		return this.eventID;
	}
	
	public void printEventDetails() {
		System.out.println("Event ID: " + this.eventID);
		System.out.println("Event Description: " + this.eventDescription);
		System.out.println("Event type: " + this.eventType);
		System.out.println("Register time: "+ this.creationTime.outputFullTime());
		System.out.print("-----------------------------------------");
		System.out.println(" ");
	}
	
}
