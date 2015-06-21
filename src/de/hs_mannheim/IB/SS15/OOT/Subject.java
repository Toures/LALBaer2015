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

	public int getAmountOfExaminees() {
		return amountOfExaminees;
	}

	public void setAmountOfExaminees(int amountOfExaminees) {
		this.amountOfExaminees = amountOfExaminees;
	}

	@Override
	public String toString() {
		return "" + name + " (" + abbreviation + ") mit " + amountOfExaminees + " Studenten";
	}

	public boolean equals(Object sub) {
		if (this == sub) {
			return true;
		} else if (sub == null) {
			return false;
		} else if (this.getName().equals(((Subject) sub).getName())) {
			return true;
		} else {
			return false;
		}
	}

	public Subject cloneDeep() {
		Subject clonedSub = new Subject(this.name, this.abbreviation);
		clonedSub.setAmountOfExaminees(this.getAmountOfExaminees());
		return clonedSub;
	}

	public void decrementAmountOfExaminees() {
		amountOfExaminees--;
		
	}

}
