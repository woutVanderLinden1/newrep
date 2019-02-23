package controller.commands.Game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewToken;

public class RemoveGameTokenCommand extends BasicCommand implements ICommand {

	private ViewToken token;
	
	public RemoveGameTokenCommand(ViewToken toremove) {
		token=toremove;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.removeGameToken(token);
	}

}
