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
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class SubjectGUI extends JFrame implements ActionListener {

	// Swing
	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddSubject;
	private JButton btnRemoveSubject;

	//
	private GUI mainGUI;
	private SubjectDataModel dataModel;
	private JTable jTable;

	public SubjectGUI(GUI gui) {
		this.mainGUI = gui;

		setTitle("Fächer");

		createLayout();

		pack();
		// setSize(400, 200);
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddSubject) {

			String name = JOptionPane.showInputDialog(this, "Name des Fachs:", "Fach hinzufügen", JOptionPane.PLAIN_MESSAGE);
			if (name != null) {
				String abbreviation = JOptionPane.showInputDialog(this, "Kürzel des Fachs:", "Fach hinzufügen", JOptionPane.PLAIN_MESSAGE);
				if (abbreviation != null) {
					mainGUI.getBackend().createSubject(name, abbreviation);
					dataModel.updateData();
				}
			}

		} else if (e.getSource() == btnRemoveSubject) {

			// dropdown Menü mit den möglichen Fächern
			ArrayList<Subject> subjects = mainGUI.getBackend().getSubjects();

			if (subjects.size() > 0) {
				Subject selectedSubject = (Subject) JOptionPane.showInputDialog(this, "Name des Fachs:", "Fach entfernen", JOptionPane.QUESTION_MESSAGE, null, subjects.toArray(), subjects.get(0));

				if (selectedSubject != null) {
					for (int i = 0; i < subjects.size(); i++) {
						if (subjects.get(i).equals(selectedSubject)) {
							subjects.remove(i);
							createTable();
							return;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Es sind noch keine Fächer vorhanden.", "Fach entfernen", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// CENTER Panel
		createTable();

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	private void createTable() {

		dataModel = new SubjectDataModel(mainGUI);
		jTable = new JTable(dataModel);

		scrollSubjectTable = new JScrollPane(jTable);

		getContentPane().add(scrollSubjectTable, BorderLayout.CENTER);
	}

	private void createSouthButtons() {
		south = new JPanel();
		south.setLayout(new GridLayout(1, 2));

		btnAddSubject = new JButton("Fach hinzufügen");
		btnAddSubject.addActionListener(this);
		south.add(btnAddSubject);

		btnRemoveSubject = new JButton("Fach löschen");
		btnRemoveSubject.addActionListener(this);
		south.add(btnRemoveSubject);

	}

}

class SubjectDataModel extends AbstractTableModel {

	private GUI mainGUI;

	private ArrayList<Subject> subjects;

	private final int COLUMS = 3;

	SubjectDataModel(GUI mainGUI) {
		this.mainGUI = mainGUI;

		updateData();
	}

	public void updateData() {

		subjects = mainGUI.getBackend().getSubjects();

		fireTableDataChanged(); // Notifies all listeners that all cell values in the table's rows may have changed.

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
		if (col == 0) {
			return "Name";
		} else if (col == 1) {
			return "Abkürzung";
		} else {
			return "Anzahl der Studenten";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return subjects.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			return subjects.get(rowIndex).getAbbreviation();
		} else {
			return subjects.get(rowIndex).getAmountOfExaminees();
		}

	}

	public void setValueAt(Object value, int row, int col) {

		if (col == 0) {
			subjects.get(row).setName(value.toString());
		} else if (col == 1) {
			subjects.get(row).setAbbreviation(value.toString());
		}

		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {

		if (col == 0 || col == 1) {
			return true;
		}

		return false;
	}

}