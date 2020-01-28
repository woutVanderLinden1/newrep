package HeroEditor;

import java.awt.Color;
import java.awt.Dimension;

import ItemEditor.SavedItem;
import frame.SubContainer;
import view.viewItems.ItemBox.ItemInfoContainer;

public class HeroFeatureEditor extends SubContainer {
	private SavedHero hero;
	private ItemInfoContainer box;

	public HeroFeatureEditor(int width, int height) {
		super(width, height);
		this.setBackground(Color.yellow);
		
		box=new ItemInfoContainer(300,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}
	public HeroFeatureEditor(Dimension dimension) {
		super(dimension);
		this.setBackground(Color.yellow);
		box=new ItemInfoContainer(330,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}

	public void initialiseItem() {
		box.removeAll();
		//add all features of te item
		hero.addItemSpecifics(box);
		
	}
	

	public void setHero(SavedHero it) {
		// TODO Auto-generated method stub
		hero=it;
		initialiseItem();
	}
	
}
