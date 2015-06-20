package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame implements ActionListener {

	private final static String TITLE = "IM-Planer";
	private final static int NUM_OF_PLANS = 3;

	private Schedule[] schedule;
	private DataModel[] tableData;

	private Backend backend;

	// Men�leiste
	private JMenuBar jMenuBar;

	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;

	private JMenu info;
	private JMenuItem about;

	private JTabbedPane tabTable;
	private JTable tableMaster;
	private JTable tableProfessor;
	private JTable tableStudent;

	private JPanel east;

	private JButton btnRooms;
	private JButton btnStudents;
	private JButton btnSubjects;
	private JButton btnAddBreak;

	GUI() {
		setTitle(TITLE); // set title

		createJMenuBar();
		createLayout();

		// -------------------------------
		// Options
		// -------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();

		setLocationRelativeTo(null);

		// temp
		backend = createNewMainController();

	}

	public static void main(String[] args) {

		lookAndFeel();
		new GUI().setVisible(true); // create GUI

	}

	private static void lookAndFeel() {
		// set Look and Feel (Windows)
		UIManager.LookAndFeelInfo[] lfi = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < lfi.length; i++) {

			// Set Windows or OSX Look & Feel
			if (lfi[i].getName() == "Windows") {
				try {
					UIManager.setLookAndFeel(lfi[i].getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// JMenuBar
		if (e.getSource() == newFile) {
			System.out.println("Neue Datei");
		} else if (e.getSource() == open) {
			System.out.println("Datei �ffnen");
		} else if (e.getSource() == save) {
			System.out.println("Datei speichern");
		} else if (e.getSource() == exit) {
			System.out.println("Programm beenden");
			System.exit(0);
		} else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "LALBaer2015");
		}

		// EastButtons
		else if (e.getSource() == btnRooms) {
			System.out.println("btnRooms");
		} else if (e.getSource() == btnStudents) {
			new StudentsGUI(this);
		} else if (e.getSource() == btnSubjects) {
			new SubjectGUI(this);
		} else if (e.getSource() == btnAddBreak) {
			System.out.println("btnAddBreak");
		}

	}

	public Schedule createNewSchedule(String name) {
		return new Schedule(name);

	}

	public Backend createNewMainController() {
		return new Backend(schedule);

	}

	private void createJMenuBar() {
		jMenuBar = new JMenuBar();

		file = new JMenu("Datei");

		newFile = new JMenuItem("Neu");
		newFile.addActionListener(this);
		open = new JMenuItem("�ffnen");
		open.addActionListener(this);
		save = new JMenuItem("Speichern");
		save.addActionListener(this);
		exit = new JMenuItem("Beenden");
		exit.addActionListener(this);

		info = new JMenu("Info");

		about = new JMenuItem("�ber");
		about.addActionListener(this);

		file.add(newFile);
		file.add(open);
		file.add(save);
		file.add(exit);

		info.add(about);

		// add submenus to Bar
		jMenuBar.add(file);
		jMenuBar.add(info);
	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// NORTH Panel
		getContentPane().add(jMenuBar, BorderLayout.NORTH);

		// CENTER Panel
		createTabTable();
		getContentPane().add(tabTable, BorderLayout.CENTER);

		// EAST Panel
		createEastButtons();
		getContentPane().add(east, BorderLayout.EAST);
	}

	private void createTabTable() {
		tabTable = new JTabbedPane();

		createTable();

		JPanel master = new JPanel(new GridLayout());
		master.add(new JScrollPane(tableMaster));
		tabTable.addTab("Master", master);

		JPanel professor = new JPanel(new GridLayout());
		professor.add(new JScrollPane(tableProfessor));
		tabTable.addTab("Professor", professor);

		JPanel student = new JPanel(new GridLayout());
		student.add(new JScrollPane(tableStudent));
		tabTable.addTab("Student", student);

	}

	private void createTable() {

		// init Arrays
		schedule = new Schedule[NUM_OF_PLANS];
		tableData = new DataModel[NUM_OF_PLANS];

		// create Schedulers and Tables
		for (int i = 0; i < schedule.length; i++) {
			schedule[i] = new Schedule("Schedule " + i);
			tableData[i] = new DataModel(97, 5);
		}

		// create JTables from DataModels
		tableMaster = new JTable(tableData[0]);
		tableProfessor = new JTable(tableData[1]);
		tableStudent = new JTable(tableData[2]);

	}

	private void createEastButtons() {

		east = new JPanel();
		east.setLayout(new GridLayout(4, 1));

		btnRooms = new JButton("R�ume");
		btnRooms.addActionListener(this);
		east.add(btnRooms);
		btnStudents = new JButton("Studenten");
		btnStudents.addActionListener(this);
		east.add(btnStudents);
		btnSubjects = new JButton("F�cher");
		btnSubjects.addActionListener(this);
		east.add(btnSubjects);
		btnAddBreak = new JButton("Pause hinzuf�gen");
		btnAddBreak.addActionListener(this);
		east.add(btnAddBreak);

	}

	public Backend getBackend() {
		return backend;
	}

}
