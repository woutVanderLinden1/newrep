package view.menu;

import controller.commands.BasicCommand;
import controller.commands.ICommand;

public class OpenHeroEditorCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.openHeroEditor();
	}

}
