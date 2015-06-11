package de.hs_mannheim.IB.SS15.OOT;

/*
 * Hier befinden sich die Daten, mit der die Tabelle gefüllt wird.
 */

import javax.swing.table.AbstractTableModel;

public class TableData extends AbstractTableModel {
	private static final int ROWS = 10;
	private static final int COLS = 10;

	private Object[][] data;

	TableData() {
		data = new Object[ROWS][COLS];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				data[row][col] = "" + row * col;
			}
		}

	}

	public int getColumnCount() {
		return COLS;
	}

	public int getRowCount() {
		return ROWS;
	}

	public String getColumnName(int col) {
		return "" + col;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value.toString();
		fireTableCellUpdated(row, col);
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

}