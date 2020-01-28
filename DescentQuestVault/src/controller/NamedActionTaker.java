package controller;

import java.io.Serializable;

import ItemEditor.ActionTaker;
import StoryEditor.CampaignSaveFile;
import StoryEditor.MiniEventListener;
import StoryEditor.ProgressStatus;
import misc.save.WorldSaveFile;

public class NamedActionTaker<P> implements ActionTaker<P>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private CampaignSaveFile g;

	
	public NamedActionTaker(String name, CampaignSaveFile file) {
		this.name=name;
		g=file;
		
	}


	@Override
	public void perform(Object value) {
		
		UserInputController control=UserInputController.getController();
		control.startMiniEvent();
		ProgressStatus status=new ProgressStatus();
		
		status.setStartEvent(g.getStartEvent());
		control.addEndMiniEventListener(new MiniEventListener() {
			
			public void miniEventEnded() {
				control.endMiniEvent();
				control.showCityMenu();
			}
		});
		
		g.getStartEvent().triggerAndToNext(status);
		
	}


	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
