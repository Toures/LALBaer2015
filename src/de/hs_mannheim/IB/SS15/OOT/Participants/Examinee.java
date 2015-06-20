package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Subject;

public class Examinee extends Participant{

	public Examinee(){

	}

	public Examinee(String name, Subject[] subjects, Desire[] desires) {
		super(name,subjects,desires);
	}

	public boolean hasSubject(Subject subject) {
		for(int i = 0; i < subjects.size(); i++)
			if(subjects.get(i).equals(subject))
				return true;
		return false;
	}

	public Examinee cloneDeep(){
		Examinee clonedExaminee = new Examinee();
		ArrayList<Subject> clonedSubjects = new ArrayList<Subject>();
		Desire[] clonedDesires = new Desire[this.desires.length];
		for(int i = 0; i < this.getSubjects().size(); i++){
			clonedDesires[i] = this.getDesires()[i].cloneDeep();
		}
		for(int i = 0; i < this.getSubjects().size(); i++){
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
	public Desire[] getDesires() {
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
	public void setDesires(Desire[] desires) {
		this.desires = desires;
	}

	@Override
	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

}
