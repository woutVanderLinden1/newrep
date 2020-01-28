package Shop.ItemShop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;
import frame.SubContainer;

public class InteractionPanel<T extends ImageHolder> extends SubContainer {

	private JPanel itembuttonpanel;
	private ArrayList<T> items;
	private ActionTaker actionTaker;
	


	public InteractionPanel(String string, Dimension dimension, ArrayList<T> equipment, ActionTaker<T> actionTaker) {
		super(dimension);
		items=equipment;
		this.actionTaker=actionTaker;
		JLabel label=new JLabel();
		label.setText(string);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		this.add(label,c);
		itembuttonpanel=new JPanel();
	//	 itembuttonpanel.setBackground(Color.black);
		Dimension dim=new Dimension( dimension.width-40,160);
		 
		 itembuttonpanel.setMinimumSize(dim);
		 itembuttonpanel.setSize(dim);
		 itembuttonpanel.setPreferredSize(dim);
		 JScrollPane pane=new JScrollPane(itembuttonpanel);
		 pane.setHorizontalScrollBarPolicy(
				   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 pane.setMinimumSize(dim);
		 pane.setSize(dim);
		 pane.setPreferredSize(dim);
		 c.gridx=0;
			c.gridy=1;
		 this.add(pane,c);
		
	}

	public void initializeShopPanel() {
		itembuttonpanel.removeAll();
		 
		  Dimension newsize=new Dimension(140*items.size(),160);
		  itembuttonpanel.setPreferredSize(newsize);
		  itembuttonpanel.setMinimumSize(newsize);
		  itembuttonpanel.setSize(newsize);
		  itembuttonpanel.setLayout(new GridBagLayout());
		  
		  GridBagConstraints c = new GridBagConstraints();
		  int y=0;
		  int x=0;
		  for(T it:items) {
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
			  itembuttonpanel.add(button,c);
			  button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					actionTaker.perform(it);
				}
				  
			  });
			  x++;
			  if(x>2) {
				  x=0;
				  y++;
			  }
		  }
	}
}
