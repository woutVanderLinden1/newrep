package view.events;

import ItemEditor.ActionTaker;
import model.ItemController;
import model.event.Event;
import model.event.Univent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public class AddExpToHeroesEvent extends Event {
	
	private int amount;
	private static int nr;

	public AddExpToHeroesEvent(int amount2) {
		this.changeName(("AddExpEvent"+nr++));
		this.setIDName(name);
		amount=amount2;
	}
	
	
	@Override
	public void initialise(QuestCreator questCreator) {
		

	}
	@Override
	public void trigger() {
		ItemController control=ItemController.getItemController();
		if(control.getCampaignFile()!=null) {
			control.getCampaignFile().addHeroExp(amount);
		}
		
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new AddExpToHeroesEvent(amount);
	}

	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		itemInfoText.addNumericEditButton("Amount", itemInfoText, amount, new ActionTaker<Integer>() {

			@Override
			public void perform(Integer value) {
				amount=value;
			}
			
		});
	}


}
