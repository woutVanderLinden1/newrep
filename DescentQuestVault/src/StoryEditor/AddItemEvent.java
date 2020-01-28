package StoryEditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;
import controller.UserInputController;
import controller.commands.Utils;
import misc.BaseFile;
import misc.CampaignFile;
import misc.Tools;
import model.ItemController;
import model.event.Event;
import model.event.Univent;
import view.Items.Map.SubQuestFile;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public class AddItemEvent extends Event {

	private SavedItem toAdd;
	
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void trigger() {
		
		ItemController control=ItemController.getItemController();
		CampaignFile cfile=control.getCampaignFile();
		BaseFile file=cfile.getBaseFile();
		if(toAdd==null||!file.hasFullEquipment(toAdd)) {
        	
    		addRandomEquipment(file);
    	
		}else {
			
			boolean bool=this.ShowGotItemDialog(toAdd);
			if(bool) {
				file.addItem(toAdd);
			}
			
		}
			
	}
	
	public static boolean ShowGotItemDialog(SavedItem toadd) {
		  UserInputController control=UserInputController.getController();
		  
	    System.out.println("Got item"+toadd.getName());
	    JPanel textPanel=new JPanel();
	    textPanel.setFont(new Font("Arial", Font.BOLD, 20)); 
	    textPanel.setLayout(new GridBagLayout());
	    JLabel name=new JLabel(toadd.getName());
	    name.setText("You got "+toadd.getName());
	    name.setMinimumSize(new Dimension(100,25));
	    name.setPreferredSize(new Dimension(100,25));
	 
	    JLabel textArea = new JLabel( toadd.getImageIcon());
	   
	    
	    textArea.setSize(textArea.getPreferredSize().width, textArea.getPreferredSize().height);
	    
	    

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
	    textPanel.add(name,c);
	    c.gridx = 0;
        c.gridy = 1;
	    textPanel.add(textArea,c);
	
	   
	   
	    int ret = JOptionPane.showConfirmDialog(control.getMainFrame(), textPanel, "SubQuest Info", JOptionPane.OK_OPTION);
	    if (ret == 0) {
	    
	        return	true;
	    } else {
	       // MyDialogs.Toast("Canceled by user\nChanges not saved", "Your choise");
	    }
	    return false;
	} 

	private void addRandomEquipment(BaseFile file) {
		UserInputController control=UserInputController.getController();
		int added=0;
		ArrayList<SavedItem> possibleevents=new ArrayList<SavedItem>();
		String directoryPath= System.getProperty("user.dir")+"//Items";
		File dir = new File(directoryPath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		     Optional<String> answer=this.getExtensionByStringHandling(child.getName());
		      System.out.println();
		      if(answer.get().equals("ser")){
		    	  added++;
		    	  SavedItem i=null;
					 FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(child);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						i = (SavedItem) in.readObject();
					    in.close();
					    fileIn.close();
					    System.out.println("the read file is " +i);
					    if(i.isShopavailable()&&!file.hasFullEquipment(i)) {
					    	  possibleevents.add(i);
					    }
					  
					    	
					    
					
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		      }
		    }
		  }
		int p=Tools.getRandomInt(possibleevents.size());
		SavedItem tohappen=possibleevents.get(p);
		boolean bool=this.ShowGotItemDialog(tohappen);
		if(bool) {
			file.addItem(tohappen);
		}
	
		
		
		
	}

	public SavedItem getToAdd() {
		return toAdd;
	}

	public void setToAdd(SavedItem toAdd) {
		this.toAdd = toAdd;
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		super.addEventSpecifics(itemInfoText);
		String button="random";
		if(toAdd!=null) {
			button=toAdd.getName();
		}
		
		itemInfoText.addButton(button, "change item", new ActionTaker<ActionEvent>() {

			@Override
			public void perform( ActionEvent value) {
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
				UserInputController usercontrol=UserInputController.getController();
				int returnVal = fc.showOpenDialog(usercontrol.getMainFrame());
				File f=fc.getSelectedFile();
				if(f!=null) {
					
					SavedItem g=null;
					 FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(f);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						g = (SavedItem) in.readObject();
					    in.close();
					    fileIn.close();
					    System.out.println("the read file is " +g);
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setToAdd(g);
					//view.loadCampaignGame(g);
				
			}
			
			}
		});
	}
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}


}
