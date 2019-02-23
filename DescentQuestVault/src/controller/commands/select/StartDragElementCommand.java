package controller.commands.select;

import java.awt.Color;
import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.stack.StackElements.ChangedColorStackElement;
import view.Items.Map.MapItem;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.viewItems.ShapeItem;

public class StartDragElementCommand extends BasicCommand implements ICommand {

	private MapItem toselect;
	private int x;
	private int y;
	private int onscreenX;
	private int onscreenY;
	private ViewSquare square;
	
	public StartDragElementCommand(MapItem topItem, int x, int y, int xOnScreen, int yOnScreen, ViewSquare square) {
		toselect=topItem;
		this.x=x;
		this.y=y;
		onscreenX=xOnScreen;
		onscreenY=yOnScreen;
		this.square=square;
	}

	public void perform() {
	
		view.startDragItem(toselect);
		view.setSelected(toselect);
		
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		
		control.startDrag(toselect,onscreenX,onscreenY);
		control.resetColors();
		
		//color=new Color(255,255,255,97);
		if(square!=null) {
			
			ArrayList<ViewSquare> changedTiles=view.changeShapeColor((ShapeItem) toselect.getImageItem(),square,new Color(255,255,255,97));
			control.addColorStackElement(new ChangedColorStackElement(changedTiles));
	
		}
		System.out.println("thebasesquare is "+ toselect.getBaseSquare());
		control.keepTileMoveChange(toselect.getBaseSquare(),toselect);
		
	}

}
