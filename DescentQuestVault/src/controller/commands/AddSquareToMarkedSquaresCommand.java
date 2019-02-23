package controller.commands;

import java.util.ArrayList;

import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;

public class AddSquareToMarkedSquaresCommand extends BasicCommand implements ICommand {
	private ViewSquare mainSquare;
	private ArrayList<ViewSquare> square;
	private ViewMonster monster;

	/*
	public AddSquareToMarkedSquaresCommand(ViewSquare square) {
		this.square=square;
	}
	*/

	public AddSquareToMarkedSquaresCommand(ViewSquare square2, ArrayList<ViewSquare> occupiedSquares,ViewMonster monster) {
		// TODO Auto-generated constructor stub
		square=occupiedSquares;
		mainSquare=square2;
		this.monster=monster;
		
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		
		control.addToMarkedSquares(square);
		
	}

}
