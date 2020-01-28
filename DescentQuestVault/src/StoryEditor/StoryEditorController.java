package StoryEditor;

import java.awt.Frame;
import java.awt.event.MouseEvent;

import controller.UserInputController;
import controller.commands.ICommand;
import frame.MainFrame;
import misc.CampaignFile;
import misc.DefaultCampaingFile;
import misc.save.WorldSaveFile;
import model.IModel;
import model.ItemController;
import monsterEditor.MonsterEditorController;
import view.IView;

public class StoryEditorController extends UserInputController {

	private static StoryEditorController control;
	private StoryEditor frame;
	private CampaignFile campfile;
	

	public StoryEditorController(IView newview, IModel newmod, StoryEditor theFrame) {
		super(newview, newmod, theFrame);
		frame=theFrame;
		newmod.addStartingValues(new DefaultCampaingFile());
		
	}

	


	public static StoryEditorController getStoryEditorController() {
		
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


	public CampaignSaveFile saveCampaign() {
		// TODO Auto-generated method stub
		return frame.saveCampaign();
	}


	public void startDrag(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		frame.startDrag(todrag);
	}


	public void startStory(ProgressStatus status) {
		
		frame.startStory(status);
		
	}


	public void createCampaignFile(StartStoryEvent startfield, EndStoryEvent endfield) {
		 campfile=new CampaignFile();
		campfile.addStartEventPanel(startfield);
		campfile.addEndEventPanel(endfield);
		
		campfile.setValues(ItemController.getItemController().getValues());
	}
	
	public CampaignFile getCampaingFile() {
		
		campfile.setValues(ItemController.getItemController().getValues());
		return campfile;
	}




	public void minimizeFrame() {
		// TODO Auto-generated method stub
		frame.setState(Frame.ICONIFIED);
	}




	
}
