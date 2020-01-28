package view.Items.Map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.commons.io.FilenameUtils;

import ItemEditor.ActionTaker;
import controller.commands.BasicCommand;
import misc.Tools;
import misc.save.WorldSaveFile;

public class SaveAsSubQuestCommand extends BasicCommand {
	
	private Component com;
	private MapLocation maploc;
	private MapFrame frame;

	public SaveAsSubQuestCommand(MapPanel map) {
		com=map;
	}

	@Override
	public void perform() {
		frame=new MapFrame(new ActionTaker<MapLocation>() {

			@Override
			public void perform(MapLocation value) {
				maploc=value;
				closeFrame();
				frame.dispose();
			
			}

		
		});
		frame.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	        		SaveSubQuest();
	                
	              
	              
	            }
	        });
		frame.setVisible(true);
		
		

	}
	public static String[] textAreaDialog(Object obj,String title) {
	    if(title == null) {
	        title = "Your input";
	    }
	    
	    JPanel textPanel=new JPanel();
	    
	    textPanel.setLayout(new GridBagLayout());
	    JLabel name=new JLabel("name");
	    name.setMaximumSize(new Dimension(100,15));
	    JTextField panel=new JTextField();
	    panel.setMaximumSize(new Dimension(340,20));
	    panel.setPreferredSize(new Dimension(340,20));
	    JLabel descript=new JLabel("description");
	    descript.setMaximumSize(new Dimension(100,15));
	    JTextArea textArea = new JTextArea();
	    textArea.setColumns(30);
	    textArea.setRows(10);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setSize(textArea.getPreferredSize().width, textArea.getPreferredSize().height);
	    JScrollPane pane=new JScrollPane(textArea);
	    

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
	    textPanel.add(name,c);
	    c.gridx = 0;
        c.gridy = 1;
	    textPanel.add(panel,c);
	    c.gridx = 0;
        c.gridy = 2;
	    textPanel.add(descript,c);
	    c.gridx = 0;
        c.gridy = 3;
	    textPanel.add(pane,c);
	   
	    int ret = JOptionPane.showConfirmDialog((Component) obj, textPanel, title, JOptionPane.OK_OPTION);
	    if (ret == 0) {
	    	String[] toreturn={panel.getText(),textArea.getText()};
	        return	toreturn;
	    } else {
	       // MyDialogs.Toast("Canceled by user\nChanges not saved", "Your choise");
	    }
	    return null;
	} 
	public static void main(String[] args) {
		textAreaDialog(null,"try");
	}
	public void SaveSubQuest(){
		
		//add questname+description
		String[] texts=null;
	   while (texts==null) {
		   texts=textAreaDialog(frame,"Enter name and description");
	   }
		
		
		
		SubQuestFile file=new SubQuestFile(view.saveGame(),maploc);
		file.setName(texts[0]);
		file.setDescription(texts[1]);
		final JFileChooser fc = new JFileChooser();
		 File workingDirectory = new File(System.getProperty("user.dir")+"/SubQuests");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showSaveDialog(com);
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
	}
	private void closeFrame() {
		// TODO Auto-generated method stub
		frame.setVisible(false);
		SaveSubQuest();
	}
	

}
