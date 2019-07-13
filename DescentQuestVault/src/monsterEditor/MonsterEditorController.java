package monsterEditor;

import java.awt.Component;
import java.awt.event.MouseEvent;

import controller.UserInputController;
import controller.commands.ICommand;
import controller.turns.GameController;
import frame.MainFrame;
import model.IModel;
import model.Monster.Monster;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import view.IView;
import view.ViewManager;

public class MonsterEditorController extends UserInputController {

	private MonsterEditor edit;
	
	public MonsterEditorController(IView newview, IModel newmod, MonsterEditor theFrame) {
		super(newview, newmod, theFrame);
		edit=theFrame;
		// TODO Auto-generated constructor stub
	}

	private static MonsterEditorController monstereditcontrol;
	
	public static MonsterEditorController getMonsterEditorController() {
		// TODO Auto-generated method stub
		return monstereditcontrol;
	}

	public static UserInputController createMonsterEditorController(IModel object, ViewManager viewMan,
			MonsterEditor monsterEditor) {
		// TODO Auto-generated method stub
		monstereditcontrol=new MonsterEditorController(viewMan,object,monsterEditor);
		return monstereditcontrol;
	}

	@Override
	public synchronized void performCommand(ICommand toExecute) {
		// TODO Auto-generated method stub
		if(toExecute!=null) {
			toExecute.setView(this.getTheView());
			super.performCommand(toExecute);
		}
	}
	public void sendDragListenerEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		
		super.sendDragListenerEvent(e);
	}

	public void saveMovement() {
		// TODO Auto-generated method stub
		edit.saveMovement();
	}
	public MonsterTurnTrigger getMovement() {
		// TODO Auto-generated method stub
		return edit.getMonsterMovement();
	}

	public Component getMonsterEditor() {
		// TODO Auto-generated method stub
		return edit;
	}

	public Monster getMonster() {
		// TODO Auto-generated method stub
		return edit.getMonster();
	}

	public void loadMovement(MonsterTurnTrigger g) {
		// TODO Auto-generated method stub
		edit.loadMovement(g);
	}


}
