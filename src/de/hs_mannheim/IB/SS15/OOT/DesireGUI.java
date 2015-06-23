package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Participant;

public class DesireGUI extends JFrame implements ActionListener {

	private JButton ok, cancel;
	private JComboBox<String> fromHoursCombo, fromMinutesCombo, toHoursCombo, toMinutesCombo;
	private JComboBox<Integer> priorityCombo;
	private JFrame parent;
	private JTextField desireComment;
	private Participant participant;
	//	private JSpinner timeSpinner;
	//	private JComponent editor;

	Integer[] minutes = { 0, 5,10,15,20,25,30,35,40,45,50,55};
	Integer[] hours = {7, 8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
	Integer[] lengthStrings = { "5","10","15","20","25","30","35","40","45","50","55","60" };
	Integer[] priority = {1, 2, 3};

	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			int fromHour = Integer.parseInt((String)fromHoursCombo.getSelectedItem());

			int toHour = Integer.parseInt((String)toHoursCombo.getSelectedItem());
			int toMinute = Integer.parseInt((String)toHoursCombo.getSelectedItem());
			String comment = desireComment.getText();
			participant.addDesire(new Desire(fromHour, fromMinute, toHour, toMinute, 
					comment,(Integer)priorityCombo.getSelectedItem()));
			//TODO check if desire appears in backend
			this.setVisible(false);
			this.parent.setEnabled(true);
		} else if(e.getSource()==cancel){
			this.setVisible(false);
			this.parent.setEnabled(true);
		}
	}
	
	//--GUI
	public DesireGUI(JFrame gui, Participant participant) {
		super("Wunsch eintragen");
		this.parent = gui;
		this.participant = participant;
		parent.setEnabled(false);		
		
		getContentPane().setLayout(new BorderLayout());

		createNorthpanel();
		createCenterpanel();
		createSouthpanel();
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void createNorthpanel(){
		//north panel
		JPanel north = new JPanel();
		JLabel instructionLabel = new JLabel("Bitte geben Sie die Uhrzeit und die Länge der Pause an");
		north.add(instructionLabel);
		getContentPane().add(north, BorderLayout.NORTH);
	}

	private void createSouthpanel(){
		//south panel
		JPanel south = new JPanel();
		ok = new JButton("Ok");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		south.add(ok);
		south.add(cancel);
		getContentPane().add(south, BorderLayout.SOUTH);
	}
	
	private void createCenterpanel(){
		//center panel
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(4,2));
		JLabel fromLabel = new JLabel("Von:");
		JLabel toLabel = new JLabel("Bis");

		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(new GridLayout(1,2));

		fromHoursCombo = new JComboBox<String>(hours);
		fromMinutesCombo = new JComboBox<String>(minutes);

		fromPanel.add(fromHoursCombo);
		fromPanel.add(fromMinutesCombo);
		
		JPanel toPanel = new JPanel();
		toPanel.setLayout(new GridLayout(1,2));
		
		toHoursCombo = new JComboBox<String>(hours);
		toMinutesCombo = new JComboBox<String>(minutes);

		toPanel.add(toHoursCombo);
		toPanel.add(toMinutesCombo);
		
		priorityCombo = new JComboBox<Integer>(priority);
		desireComment = new JTextField();
		
		center.add(fromLabel);
		center.add(fromPanel);
		//center.add(timeSpinner);
		center.add(toLabel);
		center.add(toPanel);
		center.add(new JLabel("Kommentar:"));
		center.add(desireComment);
		center.add(new JLabel("Priorität"));
		center.add(priorityCombo);
		getContentPane().add(center, BorderLayout.CENTER);
	}

}