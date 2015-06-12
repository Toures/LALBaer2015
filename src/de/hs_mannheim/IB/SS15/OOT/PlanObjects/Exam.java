package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class Exam implements PlanObject{

	private int length;
	private Subject[] subjects;
	private Examinee examinee;
	private Examiner[] examiner;
	private Assessor assessor;
	
	
	public Exam(Subject[] subjects, Examinee examinee, Examiner[] examiner, Assessor assessor, int length) {
		this.subjects = subjects;
		this.examinee = examinee;
		this.examiner = examiner;
		this.assessor = assessor;
		this.length = length;
		//felder
	}

	@Override
	public int getLength() {
		return this.length;
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

}
