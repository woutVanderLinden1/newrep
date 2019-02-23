package controller.commands.select;

import java.awt.Color;
import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.stack.StackElements.ChangedColorStackElement;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.viewItems.ItemBox.SelectAble;

public class StartTileDragCommand extends BasicCommand implements ICommand {
	private ViewTile toselect;
	private int x;
	private int y;
	private int onscreenX;
	private int onscreenY;
	private ViewSquare square;
	
	public StartTileDragCommand(ViewTile selected,int x ,int y, int onscreenx,int onscreeny,ViewSquare square) {
		toselect=selected;
		this.x=x;
		this.y=y;
		onscreenX=onscreenx;
		onscreenY=onscreeny;
		this.square=square;
	}

	public StartTileDragCommand(ViewTile selected, int x2, int y2, int onscreenx, int onscreeny) {
		// TODO Auto-generated constructor stub
		toselect=selected;
		this.x=x;
		this.y=y;
		onscreenX=onscreenx;
		onscreenY=onscreeny;
		//square=((ViewTile)toselect).getSquare();
	}

	public void perform() {
	
		view.startDragTile(toselect);
		view.setSelected(toselect);
		
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		
		control.startDrag(toselect,onscreenX,onscreenY);
		control.resetColors();
		
		//color=new Color(255,255,255,97);
		if(square!=null) {
			ArrayList<ViewSquare> changedTiles=view.changeShapeColor(toselect.getTileItem(),square,new Color(255,255,255,97));
			control.addColorStackElement(new ChangedColorStackElement(changedTiles));
			
		}
		
		control.keepTileMoveChange(toselect.getBaseSquare(),toselect);
	}
	
}
