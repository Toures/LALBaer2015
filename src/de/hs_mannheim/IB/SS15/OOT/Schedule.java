package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Break;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Schedule {

	private String name;

	private ArrayList<Exam> exams;
	private ArrayList<Break> breaks;

	public Schedule(String name) {
		this.name = name;
		
	}

	public DataModel createNewTable(int rows, int columns) {
		return new DataModel(rows, columns);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	public ArrayList<Break> getBreaks() {
		return breaks;
	}

	public void setBreaks(ArrayList<Break> breaks) {
		this.breaks = breaks;
	}


}
