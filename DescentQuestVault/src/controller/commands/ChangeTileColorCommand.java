package controller.commands;

import java.awt.Color;
import java.util.Stack;

import controller.UserInputController;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.ChangedColorStackElement;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import view.IView;
import view.Items.Map.ViewSquare;

public class ChangeTileColorCommand extends BasicCommand {

	private ViewSquare tile;
	private Color color;

	public ChangeTileColorCommand(ViewSquare tile, Color color) {
		setColor(color);
		setTile(tile);
	}
	
	public ViewSquare getTile() {
		return tile;
	}

	public void setTile(ViewSquare tile) {
		this.tile = tile;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	

	@Override
	public void perform() {
		System.out.println("performed");
		control.resetColors();
		
		control.addColorStackElement(new ChangedColorStackElement(tile));
		view.changeTileColor(tile,color);

	}



}
