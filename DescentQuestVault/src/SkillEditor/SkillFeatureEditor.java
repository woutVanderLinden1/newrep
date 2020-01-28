package SkillEditor;

import java.awt.Color;
import java.awt.Dimension;

import ItemEditor.SavedItem;
import frame.SubContainer;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SkillFeatureEditor extends SubContainer{

	private ItemInfoContainer box;
	private SavedClass clas;
	private SavedSkill skil;

	public SkillFeatureEditor(int width, int height) {
		super(width, height);
		this.setBackground(Color.yellow);
		
		box=new ItemInfoContainer(300,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}
	public SkillFeatureEditor(Dimension dimension) {
		super(dimension);
		this.setBackground(Color.yellow);
		box=new ItemInfoContainer(330,700);
		this.add(box);
		// TODO Auto-generated constructor stub
	}

	public void initialiseSkill() {
		box.removeAll();
		//add all features of te item
		skil.addItemSpecifics(box);
		
	}
	
	
	public void setClass(SavedClass currentclass2) {
		clas=currentclass2;
		skil=clas.getClasscard();
		initialiseSkill();
		
	}
	public void setSkill(SavedSkill it) {
		skil=it;
		initialiseSkill();
	}
	public void refresh() {
		this.initialiseSkill();
		
	}
	


}
