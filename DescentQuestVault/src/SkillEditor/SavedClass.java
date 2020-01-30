package SkillEditor;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;
import Shop.ItemShop.ImageHolder;
import controller.UserInputController;
import controller.commands.Utils;
import misc.save.WorldSaveFile;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SavedClass implements Serializable ,ImageHolder{
	

	private ArrayList<SavedSkill> savedskills=new ArrayList<SavedSkill>();
	private String classname;
	private String imagestring;
	private HeroType type;
	private ArrayList<SavedItem> initialitems=new ArrayList<SavedItem>();
	private ArrayList<SavedSkill> initialskills=new ArrayList<SavedSkill>();
	private ClassSkillCard classcard;
	private transient SkillEditor editor;
	
	public SavedClass(String classname, HeroType type,SkillEditor editor) {
		super();
		this.editor=editor;
		this.classname = classname;
		this.type = type;
		classcard=new ClassSkillCard(this);
		savedskills.add(classcard);
		initialskills.add(classcard);
	}
	public ArrayList<SavedSkill> getSavedskills() {
		return savedskills;
	}
	public void setSavedskills(ArrayList<SavedSkill> savedskills) {
		this.savedskills = savedskills;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
		classcard.setSkillname(classname);
	}
	public HeroType getType() {
		return type;
	}
	public void setType(HeroType type) {
		this.type = type;
	}
	public ArrayList<SavedItem> getInitialitems() {
		return initialitems;
	}
	public void setInitialitems(ArrayList<SavedItem> initialitems) {
		this.initialitems = initialitems;
	}
	public ArrayList<SavedSkill> getInitialskills() {
		return initialskills;
	}
	public void setInitialskills(ArrayList<SavedSkill> initialskills) {
		this.initialskills = initialskills;
	}
	public ClassSkillCard getClasscard() {
		return classcard;
	}
	public void setClasscard(ClassSkillCard classcard) {
		this.classcard = classcard;
	}
	public String getImagestring() {
		return imagestring;
	}
	public void setImagestring(String imageString2) {
		imagestring=imageString2;
		
	}
	public void addSkill(SavedSkill currentskill) {
		savedskills.add(currentskill);
		
	}
	public void addItemSpecifics(ItemInfoContainer box) {
		
		box.addEditButton("name", box, classname, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setClassname(performstring);
			}

			
		});
		HeroType[] list= {HeroType.WARRIOR,HeroType.CLERIC,HeroType.MAGE,HeroType.SCOUT};
		JComboBox bos=box.addJComboBox("Type", list, new ActionTaker<HeroType>() {

			@Override
			public void perform(HeroType performstring) {
				// TODO Auto-generated method stub
				setType(performstring);
			}

			
		});
		bos.setSelectedItem(this.type);
		for(SavedItem item:initialitems) {
			box.addButton(item.getName(),"remove",new ActionTaker<ActionEvent>(){

				@Override
				public void perform(ActionEvent value) {
					initialitems.remove(item);
					
				}
				
			});
		}
		
		box.addButton("initial item","add",new ActionTaker<ActionEvent>(){

			@Override
			public void perform(ActionEvent value) {
				
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
				File workingDirectory = new File(System.getProperty("user.dir")+"/Items");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showOpenDialog(editor);
				File f=fc.getSelectedFile();
				if(f!=null) {
					
				SavedItem item=null;
					 FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(f);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						item = (SavedItem) in.readObject();
					    in.close();
					    fileIn.close();
					    System.out.println("the read file is " +item);
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					initialitems.add(item);
					editor.refreshSkilleditor();
				}
			}

		
			
		});
		
		
	}

	public void setEditor(SkillEditor skillEditor) {
		editor=skillEditor;
		
	}
	public void removeSkill(SavedSkill skil) {
		savedskills.remove(skil);
		
	}
	@Override
	public Icon getImageIcon() {
		BufferedImage img;
		if(imagestring==null||imagestring.equals("")) {
			return null;
		}
		try {
			img = ImageIO.read(new File(imagestring));
			Image newimg = img.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			return new ImageIcon(newimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return classname;
	}

}
