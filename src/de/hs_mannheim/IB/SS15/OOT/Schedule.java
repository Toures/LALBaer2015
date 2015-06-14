package de.hs_mannheim.IB.SS15.OOT;

import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Break;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Schedule {

	private String name;

	private Exam[] exams;
	private Break[] breaks;

	public Schedule(String name) {
		this.name = name;
		
	}

	public DataModel createNewTable(int rows, int columns) {
		return new DataModel(rows, columns);
		
	}
}
