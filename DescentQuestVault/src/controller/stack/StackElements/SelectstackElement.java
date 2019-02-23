package controller.stack.StackElements;

import controller.analyzer.states.IAnalyzerState;
import controller.analyzer.states.SelectedElementState;
import controller.commands.ICommand;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;

public class SelectstackElement implements IStackElement {

	private SelectAble selected;
	
	public SelectstackElement(SelectAble item) {
		selected=item;
	}

	@Override
	public ICommand prepareCommand(ICommand toPerform) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAnalyzerState getCorrespondingState() {
		// TODO Auto-generated method stub
		return new SelectedElementState();
	}



	@Override
	public void reset() {
		

	}

	@Override
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return true;
	}

}
