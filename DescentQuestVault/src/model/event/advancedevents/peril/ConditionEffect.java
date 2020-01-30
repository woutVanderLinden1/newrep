package model.event.advancedevents.peril;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import controller.UserInputController;
import controller.commands.Game.ShowTextCommand;
import model.Condition;
import model.Item;
import model.Resources;
import model.Monster.Monster;
import model.event.Event;
import model.event.EventEndListener;
import model.event.Univent;
import view.Items.Map.ViewMonster;
import view.menu.QuestCreator;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class ConditionEffect extends Event {

	private Condition cond;
	
	public ConditionEffect(Condition cond) {
		  this.cond=cond;
		  this.setName("Condition effect");
		  this.setIDName("Condition effect");
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}

	public boolean isStopEvent() {
		return true;
	}
	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return new ConditionEffect(cond);
	}

	@Override
	public void trigger() {
		UserInputController control=UserInputController.getController();
		//control.performCommand(new HoldToContinueCommand(this));
		control.performCommand(new ShowTextCommand("The heroes are now "+ cond +".",new EventEndListener() {

			@Override
			public void eventEnded() {
				triggerEventEndListeners();
			}
			
		}));
	}
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		Condition[] conditions= Condition.getConditions().toArray(new Condition[Condition.getConditions().size()]);
		
		//comboOptions.forEach(a->a.getName());
		
		JLabel lab=new JLabel("Condition");
		lab.setText("Condition");
		JComboBox<Condition> button =new JComboBox<Condition>(conditions);
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cond=(Condition) button.getSelectedItem();
				
		    }
		};
		button.addActionListener(listen);
		//button
		
	
		//button.addActionListener(arg0);
		
		//adddropdownbutton for peril timing
		itemInfoText.addPreComboBox(lab, button);
	
	}
	
	
}
