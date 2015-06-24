package de.hs_mannheim.IB.SS15.OOT;

import java.io.Serializable;

public class Schedule implements Serializable {

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
