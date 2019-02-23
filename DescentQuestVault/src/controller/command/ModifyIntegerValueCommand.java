package controller.command;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.values.CustomInteger;
import model.values.Modification;

public class ModifyIntegerValueCommand extends BasicCommand implements ICommand {
	
	private int modvalue;
	private Modification mod;
	private CustomInteger integer;

	public ModifyIntegerValueCommand(int modvalue, Modification mod, CustomInteger item) {
		this.modvalue=modvalue;
		this.mod=mod;
		integer=item;
		
	}

	@Override
	public void perform() {
		
		integer.setTheInteger(mod.calculate(modvalue,integer.getTheInteger()));

	}

}
