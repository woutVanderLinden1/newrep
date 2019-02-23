package controller.commands;

public class FillExitCommand extends BasicCommand implements ICommand {

	
	public void perform() {
		view.fillExits();
	}
}
