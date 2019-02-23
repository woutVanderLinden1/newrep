package controller.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;

import controller.stack.StackElements.MapEditStackElement;
import misc.save.WorldSaveFile;
import model.event.MonsterTurnTrigger;
import monsterEditor.MonsterEditorController;

public class LoadMovementCommand extends BasicCommand implements ICommand {

	
	
	@Override
	public void perform() {
		MonsterEditorController control=MonsterEditorController.getMonsterEditorController();
		// TODO Auto-generated method stub
		MonsterTurnTrigger file=control.getMovement();//.copy();
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
		File workingDirectory = new File(System.getProperty("user.dir")+"/Movement/"+file.getMonster().getName());
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(control.getMonsterEditor());
		File f=fc.getSelectedFile();
		if(f!=null) {
			
			MonsterTurnTrigger g=null;
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (MonsterTurnTrigger) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//view.startQuestEditor();
			control.loadMovement(g);
			mainStack.addStackElement(new MapEditStackElement());
		}
		
		

	}

}
