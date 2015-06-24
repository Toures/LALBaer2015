package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Participant;

public class DesireGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton ok, cancel;
	private JComboBox<Integer> fromHoursCombo, fromMinutesCombo, toHoursCombo, toMinutesCombo, priorityCombo;
	private JFrame parent;
	private JTextField desireComment;
	private Participant participant;
	//	private JSpinner timeSpinner;
	//	private JComponent editor;

	Integer[] minutes = new Integer[12];
	Integer[] hours;
	Integer[] priority = {1, 2, 3};

	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			int from = (Integer)fromHoursCombo.getSelectedItem()*60+(int)fromMinutesCombo.getSelectedItem();
			int to = (Integer)toHoursCombo.getSelectedItem()*60+(int)toMinutesCombo.getSelectedItem();

			participant.addDesire(new Desire(from, to, desireComment.getText(),(Integer)priorityCombo.getSelectedItem()));

			this.setVisible(false);
			this.parent.setEnabled(true);
			this.parent.toFront();
			this.parent.repaint();
		} else if(e.getSource()==cancel){
			this.setVisible(false);
			this.parent.setEnabled(true);
			parent.toFront();
			parent.repaint();
		}
	}

	//--GUI
	public DesireGUI(JFrame parentFrame, Participant participant) {
		super("Wunsch eintragen");
		this.parent = parentFrame;
		this.participant = participant;

		int hourStart = Backend.TIME_BEGIN / 60;
		int hourEnd = Backend.TIME_END / 60;
		//hours-combo
		hours = new Integer[hourEnd-hourStart];
		for(int i = 0; i <  hours.length; i++ ){
			hours[i] = hourStart+i;
		}
		//minutes-combo
		for(int i = 0; i < 12; i++){
			minutes[i] = i*5;
		}		

		parent.setEnabled(false);		
		getContentPane().setLayout(new BorderLayout());

		createNorthpanel();
		createCenterpanel();
		createSouthpanel();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);

		addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent arg0){
				parent.setEnabled(true);
				parent.toFront();
				parent.repaint();
			}
		});		
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

		fromHoursCombo = new JComboBox<Integer>(hours);
		fromMinutesCombo = new JComboBox<Integer>(minutes);

		fromPanel.add(fromHoursCombo);
		fromPanel.add(fromMinutesCombo);

		JPanel toPanel = new JPanel();
		toPanel.setLayout(new GridLayout(1,2));

		toHoursCombo = new JComboBox<Integer>(hours);
		toMinutesCombo = new JComboBox<Integer>(minutes);

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