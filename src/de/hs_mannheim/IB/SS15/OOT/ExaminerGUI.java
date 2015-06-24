package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class ExaminerGUI extends JFrame implements ActionListener {

	// Swing
	private JScrollPane scrollSubjectTable;

	private JPanel south;
	private JPanel center;

	private JButton btnAddExaminer;
	private JButton btnAddNewExaminer;

	private JComboBox subjectList;
	private JComboBox examinerList;
	private JTextField name;

	//
	private GUI mainGUI;

	public ExaminerGUI(GUI gui) {
		this.mainGUI = gui;

		setTitle("Prüfer");

		createLayout();

		pack();
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddExaminer) {

			if (mainGUI.getBackend().getSubjects().size() > 0 && mainGUI.getBackend().getExaminee().size() > 0) {
				Subject selectedSubject = (Subject) subjectList.getSelectedItem();
				Examiner selectedExaminer = (Examiner) examinerList.getSelectedItem();

				selectedExaminer.addSubject(selectedSubject);
			} else {
				JOptionPane.showMessageDialog(this, "Einer der beiden Werte ist ungültig.", "Prüfer", JOptionPane.ERROR_MESSAGE);

			}

		} else if (e.getSource() == btnAddNewExaminer) {

			if (mainGUI.getBackend().getSubjects().size() > 0 && name != null && name.getText() != null && !name.getText().equals("")) {

				Subject selectedSubject = (Subject) subjectList.getSelectedItem();
				String examinerName = name.getText();

				ArrayList<Subject> selectedSubjectList = new ArrayList<Subject>();
				selectedSubjectList.add(selectedSubject);

				mainGUI.getBackend().createExaminer(examinerName, selectedSubjectList, new ArrayList<Desire>());
			} else {
				JOptionPane.showMessageDialog(this, "Einer der beiden Werte ist ungültig.", "Prüfer", JOptionPane.ERROR_MESSAGE);

			}
		}

	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// CENTER Panel
		createSelectSubject();

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	private void createSelectSubject() {

		center = new JPanel();
		center.setLayout(new GridLayout(3, 1));

		JPanel layout1 = new JPanel();
		layout1.setLayout(new GridLayout(1, 3));

		Label subjects = new Label("Fach :");
		layout1.add(subjects);
		subjectList = new JComboBox(mainGUI.getBackend().getSubjects().toArray());
		layout1.add(subjectList);

		center.add(layout1);

		// 2

		JPanel layout2 = new JPanel();
		layout2.setLayout(new GridLayout(1, 3));
		Label pruefer = new Label("Prüfer :");
		layout2.add(pruefer);
		JComboBox examinerList = new JComboBox(mainGUI.getBackend().getExaminer().toArray());
		layout2.add(examinerList);

		btnAddExaminer = new JButton("hinzufügen");
		btnAddExaminer.addActionListener(this);
		layout2.add(btnAddExaminer);

		center.add(layout2);

		// 3

		JPanel layout3 = new JPanel();
		layout3.setLayout(new GridLayout(1, 3));
		Label pruefer2 = new Label("Neuer Prüfer :");
		layout3.add(pruefer2);
		name = new JTextField();
		layout3.add(name);

		btnAddNewExaminer = new JButton("neuer Prüfer hinzufügen");
		btnAddNewExaminer.addActionListener(this);
		layout3.add(btnAddNewExaminer);

		center.add(layout3);

		getContentPane().add(center, BorderLayout.CENTER);
	}

	private void createSouthButtons() {
		south = new JPanel();
		south.setLayout(new GridLayout(1, 4));

	}

}