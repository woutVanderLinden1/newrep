package StoryEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FilenameUtils;

import controller.UserInputController;
import controller.stack.EndGameCommand;
import model.ItemController;
import model.event.Event;
import model.event.Univent;

public class StartStoryEvent extends StoryTextEvent implements EndStoryEventListener {
	
	private ProgressStatus stat=new ProgressStatus();

	public StartStoryEvent() {
		super();
		this.name="Start story";
		this.setIDName(name);
		this.setStoryKind(StoryEventKind.START);
	}

	public void triggerAndToNext(ProgressStatus status) {
		stat=status;
		StoryProgressController progresscontrol=StoryProgressController.getController();
		
		progresscontrol.addEndStoryEventListener(this);
		UserInputController control=UserInputController.getController();
		
		StoryEvent nextevent=status.getNextStoryEvent();
		if(nextevent==null) {
			
			control.performCommand(new EndCampaignCommand());
		}
		else{
			nextevent.triggerStoryEvent(status);
		}
		
	}

	@Override
	public void storyEventEnded() {
		StoryEditorController control=StoryEditorController.getStoryEditorController();
		updateCampaignFile();
		StoryEvent nextevent=stat.getNextStoryEvent();
		
		if(nextevent==null) {
			if(control==null) {
				UserInputController.getController().performCommand(new EndCampaignCommand());
			}
			else {
				StoryProgressController progresscontrol=StoryProgressController.getController();
				
				progresscontrol.clearListeners();
				control.performCommand(new EndCampaignCommand());
			}
		}
		else{
			nextevent.triggerStoryEvent(stat);
		}
		
		
	}

	private void updateCampaignFile() {
		ItemController control=ItemController.getItemController();
		stat.updateValues(control.getValues());
		  File newfile =new File(System.getProperty("user.dir")+"\\Campaigns\\"+stat.getTeamName()+".ser");
		  if (FilenameUtils.getExtension(newfile.getName()).equalsIgnoreCase("ser")) {
			    // filename is OK as-is
			} else {
			   // newfile = new File(newfile.toString() + ".ser");  // append .xml if "foo.jpg.xml" is OK
			    newfile = new File(newfile.getParentFile(), FilenameUtils.getBaseName(newfile.getName())+".ser"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
			}
		  if(newfile!=null) {
			  try {
			         FileOutputStream fileOut =
			         new FileOutputStream(newfile);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(stat);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in "+ newfile.toString());
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
		  }
		
		
	}


	

}
