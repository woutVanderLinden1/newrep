package model.event;

import controller.commands.Game.RemoveGameDoorCommand;
import controller.commands.Game.RemoveGameTileCommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewTile;
import view.game.GameTile;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class RemoveTileEvent extends Event implements NameChangeListener{

	private ViewTile toremove;
	private transient RemoveGameTileCommand command;
	private transient GameTile gametile;
	
	private boolean namebased=true;
	
	public RemoveTileEvent(ViewTile viewTile) {
		toremove=viewTile;
		
		addCommand(command);
		setIDName("removeTile "+ toremove.getIDName());
		setName("remove tile "+ toremove.getName());
		viewTile.addNameChangeListener(this);
	}



	public ViewTile getToremove() {
		return toremove;
	}



	public void setToremove(ViewTile toremove) {
		this.toremove = toremove;
	}



	public RemoveGameTileCommand getCommand() {
		return command;
	}



	public void setCommand(RemoveGameTileCommand command) {
		this.command = command;
	}



	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("place door "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}
	public Univent copy() {
		return new RemoveTileEvent(toremove);
		
	}
	
	public void setGameTile(GameTile newtile) {
		removeCommand(command);
		gametile=newtile;
		setCommand(new RemoveGameTileCommand(gametile));
		addCommand(command);
	}

}
