package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import misc.ActivateAble;
import model.Activation;

public class RemoveActivationFromMapItemCommand extends BasicCommand implements ICommand {

	private ActivateAble active;
	private Activation activation;
	
	public RemoveActivationFromMapItemCommand(ActivateAble hold, Activation act) {
		// TODO Auto-generated constructor stub
		active=hold;
		activation=act;
	}

	@Override
	public void perform() {
		
		view.removeActivationFromActivateAble(activation,active);
	}
}
