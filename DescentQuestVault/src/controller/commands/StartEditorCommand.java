package controller.commands;

import controller.stack.StackElements.MapEditStackElement;
import model.IModel;
import model.ItemController;
import model.values.CustomInteger;

public class StartEditorCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		System.out.println("performed yeah");
		view.startQuestEditor();
		CustomInteger hope=new CustomInteger("Hope",2);
		CustomInteger peril=new CustomInteger("peril",0);
		CustomInteger fame=new CustomInteger("fame",0);
		CustomInteger gold=new CustomInteger("gold",0);
		CustomInteger despair=new CustomInteger("despair",0);
		model=new IModel();
		System.out.println("hope instance 1 "+hope);
		ItemController itcontrol=ItemController.getItemController();
		itcontrol.setHope(hope);
		itcontrol.setPeril(peril);
		itcontrol.setFame(fame);
		itcontrol.setGold(gold);
		itcontrol.setDespair(despair);
		model.addStartingValues(hope);
		view.addStartTriggers(hope);
		mainStack.addStackElement(new MapEditStackElement());
		control.startEditor();
	}

}
