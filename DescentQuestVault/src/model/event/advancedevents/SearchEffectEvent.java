package model.event.advancedevents;

import java.util.Random;

import controller.UserInputController;
import controller.commands.AddGoldCommand;
import model.ItemController;
import model.event.Event;
import model.event.Univent;
import model.event.extraevents.TextStop;
import view.menu.QuestCreator;


public class SearchEffectEvent extends Event {
	Random rand=new Random();
	
	public SearchEffectEvent() {
		this.setName("searcheffect");
		this.setIDName("searcheffect");
	}
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean isStopEvent() {
		return true;
	}

	public void trigger() {
		//add gold based on player fame
		//trigger text associated
		int fame=ItemController.getItemController().getFame().getTheInteger();
		int goldvalue =0;
		if(fame<4) {
		
			goldvalue=rand.nextInt(8);
		}
		if(fame>=4&&fame<8) {
			
			goldvalue=rand.nextInt(10)+2;
		}
		if(fame>=8&&fame<12) {
			
			goldvalue=rand.nextInt(12)+4;
		}
		if(fame>=12&&fame<20) {
			
			goldvalue=rand.nextInt(16)+6;
		}
		if(fame>=20) {
			
			goldvalue=rand.nextInt(20)+10;
		}
		AddGoldCommand mand=new AddGoldCommand(goldvalue);
		TextStop stop=new TextStop("you found "+goldvalue+" gold and a search card");
		stop.trigger();
		UserInputController control= UserInputController.getController();
		control.performCommand(mand);
	}
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new SearchEffectEvent();
	}

}
