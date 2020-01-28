package StoryEditor;

import java.io.Serializable;
import java.util.HashMap;

import misc.CampaignFile;
import model.values.CustomValue;

public class ProgressStatus implements Serializable {

	//af file that keeps the current mission
	private StoryEvent currentStoryEvent;
	private CampaignFile file;

	public ProgressStatus() {
		file=new CampaignFile();
	}
	
	public StoryEvent getNextStoryEvent() {
		currentStoryEvent=file.getNextEvent(currentStoryEvent);
		return currentStoryEvent;
	}

	public CampaignFile getFile() {
		return file;
	}

	public void setFile(CampaignFile file) {
		this.file = file;
	}

	public void setNextStoryEvent(StoryEvent nextStoryEvent) {
		this.currentStoryEvent = nextStoryEvent;
	}

	public void updateValues(HashMap<String, CustomValue> values) {
		HashMap<String,CustomValue>map=file.getValues();
		for(String str:map.keySet()) {
			if(values.get(str)!=null) {
				map.put(str, values.get(str));
			}
		}
		
	}

	public String getTeamName() {
		// TODO Auto-generated method stub
		return file.getTeamName();
	}

	public void setValues(HashMap<String, CustomValue> customValues) {
		file.setValues(customValues);
		
	}

	public StartStoryEvent getStartEvent() {
		// TODO Auto-generated method stub
		return file.getStartEvent();
	}

	public void setStartEvent(StartStoryEvent startEvent) {
		// TODO Auto-generated method stub
		file.setStartEvent(startEvent);
	}
	
}
