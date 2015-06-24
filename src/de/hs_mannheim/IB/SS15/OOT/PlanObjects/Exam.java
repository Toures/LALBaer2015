package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import java.io.Serializable;

import de.hs_mannheim.IB.SS15.OOT.Exceptions.SameSubjectException;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.Backend;
import de.hs_mannheim.IB.SS15.OOT.Subject;

public class Exam implements PlanObject {

	private int length;
	private int start; // Start of the exam will be concluded by the organizing
						// algorithm.
	private Subject[] subjects = new Subject[2];
	private Examinee examinee;
	private Examiner[] examiner = new Examiner[2];
	private Assessor assessor;
	private Desire examDesire;

	public Exam() {

	}

	public Exam(Subject[] subjects, Examinee examinee, Examiner[] examiner,
			Assessor assessor, int length) {
		for (int i = 0; i < this.subjects.length; i++) {
			this.subjects[i] = subjects[i];
		}
		this.examinee = examinee;
		this.examiner = examiner;
		this.assessor = assessor;
		this.length = length;
	}

	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public boolean isBreak() {
		return false;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Assessor getAssessor() {
		return assessor;
	}

	public void setAssessor(Assessor assessor) {
		this.assessor = assessor;
	}

	public Examinee getExaminee() {
		return examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	public Examiner[] getExaminer() {
		return examiner;
	}

	public void setExaminer(Examiner[] examiner) {
		this.examiner = examiner;
	}

	public Subject[] getSubjects() {
		return subjects;
	}

	public void setSubjects(Subject[] subjects) {
		this.subjects = subjects;
	}

	public boolean addSubject(Subject subject) {
		if (subjects[0]!= null && subjects[0].equals(subject)) {
			try {
				throw new SameSubjectException(
						"hinzuf�gen von 2 gleichen f�cher nicht m�glich");
			} catch (SameSubjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {

			if (subjects[0] != null && subjects[1] != null) {
				return false;
			} else if (subjects[0] != null && subjects[1] == null) {
				subjects[1] = subject;
				return true;
			} else {
				subjects[0] = subject;
				return true;
			}
		}
	}

	/**
	 * Returns whether on of the participants of this exam have constraints of a
	 * certain type at a certain time. Also checks higher priority constraints
	 * than priority.
	 * 
	 * @param priority
	 *            How high the priority is: 1 - low, 2 - med, 3 - high
	 * @param time
	 *            At which time to check (in minutes).
	 * @return True if available, false if not.
	 */
	public boolean checkDesires(int priority, int time) {
		for (Examiner examiner : examiner)
			if (examiner != null)
				if (!examiner.isAvailable(priority, time)
						|| !examiner.isAvailable(priority, time + length))
					return false;
		if (assessor != null)
			if (!assessor.isAvailable(priority, time)
					|| !assessor.isAvailable(priority, time + length))
				return false;
		if (!examinee.isAvailable(priority, time)
				|| !examinee.isAvailable(priority, time + length))
			return false;

		return true;
	}

	/**
	 * Clears exam desires of all participants of this exam
	 */
	public void clearExamDesires() {
		examiner[0].clearExamDesires(this);
		examiner[1].clearExamDesires(this);
		examinee.clearExamDesires(this);
	}

	/**
	 * Adds exam desire to all participants of this exam at the time of the exam
	 * Should be called in conjunction with clearExamDesires, if
	 * 
	 * @param time
	 */
	public void addExamDesires(int time) {
		this.start = time;
		this.examDesire = new Desire(time, time + length, this.toString(), 3);
		for (Examiner examiner : this.examiner)
			if (examiner != null)
				examiner.addDesire(examDesire);
		examinee.addDesire(examDesire);
		// TODO: Was ist mit Beisitzern? Sie haben keine Desires, diese sind
		// aber notwendig mit diesem L�sungsansatz um anzuzeigen,
		// dass sie in einer Pr�fung besch�ftigt sind und keine anderen
		// Pr�fungen beisitzen k�nnen.
	}

	/**
	 * This method should only be use for the swapping of exams, because it only includes the change of the time period
	 * and not the exams itself or its priority
	 * @param time the new starting time of the exam
	 */
	public void changeDesire(int time){
		this.start = time;
		this.examDesire.setTime(time, time + length);
	}
	
	public void addExaminer(Examiner ex) {
		if (examiner[0] == null) {
			examiner[0] = ex;
		} else if (examiner[1] == null) {
			examiner[1] = ex;
		}
	}

	@Override
	public String toString() {
		String output = "Pr�fung in " + subjects[0].getAbbreviation();
		if (subjects[1] != null)
			output += " und " + subjects[1].getAbbreviation();
		output += " \n";
		if (start % 60 < 10)
			output += (start / 60) + ":0" + (start % 60);
		else
			output += (start / 60) + ":" + (start % 60);
		output += " - ";
		if (start + length % 60 < 10)
			output += (start + length / 60) + ":0" + (start + length % 60);
		else
			output += (start + length / 60) + ":" + (start + length % 60);
		output += " Uhr \nPr�fer: " + examiner[0].getName();
		if (examiner[1] != null)
			output += " und " + examiner[1].getName();
		output += " \nPr�fling: " + examinee.getName();
		return output;
	}

}
