package de.hs_mannheim.IB.SS15.OOT;

import java.io.Serializable;
import java.util.ArrayList;

import de.hs_mannheim.IB.SS15.OOT.Exceptions.FullCalendarException;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Break;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class Backend implements Serializable {

	public static final int TIME_BEGIN = 8 * 60; // 8:00 am
	public static final int TIME_END = 20 * 60; // 8:00 pm
	static int MAX_PARALLEL_EXAMS = 1;

	ArrayList<Subject> subjects;
	ArrayList<Examinee> examinee;
	ArrayList<Examiner> examiner;
	ArrayList<Assessor> assessor;
	ArrayList<Exam> exams;
	ArrayList<Break> breaks;
	Schedule[] schedule;

	public Backend() {
		subjects = new ArrayList<Subject>();
		examinee = new ArrayList<Examinee>();
		examiner = new ArrayList<Examiner>();
		assessor = new ArrayList<Assessor>();
		breaks = new ArrayList<Break>();

		schedule = new Schedule[3]; // master - profs - students
		schedule[0] = new Schedule("Masterplan");
		schedule[1] = new Schedule("ProfsPlan");
		schedule[2] = new Schedule("StudentPlan");

		updateSchedules();
	}

	public void updateSchedules() {
		int rows = 97; // TODO calculate size
		
		schedule[0].setTable(new DataModel(rows, 1 + MAX_PARALLEL_EXAMS));
		schedule[1].setTable(new DataModel(rows, 1 + MAX_PARALLEL_EXAMS));
		schedule[2].setTable(new DataModel(rows, 1 + MAX_PARALLEL_EXAMS));
	}



	public Examinee createExaminee(String name, ArrayList<Subject> subjects,
			ArrayList<Desire> desires) throws IllegalArgumentException {
		if (name == null || subjects == null || desires == null) {
			throw new IllegalArgumentException(
					"null-argument was given to createExaminee, no examinee is added");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("Name is empty");
		} else if (subjects.isEmpty()) {
			throw new IllegalArgumentException("subject is empty");
		} else {
			Examinee returnExaminee = new Examinee(name, subjects, desires);
			for (int i = 0; i < subjects.size(); i++) {
				subjects.get(i).incrementAmountOfExaminees();
			}
			examinee.add(returnExaminee);
			return returnExaminee;
		}
	}

	public Examiner createExaminer(String name, ArrayList<Subject> subjects,
			ArrayList<Desire> desires) {
		if (name == null || subjects == null || desires == null) {
			throw new IllegalArgumentException(
					"null-argument was given to createExaminer, no examiner is added");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("Name is empty");
		} else if (subjects.isEmpty()) {
			throw new IllegalArgumentException("subject is empty");
		} else {

			Examiner returnExaminer = new Examiner(name, subjects, desires);
			examiner.add(returnExaminer);

			return returnExaminer;

		}
	}

	public Assessor createAssessor(String name, ArrayList<Subject> subjects) {
		if (name == null || subjects == null) {
			throw new IllegalArgumentException(
					"null-argument was given to createAssessor, no assessor is added");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("Name is empty");
		} else if (subjects.isEmpty()) {
			throw new IllegalArgumentException("subject is empty");
		} else {
			Assessor returnAssessor = new Assessor(name, subjects);
			assessor.add(returnAssessor);
			return returnAssessor;
		}
	}

	public Subject createSubject(String name, String abbreviation) {
		if (name == null || abbreviation == null) {
			throw new IllegalArgumentException(
					"null-argument was given to createSubject, no subject is added");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		} else if (abbreviation.isEmpty()) {
			throw new IllegalArgumentException("abbreviation is empty");
		}
		Subject returnSubject = new Subject(name, abbreviation);
		subjects.add(returnSubject);

		return returnSubject;
	}
	
	public Schedule[] getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule[] schedule) {
		this.schedule = schedule;
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

	public void removeAssessor(Assessor ass) {
		if (ass == null) {
			throw new IllegalArgumentException("Beisitzer nicht vorhanden");
		} else if (assessor.isEmpty()) {
			throw new IllegalArgumentException("assessor.isEmpty()");
		} else {
			for (Assessor a : assessor) {
				if (a.equals(ass)) {
					assessor.remove(a);
					return;
				}
				throw new IllegalArgumentException("Beisitzer nicht in Liste");
			}
		}
	}

	public void removeExaminee(Examinee ex) {
		if (ex == null) {
			throw new IllegalArgumentException("Student nicht vorhanden");
		} else if(examinee.isEmpty()) {
			throw new IllegalArgumentException("examinee.isEmpty()");
		} else {
		
			ArrayList<Subject> subjs = new ArrayList<Subject>();
			for (int i = 0; i < examinee.size(); i++) {
				if (examinee.get(i).equals(ex)) {
					subjs = examinee.get(i).getSubjects();
					for(int j = 0; j < subjs.size(); j++){
						subjs.get(j).decrementAmountOfExaminees();
					}
					examinee.remove(i);
					return;
				}
				throw new IllegalArgumentException("Student nicht in Liste");
			}
		}
	}

	public void removeExaminer(Examiner ex) {
		if (ex == null) {
			throw new IllegalArgumentException("Pr�fer nicht vorhanden");
		} else if (examiner.isEmpty()) {
			throw new IllegalArgumentException("examiner.isEmpty()");
		} else {
			for (Examiner e : examiner) {
				if (e.equals(ex)) {
					examiner.remove(e);
					return;
				}
				throw new IllegalArgumentException();
			}
		}
	}

	public void removeSubject(Subject sub) {
		if (sub == null) {
			throw new IllegalArgumentException("Keine F�cher vorhanden");
		}else if (subjects.isEmpty()) {
			throw new IllegalArgumentException("subjects.isEmpty()");
		} else {
			for (Subject s : subjects) {
				if (s.equals(sub)) {
					subjects.remove(s);
					return;
				}
				throw new IllegalArgumentException();
			}
		}
	}

	//TODO: Mal nach den neuen Standards machen (Jonas' Aufgabe)
	//Hinweis zu den Tables und Schedules: Schedule hat eine table, kriegt man �ber getTable()
	//schedule[0] ist der master table, sie hat max 3 reihen (MAX_PARALLEL_EXAMS) und vorhanden sind
	//in dieser Tabelle exams (welche die 5 minuten zwischenpause beinhalten von der l�nge her) und
	//breaks.
	
//	public Schedule getExaminerSchedule(Examiner examiner) {
//		Schedule ret = new Schedule("ExaminerSchedule");
//		ArrayList<Exam> temp = exams;
//		for (int i = 0; i < temp.size(); i++) {
//			if (temp.get(i).getExaminer().equals(examiner)) {
//				ret.getExams().add(temp.get(i));
//			}
//		}
//
//		return ret;
//	}
//
//	public Schedule getStudentSchedule() {
//		Schedule ret = new Schedule("StudentSchedule");
//		ArrayList<Exam> temp = schedule[0].getExams();
//		ret.setExams(temp);
//		return ret;
//	}
	
	public void addBreak(int time, int length) {
		
		if (time < 0 || length < 0) {
			throw new IllegalArgumentException("time oder lenth ist negativ");
		} else if (length == 0) {
			throw new IllegalArgumentException("length muss gr��er 0 sein");
		}
		
		breaks.add(new Break(time, length));
	}
	
	public boolean removeBreak(int time) {
		for(int i = breaks.size()-1; i >= 0; i--) {
			if(time >= breaks.get(i).getTime() && time <= breaks.get(i).getTime()+breaks.get(i).getLength()) {
				breaks.remove(i);
				return true;
			}
		}
		return false;		
	}

	/**
	 * This method generates all Exams out of all the given subjects, examinee's and examiners
	 * @throws IllegalArgumentException if the subject list, examiner list and/or examinee list is empty
	 */
	public void generateExams() {
		if(this.examinee.size() != 0 && this.examiner.size() != 0 && this.subjects.size() != 0){

			int tmpIndex = 0;
			boolean inserted = false;

			// Lists of subjects and examinee's that got deep cloned for use
			ArrayList<Subject> subjectsForExam = new ArrayList<Subject>();
			ArrayList<Examinee> examineeCollection = new ArrayList<Examinee>();
			ArrayList<Examiner> examiners = new ArrayList<Examiner>();

			// List of the exams; builds up throughout this method
			ArrayList<Exam> examCollection = new ArrayList<Exam>();

			// ArrayList that is changed several times within the method; it holds
			// the amount of combinations of 1 chosen subject with the rest
			ArrayList<Integer> combi1SpecSubjectAndOthers = new ArrayList<Integer>();

			// sets up the examineeCollection-list with clones
			for (int count = 0; count < examinee.size(); count++) {
				examineeCollection.add(examinee.get(count).cloneDeep());
			}

			// sets up the examiners-list with clones
			for (int count = 0; count < examiner.size(); count++) {
				examiners.add(examiner.get(count).cloneDeep());
			}

			// get an ArrayList with the subjects sorted from highest amount of
			// examinee's to lowest (each subject cloned)
			for (int i = 0; i < subjects.size(); i++) {
				if (subjectsForExam.size() != 0) {
					for (int j = 0; j < subjectsForExam.size(); j++) {
						inserted = false;
						if (subjects.get(i).getAmountOfExaminees() > subjectsForExam
								.get(j).getAmountOfExaminees()) {
							subjectsForExam.add(j, subjects.get(i).cloneDeep());
							j = subjectsForExam.size();
							inserted = true;
						}
					}
					if (!inserted) {
						subjectsForExam.add(subjects.get(i).cloneDeep());
					}
				} else {
					subjectsForExam.add(subjects.get(i).cloneDeep());
				}
			}

			for (int i = 0; i < subjectsForExam.get(0).getAmountOfExaminees(); i++) {
				examCollection.add(new Exam());
				examCollection.get(i).addSubject(subjectsForExam.get(0));
			}

			// the subject with the most participants needs 1 exam for each
			// participant
			combi1SpecSubjectAndOthers.add(0);

			while (subjectsForExam.size() > 0) {

				fillListOfCombinations(subjectsForExam, examineeCollection,
						combi1SpecSubjectAndOthers);
				int highestComb, indexOfhighestComb;
				indexOfhighestComb = getIndexOfHighestValue(combi1SpecSubjectAndOthers);
				highestComb = combi1SpecSubjectAndOthers.get(indexOfhighestComb);

				// combinations with other subjects exist
				if (highestComb != 0) {

					for (int i = 0; i < examineeCollection.size(); i++) {
						if (examineeCollection.get(i).hasSubject(subjectsForExam.get(0))
								&& examineeCollection.get(i).hasSubject(subjectsForExam.get(indexOfhighestComb))) {

							if (examineeCollection.get(i).getSubjects().size() == 2) {
								examCollection.get(tmpIndex).setExaminee(
										examineeCollection.get(i));
								examCollection.get(tmpIndex).addSubject(
										subjectsForExam.get(indexOfhighestComb));
								examineeCollection.remove(i);
								i--;
								tmpIndex++;
							} else {
								examCollection.get(tmpIndex).setExaminee(
										examineeCollection.get(i));
								examCollection.get(tmpIndex).addSubject(
										subjectsForExam.get(indexOfhighestComb));
								examineeCollection.get(i).removeSubject(
										subjectsForExam.get(indexOfhighestComb));
								examineeCollection.get(i).removeSubject(
										subjectsForExam.get(0));
								tmpIndex++;
							}

							subjectsForExam.get(indexOfhighestComb)
							.decrementAmountOfExaminees();
							subjectsForExam.get(0).decrementAmountOfExaminees();
						}
					}

					// the second subject is done due to creating all these
					// combinations, no need to work with this later on
					if (subjectsForExam.get(indexOfhighestComb)
							.getAmountOfExaminees() == 0) {
						subjectsForExam.remove(indexOfhighestComb);
					}

					// no combination exists
				} else {

					for (int i = 0; i < examineeCollection.size(); i++) {
						if (examineeCollection.get(i).hasSubject(
								subjectsForExam.get(0))) {
							examCollection.get(tmpIndex).setExaminee(
									examineeCollection.get(i));
							examineeCollection.get(i).removeSubject(
									subjectsForExam.get(0));
							tmpIndex++;
							subjectsForExam.get(0).decrementAmountOfExaminees();
							if (examineeCollection.get(i).getSubjects().size() == 0) {
								examineeCollection.remove(i);
							}
						}
					}
				}

				// all exams for this subjects are done, move on to the next one
				if (subjectsForExam.get(0).getAmountOfExaminees() == 0
						&& subjectsForExam.size() != 1) {
					subjectsForExam.remove(0);
					subjectsForExam = sortSubjectsToExaminees(subjectsForExam);
					for (int i = 0; i < subjectsForExam.get(0)
							.getAmountOfExaminees(); i++) {
						examCollection.add(new Exam());
						examCollection.get(tmpIndex + i).addSubject(
								subjectsForExam.get(0));
					}
				} else if (subjectsForExam.get(0).getAmountOfExaminees() == 0
						&& subjectsForExam.size() == 1) {
					subjectsForExam.remove(0);
				}

				for (int i = 1; i < combi1SpecSubjectAndOthers.size();) {
					combi1SpecSubjectAndOthers.remove(i);
				}
			}

			ArrayList<Exam> tmpExams = new ArrayList<Exam>();

			// temporary list of all exams for use
			for (int i = 0; i < examCollection.size(); i++) {
				tmpExams.add(examCollection.get(i));
			}

			while (examsHaveNoExaminer(examCollection)) {
				for (int i = 0; tmpExams.size() != 0;) {

					// exam with only one subject
					if (tmpExams.get(i).getSubjects()[0] != null
							&& tmpExams.get(i).getSubjects()[1] == null) {

						for (int j = 0; j < examiners.size(); j++) {
							if (examiners.get(j).hasSubject(
									tmpExams.get(i).getSubjects()[0])) {
								if (tmpExams.get(i).getExaminer()[0] == null) {
									tmpExams.get(i).addExaminer(examiners.get(j));
								}
							}
						}
						tmpExams.remove(i);
					} else {

						// looks for examiners that have both subjects of the
						// current exams in their subject list, so there would be no
						// need for a second examiner
						for (int j = 0; j < examiners.size(); j++) {
							if (examiners.get(j).hasSubject(
									tmpExams.get(i).getSubjects()[0])
									&& examiners.get(j).hasSubject(
											tmpExams.get(i).getSubjects()[1])) {
								tmpExams.get(i).addExaminer(examiners.get(j));
								tmpExams.remove(i);
							}
						}

						if(tmpExams.size() != 0){
							// looks for examiners that have at least 1 subject of the
							// current exam to add as examiner
							for (int j = 0; j < examiners.size(); j++) {
								if (examiners.get(j).hasSubject(
										tmpExams.get(i).getSubjects()[0])
										|| examiners.get(j).hasSubject(
												tmpExams.get(i).getSubjects()[1])) {
									if (tmpExams.get(i).getExaminer()[0] == null
											|| tmpExams.get(i).getExaminer()[1] == null) {
										tmpExams.get(i).addExaminer(examiners.get(j));
									} else {
										tmpExams.remove(i);
										j = -1;
									}
								}
							}
						}
					}

					// no examiners left, but some exams have only one examiner,
					// they get assessors later on
					if (examiners.size() == 0) {
						for (int j = 0; j < tmpExams.size(); j++) {
							tmpExams.remove(j);
						}
					}

					if(tmpExams != null && tmpExams.size() != 0){

						// last exam was finished, can be removed so the loop ends
						if (tmpExams.get(i).getExaminer()[0] != null
								&& tmpExams.size() == 1) {
							tmpExams.remove(i);
						}
					}
				}
			}

			for (int i = 0; i < examCollection.size(); i++) {
				if (examCollection.get(i).getSubjects()[1] != null) {
					if (examCollection.get(i).getSubjects()[0].isPreEffort()
							&& examCollection.get(i).getSubjects()[1].isPreEffort()) {
						examCollection.get(i).setLength(15);

					} else if ((examCollection.get(i).getSubjects()[0]
							.isPreEffort() && !examCollection.get(i).getSubjects()[1]
									.isPreEffort())
									|| (!examCollection.get(i).getSubjects()[0]
											.isPreEffort() && examCollection.get(i)
											.getSubjects()[1].isPreEffort())) {
						examCollection.get(i).setLength(20);

					} else {
						examCollection.get(i).setLength(25);
					}
				} else {
					if (examCollection.get(i).getSubjects()[0].isPreEffort()) {
						examCollection.get(i).setLength(10);

					} else {
						examCollection.get(i).setLength(15);
					}
				}
			}
			this.exams = examCollection;
		}else if(examinee.size() == 0){
			throw new IllegalArgumentException("Es existieren noch keine Pr�flinge.");
		}else if(examiner.size() == 0){
			throw new IllegalArgumentException("Es existieren noch keine Pr�fer.");
		}else{
			throw new IllegalArgumentException("Es existieren noch keine F�cher.");
		}
	}

	private boolean examsHaveNoExaminer(ArrayList<Exam> examCol) {
		for (int i = 0; i < examCol.size(); i++) {
			if (examCol.get(i).getExaminer() == null
					|| examCol.get(i).getExaminer()[0] == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sorts a list of subjects relative to the amount of examinee's it has.
	 * From highest to lowest
	 * 
	 * @param subjList
	 *            the to be sorted list of subjects
	 * @return a sorted list of subjects relative to the amount of examinee's it
	 *         has
	 */
	private ArrayList<Subject> sortSubjectsToExaminees(
			ArrayList<Subject> subjList) {
		ArrayList<Subject> tmpSubjectList = new ArrayList<Subject>();
		boolean inserted = false;

		for (int i = 0; i < subjList.size(); i++) {
			if (tmpSubjectList.size() != 0) {
				for (int j = 0; j < tmpSubjectList.size(); j++) {
					inserted = false;
					if (subjList.get(i).getAmountOfExaminees() > tmpSubjectList
							.get(j).getAmountOfExaminees()) {
						tmpSubjectList.add(j, subjList.get(i));
						j = tmpSubjectList.size();
						inserted = true;
					}
				}
				if (!inserted) {
					tmpSubjectList.add(subjList.get(i));
				}
			} else {
				tmpSubjectList.add(subjList.get(i));
			}
		}
		return tmpSubjectList;
	}

	/**
	 * method to get the index of the highest value in the given list
	 * 
	 * @param combinationList
	 *            the list to find the highest value from
	 * @return index of the highest value in the list
	 */
	private int getIndexOfHighestValue(ArrayList<Integer> combinationList) {
		int index = 0;
		for (int i = 1; i < combinationList.size(); i++) {
			if (combinationList.get(i) > combinationList.get(index)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Method to fill the ArrayList<Integer> in relation to the criteria, ergo
	 * amount of examinee's that participate in the chosen subject, but have
	 * also others. Eventually filling it up with the possible combination of
	 * said chosen subject and their others.
	 * 
	 * @param subj
	 *            list of all subjects, but u need only one specific one
	 * @param examinee
	 *            list of all examinee's
	 * @param combinationList
	 *            the to be filled list
	 */
	private void fillListOfCombinations(ArrayList<Subject> subj,
			ArrayList<Examinee> examinee, ArrayList<Integer> combinationList) {
		int combinationCnt = 0;

		for (int i = 1; i < subj.size(); i++) {
			for (int cnt = 0; cnt < examinee.size(); cnt++) {
				if (examinee.get(cnt).hasSubject(subj.get(0))
						&& examinee.get(cnt).hasSubject(subj.get(i))) {
					combinationCnt++;
				}
			}
			combinationList.add(combinationCnt);
			combinationCnt = 0;
		}

	}

	/**
	 * Generates the master table with all given exams.
	 * @throws FullCalendarException 
	 * 				will be thrown if there is a problem with examiners and examinees that both have no time
	 * 				at all.
	 */
	public void generateMasterTable() throws FullCalendarException {

		int times[] = new int[MAX_PARALLEL_EXAMS];
		DataModel master = schedule[0].createNewTable(
				(TIME_END - TIME_BEGIN) / 5, MAX_PARALLEL_EXAMS+1); // New master
		// table
		int favoriteRow[] = new int[examiner.size()]; // The first time an
		// examiner is added to
		// the master plan, he
		// will be preferably
		// put in the same
		// column.
		boolean tested[] = new boolean[examinee.size()]; // If someone already
		// was tested and
		// has another exam,
		// the program will
		// try to put those
		// exams far away,
		// time-wise.

		// ---Begin with examiners and their favouriteRow--- //
		for (Exam exam : exams) {
			Examiner[] examiner = exam.getExaminer();
			int examiner1Index = this.examiner.indexOf(examiner[0]);
			int examiner2Index = this.examiner.indexOf(examiner[1]);

			// CASE: first occurence of examiner
			if (favoriteRow[examiner1Index] == 0) { // Has no favorite row yet.
				int col = 0, lowestTime = 48 * 60; // Two days should be enough
				for (int i = 0; i < times.length; i++) {
					if (times[i] > lowestTime) {
						lowestTime = times[i];
						col = i;
					}
				}
				favoriteRow[examiner1Index] = col; // Assign new row
				if (examiner2Index != -1 && favoriteRow[examiner2Index] == 0)
					favoriteRow[examiner2Index] = col;
			}

			int[] i = new int[3];
			
			//Look where the next available appointment would be if high, med and low priority
			//desires are considered. Breaks are also considered.
			int examinerRow = favoriteRow[examiner1Index];
			while( !exam.checkDesires(3, times[examinerRow]+i[0]) ||
					times[examinerRow]+i[0] > TIME_END - exam.getLength()) {
				i[0] += 5;
				for(int k = 0; k < exam.getLength(); k += 5) {
					if(master.getValueAt(times[examinerRow]+i[0]+k, examinerRow+1) != null) {
						if(master.getValueAt(times[examinerRow]+i[0]+k, examinerRow+1).isBreak()) {
							i[0] += k + master.getValueAt(times[examinerRow]+i[0]+k, examinerRow+1).getLength();
							break;
						}
					}
				}
			}
			while( !exam.checkDesires(2, times[examinerRow]+i[1]) ||
					times[examinerRow]+i[1] > TIME_END - exam.getLength()) {
				i[1] += 5;
				for(int k = 0; k < exam.getLength(); k += 5) {
					if(master.getValueAt(times[examinerRow]+i[1]+k, examinerRow+1) != null) {
						if(master.getValueAt(times[examinerRow]+i[1]+k, examinerRow+1).isBreak()) {
							i[1] += k + master.getValueAt(times[examinerRow]+i[1]+k, examinerRow+1).getLength();
							break;
						}
					}
				}
			}
			while( !exam.checkDesires(1, times[examinerRow]+i[1]) ||
					times[examinerRow]+i[2] > TIME_END - exam.getLength()) {
				i[2] += 5;
				for(int k = 0; k < exam.getLength(); k += 5) {
					if(master.getValueAt(times[examinerRow]+i[2]+k, examinerRow+1) != null) {
						if(master.getValueAt(times[examinerRow]+i[2]+k, examinerRow+1).isBreak()) {
							i[2] += k + master.getValueAt(times[examinerRow]+i[2]+k, examinerRow+1).getLength();
							break;
						}
					}
				}
			}
			//First, check low priority desires, if they interfere and if there is no possible
			//appointment if so, go to higher priority and check if there is a possible appointment
			if(times[examinerRow]+i[2] > TIME_END - exam.getLength())
				if(times[examinerRow]+i[1] > TIME_END - exam.getLength())
					if(times[examinerRow]+i[0] > TIME_END - exam.getLength())
						throw(new FullCalendarException());
					else {
						for(int k = 0; k < exam.getLength(); k += 5)
							master.setValueAt(exam, times[examinerRow]+i[0]+k/5,
								examinerRow+1);
						if(i[0] == exam.getLength())
							times[examinerRow] += i[0];
						exam.addExamDesires(times[examinerRow]+i[0]);
					}
				else {
					for(int k = 0; k < exam.getLength(); k += 5)
						master.setValueAt(exam, times[examinerRow]+i[1]+k/5,
								examinerRow+1);
					if(i[1] == exam.getLength())
						times[examinerRow] += i[1];
					exam.addExamDesires(times[examinerRow]+i[1]);
				}
			else {
				for(int k = 0; k < exam.getLength(); k += 5)
					master.setValueAt(exam, times[examinerRow]+i[2]+k/5,
							examinerRow+1);
				if(i[2] == exam.getLength())
					times[examinerRow] += i[2];
				exam.addExamDesires(times[examinerRow]+i[2]);
			}
			
			tested[this.examinee.indexOf(exam.getExaminee())] = true;
		}
		
		schedule[0].setTable(master);
	}
	
	/**
	 * Generates a single table with all examiners having their own row. All contents are shifted one down,
	 * so the first row can be the examiner which the column belongs to.
	 */
	public void generateExaminerPlan() {
		DataModel master = schedule[0].getTable();
		DataModel masterExaminer = schedule[1].createNewTable(
				(TIME_END - TIME_BEGIN) / 5, examiner.size()+1);;
		int examinerNo = 1;
		for(Examiner examiner : this.examiner) {
			masterExaminer.setValueAt(examiner, 0, examinerNo);
			for(int row = 0; row < master.getRowCount(); row++) {
				for(int col = 0; col < master.getColumnCount(); col++) {
					if(master.getValueAt(row, col) != null && master.getValueAt(row, col).isExam() == true) {
						Exam exam = (Exam)master.getValueAt(row, col);
						if(exam.getExaminer()[0].equals(examiner) || exam.getExaminer()[1].equals(examiner)) {
							masterExaminer.setValueAt(exam, (exam.getStart()/5)+1, examinerNo);
						}
					}
				}
			}
			examinerNo++;
		}
		schedule[1].setTable(masterExaminer);
	}
	
	public void generateExamineePlan() {
		DataModel master = schedule[0].getTable();
		
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}

}