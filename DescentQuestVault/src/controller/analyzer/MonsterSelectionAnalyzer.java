package controller.analyzer;

import java.awt.event.MouseEvent;

import controller.KeyAction;
import controller.MouseAction;
import controller.analyzer.states.IAnalyzerState;
import controller.commands.ICommand;
import view.IDrawWindow;
import view.IView;
import view.Items.Map.ViewSquare;
import view.events.BaseField;
import view.events.EventField;
import view.events.TriggerField;
import view.game.GameSquare;

public class MonsterSelectionAnalyzer extends Analyzer{

	@Override
	public ICommand makeCommand(int x, int y, MouseAction act, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return super.makeCommand(x, y, act, wind, view);
	}

	@Override
	public ICommand makeGridCommand(int x, int y, MouseEvent event, int mouseClicked, ViewSquare square, IView view,
			boolean dragging) {
		// TODO Auto-generated method stub
		return super.makeGridCommand(x, y, event, mouseClicked, square, view, dragging);
	}

	@Override
	public IAnalyzerState getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}

	@Override
	public void setState(IAnalyzerState state) {
		// TODO Auto-generated method stub
		super.setState(state);
	}

	@Override
	public ICommand makeCommand(KeyAction act, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return super.makeCommand(act, wind, view);
	}

	@Override
	public ICommand generateSelectCommand(int mousePressed, IView view, int x, int y, MouseEvent ev) {
		// TODO Auto-generated method stub
		return super.generateSelectCommand(mousePressed, view, x, y, ev);
	}

	@Override
	public ICommand makeTriggerFieldCommand(int x, int y, IView view, MouseEvent arg0, int mouseEntered,
			TriggerField thefield, boolean dragging) {
		// TODO Auto-generated method stub
		return super.makeTriggerFieldCommand(x, y, view, arg0, mouseEntered, thefield, dragging);
	}

	@Override
	public ICommand generateSelectFieldCommand(MouseEvent arg0, BaseField field) {
		// TODO Auto-generated method stub
		return super.generateSelectFieldCommand(arg0, field);
	}

	@Override
	public ICommand makeGameGridCommand(int x, int y, MouseEvent e, int mouseClicked, GameSquare square, IView view,
			boolean dragging) {
		// TODO Auto-generated method stub
		return super.makeGameGridCommand(x, y, e, mouseClicked, square, view, dragging);
	}

}
