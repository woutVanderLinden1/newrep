package controller;

import ItemEditor.ActionTaker;
import StoryEditor.CampaignSaveFile;
import StoryEditor.MiniEventListener;
import StoryEditor.ProgressStatus;

public class UnNamedActionTaker<P> implements ActionTaker<P> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EndTravelEventListener listen;
	private CampaignSaveFile g;

	
	public UnNamedActionTaker(CampaignSaveFile file, EndTravelEventListener listen) {
		this.listen=listen;
		g=file;
		
	}


	@Override
	public void perform(P value) {
		
		UserInputController control=UserInputController.getController();
		control.startMiniEvent();
		ProgressStatus status=new ProgressStatus();
		
		status.setStartEvent(g.getStartEvent());
		control.addEndMiniEventListener(new MiniEventListener() {
			
			public void miniEventEnded() {
				control.endMiniEvent();
				listen.travelEnded();
			}
		});
		
		g.getStartEvent().triggerAndToNext(status);
		
	}

}
