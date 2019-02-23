package controller.commands.Game;

import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.Trigger;
import model.event.Univent;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextStop;

public class HoldToContinueCommand extends BasicCommand implements ICommand {
	
	private StopAble trigger;


	public HoldToContinueCommand(StopAble textStop) {
		this.trigger=textStop;
	
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.keepToContinue(trigger);
	}

}
