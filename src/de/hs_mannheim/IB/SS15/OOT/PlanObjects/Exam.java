package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.Subject;

public class Exam implements PlanObject{

	private int length;
	private Subject[] subjects = new Subject[2];
	private Examinee examinee;
	private Examiner[] examiner;
	private Assessor assessor;
	
	public Exam(){
		
	}	
	
	public Exam(Subject[] subjects, Examinee examinee, Examiner[] examiner, Assessor assessor, int length) {
		for(int i = 0; i < this.subjects.length; i++){
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
	
	public boolean addSubject(Subject subject){
		if(subjects[0] != null && subjects[1] != null){
			return false;
		}else if(subjects[0] != null && subjects[1] == null){
			subjects[1] = subject;
			return true;
		}else{
			subjects[0] = subject;
			return true;
		}
	}

}
