package ItemEditor;

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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import frame.SubContainer;
import misc.save.WorldSaveFile;

public class ItemChooser extends SubContainer{
	
	private ItemEditor edit;
	private JPanel itembuttonPanel;
	private ArrayList<SavedItem> shownitems = new ArrayList<SavedItem>();

	public ItemChooser(int width, int height, ItemEditor edit) {
		super(width, height);
		this.edit =edit;
		this.setBackground(Color.cyan);
		 this.setLayout(new GridBagLayout());
		 
		 GridBagConstraints c = new GridBagConstraints();
		 JButton savebutton=new JButton("save");
		 savebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.saveItem();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
	//	 c.fill = GridBagConstraints.HORIZONTAL;
		 c.ipady = 40;  
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 0;
		 this.add(savebutton, c);
		 JButton newbutton=new JButton("new");
		 newbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newItem();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		// c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 1;
		 c.gridy = 0;
		 this.add(newbutton, c);
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
		shownitems.clear();
		int added=0;
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
	  for(SavedItem it:shownitems) {
		  JButton button=new JButton(it.getName());
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
				edit.setItem(it);
			}
			  
		  });
		  x++;
		  if(x>2) {
			  x=0;
			  y++;
		  }
	  }
	
	}

	public ItemChooser(Dimension dimension,ItemEditor edit) {
		super(dimension);
		this.edit =edit;
		this.setBackground(Color.cyan);
		 this.setLayout(new GridBagLayout());
		 
		 GridBagConstraints c = new GridBagConstraints();
		 JButton savebutton=new JButton("save");
		 savebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.saveItem();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		 c.fill = GridBagConstraints.HORIZONTAL;
		 
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 0;
		 this.add(savebutton, c);
		 JButton newbutton=new JButton("new");
		 newbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit.newItem();
				
			}
			 
		 });
		 c.insets = new Insets(5,5,0,0);  //top padding
		 c.fill = GridBagConstraints.HORIZONTAL;
		 c.weightx = 0.5;
		 c.gridx = 1;
		 c.gridy = 0;
		 this.add(newbutton, c);
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
		 c.gridwidth = 3;
		 c.ipady = 40; 
		 this.add(pane, c);
		 //this.pack();
		// TODO Auto-generated constructor stub
		 
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
