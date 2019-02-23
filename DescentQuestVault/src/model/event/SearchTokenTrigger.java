package model.event;

import model.event.Trigger;
import view.Items.Map.ViewToken;
import view.viewItems.NameChangeListener;

public class SearchTokenTrigger extends Trigger implements NameChangeListener{

	private boolean namebased=true;
	private ViewToken token;
	
	public SearchTokenTrigger(ViewToken viewToken) {
		// TODO Auto-generated constructor stub
		setIDName("Search");
		setName("search "+ viewToken.getName());
		viewToken.addNameChangeListener(this);
		token=viewToken;
	}


	@Override
	public void nameChanged(String newname) {
		System.out.println("this triggers "+newname);
		this.restateName(newname);
	}


	private void restateName(String newname) {
		// TODO Auto-generated method stub
		if(namebased) {
			setName("search "+newname);
		}
		
	}
	public void changeName(String newname) {
		namebased=false;
		super.changeName(newname);
	}
	public Univent copy() {
		SearchTokenTrigger toreturn=new SearchTokenTrigger(token);
		this.addAllTriggers(toreturn);
		return toreturn;
	}
}
