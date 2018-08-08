package logItem;

public class Measurement {
	String timeStamp = "[placeholder]";
	int valueCount = 1;
	double[] valueSet = new double[valueCount];
	
	public Measurement(int maxSlot) {
		this.timeStamp = timeStamp;
		this.valueCount = maxSlot;
		this.valueSet = new double[maxSlot];
		for ( int i = 0; i < maxSlot; i++ ) {
			this.valueSet[i] = 0;
		}
	}
	
	public void setTimeStamp(String newTimeStamp) {
		this.timeStamp = newTimeStamp;
	}
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public void setEntityCount(int newCount) {
		this.valueCount = newCount;
	}
	
	public int getEntityCount() {
		return this.valueCount;
	}
	
	public void setEntityValue(int slot,double value) {
		this.valueSet[slot] = value;
	}
	
	public double getEntityValue(int slot) {
		return this.valueSet[slot];
	}
	
	public void printDataRow() {
		System.out.print(this.timeStamp+"\t\t");
		for (int i = 0; i < valueCount; i++) {
			System.out.print(this.valueSet[i]+"\t\t");
			
		}
		System.out.println(" ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}
