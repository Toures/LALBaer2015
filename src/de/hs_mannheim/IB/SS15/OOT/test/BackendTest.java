package de.hs_mannheim.IB.SS15.OOT.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.hs_mannheim.IB.SS15.OOT.Backend;
import de.hs_mannheim.IB.SS15.OOT.Schedule;
import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class BackendTest {

	private Backend backend;

	private ArrayList<Subject> subjects;
	private ArrayList<Desire> desires;

	@Before
	public void setUp() throws Exception {
		Schedule[] schedule = { new Schedule("test Schedule") };
		backend = new Backend(schedule);

		subjects = backend.getSubjects(); // from Backend
		desires = new ArrayList<Desire>();

	}

	/*
	 * createExaminer
	 */

	@Test(expected = IllegalArgumentException.class)
	public void createExamineeTest_NameNull() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee(null, subjects, desires);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createExamineeTest_NameEmpty() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee("", subjects, desires);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createExamineeTest_SubjectsNull() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee("Examinee01", null, desires);
	}

	@Test(expected = IllegalArgumentException.class)
	// TODO Exception (was für eine ??)
	public void createExamineeTest_ZeroSubjects() {
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee("Examinee01", subjects, desires);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createExamineeTest_DesiresNull() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createExaminee("Examinee01", subjects, null);
	}

	@Test
	public void createExamineeTest_ZeroDesires() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createExaminee("Examinee01", subjects, desires);
	}

	@Test
	public void createExamineeTest_LegalArguments() {

		String name = "Examinee01";
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		Examinee examinee = backend.createExaminee(name, subjects, desires);

		// test ob die übergebenen Elemente mit denen von examinee übereinstimmen
		assertEquals(name, examinee.getName());
		assertEquals(subjects, examinee.getSubjects());
		assertEquals(desires, examinee.getDesires());

	}

	/*
	 * createExaminer
	 */

	@Test(expected = IllegalArgumentException.class)
	public void createExaminerTest_NameNull() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer(null, subjects, desires);

	}

	@Test(expected = IllegalArgumentException.class)
	public void createExaminerTest_NameEmpty() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("", subjects, desires);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createExaminerTest_SubjectsNull() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("Examiner01", null, desires);
	}

	@Test(expected = IllegalArgumentException.class)
	// TODO Exception (was für eine ??)
	public void createExaminerTest_ZeroSubjects() {
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("Examiner01", subjects, desires);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createExaminerTest_DesiresNull() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createExaminer("Examiner01", subjects, null);
	}

	@Test
	public void createExaminerTest_ZeroDesires() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createExaminer("Examiner01", subjects, desires);
	}

	@Test
	public void createExaminerTest_LegalArguments() {

		String name = "Examiner01";
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		Examiner examiner = backend.createExaminer(name, subjects, desires);

		// test ob die übergebenen Elemente mit denen von examinee übereinstimmen
		assertEquals(name, examiner.getName());
		assertEquals(subjects, examiner.getSubjects());
		assertEquals(desires, examiner.getDesires());

	}

	/*
	 * createAssessor
	 */

	@Test(expected = IllegalArgumentException.class)
	public void createAssessorTest_NameNull() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createAssessor(null, subjects);

	}

	@Test(expected = IllegalArgumentException.class)
	public void createAssessorTest_NameEmpty() {
		subjects.add(new Subject("Subject01", "sub01"));

		backend.createAssessor("", subjects);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createAssessorTest_SubjectsNull() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createAssessor("Assessor01", null);
	}

	@Test(expected = IllegalArgumentException.class)
	// TODO Exception (was für eine ??)
	public void createAssessorTest_ZeroSubjects() {
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createAssessor("Assessor01", subjects);
	}

	@Test
	public void createAssessor_LegalArguments() {

		String name = "Examiner01";
		subjects.add(new Subject("Subject01", "sub01"));

		Assessor assessor = backend.createAssessor(name, subjects);

		// test ob die übergebenen Elemente mit denen von examinee übereinstimmen
		assertEquals(name, assessor.getName());
		assertEquals(subjects, assessor.getSubjects());

	}

	/*
	 * createSubject
	 */

	@Test(expected = IllegalArgumentException.class)
	public void createSubject_NameNull() {
		backend.createSubject(null, "OOT");

	}

	@Test(expected = IllegalArgumentException.class)
	public void createSubject_NameEmpty() {
		backend.createSubject("", "OOT");

	}

	@Test(expected = IllegalArgumentException.class)
	public void createSubject_AbbreviationNull() {
		backend.createSubject("Objektorientierte Programierung", null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void createSubject_AbbreviationEmpty() {
		backend.createSubject("Objektorientierte Programierung", "");

	}

	@Test
	public void createSubject_LegalArguments() {
		assertEquals(0, backend.getSubjects().size());
		backend.createSubject("Objektorientierte Programierung", "OOT");
		assertEquals(1, backend.getSubjects().size());

	}
	
	/*
	 * removeAssessor(Assessor)
	 */

	@Test(expected = IllegalArgumentException.class)
	public void removeAssessor_Null() {
		subjects.add(new Subject("Subject01", "Sub01"));

		ArrayList<Assessor> assessors = backend.getAssessor();
		assessors.add(new Assessor("Assessor01", subjects));

		backend.removeAssessor(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAssessor_NonExistingAssessor() {
		subjects.add(new Subject("Subject01", "Sub01"));

		ArrayList<Assessor> assessors = backend.getAssessor();
		assessors.add(new Assessor("Assessor01", subjects));

		backend.removeAssessor(new Assessor("Assessor02", subjects));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAssessor_emptyList() {
		subjects.add(new Subject("Subject01", "Sub01"));
		
		backend.removeAssessor(new Assessor("Assessor01", subjects));
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeAssessor_LegalArgument() {
		subjects.add(new Subject("Subject01", "Sub01"));

		ArrayList<Assessor> assessors = backend.getAssessor();
		assessors.add(new Assessor("Assessor01", subjects));

		backend.removeAssessor(new Assessor("Assessor01", subjects));
	}
	
	/*
	 * removeExaminee(Examinee)
	 */

	@Test(expected = IllegalArgumentException.class)
	public void removeExaminee_Null() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee("Examinee01", subjects, desires);
		
		backend.removeExaminee(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeExaminee_NonExistingExaminee() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminee("Examinee01", subjects, desires);
		
		backend.removeExaminee(new Examinee("Examinee02", subjects, desires));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeExaminee_emptyList() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));
		
		backend.removeExaminee(new Examinee("Examinee01", subjects, desires));
	}
	
	@Test
	public void removeExaminee_LegalArgument() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		Examinee examine = backend.createExaminee("Examinee01", subjects, desires);
		
		backend.removeExaminee(examine);
	}
		

	/*
	 * generateExams
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void generateExamsTest_DesiresNull() {
		
		// subject
		subjects.add(new Subject("Subject01", "sub01"));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, null);

		// generateExams
		backend.generateExams();
		
	}
	
	@Test
	public void generateExamsTest_DesiresEmpty() {
		// subject
		subjects.add(new Subject("Subject01", "sub01"));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);

		// generateExams
		backend.generateExams();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void generateExamsTest_NoSubjects() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);

		// generateExams
		backend.generateExams();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void generateExamsTest_NoExaminer() {
		
		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));
		
		// subject
		subjects.add(new Subject("Subject01", "sub01"));
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);

		// generateExams
		backend.generateExams();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void generateExamsTest_NoExaminee() {
		
		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));
		
		// subject
		subjects.add(new Subject("Subject01", "sub01"));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());

		// generateExams
		backend.generateExams();
		
	}
	
	@Test
	public void generateExamsTest_Simple_1Subject_1Examinee() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));
		
		// subject
		subjects.add(new Subject("Subject01", "sub01"));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);

		// generateExams
		backend.generateExams();

	}

	@Test
	public void generateExamsTest_Simple_1Subject_2Examinee() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// subject
		subjects.add(new Subject("Subject01", "sub01"));
		
		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());

		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);
		Examinee examinee2 = backend.createExaminee("Examinee02", subjects, desires);

		// generateExams
		backend.generateExams();

	}

	@Test
	public void generateExamsTest_Simple_2Subject_1Examinee() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// subject
		subjects.add(new Subject("Subject01", "sub01"));
		subjects.add(new Subject("Subject02", "sub02"));
		
		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());

		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);

		// generateExams
		backend.generateExams();

	}

	@Test
	public void generateExamsTest_Simple_2Subject_2Examinee_BothSameSubjects() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// subject
		subjects.add(new Subject("Subject01", "sub01"));
		subjects.add(new Subject("Subject02", "sub02"));

		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());
		
		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", subjects, desires);
		Examinee examinee2 = backend.createExaminee("Examinee02", subjects, desires);

		// generateExams
		backend.generateExams();

	}

	@Test
	public void generateExamsTest_Simple_2Subject_2Examinee_DifferentSubjects() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// subject
		Subject sub01 = new Subject("Subject01", "sub01");
		Subject sub02 = new Subject("Subject02", "sub02");

		subjects.add(sub01);
		subjects.add(sub02);		

		ArrayList<Subject> tempSubjects1 = new ArrayList<Subject>();
		tempSubjects1.add(sub01);
		tempSubjects1.add(sub02);
		ArrayList<Subject> tempSubjects2 = new ArrayList<Subject>();
		tempSubjects2.add(sub01);
		
		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());

		// examinee
		Examinee examinee1 = backend.createExaminee("Examinee01", tempSubjects1, desires);
		Examinee examinee2 = backend.createExaminee("Examinee02", tempSubjects2, desires);

		// generateExams
		backend.generateExams();

	}

	@Test
	public void generateExamsTest_Simple_2Subject_2Examinee_TotallyDifferentSubjects() {

		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		// subject
		Subject sub01 = new Subject("Subject01", "sub01");
		Subject sub02 = new Subject("Subject02", "sub02");

		subjects.add(sub01);
		subjects.add(sub02);

		ArrayList<Subject> tempSubjects1 = new ArrayList<Subject>();
		tempSubjects1.add(sub01);
		ArrayList<Subject> tempSubjects2 = new ArrayList<Subject>();
		tempSubjects2.add(sub02);
		
		// examiner
		Examiner examiner01 = backend.createExaminer("Examiner01", subjects, new ArrayList<Desire>());

		// examiner
		Examinee examinee1 = backend.createExaminee("Examinee01", tempSubjects1, desires);
		Examinee examinee2 = backend.createExaminee("Examinee02", tempSubjects2, desires);

		// generateExams
		backend.generateExams();

	}

}
