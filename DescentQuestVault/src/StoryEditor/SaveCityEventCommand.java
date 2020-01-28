package StoryEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import controller.UserInputController;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import misc.save.WorldSaveFile;

public class SaveCityEventCommand extends BasicCommand implements ICommand {

	
	
	@Override
	public void perform() {
		
		UserInputController control=UserInputController.getController();
		String name = (String)JOptionPane.showInputDialog(
				control.getMainFrame(),
                "Enter name for the CityEvent",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "ham");
		WorldSaveFile file=	((StoryEditorController) control).saveCampaign();
		file.setName(name);
		final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/CityEvent");
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
