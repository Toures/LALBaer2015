package de.hs_mannheim.IB.SS15.OOT;

/*
 * Hier befinden sich die Daten, mit der die Tabelle gefüllt wird.
 */

import javax.swing.table.AbstractTableModel;

import de.hs_mannheim.IB.SS15.OOT.PlanObjects.PlanObject;

public class DataModel extends AbstractTableModel {
	private int amountOfRows;
	private int amountOfColumns;

	private PlanObject[][] data;

	DataModel(int rows, int columns) {
		this.amountOfRows = rows;
		this.amountOfColumns = columns;

		data = new PlanObject[amountOfRows][amountOfColumns];
		for (int tempRowCounter = 0; tempRowCounter < data.length; tempRowCounter++) {
			for (int temColumCounter = 0; temColumCounter < data[0].length; temColumCounter++) {
				data[tempRowCounter][temColumCounter] = new PlanObject() {

					@Override
					public int getLength() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public String toString() {
						return " - ";

					}

				};
			}
		}

	}

	@Override
	public int getColumnCount() {
		return amountOfColumns;
	}

	@Override
	public int getRowCount() {
		return amountOfRows;
	}

	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "Zeit";
		} else {
			return "Raum " + col;
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return row + ". Stunde";
		}

		return data[row][col];
	}

	public void setValueAt(PlanObject value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}