package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Subject;

public class Examinee extends Participant {

	public Examinee() {

	}

	public Examinee(String name, ArrayList<Subject> subjects, ArrayList<Desire> desires) {
		super(name, subjects, desires);
	}

	public boolean hasSubject(Subject subject) {
		for (int i = 0; i < subjects.size(); i++)
			if (subjects.get(i).equals(subject))
				return true;
		return false;
	}

	public void addSubject(Subject sub) {
		this.subjects.add(sub);
	}

	public void removeSubject(Subject sub) {
		for (int i = 0; i < subjects.size(); i++) {
			if (this.subjects.get(i).equals(sub)) {
				this.subjects.remove(i);
			}
		}
	}

	public Examinee cloneDeep() {
		Examinee clonedExaminee = new Examinee();
		ArrayList<Subject> clonedSubjects = new ArrayList<Subject>();
		ArrayList<Desire> clonedDesires = new ArrayList<Desire>();
		if (this.desires != null && this.desires.size() != 0) {
			for (int i = 0; i < this.getSubjects().size(); i++) {
				clonedDesires.add(this.getDesires().get(i).cloneDeep());
			}
			clonedExaminee.setDesires(clonedDesires);
		} else {
			clonedExaminee.setDesires(clonedDesires);
		}
		for (int i = 0; i < this.getSubjects().size(); i++) {
			clonedSubjects.add(this.getSubjects().get(i).cloneDeep());
		}
		clonedExaminee.setSubjects(clonedSubjects);
		clonedExaminee.setName(this.getName());

		return clonedExaminee;
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
