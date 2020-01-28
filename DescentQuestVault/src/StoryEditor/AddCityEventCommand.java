package StoryEditor;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.commands.Utils;
import controller.stack.StackElements.MapEditStackElement;

public class AddCityEventCommand extends BasicCommand implements ICommand {
	
	private Component com;

	public AddCityEventCommand(Component mainFrame) {
		com=mainFrame;
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
		File workingDirectory = new File(System.getProperty("user.dir")+"/CityEvent");
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
			((StoryEditorView) view).addCityEventFile(g);
			//view.loadCampaignGame(g);
			//mainStack.addStackElement(new MapEditStackElement());
		}

	}

}
