package StoryEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

import controller.UserInputController;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import misc.save.WorldSaveFile;

public class SaveTravelEventCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		UserInputController control=UserInputController.getController();
		CampaignSaveFile file=	((StoryEditorController) control).saveCampaign();
		final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/TravelEvent");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showSaveDialog(control.getMainFrame());
		  File newfile = fc.getSelectedFile();
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
			         out.writeObject(file);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in "+ newfile.toString());
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
		  }
		
	

	}

	

}
