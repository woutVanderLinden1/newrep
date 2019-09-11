package StoryEditor;

import java.awt.event.MouseEvent;

import controller.UserInputController;
import controller.commands.ICommand;
import frame.MainFrame;
import model.IModel;
import monsterEditor.MonsterEditorController;
import view.IView;

public class StoryEditorController extends UserInputController {

	private static StoryEditorController control;
	private StoryEditor frame;

	public StoryEditorController(IView newview, IModel newmod, StoryEditor theFrame) {
		super(newview, newmod, theFrame);
		frame=theFrame;
		
	}

	
	public static StoryEditorController getStoryEditorController() {
		// TODO Auto-generated method stub
		return control;
	}
	public static UserInputController createStoryEditorController(IModel object, StoryEditorView view,
			StoryEditor storyEditor) {
		control =new StoryEditorController(view,object ,storyEditor);
		return control;
	}

	@Override
	public synchronized void performCommand(ICommand toExecute) {
		// TODO Auto-generated method stub
		if(toExecute!=null) {
			toExecute.setView(this.getTheView());
			super.performCommand(toExecute);
		}
	}
	public void sendDragListenerEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		
		super.sendDragListenerEvent(e);
	}
	/*
	public void addTextToTextFrameElement() {
		
		frame.addTextToTextFrameElement();
	}


	public void addDialogToTextFrameElement() {
		frame.addDialogToTextFrameElement();
		
	}


	public void addOptionToTextFrameElement() {
		frame.addOptionToTextFrameElement();
		
	}


	public void addSoundToTextFrameElement() {
		frame.addSoundToTextFrameElement();
		
	}

	*/
}
