package de.hs_mannheim.IB.SS15.OOT;

import java.util.ArrayList;

import sun.security.util.Length;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.Participants.Participant;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Backend {
	
	public static final int TIME_BEGIN = 8*60; // 8:00 am
	public static final int TIME_END = 20*60; // 8:00 pm
	static final int MAX_PARALLEL_EXAMS = 3;

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

	public Examinee createExaminee(String name, ArrayList<Subject> subjects, ArrayList<Desire> desires){
		Examinee returnExaminee = new Examinee(name,subjects, desires);
		examinee.add(returnExaminee);
		return returnExaminee;
	}
	
	
	public Examiner createExaminer (String name, ArrayList<Subject> subjects, ArrayList<Desire> desires){
		Examiner returnExaminer = new Examiner(name, subjects, desires);
		examiner.add(returnExaminer);
		return returnExaminer;
	}
	
	public Assessor createAssessor (String name, ArrayList<Subject> subjects){
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
	
	public void generateExams() {
		ArrayList<Subject> subjectsForExam = new ArrayList<Subject>();
		ArrayList<Exam> examCollection = new ArrayList<Exam>();
		ArrayList<Examinee> examineeCollection = new ArrayList<Examinee>();
		ArrayList<Examinee> tmpExamineeCollection = new ArrayList<Examinee>();

		for(int count = 0; count < examinee.size(); count++){
			examineeCollection.add(examinee.get(count).cloneDeep());
		}
		
		//get an ArrayList with the subjects sorted from highest amount of examinees to lowest
		for (int i = 0; i < subjects.size(); i++) {
			if(subjectsForExam.size() != 0 && subjectsForExam.get(i) != subjects.get(i)){
				for (int j = 0; j < subjectsForExam.size(); j++) {
					if(subjects.get(i).getAmountOfExaminees() > subjectsForExam.get(j).getAmountOfExaminees()){
						subjectsForExam.add(j, subjects.get(i).cloneDeep());
						j = subjectsForExam.size();
					}
				}
			}else{
				subjectsForExam.add(subjects.get(i).cloneDeep());
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
		
		//if examinees with only 2 subjects exist and have this sepcific combination they get inserted
		int newCnt = 0;
		for(int i = 0; i < examineeCollection.size(); i++){
			if(examineeCollection.get(i).getSubjects().size() == 2){
				if(examineeCollection.get(i).hasSubject(examCollection.get(0).getSubjects()[0]) 
						&& examineeCollection.get(i).hasSubject(examCollection.get(0).getSubjects()[1])){
					examCollection.get(newCnt).setExaminee(examineeCollection.get(i));
					examineeCollection.remove(i);
					newCnt++;
				}
			}
		}
		
		for(int i = 0; i < examineeCollection.size(); i++){
			if(examineeCollection.get(i).hasSubject(examCollection.get(0).getSubjects()[0]) 
					&& examineeCollection.get(i).hasSubject(examCollection.get(0).getSubjects()[1])){
				examCollection.get(newCnt).setExaminee(examineeCollection.get(i));
				tmpExamineeCollection.add(examineeCollection.get(i));
				examineeCollection.remove(i);
				newCnt++;
			}
		}
		
		
		
		if(highestVal < subjectsForExam.get(0).getAmountOfExaminees()){
			int difference = highestVal - subjectsForExam.get(0).getAmountOfExaminees(), secondDiff;
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
				secondDiff = difference - couples.get(newIndex);
				if(couples.contains(secondDiff)){
					for(int i = 0; i < couples.size(); i++){
						if(couples.get(i) == secondDiff){
							newIndex = i;
						}
					}
				}else{
					
				}
			}
			
			for(int i = 0; i < couples.get(newIndex); i++){
				examCollection.get(i).addSubject(subjectsForExam.get(newIndex));
			}
			
		}else{
			
		}

	}
	
	/**
	 * Generates the master table with all given exams.
	 */
	public void generateMasterTable() {
		
		int times[] = new int[MAX_PARALLEL_EXAMS];
		DataModel master = schedule[0].createNewTable((TIME_END-TIME_BEGIN)/5, MAX_PARALLEL_EXAMS); //New master table
		int favoriteRow[] = new int[examiner.size()]; //The first time an examiner is added to the master plan, he will be preferably put in the same column.
		boolean tested[] = new boolean[examinee.size()]; //If someone already was tested and has another exam, the program will try to put those exams far away, time-wise.
		
		//---Begin with examiners and their favouriteRow---//
		for(Exam exam : exams) {
			Examiner[] examiner = exam.getExaminer();
			int examiner1Index = this.examiner.indexOf(examiner[0]);
			int examiner2Index = this.examiner.indexOf(examiner[1]);
			
			//CASE: first occurence of examiner
			if(favoriteRow[examiner1Index] == 0) { //Has no favorite row yet.
				int col = 0, lowestTime = 48*60;
				for(int i = 0; i < times.length; i++) {
					if(times[i] > lowestTime) {
						lowestTime = times[i];
						col = i;
					}
				}
				favoriteRow[examiner1Index] = col; //New favorite row
				if(favoriteRow[examiner2Index] == 0)
					favoriteRow[examiner2Index] = col;
			}
			
			if(exam.checkDesires(3, times[favoriteRow[examiner1Index]])) { //No overlapping?
				master.setValueAt(exam, times[favoriteRow[examiner1Index]], favoriteRow[examiner1Index]);
				
			}
				
		}
	}
}
