package controller.commands;

import controller.stack.StackElements.MapEditStackElement;
import model.IModel;
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
		model.addStartingValues(hope);
		view.addStartTriggers(hope);
		
		mainStack.addStackElement(new MapEditStackElement());
	}

}
