package controller.commands.select;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewToken;
import view.viewItems.DoorItem;
import view.viewItems.TokenItem;

public class AddTokenToViewSquareCommand extends BasicCommand implements ICommand {

	private TokenItem token;
	private ViewSquare square;
	private ViewToken viewtoken;
	
	
	
	public AddTokenToViewSquareCommand(TokenItem holded, ViewSquare square) {
		token=holded;
		setSquare(square);
	}
	public AddTokenToViewSquareCommand(ViewToken holded, ViewSquare square) {
		viewtoken=holded;
		setSquare(square);
	}


	public TokenItem getToken() {
		return token;
	}


	public void setToken(TokenItem token) {
		this.token = token;
	}


	public ViewSquare getSquare() {
		return square;
	}


	public void setSquare(ViewSquare square) {
		this.square = square;
	}


	public ViewToken getViewtoken() {
		return viewtoken;
	}


	public void setViewtoken(ViewToken viewtoken) {
		this.viewtoken = viewtoken;
	}


	public void perform() {
		System.out.println("placed tile");
		if(token!=null) {
			view.addTokenToSquare(token,square);
		}
		else {
			view.addViewTokenToSquare(viewtoken,square);
		}
		
		control.endTileMove();
	//	view.addDoorToSquare(door,square);
	}
}
