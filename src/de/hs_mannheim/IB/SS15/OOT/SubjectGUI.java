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

import de.hs_mannheim.IB.SS15.OOT.ExaminerDeleteGUI;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class SubjectGUI extends JFrame implements ActionListener {

	// Swing
	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddSubject;
	private JButton btnRemoveSubject;
	private JButton btnAddExaminer;
	private JButton btnRemoveExaminer;

	//
	private GUI mainGUI;
	private SubjectDataModel dataModel;
	private JTable jTable;

	public SubjectGUI(GUI gui) {
		this.mainGUI = gui;

		setTitle("F�cher");

		createLayout();

		pack();
		// setSize(400, 200);
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddSubject) {

			String name = JOptionPane.showInputDialog(this, "Name des Fachs:", "Fach hinzuf�gen", JOptionPane.PLAIN_MESSAGE);
			if (name != null) {
				String abbreviation = JOptionPane.showInputDialog(this, "K�rzel des Fachs:", "Fach hinzuf�gen", JOptionPane.PLAIN_MESSAGE);
				if (abbreviation != null) {
					try {
						mainGUI.getBackend().createSubject(name, abbreviation);
						dataModel.updateData();
					} catch (Exception error) {
						JOptionPane.showMessageDialog(this, error.getMessage(), "Fach hinzuf�gen", JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		} else if (e.getSource() == btnRemoveSubject) {

			// dropdown Men� mit den m�glichen F�chern
			ArrayList<Subject> subjects = mainGUI.getBackend().getSubjects();

			if (subjects.size() > 0) {
				Subject selectedSubject = (Subject) JOptionPane.showInputDialog(this, "Name des Fachs:", "Fach entfernen", JOptionPane.QUESTION_MESSAGE, null, subjects.toArray(), subjects.get(0));

				if (selectedSubject != null) {
					for (int i = 0; i < subjects.size(); i++) {
						if (subjects.get(i).equals(selectedSubject)) {
							subjects.remove(i);
							dataModel.updateData();
							return;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Es sind noch keine F�cher vorhanden.", "Fach entfernen", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == btnAddExaminer) {
			new ExaminerGUI(mainGUI, dataModel);
		} else if (e.getSource() == btnRemoveExaminer) {
			new ExaminerDeleteGUI(mainGUI);
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
		south.setLayout(new GridLayout(1, 4));

		btnAddSubject = new JButton("Fach hinzuf�gen");
		btnAddSubject.addActionListener(this);
		south.add(btnAddSubject);

		btnRemoveSubject = new JButton("Fach l�schen");
		btnRemoveSubject.addActionListener(this);
		south.add(btnRemoveSubject);
		
		btnAddExaminer = new JButton("Pr�fer/Beisitzer hinzuf�gen");
		btnAddExaminer.addActionListener(this);
		south.add(btnAddExaminer);
		
		btnRemoveExaminer = new JButton("Pr�fer/Beisitzer l�schen");
		btnRemoveExaminer.addActionListener(this);
		south.add(btnRemoveExaminer);

	}

}

class SubjectDataModel extends AbstractTableModel {

	private GUI mainGUI;

	private ArrayList<Subject> subjects;

	private final int COLUMS = 4;

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
			return "Abk�rzung";
		} else if (col == 2) {
			return "Anzahl der Studenten";
		} else {
			return "Pr�fer";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return subjects.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			return subjects.get(rowIndex).getAbbreviation();
		} else if (columnIndex == 2) {
			return subjects.get(rowIndex).getAmountOfExaminees();
		} else {
                    
                    Subject subject = subjects.get(rowIndex);
                    ArrayList<Examiner> examiners = mainGUI.getBackend().getExaminer();
                    
                    String strExaminers = "";
                    for (Examiner examiner : examiners)
                        if (examiner.hasSubject(subject))
                            strExaminers += (!"".equals(strExaminers)?", ":"") + examiner;
                    return strExaminers;
		}

	}

	@Override
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
