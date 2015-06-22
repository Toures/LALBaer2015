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
import javax.swing.table.AbstractTableModel;

import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;

public class StudentsGUI extends JFrame implements ActionListener {

	private GUI gui;

	private JScrollPane scrollMainTable;
	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddStudent;
	private JButton btnRemoveStudent;

	public static Subject currentSubject;
	private SelectSubjectTableModel selectSubjectTableModel;
	private JTable selectSubjectTable;
	private MainTableModel mainTableModel;
	private JTable mainJTable;

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
				mainTableModel.updateData(); // update jTable
			}
		}

	}

	private void removeStudentDialog() {
		// dropdown Menü mit den möglichen Fächern
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		if (subjects.size() > 0) {
			Subject selectedSubject = (Subject) JOptionPane.showInputDialog(this, "Vorname des Studenten:", "Studenten entfernen", JOptionPane.QUESTION_MESSAGE, null, subjects.toArray(), subjects.get(0));

			if (selectedSubject != null) {

				// TODO removeSubject aus Backend updaten
				// gui.getBackend().removeSubject(selectedSubject);

				for (int i = 0; i < subjects.size(); i++) {
					if (subjects.get(i).equals(selectedSubject)) {
						subjects.remove(i);
						mainTableModel.updateData();
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
		createSubjectTable();
		createMainTable();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(selectSubjectTable), new JScrollPane(mainJTable));
		splitPane.setDividerLocation(150);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	private void createSubjectTable() {

		mainTableModel = new MainTableModel(gui);
		mainJTable = new JTable(mainTableModel);

		mainJTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			// TODO not fired?!?!
			@Override
			public void valueChanged(ListSelectionEvent e) {

				System.out.println("Selected Row = " + mainJTable.getSelectedRow());

				currentSubject = gui.getBackend().getSubjects().get(mainJTable.getSelectedRow()); // update jTable
				mainTableModel.updateData();

			}
		});
	}

	private void createMainTable() {

		selectSubjectTableModel = new SelectSubjectTableModel(gui);
		selectSubjectTable = new JTable(selectSubjectTableModel);

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

class SelectSubjectTableModel extends AbstractTableModel {

	private GUI mainGUI;

	private ArrayList<Subject> subjects;

	private final int COLUMS = 1;

	SelectSubjectTableModel(GUI mainGUI) {
		this.mainGUI = mainGUI;

		subjects = mainGUI.getBackend().getSubjects();
		if (subjects != null && subjects.size() > 0 && subjects.get(0) != null) {
			StudentsGUI.currentSubject = subjects.get(0);
		}

	}

	@Override
	public int getRowCount() {
		return subjects.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMS;
	}

	@Override
	public String getColumnName(int col) {
		return "Fächer";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return subjects.get(rowIndex).getName() + " (" + subjects.get(rowIndex).getAbbreviation() + ")";

	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}

class MainTableModel extends AbstractTableModel {

	private GUI mainGUI;

	private ArrayList<Examinee> examinee;

	private final int COLUMS = 2;

	MainTableModel(GUI mainGUI) {
		this.mainGUI = mainGUI;

		examinee = new ArrayList<Examinee>();

		updateData();

	}

	public void updateData() {

		examinee.clear();

		fireTableDataChanged();

		for (int i = 0; i < mainGUI.getBackend().getExaminee().size(); i++) {
			if (mainGUI.getBackend().getExaminee().get(i) != null && mainGUI.getBackend().getExaminee().get(i).hasSubject(StudentsGUI.currentSubject)) {
				examinee.add(mainGUI.getBackend().getExaminee().get(i));
			}
		}

		fireTableDataChanged(); // Notifies all listeners that all cell values in the table's rows may have changed.

	}

	@Override
	public int getRowCount() {
		return examinee.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMS;
	}

	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "Vorname";
		} else if (col == 1) {
			return "Nachname";
		}

		return null;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return examinee.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			return examinee.get(rowIndex).getName();
		}

		return null;

	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
