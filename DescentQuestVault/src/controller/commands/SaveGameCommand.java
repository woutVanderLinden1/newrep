package controller.commands;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

import misc.save.WorldSaveFile;

public class SaveGameCommand extends BasicCommand {

	private Component comp;
	
	public SaveGameCommand(Component comp) {
		this.comp=comp;
	}
	
	@Override
	public void perform() {
		WorldSaveFile file=view.saveGame();
		final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/SavedWorlds");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showSaveDialog(comp);
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
		/*
		  try {
		         //FileOutputStream fileOut =
		         //new FileOutputStream("SavedWorlds/world1.ser");
		         //ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         //out.writeObject(file);
		         //out.close();
		         //fileOut.close();
		         System.out.printf("Serialized data is saved in SavedWorlds/world1.ser");
		      } catch (IOException i) {
		         i.printStackTrace();
		      }
		      */
		   
	
	}

}
