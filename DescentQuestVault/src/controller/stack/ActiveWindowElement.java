package controller.stack;

import controller.analyzer.states.IAnalyzerState;
import controller.commands.ICommand;
import controller.stack.StackElements.IStackElement;

public class ActiveWindowElement implements IStackElement {

	@Override
	public ICommand prepareCommand(ICommand toPerform) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAnalyzerState getCorrespondingState() {
		// TODO Auto-generated method stub
		return null;
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
