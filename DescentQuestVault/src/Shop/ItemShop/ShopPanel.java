package Shop.ItemShop;

import java.awt.Dimension;
import java.util.ArrayList;

import ItemEditor.ActionTaker;
import ItemEditor.SavedItem;

public class ShopPanel<T extends ImageHolder> extends InteractionPanel<T>{



	public ShopPanel(String string, Dimension dimension, ArrayList<T> equipment, ActionTaker<T> actionTaker) {
		super(string, dimension,equipment,actionTaker);
	}

}
