package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class ExamsTest {
	private Exam exam;
	private Subject[] subjects = new Subject[2];
	private Examinee examinee;
	private Examiner[] examiner = new Examiner[2];
	private Assessor assessor;
	
	@Before
	public void setUp() throws Exception{
		Subject subjectOne = new Subject("Lineare Algebra", "LAL");
		Subject subjectTwo = new Subject("Analysis", "ANA");
		subjects[0]= subjectOne;
		subjects[1]=subjectTwo;
		ArrayList<Subject> subjectsArrayList = new ArrayList<Subject>();
		subjectsArrayList.add(subjectOne);
		subjectsArrayList.add(subjectTwo);
		ArrayList<Desire> desires = new ArrayList<Desire>();
		desires.add(new Desire(1,1,2,2,"wichtig",3 ));
		Examinee examineeTest = new Examinee("Harald", subjectsArrayList, desires);
		Examiner examinerTestOne = new Examiner("Dieter", subjectsArrayList, desires);
		Examiner examinerTestTwo = new Examiner("Wolfgang", subjectsArrayList, desires);
		examiner[0]=examinerTestOne;
		examiner[1]=examinerTestTwo;
		Assessor assessorTest = new Assessor("Helene", subjectsArrayList);
		exam = new Exam(subjects, examineeTest, examiner, assessorTest,3);
	}
}
