package controller.commands;

import java.awt.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controller.UserInputController;
import controller.stack.StackElements.MapEditStackElement;
import misc.save.WorldSaveFile;
import test.Employee;
import view.menu.QuestEditorMenu;

public class LoadCommand extends BasicCommand implements ICommand {

	private Component com;
	
	public LoadCommand(Component questEditorMenu) {
		com=questEditorMenu;
	}
	
	

	@Override
	public void perform() {
		UserInputController.renew();
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
		File workingDirectory = new File(System.getProperty("user.dir")+"/SavedWorlds");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(com);
		File f=fc.getSelectedFile();
		if(f!=null) {
			
			WorldSaveFile g=null;
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (WorldSaveFile) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.startQuestEditor();
			view.loadGame(g);
			mainStack.addStackElement(new MapEditStackElement());
		}
		
	      
	}

}
