package HeroPicker;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import HeroEditor.SavedHero;
import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;
import Shop.ItemShop.ImageHolder;
import SkillEditor.SavedClass;
import frame.SubContainer;

public class VerticalButtonContainer<P extends ImageHolder> extends SubContainer {
	
	private P currentSelected;
	private ArrayList<P> previousSelected=new ArrayList<P>();
	private JPanel itemButtonPanel;
	private ArrayList<P> items=new ArrayList<P>();
	private ActionTaker act;

	public VerticalButtonContainer(Dimension defaultSize, ArrayList<P> heroes, ActionTaker<P> action) {
		super(defaultSize);
		act=action;
		if(heroes!=null) {
			items=heroes;
		}
	
		 itemButtonPanel =new JPanel();
		 itemButtonPanel.setBackground(Color.gray);
		 itemButtonPanel.setLayout(new GridLayout(20,3));
		 this.refreshItemButtonPanel();
		 itemButtonPanel.setMinimumSize(new Dimension( 140,600));
		 itemButtonPanel.setSize(140,600);
		 itemButtonPanel.setPreferredSize(new Dimension(140,600));
		 JScrollPane pane=new JScrollPane(itemButtonPanel);
		 pane.setVerticalScrollBarPolicy(
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 pane.setMinimumSize(new Dimension(160,600));
		 pane.setSize(new Dimension(160,600));
		 pane.setPreferredSize(new Dimension(160,600));
		 
		 this.add(pane);
	}


	public void refreshItemButtonPanel() {
		itemButtonPanel.removeAll();
		

	  
	  itemButtonPanel.removeAll();
	  
	  Dimension newsize=new Dimension(140,Math.max(items.size()*140,280));
	  itemButtonPanel.setPreferredSize(newsize);
	  itemButtonPanel.setMinimumSize(newsize);
	  itemButtonPanel.setSize(newsize);
	  itemButtonPanel.setLayout(new GridBagLayout());
	  GridBagConstraints c = new GridBagConstraints();
	  int y=0;
	  int x=0;
	  for(P it:items) {
		
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
		  itemButtonPanel.add(button,c);
		  if(previousSelected.contains(it)) {
			  button.setBackground(Color.RED);
			  button.setEnabled(false);
		  }
		  else {
			  if(currentSelected==it) {
				  button.setBackground(Color.BLUE);
				  button.setEnabled(false);
			  }
			  else {
				  button.addActionListener(new ActionListener() {
	
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							act.perform(it);
						}
						  
					  });
			  }
		  }
		 
		  y++;
		 
	  }
		
	}


	public void setCurrent(P hero) {
	
		previousSelected.remove(hero);
		currentSelected=hero;
		this.refreshItemButtonPanel();
	}

	public void storeCurrent() {
		// TODO Auto-generated method stub
		previousSelected.add(currentSelected);
	}

	public void setButtonList(ArrayList<P> newlist) {
		items=newlist;
		
	}

	
}
