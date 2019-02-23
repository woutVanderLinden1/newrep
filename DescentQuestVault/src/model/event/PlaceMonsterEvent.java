package model.event;

import controller.commands.ICommand;
import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.PlaceGameMonsterCommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class PlaceMonsterEvent extends Event implements NameChangeListener {
	
	private ViewMonster viewmonster;
	private ICommand command;
	private boolean namebased=true;
	
	

	public PlaceMonsterEvent(ViewMonster viewMonster) {
		viewmonster=viewMonster;
		setCommand(new PlaceGameMonsterCommand(viewMonster));
		commands.add(command);
		setIDName("placemonster "+ viewmonster.getIDName());
		setName("place monster "+ viewmonster.getName());
		viewMonster.addNameChangeListener(this);
	}

	public ViewMonster getViewmonster() {
		return viewmonster;
	}

	public void setViewmonster(ViewMonster viewmonster) {
		this.viewmonster = viewmonster;
	}

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public boolean isNamebased() {
		return namebased;
	}

	public void setNamebased(boolean namebased) {
		this.namebased = namebased;
	}

	public void select() {
		super.select();
		viewmonster.select();
	}
	public void deselect() {
		super.deselect();
		viewmonster.deselect();
	}


	
	@Override
	public void initialise(QuestCreator questCreator) {
		viewmonster.initialise();
		ViewMonster monster=questCreator.addViewMonsterToSquare(viewmonster,viewmonster.getBaseSquare());
		monster.deselect();
	}
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("place monster "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new PlaceMonsterEvent(viewmonster);
	}
}
