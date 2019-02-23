package misc;

import view.viewItems.NameChangeListener;

public interface NameHolder {

	//String getName();
	String getIDName();

	String getName();
	
	public void addNameChangeListener(NameChangeListener listen);
	public void triggerNameChangeListeners(String newname);

	void changeName(String value);
	

}
