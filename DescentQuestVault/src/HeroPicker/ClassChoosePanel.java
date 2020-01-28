package HeroPicker;

import java.awt.Dimension;
import java.util.ArrayList;

import ItemEditor.ActionTaker;
import SkillEditor.SavedClass;
import frame.SubContainer;

public class ClassChoosePanel extends VerticalButtonContainer<SavedClass>{

	private HeroPicker picker;
	public ClassChoosePanel(Dimension defaultSize, HeroPicker picker,ArrayList<SavedClass> items,ActionTaker act) {
		super(defaultSize, items, act);
		this.picker=picker;
	}


}
