package model.event;

import java.util.ArrayList;

import controller.commands.ICommand;
import controller.commands.Game.PlaceGameDoorCommand;
import controller.commands.Game.PlaceGameMonsterCommand;
import controller.commands.Game.ShowTextCommand;
import model.event.extraevents.TextStop;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.events.RemoveMapMonsterEvent;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;

public class PlaceMonsterEvent extends Event implements NameChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3240955388248158505L;
	private ViewMonster viewmonster;
	private transient PlaceGameMonsterCommand command;
	private boolean namebased=true;
	
	

	public PlaceMonsterEvent(ViewMonster viewMonster) {
		viewmonster=viewMonster;
		setCommand(new PlaceGameMonsterCommand(viewMonster));
		addCommand(command);
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

	public void setCommand(PlaceGameMonsterCommand command) {
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
	public void trigger() {
	
		//commands.add(new ShowTextCommand("Place "+ viewmonster.getIDName()+ " on the map as indicated"));
		TextStop stop=new TextStop("Place "+ viewmonster.getIDName()+ " on the map as indicated");
		stop.addEventEndListener(new EventEndListener() {

			@Override
			public void eventEnded() {
				EventTriggerStack stack=EventTriggerStack.getTriggerStack();
				ArrayList<Univent> tinylist=new ArrayList<Univent>();
				tinylist.add(new RemoveMapMonsterEvent(command.getPlacedMonster()));
				if(viewmonster.hasInfo()) {
					tinylist.add(viewmonster.getMonsterInfoEvent());
				}
				
				stack.addNewEvents(tinylist);
				//stack.triggerNextStackEvent();
				triggerEventEndListeners();
			}
			
		});
		super.trigger();
		stop.trigger();
		//addmapmonsterremoval to triggerstack
		
		
	}
	
	@Override
	public void initialise(QuestCreator questCreator) {
		viewmonster.initialise();
		setCommand(new PlaceGameMonsterCommand(viewmonster));
		addCommand(command);
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
	@Override
	public boolean isStopEvent() {
		return true;
	}
}
