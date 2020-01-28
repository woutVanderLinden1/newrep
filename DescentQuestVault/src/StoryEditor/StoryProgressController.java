package StoryEditor;

import java.util.ArrayList;

import controller.UserInputController;

public class StoryProgressController {
	
	private static StoryProgressController control;
	private ArrayList<EndStoryEventListener> endstoryEventListener =new ArrayList<EndStoryEventListener>();
	private ArrayList<EndStoryEventListener> minieventendstoryEventListener =new ArrayList<EndStoryEventListener>();
	
	
	public static StoryProgressController getController() {
		// TODO Auto-generated method stub
		if(control==null) {
			control=new StoryProgressController();
		}
		return control;
	}

	public void addEndStoryEventListener(StartStoryEvent startStoryEvent) {
		UserInputController control=UserInputController.getController();
		if(control.miniEventMode) {
			minieventendstoryEventListener.add(startStoryEvent);
		}
		else {
			endstoryEventListener.add(startStoryEvent);
		}
		
		
	}

	public void StoryEventEnded() {
		
		for(EndStoryEventListener listen:endstoryEventListener) {
			listen.storyEventEnded();
		}
		
	}

	public void StoryMiniEventEnded() {
		// TODO Auto-generated method stub
		int p=0;
		while(p<minieventendstoryEventListener.size()) {
			
			EndStoryEventListener listen=minieventendstoryEventListener.get(p);
			listen.storyEventEnded();
			p++;
		}
	
	}

	public void clearListeners() {
		UserInputController control=UserInputController.getController();
		if(control.miniEventMode) {
			minieventendstoryEventListener.clear();
		}
		else {
			endstoryEventListener.clear();
		}
		
	}

	

}
