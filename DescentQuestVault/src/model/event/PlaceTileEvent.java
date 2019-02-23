package model.event;

import controller.UserInputController;
import controller.commands.MakeTileInvisibleCommand;
import controller.commands.MakeTileVisibleCommand;
import controller.commands.Game.PlaceGameTileCommand;
import view.Items.Map.ViewTile;
import view.menu.QuestCreator;
import view.viewItems.NameChangeListener;
/**
 * an event that places this tile
 * @author User
 *
 */
public class PlaceTileEvent extends Event implements NameChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ViewTile toplace;
	PlaceGameTileCommand command;
	
	public PlaceTileEvent(ViewTile viewTile) {
		toplace=viewTile;
		setCommand(new PlaceGameTileCommand(toplace));
		commands.add(command);
		setIDName("placeTile "+ toplace.getIDName());
		System.out.println("toplace "+toplace);
		setName("place tile "+ toplace.getName());
		viewTile.addNameChangeListener(this);
	}

	public PlaceGameTileCommand getCommand() {
		return command;
	}

	public void setCommand(PlaceGameTileCommand command) {
		this.command = command;
	}

	public ViewTile getToplace() {
		return toplace;
	}

	public void setToplace(ViewTile toplace) {
		this.toplace = toplace;
	}
	@Override
	public void select() {
		super.select();
		toplace.select();
	}
	@Override
	public void deselect() {
		System.out.println("deselected");
		super.deselect();
		toplace.deselect();
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		toplace.initialise();
		ViewTile tile=questCreator.addViewTileToSquare(toplace,toplace.getBaseSquare());
		tile.deselect();
	}

	@Override
	public void nameChanged(String newname) {
		System.out.println("namechanged"+toplace.getName());
		changeName("place tile "+ toplace.getName());
	}
	public void makeInvisible() {
		//make the tile invisible
		UserInputController control=UserInputController.getController();
		control.performCommand(new MakeTileInvisibleCommand(toplace));
	}
	public void makeVisible() {
		UserInputController control=UserInputController.getController();
		control.performCommand(new MakeTileVisibleCommand(toplace));
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new PlaceTileEvent(toplace);
	}
}
