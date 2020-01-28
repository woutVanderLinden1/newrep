package HeroPicker;

import java.awt.Dimension;
import java.util.ArrayList;

import HeroEditor.SavedHero;
import ItemEditor.ActionTaker;
import frame.SubContainer;
import model.Hero.Hero;

public class PlayerChoosePanel extends VerticalButtonContainer<Hero> {

	private HeroPicker picker;
	
	public PlayerChoosePanel(Dimension defaultSize,HeroPicker picker, ArrayList<Hero> herolist, ActionTaker<Hero> actionTaker) {
		super(defaultSize, herolist, actionTaker);
		this.picker=picker;
	}


}
