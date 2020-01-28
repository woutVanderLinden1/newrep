package StoryEditor;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controller.commands.BasicCommand;
import controller.commands.Utils;
import controller.stack.StackElements.MapEditStackElement;
import misc.save.WorldSaveFile;
import model.ItemController;

public class LoadCampaignGameCommand extends BasicCommand {
	
	private Component comp;

	public LoadCampaignGameCommand(Component comp) {
		this.comp=comp;
	}
	
	@Override
	public void perform() {
		
		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
			    if (f.isDirectory()) {
			        return true;
			    }

			    String extension = Utils.getExtension(f);
			    if (extension != null) {
			        if (extension.equals(Utils.ser)){
			                return true;
			        } else {
			            return false;
			        }
			    }

			    return false;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".ser";
			}
		});
		File workingDirectory = new File(System.getProperty("user.dir")+"/Campaigns");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(comp);
		File f=fc.getSelectedFile();
		if(f!=null) {
			ProgressStatus g=null;
			
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (ProgressStatus) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//view.loadGame(g);
			mainStack.addStackElement(new MapEditStackElement());
			ItemController control=ItemController.getItemController();
			ProgressStatus status=g;
			control.setCityEvents(status.getFile().getCityEvents());
			control.addAllValues(status.getFile().getValues());
			
			control.setCampaignFile(status.getFile());
			//ItemController itcontrol=ItemController.getItemController();
			//status.setFile(itcontrol.getCampaingFile());
			StoryEditorController storycontrol=StoryEditorController.getStoryEditorController();
			status.setFile(g.getFile());
			StartStoryEvent ev=status.getStartEvent();
			ev.triggerAndToNext(status);
			//storycontrol.startStory(status);
			//storycontrol.minimizeFrame();
		}
		// TODO Auto-generated method stub
		
	}

}
