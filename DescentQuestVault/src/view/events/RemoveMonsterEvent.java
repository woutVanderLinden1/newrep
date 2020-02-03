package view.events;

import controller.UserInputController;
import controller.command.game.RemoveGameMonsterCommand;
import model.event.Event;
import model.event.Univent;
import view.Items.Map.ViewMonster;
import view.game.GameMonster;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class RemoveMonsterEvent extends Event implements NameChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 706092979699120364L;
	private ViewMonster toremove;
	private transient GameMonster gameremove;

	public RemoveMonsterEvent(ViewMonster viewMonster) {
		toremove=viewMonster;
		this.setName("remove " +toremove.getName());
		viewMonster.addNameChangeListener(this);
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}

	public void trigger() {
		UserInputController control=UserInputController.getController();
		System.out.println(this);
	
		control.performCommand(new RemoveGameMonsterCommand(gameremove));
		
	}
	
	@Override
	public Univent copy() {
		
		return null;
	}

	@Override
	public void nameChanged(String newname) {
		this.setName("remove " +toremove.getName());
	}

	public void setGameMonster(GameMonster gameMonster) {
	
		gameremove=gameMonster;
		System.out.println("setted gamemonster "+gameremove);
	}

	public GameMonster getGameMonster() {
		// TODO Auto-generated method stub
		return gameremove;
	}

}
