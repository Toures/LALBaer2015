package de.hs_mannheim.IB.SS15.OOT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import de.hs_mannheim.IB.SS15.OOT.Exceptions.FullCalendarException;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;

public class GUI extends JFrame implements ActionListener {

	private final static String TITLE = "IM-Planer";

	private Backend backend;

	// Menüleiste
	private JMenuBar jMenuBar;

	private JMenu file;
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem print;
	private JMenuItem exit;

	private JMenu info;
	private JMenuItem about;
	private JMenu professorTableMenu, masterTableMenu, studentTableMenu;

	private JTable tableMaster;
	private JTable tableProfessor;
	private JTable tableStudent;

	private JPanel tableContainer;
	private JPanel centerTablePanel;
	private JPanel east;
	private JPanel south;

	private JButton btnAddRoom;
	private JButton btnRemoveRoom;
	private JButton btnStudents;
	private JButton btnExaminer;
	private JButton btnSubjects;
	private JButton btnAddBreak;
	private JButton btnRemoveBreak;
	private JButton btnGeneratePlan;

	GUI() {
		setTitle(TITLE); // set title

		createNewMainController();

		createJMenuBar();
		createLayout();

		// -------------------------------
		// Options
		// -------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();

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

		// JMenuBar
		if (e.getSource() == newFile) {

			backend = new Backend();

		} else if (e.getSource() == open) {
			// Opening FileChooser for open Dialog
			JFileChooser fc = new JFileChooser((File) null);

			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				loadObject(fc.getSelectedFile().toString());
			}
		} else if (e.getSource() == save) {
			// Opening FileChooser for save Dialog
			JFileChooser fc = new JFileChooser((File) null);

			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// plan object needs to be serializable
				saveObject(fc.getSelectedFile().toString());
			}
		} else if (e.getSource() == print) {
			try {
				tableMaster.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == exit) {
			System.out.println("Programm beenden");
			System.exit(0);
			// EastButtons
		} else if (e.getSource() == btnAddRoom) {

			Backend.MAX_PARALLEL_EXAMS++;

			backend.updateSchedules();
			getContentPane().remove(centerTablePanel);
			createTableMenu();
			getContentPane().add(centerTablePanel, BorderLayout.CENTER);

			revalidate();
			repaint();

		} else if (e.getSource() == btnRemoveRoom) {
			if (Backend.MAX_PARALLEL_EXAMS > 1) {
				Backend.MAX_PARALLEL_EXAMS--;

				backend.updateSchedules();
				getContentPane().remove(centerTablePanel);
				createTableMenu();
				getContentPane().add(centerTablePanel, BorderLayout.CENTER);

				revalidate();
				repaint();
			} else {
				JOptionPane.showMessageDialog(this, "Es muss mindestens ein Raum existieren.", "Raum entfernen", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnStudents) {

			if (backend.getSubjects().size() <= 0) {
				JOptionPane.showMessageDialog(this, "Es sind noch keine Fächer vorhanden.", "Studenten", JOptionPane.ERROR_MESSAGE);
			} else {
				new StudentsGUI(this);
			}

		} else if (e.getSource() == btnSubjects) {
			new SubjectGUI(this);
		} else if (e.getSource() == btnExaminer) {
			new ExaminerGUI(this);
		} else if (e.getSource() == btnAddBreak) {
			new BreakGUI(this, getBackend());
		} else if (e.getSource() == btnRemoveBreak) {
			removeBreakDialog();
		} else if (e.getSource() == btnGeneratePlan) {

			try {
				backend.generateExams();
				backend.generateMasterTable();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Plan erstellen", JOptionPane.ERROR_MESSAGE);
			}
			
			System.out.println(backend.getSchedule()[0].getTable().getValueAt(0, 0));
			System.out.println(backend.getSchedule()[0].getTable().getValueAt(0, 1));
			System.out.println(backend.getSchedule()[0].getTable().getValueAt(1, 0));
			System.out.println(backend.getSchedule()[0].getTable().getValueAt(0, 1));

			backend.getSchedule()[0].getTable().fireTableDataChanged();
			backend.getSchedule()[1].getTable().fireTableDataChanged();
			backend.getSchedule()[2].getTable().fireTableDataChanged();
			
			backend.getSchedule()[1].setTable(backend.getSchedule()[0].getTable());
			backend.getSchedule()[2].setTable(backend.getSchedule()[0].getTable());
			
			revalidate();
			repaint();
			
//			createTableMenu();
			
			createTable();
			
			
			// update table
			centerTablePanel.remove(tableContainer);
			JScrollPane scrollPane = new JScrollPane(tableMaster);
			tableContainer = new JPanel(new BorderLayout());
			tableContainer.add(scrollPane, BorderLayout.CENTER);
			centerTablePanel.add(tableContainer);
			centerTablePanel.add(tableContainer);
			add(centerTablePanel);
			repaint();
			revalidate();
		}

	}

	private void saveObject(String filename) {
		ObjectOutputStream saver;
		try {
			saver = new ObjectOutputStream(new FileOutputStream(filename));
			saver.writeObject(backend);
			saver.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadObject(String filename) {
		ObjectInputStream loader;
		Backend loadedBackend = null;
		try {
			loader = new ObjectInputStream(new FileInputStream(filename));
			loadedBackend = (Backend) loader.readObject();
			loader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		backend = loadedBackend;
	}

	private void removeBreakDialog() {
		// dropdown Menü mit den möglichen Pausen

		// TODO breaks??
	}

	public void createNewMainController() {
		backend = new Backend();

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
		print = new JMenuItem("Drucken");
		print.addActionListener(this);
		exit = new JMenuItem("Beenden");
		exit.addActionListener(this);

		info = new JMenu("Info");

		about = new JMenuItem("Über");
		about.addActionListener(this);

		file.add(newFile);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(print);
		file.addSeparator();
		file.add(exit);

		info.add(about);

		// add submenus to Bar
		jMenuBar.add(file);
		jMenuBar.add(info);
	}

	public void refreshTableMenu() {
		professorTableMenu.removeAll();
		for (Examiner examiner : getBackend().getExaminer()) {
			JMenuItem newExaminerEntry = new JMenuItem(examiner.getName());
			professorTableMenu.add(newExaminerEntry);
		}
	}

	private void createTableMenu() {
		if (centerTablePanel != null) {
			this.remove(centerTablePanel);
		}

		createTable();

		centerTablePanel = new JPanel(new BorderLayout());

		JMenuBar tableMenu = new JMenuBar();

		professorTableMenu = new JMenu("Professor");
		studentTableMenu = new JMenu("Studenten");
		masterTableMenu = new JMenu("Master");

		studentTableMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(centerTablePanel);
				centerTablePanel.remove(tableContainer);
				JScrollPane scrollPane = new JScrollPane(tableStudent);
				tableContainer = new JPanel(new BorderLayout());
				tableContainer.add(scrollPane, BorderLayout.CENTER);
				centerTablePanel.add(tableContainer);
				add(centerTablePanel);
				repaint();
				revalidate();
			}
		});

		masterTableMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				centerTablePanel.remove(tableContainer);
				JScrollPane scrollPane = new JScrollPane(tableMaster);
				tableContainer = new JPanel(new BorderLayout());
				tableContainer.add(scrollPane, BorderLayout.CENTER);
				centerTablePanel.add(tableContainer);
				centerTablePanel.add(tableContainer);
				add(centerTablePanel);
				repaint();
				revalidate();
			}
		});
		tableMenu.add(masterTableMenu);
		tableMenu.add(studentTableMenu);
	
		tableMenu.add(professorTableMenu);

		masterTableMenu.add(tableMaster);

		JScrollPane tableScrollPane = new JScrollPane(tableMaster);
		tableContainer = new JPanel(new BorderLayout());
		tableContainer.add(tableScrollPane, BorderLayout.CENTER);
		centerTablePanel.add(tableContainer, BorderLayout.CENTER);
		centerTablePanel.add(tableMenu, BorderLayout.NORTH);

		getContentPane().add(centerTablePanel, BorderLayout.CENTER);
	}

	private void createLayout() {
		// set Layout
		getContentPane().setLayout(new BorderLayout());

		// NORTH Panel
		getContentPane().add(jMenuBar, BorderLayout.NORTH);

		// CENTER Panel
		createTableMenu();

		// SOUTH Panel
		createSouthButtons();
		getContentPane().add(south, BorderLayout.SOUTH);

		// EAST Panel
		createEastButtons();
		getContentPane().add(east, BorderLayout.EAST);
	}

	private void createTable() {

		System.out.println();

		// create JTables from DataModels
		tableMaster = new JTable(backend.getSchedule()[0].getTable());
		tableProfessor = new JTable(backend.getSchedule()[1].getTable());
		tableStudent = new JTable(backend.getSchedule()[2].getTable());

	}

	private void createSouthButtons() {

		south = new JPanel();
		south.setLayout(new GridLayout(1, 4));

		btnAddRoom = new JButton("Raum hinzufügen");
		btnAddRoom.addActionListener(this);
		south.add(btnAddRoom);

		btnRemoveRoom = new JButton("Raum entfernen");
		btnRemoveRoom.addActionListener(this);
		south.add(btnRemoveRoom);

		btnAddBreak = new JButton("Pause hinzufügen");
		btnAddBreak.addActionListener(this);
		south.add(btnAddBreak);

		btnRemoveBreak = new JButton("Pause entfernen");
		btnRemoveBreak.addActionListener(this);
		south.add(btnRemoveBreak);

	}

	private void createEastButtons() {

		east = new JPanel();
		east.setLayout(new GridLayout(3, 1));

		btnSubjects = new JButton("Fächer");
		btnSubjects.addActionListener(this);
		east.add(btnSubjects);

		// btnExaminer = new JButton("Prüfer");
		// btnExaminer.addActionListener(this);
		// east.add(btnExaminer);

		btnStudents = new JButton("Studenten");
		btnStudents.addActionListener(this);
		east.add(btnStudents);

		btnGeneratePlan = new JButton("Plan erstellen");
		btnGeneratePlan.addActionListener(this);
		east.add(btnGeneratePlan);

	}

	public Backend getBackend() {
		return backend;
	}

}