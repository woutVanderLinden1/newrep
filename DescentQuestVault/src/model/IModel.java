package model;

import model.values.CustomInteger;

public class IModel {

	public void addStartingValues(CustomInteger... hope) {
		//add gold
		//add hope
		//Add fame
		//add peril
		//add despair
	
		ItemController control=ItemController.getItemController();
		control.addAllValues(hope);
	
	}

}
