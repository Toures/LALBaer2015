package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Break;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Schedule {

	private String name;
	private DataModel table;

	public Schedule(String name) {
		this.name = name;	
	}

	public DataModel createNewTable(int rows, int columns) {
		table = new DataModel(rows, columns);
		return table;
	}
	
	public void setTable(DataModel table) {
		this.table = table;
	}
	
	public DataModel getTable() {
		return this.table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
