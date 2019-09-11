package model;

import misc.CampaignFile;
import model.values.CustomInteger;

public class IModel {

	public void addStartingValues(CampaignFile file) {
		//add gold
		//add hope
		//Add fame
		//add peril
		//add despair
		
		ItemController control=ItemController.getItemController();
		control.addStartingValues(file);
	
	}

}
