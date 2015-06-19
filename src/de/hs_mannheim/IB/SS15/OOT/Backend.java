package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.Participants.Participant;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Backend {

	ArrayList<Subject> subjects;

	ArrayList<Examinee> examinee;
	ArrayList<Examiner> examiner;
	ArrayList<Assessor> assessor;
	ArrayList<Exam> exams;
	Schedule[] schedule;

	public Backend(Schedule[] schedule) {
		this.schedule = schedule;

		subjects = new ArrayList<Subject>();
		examinee = new ArrayList<Examinee>();
		examiner = new ArrayList<Examiner>();
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
	public Schedule getExaminerSchedule(Examiner examiner) {
		Schedule ret = new Schedule("ExaminerSchedule");
		ArrayList<Exam> temp = schedule[0].getExams();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getExaminer().equals(examiner)) {
				ret.getExams().add(temp.get(i));
			}
		}
		
		return ret;
	}

	public Schedule getStudentSchedule() {
		Schedule ret = new Schedule("StudentSchedule");
		ArrayList<Exam> temp = schedule[0].getExams();
		ret.setExams(temp);
		return ret;
	}
	/**
	 * Checks if the name is in the arrayList
	 * @param list the List 
	 * @param name the name of the element you want to check
	 * @return true if the name is in the list false if not
	 */
	public boolean isInList(ArrayList<Participant> list, String name){
		for(Participant p : list){
			if(p.getName().equals(name)){
				return true;
			}
		}
		return false;
		
	}
	
	public void generateExams() {
		ArrayList<Subject> subjectsForExam = new ArrayList<Subject>();
		ArrayList<Exam> examCollection = new ArrayList<Exam>();
		ArrayList<Examinee> examineeCollection = new ArrayList<Examinee>();

		for(int count = 0; count < examinee.size(); count++){
			examineeCollection.add(examinee.get(count));
		}
		
		//get an ArrayList with the subjects sorted from highest amount of examinees to lowest
		for (int i = 0; i < subjects.size(); i++) {
			if(subjectsForExam.size() != 0 && subjectsForExam.get(i) != subjects.get(i)){
				for (int j = 0; j < subjectsForExam.size(); j++) {
					if(subjects.get(i).getAmountOfExaminees() > subjectsForExam.get(j).getAmountOfExaminees()){
						subjectsForExam.add(j, subjects.get(i));
						j = subjectsForExam.size();
					}
				}
			}else{
				subjectsForExam.add(subjects.get(i));
			}
		}

		//create the amount of exams needed 100%
		for(int i = 0; i < subjectsForExam.get(0).getAmountOfExaminees(); i++){
			examCollection.add(new Exam());
			examCollection.get(i).addSubject(subjectsForExam.get(0));
		}
		
		ArrayList<Integer> couples = new ArrayList<Integer>();
		int coupleCounter = 0; 
		
		//get an ArrayList with combination of subjects with the first subject of the list from before
		for(int i = 1; i < subjectsForExam.size(); i++){
			for(int cnt = 0; cnt < examinee.size(); cnt++){
				if(examinee.get(cnt).hasSubject(subjectsForExam.get(0)) && examinee.get(cnt).hasSubject(subjectsForExam.get(i))){
					coupleCounter++;
				}
			}
			couples.add(coupleCounter);
			coupleCounter = 0;
		}
		
		int highestVal = -1, indexOfHighestVal = 0;
		//gets the combination that appears the most
		for (int i = 0; i < couples.size(); i++) {
			if(i == 0){
				highestVal = couples.get(i);
			}else if(couples.get(i) > couples.get(indexOfHighestVal)){
				indexOfHighestVal = i;
				highestVal = couples.get(i);
			}
		}
		
		for(int i = 0; i < couples.get(indexOfHighestVal); i++){
			examCollection.get(i).addSubject(subjectsForExam.get(indexOfHighestVal));
		}
		
		//!!!!!!!!!!!!!filter Students that have this combination and delete them from the temp list!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HERE TO KEEP WORKING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(highestVal < subjectsForExam.get(0).getAmountOfExaminees()){
			int difference = highestVal - subjectsForExam.get(0).getAmountOfExaminees();
			int newIndex = 0;
			if(couples.contains(difference)){
				for(int i = 0; i < couples.size(); i++){
					if(couples.get(i) == difference){
						newIndex = i;
					}
				}
			}else{
				for (int i = 0; i < couples.size(); i++) {
					if(couples.get(i) < difference){
						newIndex = i;
					}
				}
			}
			
			for(int i = 0; i < couples.get(newIndex); i++){
				examCollection.get(i).addSubject(subjectsForExam.get(newIndex));
			}
			
		}else{
			
		}

	}
}
