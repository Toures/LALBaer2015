package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.io.Serializable;

public class Desire implements Serializable{

	int[] time = new int[2];
	String comment;
	int priority;

	/**
	 * Desire/Constraint constructor.
	 * @param from Beginning of constraint (in minutes, starting from 0:00) e.g.: 8:50 is 530.
	 * @param to End of constraint.
	 * @param comment Why this desire exists.
	 * @param priority How urgent the desire is: 1 - low priority, 2 - medium priority, 3 - high priority.
	 */
	public Desire(int from, int to, String comment, int priority) {
		this.time[0] = from;
		this.time[1] = to;
		this.comment = comment;
		if(priority > 3)
			this.priority = 3;
		else if(priority < 1)
			this.priority = 1;
		else
			this.priority = priority;
	}

	public Desire cloneDeep() {
		return new Desire(this.time[0], this.time[1], this.comment, this.priority);
	}
	
	public int[] getTime() {
		return time;
	}
	
	public void setTime(int from, int to) {
		this.time[0] = from;
		this.time[1] = to;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		if(priority > 3)
			this.priority = 3;
		else if(priority < 1)
			this.priority = 1;
		else
			this.priority = priority;
	}

	public String toString(){
		int startHour = time[0]/60;
		int startMinute = time[0]%60;
		int endHour = time[1]/60;
		int endMinute = time[1]%60;
		String von = "Von:";
		String bis = "Bis:";
		String vonFixed;
		String bisFixed;
		if (startMinute<10){
			vonFixed = von+startHour+"0"+startMinute;
		}else{
			vonFixed = von+startHour+startMinute;
		}
		if (endMinute < 10){
			bisFixed = bis+endHour+"0"+endMinute;
		}else{
			bisFixed = bis+endHour+endMinute;
		}
		return vonFixed+"\n"+bisFixed;
	}
}