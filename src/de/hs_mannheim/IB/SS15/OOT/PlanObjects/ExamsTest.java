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
	private Exam exam, exam2, exam3;
	private Subject[] subjects = new Subject[2];
	private Subject[] subjects2 = new Subject[2];
	private Subject[] subjects3 = new Subject[2];
	private Examinee examinee;
	private Examiner[] examiner = new Examiner[2];
	private Assessor assessor;
	Subject subjectOne; 
	Subject subjectTwo ;
	
	@Before
	public void setUp() throws Exception{
		subjectOne  = new Subject("Lineare Algebra", "LAL");
		subjectTwo = new Subject("Analysis", "ANA");
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
		subjects3[0]=subjectOne;
		exam2= new Exam(subjects2, examineeTest, examiner, assessorTest,3);
		exam3= new Exam(subjects3, examineeTest, examiner, assessorTest,3);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void addSubjectTest_NameNull(){
		exam.addSubject(null);
		exam2.addSubject(null);
		exam3.addSubject(null);
	}
	@Test// (expected = SameSubjectException.class)
	public void addSubjectTest_ArrayHalfFullSameSubject(){
		exam3.addSubject(subjectOne);
		exam.addSubject(subjectOne);
	}
	
	@Test
	public void addSubjectTest_ArrayHalfFull_Legal(){
		assertEquals(true, exam3.addSubject(subjectTwo));
	}
	
	@Test
	public void addSubjectTest_LegalArguments(){
		assertEquals(true, exam2.addSubject(subjectOne));
		assertEquals(true, exam2.addSubject(subjectTwo));
		assertEquals(true, exam3.addSubject(subjectTwo));
	}
}
