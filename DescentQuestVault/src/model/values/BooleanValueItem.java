package model.values;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.UserInputController;
import controller.commands.CreateBooleanValueCommand;
import controller.commands.ICommand;
import model.event.IfBooleanTrigger;
import model.event.InitialiseBooleanEvent;
import model.event.SetBooleanValueEvent;
import model.event.Univent;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;

public class BooleanValueItem extends ValueItem {

	
	private CustomBoolean val;
	
	private InitialiseBooleanEvent ev;
	private ArrayList<Univent> eventlist=new ArrayList<Univent>();
	

	private SetBooleanValueEvent setEvent;
	private IfBooleanTrigger iftrigger;
	//private WhileBooleanTrigger whiletrigger;
	
	public BooleanValueItem(CustomBoolean val) {
		super(val);
		setSetEvent(new SetBooleanValueEvent(false,this));
		setIftrigger(new IfBooleanTrigger(true,this));
		eventlist.add(setEvent);
		eventlist.add(iftrigger);
		// TODO Auto-generated constructor stub
	}

	public SetBooleanValueEvent getSetEvent() {
		return setEvent;
	}

	public void setSetEvent(SetBooleanValueEvent setEvent) {
		this.setEvent = setEvent;
	}

	public IfBooleanTrigger getIftrigger() {
		return iftrigger;
	}

	public void setIftrigger(IfBooleanTrigger iftrigger) {
		this.iftrigger = iftrigger;
	}

	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		return eventlist;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new BooleanValueItem(val);
	}

	@Override
	public ICommand getNewCreationCommand() {
		// TODO Auto-generated method stub
		return new CreateBooleanValueCommand();
	}

	@Override
	public ValueKind getValueKind() {
		// TODO Auto-generated method stub
		return ValueKind.BOOLEAN;
	}
	
	

	
	

}
