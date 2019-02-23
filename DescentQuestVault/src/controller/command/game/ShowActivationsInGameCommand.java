package controller.command.game;

import java.awt.Point;
import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import misc.ActivateAble;
import view.Items.Map.MapItem;

public class ShowActivationsInGameCommand extends BasicCommand implements ICommand {

	private ArrayList<ActivateAble> list;
	private Point point;
	
	public ShowActivationsInGameCommand(Point point,ArrayList<ActivateAble> arrayList) {
		list=arrayList;
		this.point=point;
	}

	@Override
	public void perform() {
		view.showActivateAbles(point,list);

	}

}
