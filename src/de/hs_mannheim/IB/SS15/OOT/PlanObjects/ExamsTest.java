package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class ExamsTest {
	private Exam exam, exam2, exam3;
	Desire desireOne, desireTwo, desireThree;
	private Subject[] subjects = new Subject[2];
	private Subject[] subjects2 = new Subject[2];
	private Subject[] subjects3 = new Subject[2];
	private Examinee examinee;
	private Examiner[] examiner = new Examiner[2];
	private Assessor assessor;
	Subject subjectOne,subjectTwo, subjectThree;
	ArrayList<Desire> desiresListOne ;
	
	@Before
	public void setUp() throws Exception{
		
		// Subjects
		subjectOne  = new Subject("Lineare Algebra", "LAL");
		subjectTwo = new Subject("Analysis", "ANA");
		subjectThree = new Subject("Höhere Mathematik 1", "HM1", true);
		subjects[0]= subjectOne;
		subjects[1]=subjectTwo;
		subjects3[0]=subjectOne;
		subjects3[1]=null;
		subjects2[0]=null;
		subjects2[1]=null;
		
		
		ArrayList<Subject> subjectsArrayList = new ArrayList<Subject>();
		subjectsArrayList.add(subjectOne);
		subjectsArrayList.add(subjectTwo);
		
		// desires
		desiresListOne = new ArrayList<Desire>();
		desireOne=new Desire(1,1,2,2,"wichtig",3 );
		desiresListOne.add(desireOne);
		
		//examiner && examinee
		Examinee examineeTest = new Examinee("Harald", subjectsArrayList, null);
		Examiner examinerTestOne = new Examiner("Dieter", subjectsArrayList, desiresListOne);
		Examiner examinerTestTwo = new Examiner("Wolfgang", subjectsArrayList, null);
		examiner[0]=examinerTestOne;
		examiner[1]=examinerTestTwo;
		
		//assessor
		Assessor assessorTest = new Assessor("Helene", subjectsArrayList);
		
		// create exams
		exam = new Exam(subjects, examineeTest, examiner, assessorTest,3);
		exam2= new Exam(subjects2, examineeTest, examiner, assessorTest,3);
		exam3= new Exam(subjects3, examineeTest, examiner, assessorTest,3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addSubjectTest_NameNull(){
		exam.addSubject(null);
		exam2.addSubject(null);
		exam3.addSubject(null);
	}
	@Test (expected = SameSubjectException.class)
	public void addSubjectTest_ArrayHalfFullSameSubject(){
		exam3.addSubject(subjectOne);
		exam.addSubject(subjectOne);
	}
	
	@Test
	public void addSubjectTest_ArrayFull(){
		assertEquals(false, exam.addSubject(subjectThree));
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
