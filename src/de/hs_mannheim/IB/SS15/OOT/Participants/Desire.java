package de.hs_mannheim.IB.SS15.OOT.Participants;

public class Desire {
	int timeFrom;
	int timeTo;
	String comment;
	int priority;
	public Desire(int from, int to, String comment, int prio) {
		this.timeFrom = from;
		this.timeTo = to;
		this.comment = comment;
		this.priority = prio;
	}

}
