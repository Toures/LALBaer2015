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

public class SubjectGUI extends JFrame implements ActionListener {

	private GUI gui;

	private JPanel south;

	private JButton btnAddSubject;
	private JButton btnRemoveSubject;

	public SubjectGUI(GUI gui) {
		this.gui = gui;

		setTitle("Fächer");

		createLayout();

		pack();
		setSize(400, 200);
		setLocationRelativeTo(gui);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAddSubject) {

			String name = JOptionPane.showInputDialog(null, "Name des Fachs:", "Fach hinzufügen", JOptionPane.PLAIN_MESSAGE);
			if (name != null) {
				String abbreviation = JOptionPane.showInputDialog(null, "Kürzel des Fachs:", "Fach hinzufügen", JOptionPane.PLAIN_MESSAGE);
				if (abbreviation != null) {
					gui.getBackend().createSubject(name, abbreviation);
				}
			}

		} else if (e.getSource() == btnRemoveSubject) {

			String name = JOptionPane.showInputDialog(null, "Name des Fachs:", "Fach entfernen", JOptionPane.PLAIN_MESSAGE);

			ArrayList<Subject> subjects = gui.getBackend().getSubjects();

			for (int i = 0; i < subjects.size(); i++) {
				if (subjects.get(i).getName().equals(name)) {
					subjects.remove(i);
					return;
				}
			}
		}

	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// TODO CENTER (Liste der aktuellen Fächer)

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);
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
