package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import java.io.Serializable;

public interface PlanObject extends Serializable {

	/**
	 * @return Returns length in minutes.
	 */
	int getLength();

	/**
	 * @return true when PlanObject is a break.
	 */
	boolean isBreak();
	
	/**
	 * 
	 * @return true when PlanObject is an exam.
	 */
	boolean isExam();
}