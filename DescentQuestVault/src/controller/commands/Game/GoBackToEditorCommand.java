package controller.commands.Game;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class GoBackToEditorCommand extends BasicCommand implements ICommand {

	public void perform() {
		System.out.println("gone back");
		control.endGame();
	}
}
