package controller.commands;

import model.event.EventTriggerStack;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextStop;

public class ContinueCommand extends BasicCommand {

	public StopAble stop;
	
	public ContinueCommand(StopAble textStop) {
		stop=textStop;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		//stop.continueStop();
		// TODO Auto-generated method stub
		EventTriggerStack stack=EventTriggerStack.getTriggerStack();
		stack.triggerNextStackEvent();
	}

}
