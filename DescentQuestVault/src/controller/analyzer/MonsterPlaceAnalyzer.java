package controller.analyzer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.UserInputController;
import controller.commands.AddSquareToMarkedSquaresCommand;
import controller.commands.EndMonsterPlacementCommand;
import controller.commands.ICommand;
import controller.commands.RemoveFromMarkedSquareCommand;
import controller.commands.ShowOccupiedCommand;
import view.IView;
import view.Items.Map.ViewSquare;
import view.events.BaseField;
import view.events.EventField;
import view.events.TriggerField;
import view.game.GameSquare;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.SelectAble;
import view.Items.Map.ViewMonster;


public class MonsterPlaceAnalyzer extends Analyzer {
	
	private ViewMonster themonster;
	private ArrayList<ViewSquare> chekedMainSquare;
	
	public MonsterPlaceAnalyzer(ViewMonster monster, ArrayList<ViewSquare> tile) {
		themonster=monster;
		if(tile!=null) {
			chekedMainSquare=tile;
		}
		else {
			chekedMainSquare=new ArrayList<ViewSquare>();
		}
		
		chekedMainSquare.add(monster.getBaseSquare());
	}

	@Override
	public ICommand makeGameGridCommand(int x, int y, MouseEvent e, int mouseClicked, GameSquare square, IView view,
			boolean dragging) {
		
		// TODO Auto-generated method stub
		return super.makeGameGridCommand(x, y, e, mouseClicked, square, view, dragging);
	}

	@Override
	public ICommand makeGridCommand(int x, int y, MouseEvent event, int mouseClicked, ViewSquare square, IView view,
			boolean dragging) {
		SelectAble selected=view.getSelected();
		UserInputController control=UserInputController.getController();
		switch(mouseClicked) {
			case MouseEvent.MOUSE_CLICKED:
				switch(event.getButton()) {
					case MouseEvent.BUTTON3:
						return new EndMonsterPlacementCommand(chekedMainSquare,themonster);
				}
				if(chekedMainSquare.contains(square)) {
					chekedMainSquare.remove(square);
					control.performCommand(new ShowOccupiedCommand(square, (ShapeItem) selected.getImageItem(), new Color(115,40,40,95)));
					
					return new RemoveFromMarkedSquareCommand(square,view.getOccupiedSquares(square,themonster),themonster);
					
				}
				
				if(view.isLegalOccupied(square,themonster)) {
					chekedMainSquare.add(square);
					ICommand comm=new AddSquareToMarkedSquaresCommand(square,view.getOccupiedSquares(square,themonster),themonster);
					control.performCommand(new ShowOccupiedCommand(square, (ShapeItem) selected.getImageItem(), new Color(200,135,135,95)));
					return comm;
				}
				break;
			case MouseEvent.MOUSE_MOVED:
				if(chekedMainSquare.contains(square)) {
					return new ShowOccupiedCommand(square, (ShapeItem) selected.getImageItem(), new Color(255,165,40,95));
					
				}
				if(view.isLegalOccupied(square,themonster)) {
					
					return new ShowOccupiedCommand(square, (ShapeItem) selected.getImageItem(), new Color(0,245,0,95));
				}
				else {
					return new ShowOccupiedCommand(square, (ShapeItem) selected.getImageItem(), new Color(245,0,0,95));
				}
				//break;
		//place monster on clicked square ass wel
			
			default:
			
			//return new EndMonsterPlacementCommand();
		}
		return null;
		// TODO Auto-generated method stub
		//return super.makeGridCommand(x, y, event, mouseClicked, square, view, dragging);
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
		return new EndMonsterPlacementCommand(chekedMainSquare,themonster);
		//return super.makeTriggerFieldCommand(x, y, view, arg0, mouseEntered, thefield, dragging);
	}

	@Override
	public ICommand generateSelectFieldCommand(MouseEvent arg0, BaseField field) {
		// TODO Auto-generated method stub
		return new EndMonsterPlacementCommand(chekedMainSquare,themonster);
		//return super.generateSelectFieldCommand(arg0, field);
	}

	public ViewMonster getMonsterplaced() {
		// TODO Auto-generated method stub
		return themonster;
	}

	
	
	
	
}
