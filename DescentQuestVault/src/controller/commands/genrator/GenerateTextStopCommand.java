package controller.commands.genrator;

import controller.UserInputController;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.stack.MainStackManager;
import controller.stack.SideStackManager;
import controller.stack.StackElements.IStackElement;
import model.IModel;
import model.event.extraevents.TextStop;
import model.event.modifier.Modifier;
import view.IView;

public class GenerateTextStopCommand extends BasicCommand {

	@Override
	public void perform() {
		control.performCommand(new AddEventToTriggerFieldCommand(new TextStop(),control.getSelected()));

	}
	
	public String getStringName() {
		return "create textstop";
	}

}
