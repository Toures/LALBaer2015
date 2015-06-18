package de.hs_mannheim.IB.SS15.OOT;

public class Subject {

	private String name;
	private String abbreviation;

	private int amountOfExaminees;

	public Subject(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public String toString() {
		return "" + name + " (" + abbreviation + ") mit " + amountOfExaminees + " Studenten";
	}
	


}
