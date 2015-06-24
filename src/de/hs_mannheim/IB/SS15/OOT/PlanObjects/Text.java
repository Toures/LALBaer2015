package de.hs_mannheim.IB.SS15.OOT.PlanObjects;

import java.io.Serializable;

public class Text implements PlanObject, Serializable {

	String text;

	public Text(String text) {
		this.text = text;
	}

	@Override
	public int getLength() {
		return 5;
	}

	@Override
	public boolean isBreak() {
		return false;
	}

	@Override
	public String toString() {
		return text;
	}

}
