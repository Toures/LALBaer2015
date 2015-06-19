package de.hs_mannheim.IB.SS15.OOT.Participants;

import de.hs_mannheim.IB.SS15.OOT.Subject;


public class Assessor extends Participant {

	public Assessor(String name, Subject[] subjects) {
		super(name, subjects, null);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
