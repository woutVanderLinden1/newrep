package controller.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

import controller.UserInputController;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import monsterEditor.MonsterEditorController;

public class SaveMovementCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		MonsterEditorController control=MonsterEditorController.getMonsterEditorController();
		// TODO Auto-generated method stub
		MonsterTurnTrigger file=control.getMovement();
		
		final JFileChooser fc = new JFileChooser();
		System.out.println(System.getProperty("user.dir")+"/Movement/"+file.getMonster());
		 File workingDirectory = new File(System.getProperty("user.dir")+"/Movement/"+file.getMonster().getName());
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showSaveDialog(control.getMonsterEditor());
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
