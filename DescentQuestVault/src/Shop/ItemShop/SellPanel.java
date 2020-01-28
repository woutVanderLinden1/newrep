package Shop.ItemShop;

import java.awt.Dimension;
import java.util.ArrayList;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;

public class SellPanel<T extends ImageHolder> extends InteractionPanel<T> {


	public SellPanel(String string, Dimension dimension, ArrayList<T> equipment, ActionTaker<T> actionTaker) {
		super(string,dimension,equipment,actionTaker);// TODO Auto-generated constructor stub
		
	
	}

}
