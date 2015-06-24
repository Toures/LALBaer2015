package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;


public class ExaminerDeleteGUI extends JDialog implements ActionListener{
	
	private JLabel nameLabel;
	private JButton ok, cancel;
	private JComboBox examiner;
	
	private GUI mainGUI;
        private SubjectDataModel subjectDataModel;

	
	//--Actions here
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			if(mainGUI.getBackend().getExaminer().size() != 0){
				Examiner currentNode = (Examiner)examiner.getSelectedItem();
				mainGUI.getBackend().removeExaminer(currentNode);
				this.setVisible(false);
                                subjectDataModel.updateData();
			}
			this.setVisible(false);
		} else if(e.getSource()==cancel){
			this.setVisible(false);
		}
	}
	
	//--GUI
	public ExaminerDeleteGUI(GUI gui){
		this.mainGUI = gui;
		
		setTitle("Prüfer löschen");
		
		createLayout();

		pack();
		setLocationRelativeTo(gui);
		setVisible(true);
	}
        
        public ExaminerDeleteGUI(GUI gui, SubjectDataModel dataModel)
        {
            this(gui);
            this.subjectDataModel = dataModel;
        }
	
	private void createLayout(){
		
		getContentPane().setLayout(new BorderLayout());
		
		//north panel
		JPanel north = new JPanel();
		nameLabel = new JLabel("Bitte wählen Sie den zu löschenden Prüfer aus: ");
		north.add(nameLabel);
		
		//center panel
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());
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