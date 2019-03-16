package controller.commands.Game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewToken;
import view.game.GameToken;

public class RemoveGameTokenCommand extends BasicCommand implements ICommand {

	private GameToken token;
	
	public RemoveGameTokenCommand(GameToken toremove) {
		token=toremove;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		view.removeGameToken(token);
	}

}
