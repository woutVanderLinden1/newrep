package controller.commands;

import java.awt.event.ActionListener;

import controller.commands.select.SelectCommand;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextTrigger;

public class CreateNewOptionCommand extends BasicCommand implements ICommand {

	private TextTrigger trig;
	private TextOption opt;
	
	
	public CreateNewOptionCommand(TextTrigger trig, TextOption textOption) {
		this.trig=trig;
		opt=textOption;
	}

	@Override
	public void perform() {
		trig.addTextOption(opt);
		view.refreshSelected();
		

	}

}
