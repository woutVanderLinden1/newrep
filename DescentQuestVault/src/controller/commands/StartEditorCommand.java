package controller.commands;

import controller.stack.StackElements.MapEditStackElement;

public class StartEditorCommand extends BasicCommand implements ICommand {

	@Override
	public void perform() {
		System.out.println("performed yeah");
		view.startQuestEditor();
		view.addStartUpTrigger();
		mainStack.addStackElement(new MapEditStackElement());
	}

}
