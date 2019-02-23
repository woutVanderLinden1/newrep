package controller.analyzer.states;

import controller.KeyAction;
import controller.MouseAction;
import controller.commands.ICommand;
import controller.commands.addTileToViewSquareCommand;
import view.IDrawWindow;
import view.IView;
import view.Items.Map.ViewSquare;


public class MapEditState implements IAnalyzerState {

	@Override
	public ICommand makeCommand(int x, int y, MouseAction act, IDrawWindow wind, IView view) {
		//if maptile is clicked return addtilecommand to that maptile
		//ViewSquare tile= view.getTileAt(x,y);
		/*
		if(tile!=null){
			return new addTileToViewSquareCommand("018B",tile);
		}
		*/
		return null;
	}

	@Override
	public ICommand makeCommand(KeyAction key, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasSelected() {
		return false;
	}
}
