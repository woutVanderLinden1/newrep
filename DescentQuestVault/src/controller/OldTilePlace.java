package controller;

import view.Items.Map.ViewSquare;
import view.viewItems.ItemBox.SelectAble;

public class OldTilePlace {

	ViewSquare square;
	SelectAble toselect;
	
	public ViewSquare getSquare() {
		return square;
	}

	public void setSquare(ViewSquare square) {
		this.square = square;
	}

	public SelectAble getToselect() {
		return toselect;
	}

	public void setToselect(SelectAble toselect) {
		this.toselect = toselect;
	}

	public OldTilePlace(ViewSquare square, SelectAble toselect) {
		setSquare(square);
		setToselect(toselect);
	}

	
}
