package model.values;

import controller.UserInputController;
import controller.commands.InitialiseValueCommand;
import model.event.SetBooleanValueEvent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;
import view.viewItems.ItemBox.ValueChangeListener;

public class CustomBoolean extends CustomValue<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2816151268047902297L;
	/**
	 * 
	 */

	private static int nrbooleans=0;
	//private boolean value;
	

	
	public boolean isValue() {
		return value;
	}

	private static String getNewName() {
		nrbooleans++;
		return("CustomBoolean"+nrbooleans);
	}
	
	public CustomBoolean(String name, Boolean value) {
		super(name, value);
		//
	
	}

	public CustomBoolean() {
		super(getNewName(), false);
		// TODO Auto-generated constructor stub
	}


	public void setValue(Boolean newvalue) {
		// TODO Auto-generated method stub
		value=newvalue;
		super.setValue(newvalue);
	}



	@Override
	public ValueKind getValueKind() {
		// TODO Auto-generated method stub
		return ValueKind.BOOLEAN;
	}

	@Override
	public void setTo(CustomValue value2) {
		setValue(((CustomBoolean) value2).isValue());
		
	}

	

	
	
	
}
