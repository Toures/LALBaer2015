package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BreakGUI extends JFrame implements ActionListener {

	private JLabel nameLabel, lengthLabel, timeLabel;
	private JButton ok, cancel;
	private JComboBox length, hoursCombo, minutesCombo;
	private JFrame parent;
	//	private JSpinner timeSpinner;
	//	private JComponent editor;

	String[] minutes = { "00","05","10","15","20","25","30","35","40","45","50","55" };
	String[] hours = { "08","09","10","11","12","13","14","15","16" };
	String[] lengthStrings = { "5","10","15","20","25","30","35","40","45","50","55","60" };

	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			//TODO create break
			this.setVisible(false);
			this.parent.setEnabled(true);
		} else if(e.getSource()==cancel){
			this.setVisible(false);
			this.parent.setEnabled(true);
		}
	}

	//--GUI
	public BreakGUI(JFrame gui) {
		super("Pause einrichten");
		this.parent = gui;
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
		nameLabel = new JLabel("Bitte geben Sie die Uhrzeit und die Länge der Pause an");
		north.add(nameLabel);
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
		center.setLayout(new GridLayout(2,2));
		timeLabel = new JLabel("Uhrzeit:");
		lengthLabel = new JLabel("Dauer in Minuten:");

		//timeSpinner for date display -> using combo boxes now
		//SpinnerModel model = new SpinnerDateModel();
		//timeSpinner = new JSpinner(model);
		//editor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		//timeSpinner.setEditor(editor);

		JPanel timePanel = new JPanel();
		timePanel.setLayout(new GridLayout(1,2));

		hoursCombo = new JComboBox(hours);
		minutesCombo = new JComboBox(minutes);

		timePanel.add(hoursCombo);
		timePanel.add(minutesCombo);

		length = new JComboBox(lengthStrings);

		center.add(timeLabel);
		center.add(timePanel);
		//center.add(timeSpinner);
		center.add(lengthLabel);
		center.add(length);
		getContentPane().add(center, BorderLayout.CENTER);
	}

}