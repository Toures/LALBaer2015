package de.hs_mannheim.IB.SS15.OOT.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.hs_mannheim.IB.SS15.OOT.Backend;
import de.hs_mannheim.IB.SS15.OOT.Schedule;
import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Exceptions.FullCalendarException;
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
		backend = new Backend();
		Schedule[] schedule = { new Schedule("test Schedule") };
		backend.setSchedule(schedule);

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

	@Test(expected = IllegalArgumentException.class)
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

	@Test(expected = IllegalArgumentException.class)
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
	 * addBreak(int time, int length) {
	 */

	@Test(expected = IllegalArgumentException.class)
	public void addBreak_length_0() {
		backend.addBreak(600, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBreak_length_negative() {
		backend.addBreak(600, -10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addBreak_time_negative() {
		backend.addBreak(-10, 10);
	}

	@Test
	public void addBreak_LegalArgument() {
		backend.addBreak(600, 10);
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
	 * removeExaminer(Examiner)
	 */

	@Test(expected = IllegalArgumentException.class)
	public void removeExaminer_Null() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("Examiner01", subjects, desires);

		backend.removeExaminer(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeExaminer_NonExistingExaminer() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("Examiner01", subjects, desires);

		backend.removeExaminer(new Examiner("Examiner02", subjects, desires));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeExaminer_emptyList() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.removeExaminer(new Examiner("Examiner01", subjects, desires));
	}

	@Test
	public void removeExaminer_LegalArgument() {
		subjects.add(new Subject("Subject01", "sub01"));
		desires.add(new Desire(630, 770, "Kommentar", 2));

		backend.createExaminer("Examiner01", subjects, desires);

		backend.removeExaminer(new Examiner("Examiner01", subjects, desires));
	}

	/*
	 * removeSubject(Subject)
	 */

	@Test(expected = IllegalArgumentException.class)
	public void removeSubject_Null() {
		backend.removeSubject(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeSubject_NonExistingSubject() {
		backend.createSubject("Objektorientierte Programierung", "OOT");

		backend.removeSubject(new Subject("Subject01", "SUB01"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeSubject_emptyList() {
		backend.removeSubject(new Subject("Subject01", "SUB01"));
	}

	@Test
	public void removeSubject_LegalArgument() {		
		backend.createSubject("Objektorientierte Programierung", "OOT");

		backend.removeSubject( new Subject("Objektorientierte Programierung", "OOT"));
	}

	/*
	 * removeBreak(int)
	 */

	@Test
	public void removeBreak_emptryList() {
		assertEquals(false, backend.removeBreak(600));

	}

	@Test
	public void removeBreak_BreakDoesNotExist() {
		backend.addBreak(500, 10);
		assertEquals(false, backend.removeBreak(600));
	}

	@Test
	public void removeBreak_BreakDoesNotExist_NegativeValue() {
		backend.addBreak(500, 10);
		assertEquals(false, backend.removeBreak(-600));
	}

	@Test
	public void removeBreak_LegalValue() {
		backend.addBreak(600, 10);
		assertEquals(true, backend.removeBreak(600));
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

	@Test(expected = IllegalArgumentException.class)
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

	@Test(expected = IllegalArgumentException.class)
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

	@Test(expected = IllegalArgumentException.class)
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
		
		// Tests
		assertEquals(1, backend.getExams().size());

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
		
		// Tests
		assertEquals(2, backend.getExams().size());

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
		
		// Tests
		assertEquals(1, backend.getExams().size());

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
		
		// Tests
		assertEquals(2, backend.getExams().size());

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
		
		// Tests
		assertEquals(2, backend.getExams().size());

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
		
		// Tests
		assertEquals(2, backend.getExams().size());

	}

	@Test
	public void generateExamsTest_25Subjects_15Examinee_5Examiners() {
		// desires
		desires.add(new Desire(630, 770, "Kommentar", 2));

		Subject sub01 = new Subject("Subject01", "sub01");
		Subject sub02 = new Subject("Subject02", "sub02");
		Subject sub03 = new Subject("Subject03", "sub03");
		Subject sub04 = new Subject("Subject04", "sub04");
		Subject sub05 = new Subject("Subject05", "sub05");
		Subject sub06 = new Subject("Subject06", "sub06");
		Subject sub07 = new Subject("Subject07", "sub07");
		Subject sub08 = new Subject("Subject08", "sub08");
		Subject sub09 = new Subject("Subject09", "sub09");
		Subject sub10 = new Subject("Subject10", "sub10");
		Subject sub11 = new Subject("Subject11", "sub11");
		Subject sub12 = new Subject("Subject12", "sub12");
		Subject sub13 = new Subject("Subject13", "sub13");
		Subject sub14 = new Subject("Subject14", "sub14");
		Subject sub15 = new Subject("Subject15", "sub15");
		Subject sub16 = new Subject("Subject16", "sub16");
		Subject sub17 = new Subject("Subject17", "sub17");
		Subject sub18 = new Subject("Subject18", "sub18");
		Subject sub19 = new Subject("Subject19", "sub19");
		Subject sub20 = new Subject("Subject20", "sub20");
		Subject sub21 = new Subject("Subject21", "sub21");
		Subject sub22 = new Subject("Subject22", "sub22");
		Subject sub23 = new Subject("Subject23", "sub23");
		Subject sub24 = new Subject("Subject24", "sub24");
		Subject sub25 = new Subject("Subject25", "sub25");

		subjects.add(sub01);
		subjects.add(sub02);
		subjects.add(sub03);
		subjects.add(sub04);
		subjects.add(sub05);
		subjects.add(sub06);
		subjects.add(sub07);
		subjects.add(sub08);
		subjects.add(sub09);
		subjects.add(sub10);
		subjects.add(sub11);
		subjects.add(sub12);
		subjects.add(sub13);
		subjects.add(sub14);
		subjects.add(sub15);
		subjects.add(sub16);
		subjects.add(sub17);
		subjects.add(sub18);
		subjects.add(sub19);
		subjects.add(sub20);
		subjects.add(sub21);
		subjects.add(sub22);
		subjects.add(sub23);
		subjects.add(sub24);
		subjects.add(sub25);

		ArrayList<Subject> subjects1 = new ArrayList<Subject>();
		ArrayList<Subject> subjects2 = new ArrayList<Subject>();
		ArrayList<Subject> subjects3 = new ArrayList<Subject>();
		ArrayList<Subject> subjects4 = new ArrayList<Subject>();
		ArrayList<Subject> subjects5 = new ArrayList<Subject>();
		ArrayList<Subject> subjects6 = new ArrayList<Subject>();
		ArrayList<Subject> subjects7 = new ArrayList<Subject>();
		ArrayList<Subject> subjects8 = new ArrayList<Subject>();
		ArrayList<Subject> subjects9 = new ArrayList<Subject>();

		ArrayList<Subject> subjects10 = new ArrayList<Subject>();
		ArrayList<Subject> subjects11 = new ArrayList<Subject>();
		ArrayList<Subject> subjects12 = new ArrayList<Subject>();
		ArrayList<Subject> subjects13 = new ArrayList<Subject>();
		ArrayList<Subject> subjects14 = new ArrayList<Subject>();

		// Examinee subjects
		subjects1.add(sub01);
		subjects1.add(sub02);
		subjects1.add(sub03);

		subjects2.add(sub04);
		subjects2.add(sub05);
		subjects2.add(sub06);
		subjects2.add(sub07);
		subjects2.add(sub08);
		subjects2.add(sub09);

		subjects3.add(sub10);
		subjects3.add(sub11);

		subjects4.add(sub12);
		subjects4.add(sub13);
		subjects4.add(sub14);
		subjects4.add(sub15);

		subjects5.add(sub16);
		subjects5.add(sub17);
		subjects5.add(sub01);

		subjects6.add(sub18);
		subjects6.add(sub10);

		subjects7.add(sub19);
		subjects7.add(sub20);
		subjects7.add(sub25);

		subjects8.add(sub21);
		subjects8.add(sub22);
		subjects8.add(sub23);
		subjects8.add(sub24);
		subjects8.add(sub05);

		subjects9.add(sub18);
		subjects9.add(sub08);
		subjects9.add(sub01);
		subjects9.add(sub06);

		// Examiner subjects
		subjects10.add(sub08);
		subjects10.add(sub06);
		subjects10.add(sub02);
		subjects10.add(sub11);
		subjects10.add(sub13);

		subjects11.add(sub07);
		subjects11.add(sub05);
		subjects11.add(sub09);
		subjects11.add(sub15);
		subjects11.add(sub25);

		subjects12.add(sub01);
		subjects12.add(sub04);
		subjects12.add(sub10);
		subjects12.add(sub12);
		subjects12.add(sub14);

		subjects13.add(sub21);
		subjects13.add(sub23);
		subjects13.add(sub03);
		subjects13.add(sub18);
		subjects13.add(sub20);

		subjects14.add(sub24);
		subjects14.add(sub22);
		subjects14.add(sub17);
		subjects14.add(sub16);
		subjects14.add(sub19);

		// Examinees
		backend.createExaminee("examinee01", subjects1, desires);
		backend.createExaminee("examinee02", subjects1, desires);
		backend.createExaminee("examinee03", subjects1, desires);
		backend.createExaminee("examinee04", subjects2, desires);
		backend.createExaminee("examinee05", subjects3, desires);
		backend.createExaminee("examinee06", subjects4, desires);
		backend.createExaminee("examinee07", subjects5, desires);
		backend.createExaminee("examinee08", subjects5, desires);
		backend.createExaminee("examinee09", subjects6, desires);
		backend.createExaminee("examinee10", subjects6, desires);
		backend.createExaminee("examinee11", subjects7, desires);
		backend.createExaminee("examinee12", subjects8, desires);
		backend.createExaminee("examinee13", subjects9, desires);
		backend.createExaminee("examinee14", subjects3, desires);
		backend.createExaminee("examinee15", subjects3, desires);

		// Examiners
		backend.createExaminer("examiner01", subjects10, desires);
		backend.createExaminer("examiner02", subjects11, desires);
		backend.createExaminer("examiner03", subjects12, desires);
		backend.createExaminer("examiner04", subjects13, desires);
		backend.createExaminer("examiner05", subjects14, desires);

		backend.generateExams();

	}
	
	/*
	 * generateMasterTable()
	 */

	@Test
	public void generateMasterTableTest_Simple_1Subject_1Examinee() throws FullCalendarException {
		
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
		
		// generateMasterTable()
		backend.generateMasterTable();
		
		
	}
	
	@Test
	public void generateMasterTableTest_Simple_1Subject_2Examinee() throws FullCalendarException {

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
		
		// generateMasterTable()
		backend.generateMasterTable();

	}

	@Test
	public void generateMasterTableTest_Simple_2Subject_1Examinee() throws FullCalendarException {

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
		
		// generateMasterTable
		backend.generateMasterTable();

	}

	@Test
	public void generateMasterTableTest_Simple_2Subject_2Examinee_BothSameSubjects() throws FullCalendarException {

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
		
		// generateMasterTable
		backend.generateMasterTable();

	}

	@Test
	public void generateMasterTableTest_Simple_2Subject_2Examinee_DifferentSubjects() throws FullCalendarException {

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
		
		// generateMasterTable()
		backend.generateMasterTable();

	}

	@Test
	public void generateMasterTableTest_Simple_2Subject_2Examinee_TotallyDifferentSubjects() throws FullCalendarException {

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
		
		// generateMasterTable
		backend.generateMasterTable();

	}


	
	
}
