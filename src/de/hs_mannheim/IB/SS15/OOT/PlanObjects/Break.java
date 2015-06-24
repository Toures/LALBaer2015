package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import java.io.Serializable;

public class Break implements PlanObject {

	private int length;
	private int time;
	
	public Break(int time, int length) {
		this.time = time;
		this.length = length;
	}

	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public boolean isBreak() {
		return true;
	}
	
	public int getTime() {
		return this.time;
	}

}