package logParser;

import javafx.scene.chart.PieChart.Data;

public class Time {
	int year = 0;
	int month = 0;
	int day = 0;
	int hour = 0;
	int minute = 0;
	int second = 0;
	
	public Time() {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	/*Format: yyyy-mm-ddThh:mm:ss*/
	public void setTime(String newTime) {
		char[] timeChar = newTime.toCharArray();
		char[] year = new char[4];
		char[] month = new char[2];
		char[] day = new char[2];
		
		char[] hour = new char[2];
		char[] minute = new char[2];
		char[] second = new char[2];
		
		for ( int i = 0; i < 4; i++) {
			year[i] = timeChar[i];
		}
		
		for ( int i = 0; i < 2; i++) {
			month[i] = timeChar[i+5];
			day[i] = timeChar[i+8];
			hour[i] = timeChar[i+11];
			minute[i] = timeChar[i+14];
			second[i] = timeChar[i+17];
		}
		
		this.year 	= 	Integer.parseInt(String.valueOf(year));
		this.month 	= 	Integer.parseInt(String.valueOf(month));
		this.day 	= 	Integer.parseInt(String.valueOf(day));
		this.hour 	= 	Integer.parseInt(String.valueOf(hour));
		this.minute = 	Integer.parseInt(String.valueOf(minute));
		this.second = 	Integer.parseInt(String.valueOf(second));
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String convertMonth(int month) {
		String[] monthList = {"Jan.","Feb.","Mar.","Apr.","May","Jun.","Jul.","Aug.","Sept.","Oct.","Nov.","Dec."};
		return monthList[month-1];
	}
	
	public String outputDate() {
		char[] monthChar = convertMonth(this.month).toCharArray();
		char[] dayChar = String.valueOf(this.day).toCharArray();
		int newCharLength = monthChar.length+dayChar.length+1;
		
		char[] outputChar = new char[newCharLength];
		for ( int i = 0; i < outputChar.length; i++) {
			outputChar[i] = ' ';
		}
		System.arraycopy(dayChar, 0, outputChar, 0, dayChar.length);
		System.arraycopy(monthChar, 0, outputChar, dayChar.length+1, monthChar.length);
		
		return String.valueOf(outputChar);
	}
	
	public char[] zeroAmend(char[] c) {
		char[] charD = new char[2];
		if(c.length == 1) {
			charD[1] = c[0];
			charD[0] = '0';
			return charD;
		}
		else 
			return c;
	}
	
	public String outputTime() {
		char[] hourChar = String.valueOf(this.hour).toCharArray();
		char[] minuteChar = String.valueOf(this.minute).toCharArray();
		char[] secondChar = String.valueOf(this.second).toCharArray();
		
		hourChar = zeroAmend(hourChar);
		minuteChar = zeroAmend(minuteChar);
		secondChar = zeroAmend(secondChar);
		
		char[] outputChar = new char[8];
		for ( int i = 0; i < outputChar.length; i++ ) {
			outputChar[i] = ':';
		}
		System.arraycopy(hourChar, 0, outputChar, 0, hourChar.length);
		System.arraycopy(minuteChar, 0, outputChar, 3, minuteChar.length);
		System.arraycopy(secondChar, 0, outputChar, 6, secondChar.length);
		
		return String.valueOf(outputChar);
	}
	
	public String outputFullTime() {
		char[] date = this.outputDate().toCharArray();
		char[] time = this.outputTime().toCharArray();
		char[] outputChar = new char[date.length+time.length+1];
		
		outputChar[date.length] = ' ';
		
		System.arraycopy(date, 0, outputChar, 0, date.length);
		System.arraycopy(time, 0, outputChar, date.length+1, time.length);
		
		return String.valueOf(outputChar);
	}

}
