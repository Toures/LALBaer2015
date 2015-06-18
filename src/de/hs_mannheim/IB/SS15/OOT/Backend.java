package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class Backend {

	ArrayList<Subject> subjects;

	ArrayList<Examinee> examinee;
	ArrayList<Examiner> examiner;
	ArrayList<Assessor> assessor;
	Schedule[] schedule;

	public Backend(Schedule[] schedule) {
		this.schedule = schedule;

		subjects = new ArrayList<Subject>();
		examinee = new ArrayList<Examinee>();
		assessor = new ArrayList<Assessor>();
		
		
	}

	public Examinee createExaminee(String name, Subject[] subjects, Desire[] desires){
		Examinee returnExaminee = new Examinee(name,subjects, desires);
		examinee.add(returnExaminee);
		return returnExaminee;
	}
	
	
	public Examiner creatExaminer (String name, Subject[] subjects, Desire[] desires){
		Examiner returnExaminer = new Examiner(name, subjects, desires);
		examiner.add(returnExaminer);
		return returnExaminer;
	}
	
	public Assessor creatAssessor (String name, Subject[] subjects){
		Assessor returnAssessor = new Assessor(name, subjects);
		assessor.add(returnAssessor);
		return returnAssessor;
	}
	
	
	public Subject createSubject(String name, String abbreviation) {
		Subject returnSubject = new Subject(name, abbreviation);
		subjects.add(returnSubject);

		return returnSubject;
	}

	
	public ArrayList<Assessor> getAssessor() {
		return assessor;
	}
	
	
	public ArrayList<Examinee> getExaminee() {
		return examinee;
	}
	
	
	public ArrayList<Examiner> getExaminer() {
		return examiner;
	}
	
	
	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
	
	
	public void removeAssessor(String name){
		for ( Assessor a : assessor){
			if(a.getName().equals(name)){
				assessor.remove(a);
			}
		}
	}
	
	public void removeExaminee(String name){
		for(Examinee e : examinee){
			if(e.getName().equals(name)){
				examinee.remove(e);
			}
		}
	}
	
	public void removeExaminer(String name){
		for (Examiner e : examiner){
			if(e.getName().equals(name)){
				examiner.remove(e);
			}
		}
	}
	public void removeSubject(String name){
		for (Subject s : subjects) {
			if(s.getName().equals(name)){
				subjects.remove(s);
			}
			
		}
	}

}
