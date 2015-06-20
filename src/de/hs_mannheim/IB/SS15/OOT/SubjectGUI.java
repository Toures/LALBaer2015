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

public class SubjectGUI extends JFrame implements ActionListener {

	private GUI gui;

	private JScrollPane scrollSubjectTable;

	private JPanel south;

	private JButton btnAddSubject;
	private JButton btnRemoveSubject;

	public SubjectGUI(GUI gui) {
		this.gui = gui;

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
					gui.getBackend().createSubject(name, abbreviation);
					createTable();
				}
			}

		} else if (e.getSource() == btnRemoveSubject) {

			// dropdown Men� mit den m�glichen F�chern
			ArrayList<Subject> subjects = gui.getBackend().getSubjects();

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
				JOptionPane.showMessageDialog(this, "Es sind noch keine F�cher vorhanden.", "Fach entfernen", JOptionPane.ERROR_MESSAGE);
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

		if (scrollSubjectTable != null) {
			// remove old subjectTable
			getContentPane().remove(scrollSubjectTable);
		}

		// create new subjectTable
		ArrayList<Subject> subjects = gui.getBackend().getSubjects();

		String columns[] = { "Name", "Abk�rzung", "Anzahl der Studenten" };

		Object rows[][] = new Object[subjects.size()][3];
		for (int i = 0; i < subjects.size(); i++) {
			rows[i][0] = subjects.get(i).getName();
			rows[i][1] = subjects.get(i).getAbbreviation();
			rows[i][2] = subjects.get(i).getAmountOfExaminees();
		}

		scrollSubjectTable = new JScrollPane(new JTable(rows, columns));

		getContentPane().add(scrollSubjectTable, BorderLayout.CENTER);

		// update UI
		repaint();
		revalidate();
	}

	private void createSouthButtons() {
		south = new JPanel();
		south.setLayout(new GridLayout(1, 2));

		btnAddSubject = new JButton("Fach hinzuf�gen");
		btnAddSubject.addActionListener(this);
		south.add(btnAddSubject);

		btnRemoveSubject = new JButton("Fach l�schen");
		btnRemoveSubject.addActionListener(this);
		south.add(btnRemoveSubject);

	}

}
