package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Subject;

public abstract class Participant {
	
	protected ArrayList<Subject> subjects = new ArrayList<Subject>();
	protected String name;
	protected Desire[] desires;
	
	protected Participant(String name, Subject[] subjects, Desire[] desires) {
		this.name = name;
		for(int i = 0; i < subjects.length; i++){
			this.subjects.add(subjects[i]);
		}
		this.desires = desires;		
	}

	public abstract String getName();
	


}
