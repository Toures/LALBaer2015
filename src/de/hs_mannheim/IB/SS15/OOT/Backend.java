package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class Backend {

	ArrayList<Subject> subjects;

	Examinee[] examinee;
	Examiner[] examiner;
	Assessor[] assesor;
	Schedule[] schedule;

	public Backend(Schedule[] schedule) {
		this.schedule = schedule;

		subjects = new ArrayList<Subject>();

		// TODO init Arrays
	}

	public Subject createSubject(String name, String abbreviation) {
		Subject returnSubject = new Subject(name, abbreviation);
		subjects.add(returnSubject);

		return returnSubject;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

}
