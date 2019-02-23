package controller.commands;

import model.event.extraevents.TextOption;
import model.event.extraevents.TextTrigger;

public class RemoveOptionCommand extends BasicCommand implements ICommand {
	
	private TextTrigger trig;
	private TextOption opt;

	public RemoveOptionCommand(TextTrigger trig, TextOption opt) {
		this.trig=trig;
		this.opt=opt;
	}

	@Override
	public void perform() {
		trig.removeTextOption(opt);
		view.refreshSelected();

	}

}
