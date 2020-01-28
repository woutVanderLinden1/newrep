package ItemEditor;

import java.awt.Color;
import java.awt.Dimension;

import frame.SubContainer;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.ItemInfoContainer;

public class FeatureEditor  extends SubContainer{
	private SavedItem item;
	private ItemInfoContainer box;

	public FeatureEditor(int width, int height) {
		super(width, height);
		this.setBackground(Color.yellow);
		
		box=new ItemInfoContainer(300,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}
	public FeatureEditor(Dimension dimension) {
		super(dimension);
		this.setBackground(Color.yellow);
		box=new ItemInfoContainer(330,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}

	public void initialiseItem() {
		box.removeAll();
		//add all features of te item
		item.addItemSpecifics(box);
		
	}
	
	public void setItem(SavedItem newitem) {
		item=newitem;
		initialiseItem();
	}
	
}
