package HeroEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.FilenameUtils;

import ItemEditor.ImageChooser;
import SkillEditor.HeroType;
import frame.MainFrame;
import misc.save.WorldSaveFile;

public class HeroEditor extends MainFrame {

	private SavedHero currentHero;
	private HeroChooser chooser;
	private ImageChooser imagechooser;
	private HeroFeatureEditor editor;
	
	public HeroEditor() {
		super();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1200,880);
		this.setMinimumSize(new Dimension(1200,880));
		this.setPreferredSize(new Dimension(1200,880));
		this.setVisible(true);
		this.setName("HeroEditor");
		this.setTitle("HeroEditor");
		
		 chooser=new HeroChooser(new Dimension(390,800),this);
		chooser.setMinimumSize(new Dimension(390,800));
		 imagechooser=new ImageChooser(new Dimension(200,800));
		imagechooser.setMinimumSize(new Dimension(200,800));
	 editor=new HeroFeatureEditor(new Dimension(390,800));
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

	public void saveHero() {
		if(currentHero==null) {
			return;
		}
		currentHero.setImagestring(imagechooser.getImageString());
		final JFileChooser fc = new JFileChooser();
		 File newfile= new File(System.getProperty("user.dir")+"/Heroes//"+currentHero.getName());
		//fc.setCurrentDirectory(workingDirectory);
		//int returnVal = fc.showSaveDialog(this);
		//  File newfile = fc.getSelectedFile();
		  if(newfile==null) {
			  return;
		  }
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
			         out.writeObject(currentHero);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in "+ newfile.toString());
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
		  }
		  chooser.refreshItemButttonPanel();
		  this.revalidate();
		  this.repaint();
		
	}

	public void newHero() {
		saveHero();
		currentHero=new SavedHero("item", "",HeroType.WARRIOR);
		setHero(currentHero);
		chooser.refreshItemButttonPanel();
		this.revalidate();
		this.repaint();
		
		
	}

	public void setHero(SavedHero it) {
		currentHero=it;
		editor.setHero(it);
		imagechooser.setImage(it.getImagestring());
		chooser.refreshItemButttonPanel();
		this.revalidate();
		this.repaint();
	}
}
