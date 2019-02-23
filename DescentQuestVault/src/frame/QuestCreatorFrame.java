package frame;

import controller.UserInputController;

public class QuestCreatorFrame extends MainFrame {

	
	public QuestCreatorFrame() {
		userInput=UserInputController.createUserInputController(null, viewMan,this);
		viewMan.prepareInitialMenu();
		
	}
}
