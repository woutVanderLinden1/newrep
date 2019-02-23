package view.Items.Map;

import java.awt.Graphics;
import java.util.ArrayList;

import frame.SubContainer;
import model.event.Event;
import model.event.PlaceMonsterEvent;
import model.event.PlaceSearchTokenEvent;
import model.event.RemoveSearchTokenEvent;
import model.event.SearchTokenTrigger;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectKind;

public class ViewToken extends MapItem {
	private PlaceSearchTokenEvent placeevent;
	private SearchTokenTrigger searchtrigger;
	private RemoveSearchTokenEvent removeevent;
	private static int tokennr=0;
	
	private static String giveTokenName() {
		tokennr++;
		return "token"+tokennr;
	}
	

	public ViewToken(TokenItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		this.setName(giveTokenName());
		placeevent=new PlaceSearchTokenEvent(this);
		removeevent=new RemoveSearchTokenEvent(this);
		searchtrigger=new SearchTokenTrigger(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWTOKEN;
	}

	public PlaceSearchTokenEvent getPlaceSearchTokenEvent() {
		// TODO Auto-generated method stub
		return placeevent;
	}

	public SearchTokenTrigger getSearchTokenTrigger() {
		// TODO Auto-generated method stub
		return searchtrigger;
	}

	public RemoveSearchTokenEvent getRemoveSearchTokenEvent() {
		// TODO Auto-generated method stub
		return removeevent;
	}

	


	public void setTriggers(ViewToken toplace) {
		this.setOpenSearchTokenTrigger(toplace.getSearchTokenTrigger());
		this.setPlaceSearchTokenEvent(toplace.getPlaceSearchTokenEvent());
		this.setRemoveSearchTokenEvent(toplace.getRemoveSearchTokenEvent());
		
	}

	private void setRemoveSearchTokenEvent(RemoveSearchTokenEvent removeSearchTokenEvent) {
		removeevent=removeSearchTokenEvent;
		
	}

	private void setPlaceSearchTokenEvent(PlaceSearchTokenEvent placeSearchTokenEvent) {
		// TODO Auto-generated method stub
		placeevent=placeSearchTokenEvent;
	}

	private void setOpenSearchTokenTrigger(SearchTokenTrigger searchTokenTrigger) {
		// TODO Auto-generated method stub
		searchtrigger=searchTokenTrigger;
	}

	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return item.getIDName();
	}

	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		ArrayList<Univent> toreturn=new ArrayList<Univent>();
		toreturn.add(placeevent);
		toreturn.add(removeevent);
		toreturn.add(this.searchtrigger);
		return toreturn; 
	}
}
