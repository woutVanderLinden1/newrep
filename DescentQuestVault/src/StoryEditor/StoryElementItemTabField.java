package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ItemTabField;

public class StoryElementItemTabField extends JPanel{

	private StoryListContainer container;
	
	public StoryElementItemTabField(int width, int height) {
		super.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.red);
		container=new StoryListContainer(500,110);
		JScrollPane listScroller = new JScrollPane(container,JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	    	    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			listScroller.setPreferredSize(new Dimension(width-30, 110));
		this.add(listScroller);
		
	}
	
	


}
