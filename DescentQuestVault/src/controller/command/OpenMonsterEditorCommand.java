package controller.command;

import java.awt.event.ActionListener;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.Monster.Monster;

public class OpenMonsterEditorCommand extends BasicCommand implements ICommand {

	
	private Monster mon;
	public OpenMonsterEditorCommand(Monster mon) {
		this.mon=mon;
	}

	public OpenMonsterEditorCommand() {
		
	}

	@Override
	public void perform() {
		if(mon!=null) {
			control.openMonsterEditor(mon);
		}
		else {
			control.openMonsterEditor();
		}
		

	}

}
