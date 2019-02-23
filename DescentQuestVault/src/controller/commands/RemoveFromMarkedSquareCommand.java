package controller.commands;

import java.util.ArrayList;

import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;

public class RemoveFromMarkedSquareCommand extends BasicCommand implements ICommand {
	
	private ViewSquare square;
	private ArrayList<ViewSquare> squares;

	public RemoveFromMarkedSquareCommand(ViewSquare square, ArrayList<ViewSquare> occupiedSquares,
			ViewMonster themonster) {
		this.square=square;
		this.squares=occupiedSquares;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		control.removeSquaresFromStack(squares);
	}

}
