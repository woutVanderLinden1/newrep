package view.events;

import controller.command.game.RemoveMapMonsterCommand;
import model.event.Event;
import model.event.Univent;
import view.game.GameMonster;
import view.menu.QuestCreator;

public class RemoveMapMonsterEvent extends Event {

	private GameMonster mon;
	public RemoveMapMonsterEvent(GameMonster mon) {
		this.mon=mon;
		RemoveMapMonsterCommand comm=new RemoveMapMonsterCommand(mon);
		commands.add(comm);
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
