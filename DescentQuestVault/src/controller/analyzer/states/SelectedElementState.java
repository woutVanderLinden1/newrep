package controller.analyzer.states;

import controller.KeyAction;
import controller.MouseAction;
import controller.commands.ICommand;
import view.IDrawWindow;
import view.IView;

public class SelectedElementState implements IAnalyzerState {

	
	
	
	@Override
	public ICommand makeCommand(int x, int y, MouseAction act, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICommand makeCommand(KeyAction key, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasSelected() {
		return true;
	}
	
}
