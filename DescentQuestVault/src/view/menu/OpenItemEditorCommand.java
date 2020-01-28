package view.menu;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class OpenItemEditorCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.openItemEditor();
	}

}
