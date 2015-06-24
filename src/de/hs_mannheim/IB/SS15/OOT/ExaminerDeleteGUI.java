package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;


public class ExaminerDeleteGUI extends JDialog implements ActionListener{
	
	private JLabel nameLabel, examLabel, examinerLabel, assessorLabel;
	private JButton ok, cancel;
	private JComboBox examiner, exam;
	private JRadioButton rbExaminer, rbAssessor;
	
	private GUI mainGUI;

	
	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			//TODO
			if(mainGUI.getBackend().getExaminer().size() != 0){
				Examiner currentNode = (Examiner)examiner.getSelectedItem();
				mainGUI.getBackend().removeExaminer(currentNode);
				this.setVisible(false);
			}
			this.setVisible(false);
		} else if(e.getSource()==cancel){
			this.setVisible(false);
		}
	}
	
	//--GUI
	public ExaminerDeleteGUI(GUI gui){
		this.mainGUI = gui;
		
		setTitle("Prüfer/Beisitzer löschen");
		
		createLayout();

		pack();
		setLocationRelativeTo(gui);
		setVisible(true);
	}
	
	private void createLayout(){
		
		getContentPane().setLayout(new BorderLayout());
		
		//north panel
		JPanel north = new JPanel();
		
		north.setLayout(new GridLayout(1,4));
		examinerLabel = new JLabel("Prüfer:");
		north.add(examinerLabel);
		rbExaminer = new JRadioButton();
		north.add(rbExaminer);
		assessorLabel = new JLabel("Beisitzer:");
		north.add(assessorLabel);
		rbAssessor = new JRadioButton();
		north.add(rbAssessor);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rbExaminer);
	    group.add(rbAssessor);

		//center panel
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(4,1));
		
		examLabel = new JLabel("Bitte wählen Sie das gewünschte Fach aus:");
		center.add(examLabel);
		exam = new JComboBox(mainGUI.getBackend().getSubjects().toArray());
		center.add(exam);
		
		nameLabel = new JLabel("Bitte wählen Sie den zu löschenden Prüfer/Beisitzer aus: ");
		center.add(nameLabel);
		examiner = new JComboBox(mainGUI.getBackend().getExaminer().toArray());
		center.add(examiner);
		
		//south panel
		JPanel south = new JPanel();
		ok = new JButton("Ok");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		south.add(ok);
		south.add(cancel);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}
}