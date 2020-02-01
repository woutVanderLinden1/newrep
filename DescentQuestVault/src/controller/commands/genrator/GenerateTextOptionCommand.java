package controller.commands.genrator;

import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.commands.select.AddMultiTriggerToTriggerFieldCommand;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import model.event.extraevents.TestOption;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextStop;
import model.event.extraevents.TextTrigger;
import view.IView;

public class GenerateTextOptionCommand extends BasicCommand {


	@Override
	public void perform() {
		ArrayList<TextOption> textop=new ArrayList<TextOption>();
		textop.add(new TextOption("hi"));
		textop.add(new TextOption("bye"));
		control.performCommand(new AddTriggerToTriggerFieldCommand(new TextTrigger(0,textop,"pls enter text"),control.getSelected()));

		//control.performCommand(new AddTriggerToTriggerFieldCommand(new TextTrigger(0,new ArrayList<TextOption>(),"pls enter text"),null));

	}
	
	public String getStringName() {
		return "create textoption";
	}

	
}
