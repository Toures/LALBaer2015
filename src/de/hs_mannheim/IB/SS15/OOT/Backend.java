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
	
	public Backend(ArrayList<Subject> subjects, ArrayList<Examinee> examinee, ArrayList<Examiner> examiner, ArrayList<Exam> exams) {
		this.examinee = examinee;
		this.examiner = examiner;
		this.subjects = subjects;
		this.exams = exams;
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
	
	public void generateExams(){
		int tmpIndex = 0;
		boolean inserted = false;

		//Lists of subjects and examinee's that got deep cloned for use
		ArrayList<Subject> subjectsForExam = new ArrayList<Subject>();
		ArrayList<Examinee> examineeCollection = new ArrayList<Examinee>();
		ArrayList<Examiner> examiners = new ArrayList<Examiner>();

		//List of the exams; builds up throughout this method
		ArrayList<Exam> examCollection = new ArrayList<Exam>();

		//ArrayList that is changed several times within the method; it holds the amount of combinations of 1 chosen subject with the rest
		ArrayList<Integer> combi1SpecSubjectAndOthers = new ArrayList<Integer>();

		//sets up the examineeCollection-list with clones
		for(int count = 0; count < examinee.size(); count++){
			examineeCollection.add(examinee.get(count).cloneDeep());
		}

		//sets up the examiners-list with clones
		for(int count = 0; count < examiner.size(); count++){
			examiners.add(examiner.get(count).cloneDeep());
		}

		//get an ArrayList with the subjects sorted from highest amount of examinee's to lowest (each subject cloned)
		for (int i = 0; i < subjects.size(); i++) {
			if(subjectsForExam.size() != 0){
				for (int j = 0; j < subjectsForExam.size(); j++) {
					inserted = false;
					if(subjects.get(i).getAmountOfExaminees() > subjectsForExam.get(j).getAmountOfExaminees()){
						subjectsForExam.add(j, subjects.get(i).cloneDeep());
						j = subjectsForExam.size();
						inserted = true;
					}
				}
				if(!inserted){
					subjectsForExam.add(subjects.get(i).cloneDeep());
				}
			}else{
				subjectsForExam.add(subjects.get(i).cloneDeep());
			}
		}

		for(int i = 0; i < subjectsForExam.get(0).getAmountOfExaminees(); i++){
			examCollection.add(new Exam());
			examCollection.get(i).addSubject(subjectsForExam.get(0));
		}

		//the subject with the most participants needs 1 exam for each participant
		combi1SpecSubjectAndOthers.add(-1);

		while(subjectsForExam.size() > 0){

			fillListOfCombinations(subjectsForExam, examineeCollection, combi1SpecSubjectAndOthers);
			int highestComb, indexOfhighestComb;
			indexOfhighestComb = getIndexOfHighestValue(combi1SpecSubjectAndOthers);
			highestComb = combi1SpecSubjectAndOthers.get(indexOfhighestComb);

			//combinations with other subjects exist
			if(highestComb != 0){

				for(int i = 0; i < examineeCollection.size(); i++){
					if(examineeCollection.get(i).hasSubject(subjectsForExam.get(0)) 
							&& examineeCollection.get(i).hasSubject(subjectsForExam.get(indexOfhighestComb))){

						if(examineeCollection.get(i).getSubjects().size() == 2){
							examCollection.get(tmpIndex).setExaminee(examineeCollection.get(i));
							examCollection.get(tmpIndex).addSubject(subjectsForExam.get(indexOfhighestComb));
							examineeCollection.remove(i);
							i--;
							tmpIndex++;
						}else{
							examCollection.get(tmpIndex).setExaminee(examineeCollection.get(i));
							examCollection.get(tmpIndex).addSubject(subjectsForExam.get(indexOfhighestComb));
							examineeCollection.get(i).removeSubject(subjectsForExam.get(indexOfhighestComb));
							examineeCollection.get(i).removeSubject(subjectsForExam.get(0));
							tmpIndex++;
						}

						subjectsForExam.get(indexOfhighestComb).decrementAmountOfExaminees();
						subjectsForExam.get(0).decrementAmountOfExaminees();
					}
				}

				//the second subject is done due to creating all these combinations, no need to work with this later on
				if(subjectsForExam.get(indexOfhighestComb).getAmountOfExaminees() == 0){
					subjectsForExam.remove(indexOfhighestComb);
				}

				//no combination exists
			}else{

				for(int i = 0; i < examineeCollection.size(); i++){
					if(examineeCollection.get(i).hasSubject(subjectsForExam.get(0))){
						examCollection.get(tmpIndex).setExaminee(examineeCollection.get(i));
						examineeCollection.get(i).removeSubject(subjectsForExam.get(0));
						tmpIndex++;
						subjectsForExam.get(0).decrementAmountOfExaminees();
						if(examineeCollection.get(i).getSubjects().size() == 0){
							examineeCollection.remove(i);
						}
					}
				}	
			}

			//all exams for this subjects are done, move on to the next one
			if(subjectsForExam.get(0).getAmountOfExaminees() == 0 && subjectsForExam.size() != 1){
				subjectsForExam.remove(0);
				subjectsForExam = sortSubjectsToExaminees(subjectsForExam);
				for(int i = 0; i < subjectsForExam.get(0).getAmountOfExaminees(); i++){
					examCollection.add(new Exam());
					examCollection.get(tmpIndex+i).addSubject(subjectsForExam.get(0));
				}
			}else if(subjectsForExam.get(0).getAmountOfExaminees() == 0 && subjectsForExam.size() == 1){
				subjectsForExam.remove(0);
			}

			for(int i = 1; i < combi1SpecSubjectAndOthers.size();){
				combi1SpecSubjectAndOthers.remove(i);
			}
		}

		ArrayList<Exam> tmpExams = new ArrayList<Exam>();

		//temporary list of all exams for use
		for (int i = 0; i < examCollection.size(); i++) {
			tmpExams.add(examCollection.get(i));
		}

		while(examsHaveNoExaminer(examCollection)){
			for (int i = 0; tmpExams.size() != 0;) {

				//exam with only one subject
				if(tmpExams.get(i).getSubjects()[0] != null && tmpExams.get(i).getSubjects()[1] == null){
					
					for (int j = 0; j < examiners.size(); j++) {
						if(examiners.get(j).hasSubject(tmpExams.get(i).getSubjects()[0])){
							if(tmpExams.get(i).getExaminer()[0] == null){
								tmpExams.get(i).addExaminer(examiners.get(j));
							}
						}
					}
					tmpExams.remove(i);
				}else{

					//looks for examiners that have both subjects of the current exams in their subject list, so there would be no need for a second examiner
					for (int j = 0; j < examiners.size(); j++) {
						if(examiners.get(j).hasSubject(tmpExams.get(i).getSubjects()[0])
								&& examiners.get(j).hasSubject(tmpExams.get(i).getSubjects()[1])){
							tmpExams.get(i).addExaminer(examiners.get(j));
							tmpExams.remove(i);
							examiners.remove(j);
							j--;
						}
					}

					//looks for examiners that have at least 1 subject of the current exam to add as examiner
					for (int j = 0; j < examiners.size(); j++) {
						if(examiners.get(j).hasSubject(tmpExams.get(i).getSubjects()[0])
								|| examiners.get(j).hasSubject(tmpExams.get(i).getSubjects()[1])){
							if(tmpExams.get(i).getExaminer()[0] == null || tmpExams.get(i).getExaminer()[1] == null){
								tmpExams.get(i).addExaminer(examiners.get(j));
							}else{
								tmpExams.remove(i);
								j = -1;
							}
						}
					}
				}
				
				//no examiners left, but some exams have only one examiner, they get assessors later on
				if(examiners.size() == 0){
					for (int j = 0; j < tmpExams.size(); j++) {
						tmpExams.remove(j);			
					}
				}
				
				//last exam was finished, can be removed so the loop ends
				if(tmpExams.get(i).getExaminer()[0] != null && tmpExams.size() == 1){
					tmpExams.remove(i);
				}
			}
		}

		for(int i = 0; i < examCollection.size(); i++){
			if(examCollection.get(i).getSubjects()[1] != null){
				if(examCollection.get(i).getSubjects()[0].isPreEffort() && examCollection.get(i).getSubjects()[1].isPreEffort()){
					examCollection.get(i).setLength(10);
					
				}else if((examCollection.get(i).getSubjects()[0].isPreEffort() && !examCollection.get(i).getSubjects()[1].isPreEffort()) 
						|| (!examCollection.get(i).getSubjects()[0].isPreEffort() && examCollection.get(i).getSubjects()[1].isPreEffort())){
					examCollection.get(i).setLength(15);
					
				}else{
					examCollection.get(i).setLength(20);
				}
			}else{
				if(examCollection.get(i).getSubjects()[0].isPreEffort()){
					examCollection.get(i).setLength(5);
					
				}else{
					examCollection.get(i).setLength(10);
				}
			}		
		}
		
		this.exams = examCollection;
	}

	private boolean examsHaveNoExaminer(ArrayList<Exam> examCol) {
		for(int i = 0; i < examCol.size(); i++){
			if(examCol.get(i).getExaminer() == null || examCol.get(i).getExaminer()[0] == null){
				return true;
			}
		}
		return false;
	}

	/**
	 * Sorts a list of subjects relative to the amount of examinee's it has. From highest to lowest
	 * @param subjList the to be sorted list of subjects
	 * @return a sorted list of subjects relative to the amount of examinee's it has
	 */
	private ArrayList<Subject> sortSubjectsToExaminees(ArrayList<Subject> subjList) {
		ArrayList<Subject> tmpSubjectList = new ArrayList<Subject>();
		boolean inserted = false;

		for (int i = 0; i < subjList.size(); i++) {
			if(tmpSubjectList.size() != 0){
				for (int j = 0; j < tmpSubjectList.size(); j++) {
					inserted = false;
					if(subjList.get(i).getAmountOfExaminees() > tmpSubjectList.get(j).getAmountOfExaminees()){
						tmpSubjectList.add(j, subjList.get(i));
						j = tmpSubjectList.size();
						inserted = true;
					}
				}
				if(!inserted){
					tmpSubjectList.add(subjList.get(i));
				}
			}else{
				tmpSubjectList.add(subjList.get(i));
			}
		}
		return tmpSubjectList;
	}

	/**
	 * method to get the index of the highest value in the given list
	 * @param combinationList the list to find the highest value from
	 * @return index of the highest value in the list
	 */
	private int getIndexOfHighestValue(ArrayList<Integer> combinationList){
		int index = 0;
		for (int i = 1; i < combinationList.size(); i++) {
			if(combinationList.get(i) > combinationList.get(index)){
				index = i;
			}
		}
		return index;
	}

	/**
	 * Method to fill the ArrayList<Integer> in relation to the criteria, ergo amount of examinee's
	 * that participate in the chosen subject, but have also others. Eventually filling it up with the possible combination
	 * of said chosen subject and their others.
	 * @param subj list of all subjects, but u need only one specific one
	 * @param examinee list of all examinee's
	 * @param combinationList the to be filled list
	 */
	private void fillListOfCombinations(ArrayList<Subject> subj, ArrayList<Examinee> examinee, ArrayList<Integer> combinationList) {
		int combinationCnt = 0;

		for(int i = 1; i < subj.size(); i++){
			for(int cnt = 0; cnt < examinee.size(); cnt++){
				if(examinee.get(cnt).hasSubject(subj.get(0)) && examinee.get(cnt).hasSubject(subj.get(i))){
					combinationCnt++;
				}
			}
			combinationList.add(combinationCnt);
			combinationCnt = 0;
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
