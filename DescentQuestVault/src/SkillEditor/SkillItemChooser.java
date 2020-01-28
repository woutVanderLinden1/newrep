package SkillEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import ItemEditor.ItemEditor;
import ItemEditor.SavedItem;
import controller.commands.Utils;
import controller.stack.StackElements.MapEditStackElement;
import frame.SubContainer;
import misc.save.WorldSaveFile;

public class SkillItemChooser extends SubContainer{
	private SkillEditor edit;
	private JPanel itembuttonPanel;
	private ArrayList<SavedSkill> shownskills = new ArrayList<SavedSkill>();
	private SavedClass activeclass;

	public SkillItemChooser(int width, int height, SkillEditor edit) {
		super(width, height);
		this.edit =edit;
		this.setBackground(Color.cyan);
		 this.setLayout(new GridBagLayout());
		 
		 GridBagConstraints c = new GridBagConstraints();
		 JButton savebutton=new JButton("save class");
		 savebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.saveClass();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
	//	 c.fill = GridBagConstraints.HORIZONTAL;
		 c.ipady = 40;  
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 0;
		 this.add(savebutton, c);
		 JButton newbutton=new JButton("new skill");
		 newbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newSkill();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 1;
		 c.gridy = 0;
		 this.add(newbutton, c);
		 JButton newclassbutton=new JButton("new class");
		 newclassbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newClass();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 2;
		 c.gridy = 0;
		 this.add(newclassbutton, c);
		 JButton loadclassbutton=new JButton("load class");
		 loadclassbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.setClass(loadClass());
				
			}

			
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 3;
		 c.gridy = 0;
		 this.add(loadclassbutton, c);
		 //add save buttonand others
		 //add actselecter
		 //JComboBox box=new JComboBox();
		 
		 //itembox
		 itembuttonPanel =new JPanel();
		 itembuttonPanel.setBackground(Color.black);
		 itembuttonPanel.setLayout(new GridLayout(20,3));
		 
		 itembuttonPanel.setMinimumSize(new Dimension( 360,600));
		 itembuttonPanel.setSize(360,600);
		 itembuttonPanel.setPreferredSize(new Dimension(360,600));
		 JScrollPane pane=new JScrollPane(itembuttonPanel);
		 pane.setVerticalScrollBarPolicy(
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 pane.setMinimumSize(new Dimension(360,600));
		 pane.setSize(new Dimension(360,600));
		 pane.setPreferredSize(new Dimension(360,600));
		 c.fill = GridBagConstraints.BOTH;
		 c.insets = new Insets(10,5,0,0);  //top padding
		 c.weightx = 0.5;
		 c.gridx = 2;
		 c.gridy = 0;
		 this.add(itembuttonPanel, c);
		 this.refreshItemButttonPanel();
		 //this.pack();
		// TODO Auto-generated constructor stub
	}

	public void refreshItemButttonPanel() {
		itembuttonPanel.removeAll();
		int added=0;
		
		if(activeclass!=null) {
			shownskills=activeclass.getSavedskills();
		}
		/*
	String directoryPath= System.getProperty("user.dir")+"/Items";
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
				    shownitems.add(i);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	      }
	    }
	  }
	  */
	  itembuttonPanel.removeAll();
	  int nrofadded=added/3;
	  Dimension newsize=new Dimension(350,nrofadded*140);
	  itembuttonPanel.setPreferredSize(newsize);
	  itembuttonPanel.setMinimumSize(newsize);
	  itembuttonPanel.setSize(newsize);
	  itembuttonPanel.setLayout(new GridBagLayout());
	  GridBagConstraints c = new GridBagConstraints();
	  int y=0;
	  int x=0;
	  for(SavedSkill it:shownskills) {
		  JButton button=new JButton(it.getSkillname());
		  button.setPreferredSize(new Dimension(140,140));
		  button.setMaximumSize(new Dimension(140,140));
		  button.setMinimumSize(new Dimension(140,140));
		  button.setSize(new Dimension(140,140));
		   button.setVerticalTextPosition(SwingConstants.BOTTOM);
		   button.setHorizontalTextPosition(SwingConstants.CENTER);
		  button.setIcon(it.getImageIcon());
		  c.insets = new Insets(0,0,0,0);  //top padding
		 
		  c.weighty = 1;
		  
		  
			 c.anchor = GridBagConstraints.FIRST_LINE_START;
			 c.gridx = x;
			 c.gridy = y;
		  itembuttonPanel.add(button,c);
		  button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				edit.setSkill(it);
				
			}
			  
		  });
		  x++;
		  if(x>2) {
			  x=0;
			  y++;
		  }
	  }
	
	}

	public SkillItemChooser(Dimension dimension,SkillEditor edit) {
		super(dimension);
		this.edit =edit;
		this.setBackground(Color.cyan);
		 this.setLayout(new GridBagLayout());
		 
		 GridBagConstraints c = new GridBagConstraints();
		 JButton savebutton=new JButton("save class");
		 savebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.saveClass();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		 c.fill = GridBagConstraints.HORIZONTAL;
		 
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 0;
		 this.add(savebutton, c);
		 JButton newbutton=new JButton("new skill");
		 newbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newSkill();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		 c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 1;
		 c.gridy = 0;
		 this.add(newbutton, c);
		 JButton newclassbutton=new JButton("new class");
		 newclassbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newClass();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 2;
		 c.gridy = 0;
		 this.add(newclassbutton, c);
		 JButton loadclassbutton=new JButton("load class");
		 loadclassbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.setClass(loadClass());
				
			}

			
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 3;
		 c.gridy = 0;
		 this.add(loadclassbutton, c);
		 //add save buttonand others
		 //add actselecter
		 //JComboBox box=new JComboBox();
		 
		 //itembox
		 itembuttonPanel =new JPanel();
		 itembuttonPanel.setBackground(Color.gray);
		 itembuttonPanel.setLayout(new GridLayout(20,3));
		 this.refreshItemButttonPanel();
		 itembuttonPanel.setMinimumSize(new Dimension( 350,1800));
		 itembuttonPanel.setSize(350,1800);
		 itembuttonPanel.setPreferredSize(new Dimension(350,1800));
		 JScrollPane pane=new JScrollPane(itembuttonPanel);
		 pane.setVerticalScrollBarPolicy(
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 pane.setMinimumSize(new Dimension(350,600));
		 pane.setSize(new Dimension(350,600));
		 pane.setPreferredSize(new Dimension(350,600));
		 c.fill = GridBagConstraints.BOTH;
		 c.insets = new Insets(10,5,0,0);  //top padding
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 1;
		 c.gridwidth = 4;
		 c.ipady = 40; 
		 this.add(pane, c);
		 //this.pack();
		// TODO Auto-generated constructor stub
		 
	}
	private SavedClass loadClass() {
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
		File workingDirectory = new File(System.getProperty("user.dir")+"/Classes");
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(this);
		File f=fc.getSelectedFile();
		if(f!=null) {
			
			SavedClass g=null;
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (SavedClass) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return g;
		}
		return null;
	      
	
	
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	public void setClass(SavedClass currentclass2) {
		// TODO Auto-generated method stub
		activeclass=currentclass2;
	}

}
