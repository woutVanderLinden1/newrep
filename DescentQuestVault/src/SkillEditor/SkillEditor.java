package SkillEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.FilenameUtils;

import ItemEditor.FeatureEditor;
import ItemEditor.ImageChooser;
import ItemEditor.ItemChooser;
import ItemEditor.SavedItem;
import frame.MainFrame;

public class SkillEditor extends MainFrame  {

	private SavedClass currentclass;
	private SavedSkill currentskill;
	private SkillItemChooser chooser;
	private ImageChooser imagechooser;
	private SkillFeatureEditor editor;
	
	public SkillEditor() {
		super();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1200,880);
		this.setMinimumSize(new Dimension(1200,880));
		this.setPreferredSize(new Dimension(1200,880));
		this.setVisible(true);
		this.setName("SkillEditor");
		this.setTitle("SkillEditor");
		
		 chooser=new SkillItemChooser(new Dimension(390,800),this);
		chooser.setMinimumSize(new Dimension(390,800));
		 imagechooser=new ImageChooser(new Dimension(200,800));
		imagechooser.setMinimumSize(new Dimension(200,800));
	 editor=new SkillFeatureEditor(new Dimension(390,800));
		editor.setMinimumSize(new Dimension(0,800));
		
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,chooser,imagechooser);
		splitPane0.setPreferredSize(new Dimension(800,800));
		splitPane0.setMinimumSize(new Dimension(800,800));
		splitPane0.setSize(800,800);
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane0,editor);
		splitPane1.setPreferredSize(new Dimension(1200,800));
		splitPane1.setMinimumSize(new Dimension(1200,800));
		splitPane1.setSize(new Dimension(1200,800));
		splitPane1.setBackground(Color.black);
		lpane.add(splitPane1,2,2);
		lpane.moveToFront(splitPane1);
		setAlwaysOnTop(true);
	}

	public void saveClass() {
		if(currentclass==null) {
			return;
		}
		currentclass.setImagestring(imagechooser.getImageString());
		//final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/Classes");
		//fc.setCurrentDirectory(workingDirectory);
		//int returnVal = fc.showSaveDialog(this);
		  File newfile = new File(System.getProperty("user.dir")+"/Classes//"+currentclass.getClassname()+".ser");;
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
			         out.writeObject(currentclass);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in "+ newfile.toString());
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
		  }
		  chooser.refreshItemButttonPanel();
		
	}

	public void newClass() {
		saveClass();
		System.out.println("new class set");
		currentclass=new SavedClass("newclass",HeroType.WARRIOR, this);
		setClass(currentclass);
		chooser.refreshItemButttonPanel();
		this.revalidate();
		this.repaint();
		
		
	}
	public void setClass(SavedClass currentclass2) {
		currentclass2.setEditor(this);
		currentclass=currentclass2;
		editor.setClass(currentclass2);
		chooser.setClass(currentclass2);
		imagechooser.setImage(currentclass2.getImagestring());
		setSkill(currentclass2.getClasscard());
		chooser.refreshItemButttonPanel();
	}

	public void newSkill() {
		if(currentclass==null) {
			return;
		}
		saveClass();
		currentskill=new SavedSkill("skill","", 1,currentclass);
		setSkill(currentskill);
		currentclass.addSkill(currentskill);
		chooser.refreshItemButttonPanel();
		this.revalidate();
		this.repaint();
	}

	public void setSkill(SavedSkill it) {
		currentskill=it;
		editor.setSkill(it);
		imagechooser.setImage(it.getImagestring());
	}

	public void refreshSkilleditor() {
		editor.refresh();
		this.revalidate();
		this.repaint();
		
	}

	
}
