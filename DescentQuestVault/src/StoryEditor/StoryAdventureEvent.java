package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import ItemEditor.SavedItem;
import controller.EndTravelEventListener;
import controller.UserInputController;
import controller.commands.Utils;
import misc.Tools;
import model.ItemController;
import model.event.Univent;
import model.values.CustomInteger;
import view.viewItems.ItemBox.ItemInfoContainer;

public class StoryAdventureEvent extends StoryEvent {
	
	private static int nr;
	private String currentAdventure="none";
	
	public StoryAdventureEvent() {
		this.name="StoryAdventureEvent"+ nr++;
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		//add textface each time we want text
		// add the opportunity to make choices
		//add adventure picker from save files
		itemInfoText.addTextBox(currentAdventure);
		this.addAdventurePicker(itemInfoText);
	
		super.addEventSpecifics(itemInfoText);
	}
	


	private void addAdventurePicker(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("Choose");
		StoryEvent hold=this;
		UserInputController control=UserInputController.getController();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
				int returnVal = fc.showOpenDialog(control.getMainFrame());
				File f=fc.getSelectedFile();
				currentAdventure=f.toString();
				itemInfoText.refreshImage();
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Story Adventure");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreButton(field,button);
		
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void triggerStoryEvent(ProgressStatus status) {
		UserInputController control=UserInputController.getController();
		control.loadAndStartGame(currentAdventure,status.getFile());
		
	
		
	}
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

}
