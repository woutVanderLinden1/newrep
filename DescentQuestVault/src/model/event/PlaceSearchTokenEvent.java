package model.event;

import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.PlaceGameSearchTokenCommand;
import view.Items.Map.ViewToken;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class PlaceSearchTokenEvent extends Event implements NameChangeListener{

	private ViewToken toplace;
	private PlaceGameSearchTokenCommand command;
	private boolean namebased=true;
	
	public PlaceSearchTokenEvent(ViewToken viewToken) {
		toplace=viewToken;
		setCommand(new PlaceGameSearchTokenCommand(viewToken));
		commands.add(command);
		setIDName("placeToken "+ toplace.getIDName());
		setName("place Token "+ toplace.getName());
		viewToken.addNameChangeListener(this);
	}

	private void setCommand(PlaceGameSearchTokenCommand newcommand) {
		command=newcommand;
		
	}

	public void select() {
		super.select();
		toplace.select();
	}
	public void deselect() {
		super.deselect();
		toplace.deselect();
	}
	public ViewToken getToplace() {
		return toplace;
	}

	public void setToplace(ViewToken toplace) {
		this.toplace = toplace;
	}

	public PlaceGameSearchTokenCommand getCommand() {
		return command;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		questCreator.addViewTokenToSquare(toplace,toplace.getBaseSquare());
		toplace.initialise();
	}

	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("place Token "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new PlaceSearchTokenEvent(toplace);
	}
}
