package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

public interface PlanObject {
	
	/**
	 * @return Returns length in minutes.
	 */
	int getLength();
	
	/**
	 * @return true when PlanObject is a break.
	 */
	boolean isBreak();
}