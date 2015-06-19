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

public class StudentsGUI extends JFrame implements ActionListener {

	private GUI gui;

	private JScrollPane scrollMainTable;
	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddStudent;
	private JButton btnRemoveStudent;

	public StudentsGUI(GUI gui) {
		this.gui = gui;

		setTitle("Studenten");

		createLayout();

		pack();
		setSize(400, 200);
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddStudent) {

			String name = JOptionPane.showInputDialog(this, "Vorname des Studenten:", "Student hinzufügen", JOptionPane.PLAIN_MESSAGE);
			if (name != null) {
				String abbreviation = JOptionPane.showInputDialog(this, "Nachname des Studenten:", "Student hinzufügen", JOptionPane.PLAIN_MESSAGE);
				if (abbreviation != null) {
					gui.getBackend().createSubject(name, abbreviation);
					createMainTable();
				}
			}

		} else if (e.getSource() == btnRemoveStudent) {

			// dropdown Menü mit den möglichen Fächern
			ArrayList<Subject> subjects = gui.getBackend().getSubjects();

			if (subjects.size() > 0) {
				Subject selectedSubject = (Subject) JOptionPane.showInputDialog(this, "Vorname des Studenten:", "Studenten entfernen", JOptionPane.QUESTION_MESSAGE, null, subjects.toArray(), subjects.get(0));

				if (selectedSubject != null) {
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

	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());
		
		// WEST panel
//		createSubjectTable();
		
		// CENTER Panel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createSubjectTable(), createMainTable());
		getContentPane().add(splitPane, BorderLayout.CENTER);
		//		createMainTable();

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
	}
	
	private JScrollPane createSubjectTable() {
		if (scrollSubjectTable != null) {
			// remove old scrollSubjectTable
			getContentPane().remove(scrollSubjectTable);
		}

		// create new subjectTable
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		String columns[] = { "Fächer" };

		Object rows[][] = new Object[subjects.size()][1];
		for (int i = 0; i < subjects.size(); i++) {
			rows[i][0] = subjects.get(i).getName() + " (" + subjects.get(i).getAbbreviation() + ")";

		}

		scrollSubjectTable = new JScrollPane(new JTable(rows, columns));

//		getContentPane().add(scrollSubjectTable, BorderLayout.WEST);

		// update UI
		repaint();
		revalidate();
		
		return scrollSubjectTable;
	}

	private JScrollPane createMainTable() {
		 
		if (scrollMainTable != null) {
			// remove old scrollMainTable
			getContentPane().remove(scrollMainTable);
		}

		// create new subjectTable
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		String columns[] = { "Vorname", "Nachname" };

		Object rows[][] = new Object[subjects.size()][2];
		for (int i = 0; i < subjects.size(); i++) {
			rows[i][0] = "Vorname " + i;
			rows[i][1] = "Nachname " + i;
		}

		scrollMainTable = new JScrollPane(new JTable(rows, columns));

//		getContentPane().add(scrollMainTable, BorderLayout.CENTER);
		
		// update UI
		repaint();
		revalidate();
		
		return scrollMainTable;
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
