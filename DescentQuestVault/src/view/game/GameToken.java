package view.game;

import java.util.ArrayList;

import activation.SearchTokenActivation;
import misc.ActivateAble;
import model.Activation;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewSearchToken;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewToken;
import view.viewItems.DoorItem;
import view.viewItems.TokenItem;

public class GameToken extends ViewSearchToken implements ActivateAble {
	
	private ViewToken tokenbasic;
	
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	

	public GameToken(GameSquare gamesquare, ViewToken toplace) {
		super((TokenItem)toplace.getImageItem(),gamesquare,0,0);
		this.setTriggers(toplace);
		tokenbasic=toplace;
		
		activationList=toplace.getActivations();
		
	}



	public void setTriggers(ViewToken toplace) {
		if(toplace.isSearch()) {
			this.setOpenSearchTokenTrigger(((ViewSearchToken) toplace).getSearchTokenTrigger());
			((ViewSearchToken) toplace).getSearchTokenTrigger().setToken(this);
		}
		
		this.setPlaceSearchTokenEvent(toplace.getPlaceSearchTokenEvent());
		toplace.getRemoveSearchTokenEvent().setGameToken(this);
		this.setRemoveSearchTokenEvent(toplace.getRemoveSearchTokenEvent());
		
		/*
		if(this.isSearch()) {
			this.setRemoveSearchTokenEvent(toplace.getRemoveSearchTokenEvent());
			if(toplace.isSearch()) {
				
			}
		
		}
		*/
		
	}



	@Override
	public boolean isActivateAble() {
		return true;
	}

	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}
	
	public boolean isBaseToken(ViewToken door) {
		return (door.equals(tokenbasic));
	}


}
