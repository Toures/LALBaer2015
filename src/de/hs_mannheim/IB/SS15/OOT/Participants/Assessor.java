package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Subject;


public class Assessor extends Participant {

	public Assessor(String name, ArrayList<Subject> subjects) {
		super(name, subjects, null);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ArrayList<Desire> getDesires() {
		return this.desires;
	}

	@Override
	public ArrayList<Subject> getSubjects() {
		return this.subjects;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDesires(ArrayList<Desire> desires) {
		this.desires = desires;
	}

	@Override
	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

}
