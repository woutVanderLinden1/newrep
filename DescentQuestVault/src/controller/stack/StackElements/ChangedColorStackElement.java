package controller.stack.StackElements;

import java.util.ArrayList;

import controller.analyzer.states.IAnalyzerState;
import controller.commands.ICommand;
import view.Items.Map.ViewSquare;

public class ChangedColorStackElement implements IStackElement {
	
	private ArrayList<ViewSquare> squares=new ArrayList<ViewSquare>();

	public ChangedColorStackElement(ViewSquare tile) {
		// TODO Auto-generated constructor stub
		squares.add(tile);;
	}

	public ChangedColorStackElement(ArrayList<ViewSquare> changedTiles) {
		squares.addAll(changedTiles);
	}

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
		for(ViewSquare square:squares) {
			if(square!=null) {
				square.setColor(null);
			}
			
		}
		
	}

	@Override
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addtoViewSquares(ViewSquare square) {
		squares.add(square);
		
	}
	public ArrayList<ViewSquare> getSquares(){
		return squares;
	}

	public void addtoViewSquares(ArrayList<ViewSquare> square) {
		
		squares.addAll(square);
	}

	public void removeViewSquares(ArrayList<ViewSquare> squares2) {

		squares.removeAll(squares2);
	}

}
