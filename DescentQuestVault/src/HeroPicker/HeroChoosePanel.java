package HeroPicker;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import HeroEditor.SavedHero;
import ItemEditor.ActionTaker;
import frame.SubContainer;

public class HeroChoosePanel extends VerticalButtonContainer<SavedHero> {

	private HeroPicker picker;
	
	
	
	
	public HeroChoosePanel(Dimension defaultSize, HeroPicker heroPicker,ArrayList<SavedHero> heroes, ActionTaker<SavedHero> action) {
		super(defaultSize,heroes,action);
		picker=heroPicker;
		
		
		
		
	}




	













}
