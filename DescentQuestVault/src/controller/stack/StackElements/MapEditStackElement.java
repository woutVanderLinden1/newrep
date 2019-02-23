package controller.stack.StackElements;

import controller.analyzer.states.IAnalyzerState;
import controller.analyzer.states.MapEditState;
import controller.commands.ICommand;

public class MapEditStackElement implements IStackElement {

	@Override
	public ICommand prepareCommand(ICommand toPerform) {
		// TODO Auto-generated method stub
		return toPerform;
	}

	@Override
	public IAnalyzerState getCorrespondingState() {
		// TODO Auto-generated method stub
		return new MapEditState();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return false;
	}

}
