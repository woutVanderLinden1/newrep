package StoryEditor;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.events.BaseField;

public class StartDragStoryItemCommand extends BasicCommand implements ICommand {


	private DraggAblePanel todrag;
	private int x;
	private int y;
	private int onscreenX;
	private int onscreenY;
	private static int happened;
	
	public StartDragStoryItemCommand(DraggAblePanel pan, int newx, int newy, int onscrnx, int onscrny) {
		todrag=pan;
		x=newx;
		y=newy;
		onscreenX=onscrnx;
		onscreenY=onscrny;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		//todrag.deactivateTextLabelMouselistener();
		System.out.println("selectddragging");
		//((StoryEditorController) control).startDrag(todrag);
		view.setSelected(todrag);
		
		//sets the selected in the stack
		//gives new analyzer and lets you move panels.
		
		control.startDrag(todrag,onscreenX,onscreenY);
		control.resetColors();
		
		happened++;
		System.out.println("started drag "+happened+" times");
		//color=new Color(255,255,255,97);
		

		//control.keepEventMoveChange(todrag.getTriggerField(),todrag);
	}

}
