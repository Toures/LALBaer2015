package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

public class Break implements PlanObject{

	private int length;
	
	public Break(int length) {
		this.length = length;
	}

	@Override
	public int getLength() {
		return this.length;
	}

}