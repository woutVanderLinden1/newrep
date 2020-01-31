package model.event.advancedevents.peril;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import ItemEditor.ActionTaker;
import controller.UserInputController;
import controller.commands.Game.ShowTextCommand;
import model.event.Event;
import model.event.EventEndListener;
import model.event.Univent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;

public class DamageMajorPerilEvent extends Event {

	private int perildamage;
	
	public DamageMajorPerilEvent(int t) {
		perildamage=t;
		this.setName("Major perildamage");
		this.setIDName("Major perildamage");
	}

	public void trigger() {
		UserInputController control=UserInputController.getController();
		//control.performCommand(new HoldToContinueCommand(this));
		control.performCommand(new ShowTextCommand("The heroes divide "+ perildamage +" damage among them",new EventEndListener() {

			@Override
			public void eventEnded() {
				triggerEventEndListeners();
			}
			
		}));
		perildamage=perildamage+2;
	}

	public boolean isStopEvent() {
		return true;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new DamageMajorPerilEvent(perildamage);
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		itemInfoText.addNumericEditButton("damage :", itemInfoText, perildamage, new ActionTaker<Integer>() {

			@Override
			public void perform(Integer value) {
				perildamage=value;
				
			}
			
		});
		super.addEventSpecifics(itemInfoText);
	}
	
	
	
}
