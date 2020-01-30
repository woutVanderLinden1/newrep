package SkillEditor;

import view.viewItems.ItemBox.ItemInfoContainer;

public class ClassSkillCard extends SavedSkill {
	
	
	private SavedClass clas;

	public ClassSkillCard(SavedClass savedClass) {
		super("Class", "", 0,savedClass);
		this.setSkillname(savedClass.getClassname());
		clas=savedClass;
	}
	public void addItemSpecifics(ItemInfoContainer box) {
		clas.addItemSpecifics(box);
	}
	

}
