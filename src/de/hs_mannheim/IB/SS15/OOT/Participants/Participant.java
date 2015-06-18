package de.hs_mannheim.IB.SS15.OOT.Participants;

import de.hs_mannheim.IB.SS15.OOT.Subject;

public abstract class Participant {
	
	Subject[] subjects;
	String name;
	Desire[] desires;
	
	protected Participant(String name, Subject[] subjects, Desire[] desires) {
		this.name = name;
		this.subjects = subjects;
		this.desires = desires;		
	}

	public Subject[] getSubjects() {
		return subjects;
	}


	public String getName() {
		return name;
	}


	public Desire[] getDesires() {
		return desires;
	}

	
	


}
