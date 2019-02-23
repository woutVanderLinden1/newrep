package controller.commands;

import model.values.CustomBoolean;

public class SetBooleanValueCommand extends BasicCommand implements ICommand {
	
	private boolean newvalue;
	private CustomBoolean toset;
	

	public SetBooleanValueCommand(boolean value, CustomBoolean toset) {
		// TODO Auto-generated constructor stub
		this.toset=toset;
		newvalue=value;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		toset.setValue(newvalue);
	}

}
