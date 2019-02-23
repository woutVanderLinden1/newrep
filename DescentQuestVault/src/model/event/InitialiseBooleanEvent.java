package model.event;

import view.menu.QuestCreator;

public class InitialiseBooleanEvent extends Event {

	@Override
	public void initialise(QuestCreator questCreator) {
		
		
	}
	
	
	
	public Univent copy() {
		return new InitialiseBooleanEvent();
		
	}

}
