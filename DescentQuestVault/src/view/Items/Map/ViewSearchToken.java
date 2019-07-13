package view.Items.Map;

import java.util.ArrayList;

import model.event.Univent;
import model.event.advancedevents.SearchEffectEvent;
import model.search.SearchToken;
import view.viewItems.TokenItem;

public class ViewSearchToken extends ViewToken {

	public ViewSearchToken(TokenItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		effect=new SearchEffectEvent();
		
	}

	public ViewSearchToken(SearchToken searchToken) {
		super(searchToken);
		effect=new SearchEffectEvent();
	
	}

	private SearchEffectEvent effect;
	

	public SearchEffectEvent getEffect() {
		return effect;
	}


	public void setEffect(SearchEffectEvent effect) {
		this.effect = effect;
	}

	public void setTriggers(ViewToken toplace) {
		this.setOpenSearchTokenTrigger(toplace.getSearchTokenTrigger());
		this.setPlaceSearchTokenEvent(toplace.getPlaceSearchTokenEvent());
		this.setRemoveSearchTokenEvent(toplace.getRemoveSearchTokenEvent());
		this.setEffect(((ViewSearchToken) toplace).getEffect());
		
	}
	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		ArrayList<Univent> toreturn=new ArrayList<Univent>();
		toreturn.add(placeevent);
		toreturn.add(removeevent);
		toreturn.add(this.searchtrigger);
		toreturn.add(effect);
		return toreturn; 
	}
}
