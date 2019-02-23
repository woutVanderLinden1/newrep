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
import controller.command.CreateIntegerValueCommand;
import controller.commands.CreateBooleanValueCommand;
import controller.commands.ICommand;
import model.event.IfBooleanTrigger;
import model.event.IfIntegerTrigger;
import model.event.InitialiseBooleanEvent;
import model.event.ModifyIntegerValueEvent;
import model.event.SetBooleanValueEvent;
import model.event.Univent;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;

public class IntegerValueItem extends ValueItem {

	

	private CustomInteger val;
	
	private InitialiseBooleanEvent ev;
	private ArrayList<Univent> eventlist=new ArrayList<Univent>();
	

	private ModifyIntegerValueEvent modifyEvent;
	private IfIntegerTrigger iftrigger;
	private CompareToOtherIntegerTrigger comparetrigger;
	//private WhileBooleanTrigger whiletrigger;
	
	public IntegerValueItem(CustomInteger val) {
		super(val);
		setModifyEvent(new ModifyIntegerValueEvent(Modification.SET,this));
		setIftrigger(new IfIntegerTrigger(Comparison.EQUALS,this));
		setComparetrigger(new CompareToOtherIntegerTrigger(Comparison.EQUALS,this));
		eventlist.add(modifyEvent);
		eventlist.add(iftrigger);
		eventlist.add(comparetrigger);
		// TODO Auto-generated constructor stub
	}

	public CompareToOtherIntegerTrigger getComparetrigger() {
		return comparetrigger;
	}

	public void setComparetrigger(CompareToOtherIntegerTrigger comparetrigger) {
		this.comparetrigger = comparetrigger;
	}

	public ModifyIntegerValueEvent getModifyEvent() {
		return modifyEvent;
	}

	public void setModifyEvent(ModifyIntegerValueEvent modifyEvent) {
		this.modifyEvent = modifyEvent;
	}

	public void setIftrigger(IfIntegerTrigger iftrigger) {
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
		return new IntegerValueItem(val);
	}

	@Override
	public ICommand getNewCreationCommand() {
		// TODO Auto-generated method stub
		return new CreateIntegerValueCommand();
	}

	@Override
	public ValueKind getValueKind() {
		// TODO Auto-generated method stub
		return ValueKind.INTEGER;
	}
	

	
	

}

