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

//import sun.nio.ch.SelChImpl;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;

public class StudentsGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUI gui;

	private JPanel south;

	private JButton btnAddStudent;
	private JButton btnRemoveStudent;
	private JButton btnAddDesire;

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
		} else if (e.getSource() == btnAddDesire) {
			addDesireToExaminee();
		}

	}

	private void addStudentDialog() {
		String name = JOptionPane.showInputDialog(this, "Name des Studenten:", "Student hinzuf�gen", JOptionPane.PLAIN_MESSAGE);
		if (name != null) {

			try {

				boolean foundStudent = false;
				if(gui.getBackend().getExaminee().size() != 0){
					for(int i = 0; i < gui.getBackend().getExaminee().size(); i++){
						if(gui.getBackend().getExaminee().get(i).equals(name)){
							foundStudent = true;
							gui.getBackend().getExaminee().get(i).addSubject(currentSubject);
						}
					}
				} else{
					if(!foundStudent){
						ArrayList<Subject> tempSub = new ArrayList<Subject>();
						tempSub.add(currentSubject);
						gui.getBackend().createExaminee(name, tempSub, new ArrayList<Desire>());
					}}
				mainTableModel.updateData(); // update jTable
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Student hinzuf�gen", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void removeStudentDialog() {
		// dropdown Men� mit den m�glichen F�chern
		ArrayList<Examinee> examinee = gui.getBackend().getExaminee();

		if (examinee.size() > 0) {
			Examinee selectedExaminee = (Examinee) JOptionPane.showInputDialog(this, "Name des Studenten:", "Studenten entfernen", JOptionPane.QUESTION_MESSAGE, null, examinee.toArray(), examinee.get(0));

			if (selectedExaminee != null) {
				gui.getBackend().removeExaminee(selectedExaminee);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Es sind noch keine Studenten vorhanden.", "Studenten entfernen", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addDesireToExaminee() {
		ArrayList<Examinee> examinee = gui.getBackend().getExaminee();

		if (examinee.size() > 0 && mainJTable.getSelectedRow() >= 0) {
			new DesireGUI(this, examinee.get(mainJTable.getSelectedRow()));
			mainTableModel.updateData(); // update jTable
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

	}

	private void createMainTable() {
		selectSubjectTableModel = new SelectSubjectTableModel(gui);
		selectSubjectTable = new JTable(selectSubjectTableModel);

		selectSubjectTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				currentSubject = gui.getBackend().getSubjects().get(selectSubjectTable.getSelectedRow()); // update jTable
				mainTableModel.updateData();
			}
		});

	}

	private void createSouthButtons() {
		south = new JPanel();
		south.setLayout(new GridLayout(1, 3));

		btnAddStudent = new JButton("Student hinzuf�gen");
		btnAddStudent.addActionListener(this);
		south.add(btnAddStudent);

		btnRemoveStudent = new JButton("Student l�schen");
		btnRemoveStudent.addActionListener(this);
		south.add(btnRemoveStudent);

		btnAddDesire = new JButton("Wunsch hinzuf�gen");
		btnAddDesire.addActionListener(this);
		south.add(btnAddDesire);

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
		return "F�cher";
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
			return "Name";
		} else if (col == 1) {
			return "W�nsche";
		}

		return null;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return examinee.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			return examinee.get(rowIndex).getDesires();
		}

		return null;

	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (col == 0) {
			examinee.get(row).setName(value.toString());
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		}

		return false;
	}
}