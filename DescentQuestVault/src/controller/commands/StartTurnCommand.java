package controller.commands;

public class StartTurnCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.startTurns();
	}

}
