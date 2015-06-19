package de.hs_mannheim.IB.SS15.OOT.Participants;

import de.hs_mannheim.IB.SS15.OOT.Subject;

public class Examinee extends Participant{

	public Examinee(String name, Subject[] subjects, Desire[] desires) {
		super(name,subjects,desires);
	}
	
	public boolean hasSubject(Subject subject) {
		 for(int i = 0; i < subjects.size(); i++)
			 if(subjects.get(i).equals(subject))
				 return true;
		 return false;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}
