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

	public void createSubject(String name, String abbreviation) {

		subjects.add(new Subject(name, abbreviation));

		// return new Subject(name, abbreviation);

	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

}
