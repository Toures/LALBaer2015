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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame implements ActionListener {

	private final static String TITLE = "IM-Planer";

	private static DataModel tableDataMaster;
	private static DataModel tableDataProfessor;
	private static DataModel tableDataStudent;

	// Menüleiste
	private JMenuBar jMenuBar;

	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem exit;

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

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		pack();
		setSize(800, 400);

		setLocationRelativeTo(null);

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

		if (e.getSource() == newFile) {
			System.out.println("Neue Datei");
		} else if (e.getSource() == open) {
			System.out.println("Datei öffnen");
		} else if (e.getSource() == save) {
			System.out.println("Datei speichern");
		} else if (e.getSource() == exit) {
			
		}

	}

	private void createJMenuBar() {
		jMenuBar = new JMenuBar();

		file = new JMenu("Datei");

		newFile = new JMenuItem("Neu");
		newFile.addActionListener(this);
		open = new JMenuItem("Öffnen");
		open.addActionListener(this);
		save = new JMenuItem("Speichern");
		save.addActionListener(this);
		exit = new JMenuItem("Beenden");
		exit.addActionListener(this);

		file.add(newFile);
		file.add(open);
		file.add(save);
		file.add(exit);

		jMenuBar.add(file);
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

		JPanel master = new JPanel();
		master.add(new JScrollPane(tableMaster));
		tabTable.addTab("Master", master);

		JPanel professor = new JPanel();
		professor.add(new JScrollPane(tableProfessor));
		tabTable.addTab("Professor", professor);

		JPanel student = new JPanel();
		student.add(new JScrollPane(tableStudent));
		tabTable.addTab("Student", student);

	}

	private void createTable() {

		tableDataMaster = new DataModel();
		tableDataProfessor = new DataModel();
		tableDataStudent = new DataModel();

		tableMaster = new JTable(tableDataMaster);
		tableProfessor = new JTable(tableDataProfessor);
		tableStudent = new JTable(tableDataStudent);

	}

	private void createEastButtons() {

		east = new JPanel();
		east.setLayout(new GridLayout(4, 1));

		btnRooms = new JButton("Räume");
		east.add(btnRooms);
		btnStudents = new JButton("Studenten");
		east.add(btnStudents);
		btnSubjects = new JButton("Fächer");
		east.add(btnSubjects);
		btnAddBreak = new JButton("Pause hinzufügen");
		east.add(btnAddBreak);

	}

}
