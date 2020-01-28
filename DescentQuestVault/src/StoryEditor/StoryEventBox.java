package StoryEditor;

import misc.save.WorldSaveFile;
import model.ItemController;
import model.event.Trigger;
import model.event.Univent;
import view.events.BaseField;
import view.events.EventBox;
import view.viewItems.ItemBox.SelectKind;

public class StoryEventBox extends EventBox implements AddStoryEventListener {
	
	private StartStoryEvent startfield=new StartStoryEvent();
	private EndStoryEvent endfield=new EndStoryEvent();

	public StoryEventBox(int i, int height) {
		super(i, height);
		this.addUniventToTriggerField(startfield, null,false);
		this.addUniventToTriggerField(endfield, null,false);
		startfield.addAddStoryEventListener(this);
		startfield.addEndEvent(endfield);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void storyEventAdded(StoryEvent added) {
		added.addAddStoryEventListener(this);
		this.addUniventToTriggerField(added, null,false);
		
	}

	@Override
	public void storyEventRemoved(StoryEvent removed) {
		this.removeEvent(removed);
		
	}

	@Override
	public void triggerAdded(Trigger added) {
		this.addUniventToTriggerField(added,null);
		System.out.println("trigger will be added");
	}

	@Override
	public void triggerRemoved(Trigger removed) {
		this.removeTrigger(removed);
		
	}
	public void initialiseController() {
		StoryEditorController control= StoryEditorController.getStoryEditorController();
		control.createCampaignFile(startfield,endfield);
	}

	public void initialise(Univent vent) {
		if(vent.getKind()!=SelectKind.TRIGGER) {
			StoryEvent stor=(StoryEvent) vent;
			
			stor.addAddStoryEventListener(this);
			stor.addEndEvent(endfield);
			if(stor.getStoryKind()==StoryEventKind.END) {
				this.removeEvent(stor);
			}
			if(stor.getStoryKind()==StoryEventKind.START) {
				this.removeEvent(startfield);
				startfield=(StartStoryEvent) stor;
			}
			
		}
		
		
	}

	public void startStory(ProgressStatus status) {
		startfield.triggerAndToNext( status);
		
		
	}

	public void removeEvents() {
		// TODO Auto-generated method stub
		
	}
	public CampaignSaveFile saveCampaign() {
		CampaignSaveFile file=new CampaignSaveFile();
		for(BaseField field:fields) {
			file.addUnivent(field.getUnivent());
		}
		ItemController itemcontrol=ItemController.getItemController();
		file.saveCustomValues(itemcontrol.getValues());
		file.setStartEvent(startfield);
		file.setCityEvents(itemcontrol.getCityEvents());
		
		//file.set
		//file.setBaseTrigger((StartUpTrigger)basetrigger);
		return file;
	}

	public void setStartEvent(StartStoryEvent startEvent) {
		startfield=startEvent;
		
	}



}
