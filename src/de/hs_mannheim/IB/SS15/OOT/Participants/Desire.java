package de.hs_mannheim.IB.SS15.OOT.Participants;

public class Desire {

	int[] time = new int[4];
	String comment;
	int priority;

	/**
	 * Desire/Constraint constructor.
	 * @param fromHour Beginning of constraint (in minutes, starting from 0:00) e.g.: 8:50 is 530.
	 * @param toMinute End of constraint.
	 * @param comment Why this desire exists.
	 * @param priority How urgent the desire is: 1 - low priority, 2 - medium priority, 3 - high priority.
	 */
	public Desire(int fromHour, int fromMinute, int toHour, int toMinute, String comment, int priority) {
		this.time[0] = fromHour;
		this.time[1] = fromMinute;
		this.time[2] = toHour;
		this.time[3] = toMinute;
		this.comment = comment;
		if(priority > 3)
			this.priority = 3;
		else if(priority < 1)
			this.priority = 1;
		else
			this.priority = priority;
	}

	public Desire cloneDeep() {
		return new Desire(this.time[0], this.time[1], this.time[2], this.time[3], this.comment, this.priority);
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

}