package de.hs_mannheim.IB.SS15.OOT.Participants;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public abstract class Participant {
	
	protected ArrayList<Subject> subjects = new ArrayList<Subject>();
	protected String name;
	protected ArrayList<Desire> desires = new ArrayList<Desire>();
	
	protected Participant(){
		
	}
	
	protected Participant(String name, ArrayList<Subject> subjects, ArrayList<Desire> desires) {
		this.name = name;
		this.subjects = subjects;
		this.desires = desires;	
	}
	
	public boolean isAvailable(int priority, int time) {
		for(Desire desire : getDesires())
			if(desire.getPriority() >= priority && desire.getTime()[0] < time && desire.getTime()[1] > time)
				return false;
		return true;
	}
	
	/**
	 * Removes all desires from this participant marked as "Exam"
	 */
	public void clearExamDesires(Exam exam) {
		for(int i = desires.size(); i > 0; i--) {
			if(desires.get(i).comment.equals(exam.toString()))
				desires.remove(i);
		}
	}
	
	/**
	 * Adds a new desire marked as "Exam"
	 * @param from When it begins.
	 * @param to When it ends.
	 */
	public void addExamDesire(int fromHour, int fromMinute, int toHour, int toMinute, int priority) {
		desires.add(new Desire(fromHour, fromMinute, toHour, toMinute, "", priority));
	}

	public abstract String getName();
	
	public abstract ArrayList<Desire> getDesires();
	
	public abstract ArrayList<Subject> getSubjects();
	
	public abstract void setName(String name);
	
	public abstract void setDesires(ArrayList<Desire> desires);
	
	public abstract void setSubjects(ArrayList<Subject> subjects);

}