package controller.commands.select;

import java.awt.Color;
import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.stack.StackElements.ChangedColorStackElement;
import view.Items.Map.ViewSquare;
import view.events.BaseField;
import view.viewItems.ShapeItem;

public class StartDragEventCommand extends BasicCommand {
	
	private BaseField todrag;
	private int x;
	private int y;
	private int onscreenX;
	private int onscreenY;
	
	public StartDragEventCommand(BaseField newtodrag,int newx,int newy,int onscrnx,int onscrny) {
		todrag=newtodrag;
		x=newx;
		y=newy;
		onscreenX=onscrnx;
		onscreenY=onscrny;
	}
	
	
	public void perform() {
		System.out.println("selectddragging");
		view.startDragItem(todrag);
		view.setSelected(todrag);
		
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		
		control.startDrag(todrag,onscreenX,onscreenY);
		control.resetColors();
		
		//color=new Color(255,255,255,97);
	

		control.keepEventMoveChange(todrag.getTriggerField(),todrag);
	}
}
