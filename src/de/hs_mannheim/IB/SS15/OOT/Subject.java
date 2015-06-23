package de.hs_mannheim.IB.SS15.OOT;

public class Subject {

	private String name;
	private String abbreviation;
	private boolean preEffort;

	private int amountOfExaminees;

	public Subject(String name, String abbreviation, boolean preEffort) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.setPreEffort(preEffort);
	}

	public Subject(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.setPreEffort(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public int getAmountOfExaminees() {
		return amountOfExaminees;
	}

	public void setAmountOfExaminees(int amountOfExaminees) {
		this.amountOfExaminees = amountOfExaminees;
	}

	@Override
	public String toString() {
		return "" + name + " (" + abbreviation + ") mit " + amountOfExaminees
				+ " Studenten";
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass())
			return false;
		Subject sub = (Subject) obj;

		if (name == null) {
			if (sub.name != null) {
				return false;
			}
		}
		else if(!name.equals(sub.name)){
			return false;
		}
		if (abbreviation == null) {
			if (sub.abbreviation != null ) {
				return false;
			}
		}
		else if(!abbreviation.equals(sub.abbreviation)){
			return false;
		}
		if(sub.preEffort != preEffort){
			return false;
		}
		return true;

	}

	public Subject cloneDeep() {
		Subject clonedSub = new Subject(this.name, this.abbreviation);
		clonedSub.setAmountOfExaminees(this.getAmountOfExaminees());
		return clonedSub;
	}

	public void decrementAmountOfExaminees() {
		amountOfExaminees--;

	}

	public boolean isPreEffort() {
		return preEffort;
	}

	public void setPreEffort(boolean preEffort) {
		this.preEffort = preEffort;
	}

}