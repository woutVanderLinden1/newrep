package controller.commands;

import java.awt.Color;
import java.util.ArrayList;

import controller.stack.StackElements.ChangedColorStackElement;
import view.Items.Map.ViewSquare;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;

public class ShowOccupiedCommand extends BasicCommand implements ICommand {
	
	private ViewSquare square;
	private ShapeItem selected;
	private Color color;

	public ViewSquare getSquare() {
		return square;
	}

	public void setSquare(ViewSquare square) {
		this.square = square;
	}

	public SelectAble getSelected() {
		return selected;
	}

	public void setSelected(ShapeItem selectAble) {
		this.selected = selectAble;
	}

	public ShowOccupiedCommand(ViewSquare square, ShapeItem selectAble, Color color2) {
		setSquare(square);
		setSelected(selectAble);
		color=color2;
	}
	
	public void perform() {
		control.resetColors();
		
		//color=new Color(255,255,255,97);
		ArrayList<ViewSquare> changedTiles=view.changeShapeColor(selected,square,color);
		control.addColorStackElement(new ChangedColorStackElement(changedTiles));
	}

}
