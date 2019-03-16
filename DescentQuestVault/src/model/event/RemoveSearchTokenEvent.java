package model.event;

import controller.commands.ICommand;
import controller.commands.Game.RemoveGameDoorCommand;
import controller.commands.Game.RemoveGameTokenCommand;
import model.event.Event;
import view.Items.Map.ViewToken;
import view.game.GameToken;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class RemoveSearchTokenEvent extends Event implements NameChangeListener {

	private ViewToken toremove;
	private GameToken token;
	private ICommand command;
	private boolean namebased=true;
	
	public ViewToken getToremove() {
		return toremove;
	}

	public void setToremove(ViewToken toremove) {
		this.toremove = toremove;
	}

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public RemoveSearchTokenEvent(ViewToken viewToken) {
		toremove=viewToken;
		//setCommand(new RemoveGameTokenCommand(toremove));
	//	commands.add(command);
		setIDName("remove token "+ toremove.getIDName());
		setName("remove token "+ toremove.getName());
		viewToken.addNameChangeListener(this);
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nameChanged(String newname) {
		System.out.println("this triggers "+newname);
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("remove token "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new RemoveSearchTokenEvent(token);
	}

	public void setGameToken(GameToken gameToken) {
		// TODO Auto-generated method stub
		commands.remove(command);
		token=gameToken;
		setCommand(new RemoveGameTokenCommand(gameToken));
		commands.add(command);
	}

}
