package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;

public class StudentsGUI extends JFrame implements ActionListener {

	private GUI gui;

	private JScrollPane scrollMainTable;
	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddStudent;
	private JButton btnRemoveStudent;

	private Subject currentSubject;

	public StudentsGUI(GUI gui) {
		this.gui = gui;

		setTitle("Studenten");

		// setup
		if (gui.getBackend().getSubjects().size() > 0) {
			currentSubject = gui.getBackend().getSubjects().get(0);
		}

		createLayout();

		pack();
		setSize(600, 400);
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddStudent) {
			addStudentDialog();

		} else if (e.getSource() == btnRemoveStudent) {
			removeStudentDialog();

		}

	}

	private void addStudentDialog() {
		String name = JOptionPane.showInputDialog(this, "Vorname des Studenten:", "Student hinzufügen", JOptionPane.PLAIN_MESSAGE);
		if (name != null) {
			String abbreviation = JOptionPane.showInputDialog(this, "Nachname des Studenten:", "Student hinzufügen", JOptionPane.PLAIN_MESSAGE);
			if (abbreviation != null) {
				// createExaminee

				// TODO (quickFix createExaminee muss geändert werden)
				ArrayList<Subject> tempSub = new ArrayList<Subject>();
				tempSub.add(currentSubject);
				gui.getBackend().createExaminee(name, tempSub, null);
				createMainTable();
			}
		}

	}

	private void removeStudentDialog() {
		// dropdown Menü mit den möglichen Fächern
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		if (subjects.size() > 0) {
			Subject selectedSubject = (Subject) JOptionPane.showInputDialog(this, "Vorname des Studenten:", "Studenten entfernen", JOptionPane.QUESTION_MESSAGE, null, subjects.toArray(), subjects.get(0));

			if (selectedSubject != null) {

				// TODO
				for (int i = 0; i < subjects.size(); i++) {
					if (subjects.get(i).equals(selectedSubject)) {
						subjects.remove(i);
						createMainTable();
						return;
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Es sind noch keine Studenten vorhanden.", "Studenten entfernen", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// CENTER Panel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createSubjectTable(), createMainTable());
		splitPane.setDividerLocation(150);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	private JScrollPane createSubjectTable() {
		// bad code too much overhead

		if (scrollSubjectTable != null) {
			// remove old scrollSubjectTable if it exists
			getContentPane().remove(scrollSubjectTable);
		}

		// create new subjectTable
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		String columns[] = { "Fächer" };

		Object rows[][] = new Object[subjects.size()][1];
		for (int i = 0; i < subjects.size(); i++) {
			rows[i][0] = subjects.get(i).getName() + " (" + subjects.get(i).getAbbreviation() + ")";

		}

		JTable jTable = new JTable(rows, columns);
		jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO autoupdate jTable
				currentSubject = gui.getBackend().getSubjects().get(jTable.getSelectedRow()); // update jTable
				createMainTable();
			}
		});

		scrollSubjectTable = new JScrollPane(jTable);

		// update UI
		repaint();
		revalidate();

		return scrollSubjectTable;
	}

	private JScrollPane createMainTable() {

		// bad code too much overhead

		if (scrollMainTable != null) {
			// remove old scrollMainTable if it exists
			getContentPane().remove(scrollMainTable);
		}

		// create new subjectTable
		ArrayList<Examinee> examinees = gui.getBackend().getExaminee();

		String columns[] = { "Vorname", "Nachname" };

		Object rows[][] = new Object[examinees.size()][2];

		getStudentWithSubject(rows, examinees);

		scrollMainTable = new JScrollPane(new JTable(rows, columns));

		// update UI
		repaint();
		revalidate();

		return scrollMainTable;
	}

	private void getStudentWithSubject(Object rows[][], ArrayList<Examinee> examinees) {

		int numOfStudentsCounter = 0;

		for (int i = 0; i < examinees.size(); i++) {
			if (examinees.get(i).hasSubject(currentSubject)) {
				rows[numOfStudentsCounter][0] = examinees.get(i).getName();
				rows[numOfStudentsCounter][1] = "Nachname " + i;
				numOfStudentsCounter++;
			}

		}

	}

	private void createSouthButtons() {
		south = new JPanel();
		south.setLayout(new GridLayout(1, 2));

		btnAddStudent = new JButton("Student hinzufügen");
		btnAddStudent.addActionListener(this);
		south.add(btnAddStudent);

		btnRemoveStudent = new JButton("Student löschen");
		btnRemoveStudent.addActionListener(this);
		south.add(btnRemoveStudent);

	}

}
