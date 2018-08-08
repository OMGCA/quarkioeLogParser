package logItem;

public class Alarm {
	int count = 0;
	String creationTime = "yyyy-mm-ddThh:mm:ss.sss";
	String time = "yyyy-mm-ddThh:mm:ss.sss";
	int alarmID = 0;
	String alarmSeverity = "UNKNOWN";
	String deviceName = "0";
	String status = "UNKNOWN";
	String alarmDescription = "UNKNOWN";
	String alarmType = "UNKNOWN";
	
	public Alarm() {
		this.count = count;
		this.creationTime = creationTime;
		this.time = time;
		this.alarmID = alarmID;
		this.alarmSeverity = alarmSeverity;
		this.deviceName = deviceName;
		this.status = status;
		this.alarmDescription = alarmDescription;
		this.alarmType = alarmType;
	}
	
	public void printAlarm() {
		System.out.println(" ");
		System.out.println("Alarm Description: " + this.alarmDescription);
		System.out.println("Alarm type: " + this.alarmType);
		System.out.println("Alarm ID: "+ this.alarmID);
		System.out.println("From device "+this.deviceName);
		System.out.println("Severity: "+this.alarmSeverity);
		System.out.println("Register time: "+this.creationTime);
		System.out.println("Current status: " + this.status);
		System.out.println("Occurance: "+this.count);
		System.out.print("-----------------------------------------");
	}
	
	
	public void setAlarmCount(int newCount) {
		this.count = newCount;
	}
	
	public int getAlarmCount() {
		return this.count;
	}
	
	/*Time follows the format of yyyy-mm-ddThh:mm:ss.sss*/
	public void setCreationTime(String newCTime) {
		this.creationTime = newCTime;
	}
	
	public String getCreationTime() {
		return this.creationTime;
	}
	
	/*Time follows the format of yyyy-mm-ddThh:mm:ss.sss*/
	public void setTime(String newTime) {
		this.time = newTime;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setAlarmID(int newAlarmID) {
		this.alarmID = newAlarmID;
	}
	
	public int getAlarmID() {
		return this.alarmID;
	}
	
	/*Available severity includes WARNING, MINOR, MAJOR and CRITICAL*/
	public void setSeverity(String newSeverity) {
		this.alarmSeverity = newSeverity;
	}
	
	public String getSeverity() {
		return this.alarmSeverity;
	}
	
	public void setDeviceID(String newDevID) {
		this.deviceName = newDevID;
	}
	
	public String getDeviceID() {
		return this.deviceName;
	}
	
	/*Available stats include ACTIVE, ACKNOWLEGDED and CLEARED*/
	public void setAlarmStatus(String newStat) {
		this.status = newStat;
	}
	
	public String getAlarmStatus() {
		return this.status;
	}
	
	public void setDescription(String newDescribe) {
		this.alarmDescription = newDescribe;
	}
	
	public String getDescription() {
		return this.alarmDescription;
	}
	
	public void setAlarmType(String newType) {
		this.alarmType = newType;
	}
	
	public String getAlarmType() {
		return this.alarmType;
	}

}
