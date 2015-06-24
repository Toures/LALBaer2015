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

public class BreakGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel nameLabel, lengthLabel, timeLabel;
	private JButton ok, cancel;
	private JComboBox<Integer> length, hoursCombo, minutesCombo;
	private JFrame parent;
	private Backend backend;
	//	private JSpinner timeSpinner;
	//	private JComponent editor;

	Integer[] minutes = new Integer[12];
	Integer[] lengthRange = new Integer[12];
	Integer[] hours;

	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			this.setVisible(false);
			this.parent.setEnabled(true);
			int time = 60*(int)hoursCombo.getSelectedItem()+(int)minutesCombo.getSelectedItem();
			int length = (int)this.length.getSelectedItem();
			backend.addBreak(time, length);
		} else if(e.getSource()==cancel){
			this.setVisible(false);
			this.parent.setEnabled(true);
		}
	}

	//--GUI
	public BreakGUI(JFrame gui, Backend backend) {
		super("Pause einrichten");
		this.parent = gui;
		this.backend = backend;

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
			lengthRange[i] = (i*5)+5;
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
			}
		});
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

		hoursCombo = new JComboBox<Integer>(hours);
		minutesCombo = new JComboBox<Integer>(minutes);

		timePanel.add(hoursCombo);
		timePanel.add(minutesCombo);

		length = new JComboBox<Integer>(lengthRange);

		center.add(timeLabel);
		center.add(timePanel);
		//center.add(timeSpinner);
		center.add(lengthLabel);
		center.add(length);
		getContentPane().add(center, BorderLayout.CENTER);
	}

}