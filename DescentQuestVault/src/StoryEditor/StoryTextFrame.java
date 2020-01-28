package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FilenameUtils;

import frame.SubContainer;
import frame.TemporaryAble;
import misc.CampaignFile;
import misc.save.WorldSaveFile;
import model.ItemController;
import view.events.BaseField;
import view.viewItems.ItemBox.SelectAble;

public class StoryTextFrame extends JPanel implements TemporaryAble{

	//private JPanel storyElementOptions=new StoryElementItemTabField(400,120);
	//private StoryElementPanel storyElementPanel;
	private StoryListElementPanel storyElementpanel;
	
	public StoryTextFrame(int i, int j) {
		super();
		
		this.setBackground(new Color(245,222,179));
		this.setPreferredSize(new Dimension(i,j));
		//this.add(storyElementOptions);
		/*
		storyElementPanel=new StoryElementPanel(new Dimension(i-35,1800));
		 JScrollPane scrollPane=new JScrollPane(storyElementPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	        	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setPreferredSize(new Dimension(i-20,j-30));
		
		this.add(scrollPane);
		*/
	
	}

	public void sendEvent(MouseEvent e, Point newLocation, SelectAble selectAble) {
		if(SubContainer.isMouseWithinComponent(this)) {
			//storyElementPanel.sendEvent(e,newLocation,selectAble);
		}
		
	}

	public void startDragEvent(BaseField todrag) {
		//storyElementPanel.startDragEvent(todrag);
		
	}

	public void startDragPanel(DraggAblePanel todrag) {
		//storyElementPanel.startDragPanel(todrag);
		
	}

	@Override
	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteSelected(SelectAble selected) {
		//storyElementPanel.deleteSelected(selected);
		
	}

	public WorldSaveFile saveThis() {
		CampaignFile saved=new CampaignFile();
		//storyElementPanel.saveFile(saved);
		ItemController control=ItemController.getItemController();
		control.saveFile(saved);
		
		//create a savefile for this
		
		final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/SavedCampaigns");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showSaveDialog(this);
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
			         out.writeObject(saved);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in "+ newfile.toString());
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
		  }
		
		//add to saved games
		return null;
	}

	public void startDrag(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		//storyElementPanel.startDrag(todrag);
	}

	/*
	public void addTextElement() {
		storyElementPanel.addTextElement();
		
	}

	public void addDialogElement() {
		
		storyElementPanel.addDialogElement();
	}

	public void addOptionElement() {
	
		storyElementPanel.addOptionElement();
	}

	public void addSoundElement() {
		storyElementPanel.addSoundElement();
		
	}
	*/

	
}
