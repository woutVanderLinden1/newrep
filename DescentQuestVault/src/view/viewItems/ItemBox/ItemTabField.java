package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import frame.SubContainer;

/**
 * hols the tab for items and allow new kinds of items to be chosen
 * @author User
 *
 */

public class ItemTabField  extends SubContainer implements Serializable{
	
	private JLabel label;
	protected JComboBox<ItemOptions> box;
	protected ListContainer list;

	public ItemTabField(int width, int height) {
		super(width, height);
		this.setBackground(Color.CYAN);
		label=new JLabel("item:");
		label.setLocation(width/2-40, height/2-20);
		this.add(label);
		initialiseBox();
			list=new ListContainer(this.getWidth()-10,100);
		list.addItems(box);
		//list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//list.setLayoutOrientation(JList.VERTICAL);
		//list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_NEVER,
    	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listScroller.setPreferredSize(new Dimension(this.getWidth()-30, 130));
		this.add(listScroller);
	}

	public void initialiseBox() {
		ItemOptions[] comboOptions = { ItemOptions.Essentials,ItemOptions.CoreSetOutdoor,ItemOptions.CoreSetIndoor, ItemOptions.Square, ItemOptions.Monster, ItemOptions.Event, ItemOptions.Token,ItemOptions.Door,ItemOptions.Text,ItemOptions.Value ,ItemOptions.Generator};
		//maybe allow creation of new groups
		box=new JComboBox<ItemOptions>(comboOptions) {
			public void actionPerformed(ActionEvent e) {
				list.addItems(box);
		    }
		};
		box.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				list.addItems(box);
		    }
		});
		this.add(box);
		box.setLocation(this.getWidth()/2-40, this.getHeight()/2);
	
	}

	public void renewItemList() {
		// TODO Auto-generated method stub
		list.addItems(box);
	}
	
	
	

}
