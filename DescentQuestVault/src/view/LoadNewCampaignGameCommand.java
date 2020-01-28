package view;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import HeroPicker.HeroPicker;
import StoryEditor.CampaignSaveFile;
import StoryEditor.ProgressStatus;
import StoryEditor.StartStoryEvent;
import StoryEditor.StoryEditorController;
import controller.EndHeroPickListener;
import controller.UserInputController;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.commands.Utils;
import misc.save.WorldSaveFile;
import model.ItemController;

public class LoadNewCampaignGameCommand extends BasicCommand implements ICommand, EndHeroPickListener{

	private Component com;
	private ProgressStatus status;
	
	public LoadNewCampaignGameCommand(Component questEditorMenu) {
		com=questEditorMenu;
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
	
		File workingDirectory = new File(System.getProperty("user.dir")+"/SavedCampaigns");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(com);
		File f=fc.getSelectedFile();
		if(f!=null) {
			
			CampaignSaveFile g=null;
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (CampaignSaveFile) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			status=new ProgressStatus();
			status.setValues(g.getCustomValues());
			status.setStartEvent(g.getStartEvent());
			ItemController itcontrol=ItemController.getItemController();
			itcontrol.setCityEvents(g.getCityEvents());
			String teamname = (String)JOptionPane.showInputDialog(
                    com,
                    "What is your teamname?"
                    ,
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "De poepscheppers");

			Object[] possibilities = {2, 3, 4};
			Integer nrofplayers = (Integer)JOptionPane.showInputDialog(
			                    com,
			                    "How many players?:\n"
			                    ,
			                    "Customized Dialog",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    2);

			//If a string was returned, say so.
			//open hero picker
			//HeroPicker picker=new HeroPicker(status.getFile().generateBaseFile(teamname, nrofplayers));
			UserInputController control=UserInputController.getController();
			control.addEndHeroPickerListener(this);
			control.StartHeroPicker(status.getFile().generateBaseFile(teamname, nrofplayers));
			//ItemController itcontrol=ItemController.getItemController();
			//status.setFile(itcontrol.getCampaingFile());
			
			
			//pick heroes
		
		}
			

	}

	@Override
	public void HeroesPicked() {
		UserInputController control=UserInputController.getController();
		control.removeEndHeroPickerListener(this);
		ItemController itcontrol=ItemController.getItemController();
	
		itcontrol.addAllValues(status.getFile().getBaseFileValues());
		
		itcontrol.setCampaignFile(status.getFile());
		StartStoryEvent ev=status.getStartEvent();
		ev.triggerAndToNext(status);
		
		
	}

}
