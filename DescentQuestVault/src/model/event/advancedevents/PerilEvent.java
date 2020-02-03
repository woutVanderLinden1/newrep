package model.event.advancedevents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.ItemController;
import model.event.Event;
import model.event.EventTriggerStack;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.peril.EndGamePeril;
import model.event.advancedevents.peril.Peril;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class PerilEvent extends MultiTrigger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980364086673355039L;
	private StandardPerilGenre genre;
	private AdvancedPerilGenre advgenre;
	private PerilTiming timing;
	private int perilinterval = 0;
	private int bigperilinterval = 0;

	private int smallperiltimer = 0;
	private int bigperiltimer = 0;
	private int finalperiltimer = 0;
	private int limit1 = 0;
	private int limit2 = 0;
	private int limit3 = 0;
	private int limit4 = 0;
	private int period = 0;
	private int period2 = 0;
	private int period3 = 0;
	private boolean firstperilstarted = false;
	private boolean secondperilstarted = false;
	private boolean finalperilstarted = false;
	private Peril firstperil;
	private Peril secondperil;
	private EndGamePeril finalPeril = new EndGamePeril();

	public Peril getFirstperil() {
		return firstperil;
	}

	public void setFirstperil(Peril firstperil) {
		this.firstperil = firstperil;
	}

	public Peril getSecondperil() {
		return secondperil;
	}

	public void setSecondperil(Peril secondperil) {
		this.secondperil = secondperil;
	}

	public StandardPerilGenre getGenre() {
		return genre;
	}

	public void setGenre(StandardPerilGenre genre) {
		this.genre = genre;
	}

	public AdvancedPerilGenre getAdvgenre() {
		return advgenre;
	}

	public void setAdvgenre(AdvancedPerilGenre advgenre) {
		this.advgenre = advgenre;
	}

	public PerilTiming getTiming() {
		return timing;
	}

	public void setTiming(PerilTiming timing) {
		this.timing = timing;
	}

	public PerilEvent(StandardPerilGenre genre, AdvancedPerilGenre advgenre, PerilTiming timing) {
		this.genre = genre;
		this.advgenre = advgenre;
		this.timing = timing;
		firstperil=new Peril();
		firstperil.setIDName("minor peril");
		secondperil=new Peril();
		secondperil.setIDName("major peril");
		this.addTriggerChoice(firstperil);
		this.addTriggerChoice(secondperil);
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.PERIL;
	}

	public PerilEvent(PerilTiming test) {
		this.setName("Peril");
		this.setName("Peril");
		this.timing = test;
		firstperil=new Peril();
		firstperil.setIDName("minor peril");
		secondperil=new Peril();
		secondperil.setIDName("major peril");
		this.addTriggerChoice(firstperil);
		this.addTriggerChoice(secondperil);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialise(QuestCreator questCreator) {

	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public void trigger() {
		ItemController control = ItemController.getItemController();
		int peril = control.getPeril().getTheInteger();

		switch (timing) {
		case TEST:
			limit1 = 1;
			limit2 = 2;
			limit3 = 3;
			period = 1;
			period2 = 1;
			period3 = 1;
			limit4 = 4;
			break;
		case NONE:
			limit1 = 4000;
			limit2 = 4000;
			limit3 = 4000;
			period = 4000;
			period2 = 4000;
			period3 = 4000;
			limit4 = 4000;
			break;
		case LONG:
			limit1 = 14;
			limit2 = 30;
			limit3 = 50;
			period = 4;
			period2 = 4;
			period3 = 2;
			limit4 = 80;
			break;
		case MEDIUM:
			limit1 = 10;
			limit2 = 20;
			limit3 = 40;
			period = 3;
			period2 = 4;
			period3 = 2;
			limit4 = 60;
			break;
		case SHORT:
			limit1 = 8;
			limit2 = 15;
			limit3 = 25;
			period = 3;
			period2 = 3;
			period3 = 2;
			limit4 = 40;
			break;
		default:
			break;

		}
		if (peril == limit1) {
			startFirstPeril();
		}
		if (peril == limit2) {
			startSecondPeril();
		}
		if (peril == limit3) {
			startThirdPeril();
		}
		if (peril == limit4) {
			startLastPeril();
		}
		ArrayList<Univent> perilist=new ArrayList<Univent>();
		if (firstperilstarted) {
			smallperiltimer--;
			if (smallperiltimer <= 0) {
				smallperiltimer = perilinterval;
				/*
				 * EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
				 * triggerstack.addNewEvents(); triggerstack.triggerNextStackEvent();
				 */
				// instead addevents of first peril
				//firstperil.trigger();
				perilist.addAll(firstperil.getUnivents());
			}

		}
		if (secondperilstarted) {
			bigperiltimer--;
			if (bigperiltimer <= 0) {
				bigperiltimer = bigperilinterval;
				/*
				 * EventTriggerStack triggerstack=EventTriggerStack.getTriggerStack();
				 * triggerstack.addNewEvents(); triggerstack.triggerNextStackEvent();
				 */
				// and of the second
				//secondperil.trigger();
				perilist.addAll(secondperil.getUnivents());
			}

		}
		if (finalperilstarted) {
			finalperiltimer--;
			// then of the final

			//finalPeril.trigger(finalperiltimer);µ
			perilist.addAll(finalPeril.getUnivents());
		}
		control.addPeril(1);
		this.setActions(perilist);
		super.trigger();
		// then trigger the events in the stack

	}

	private void startLastPeril() {
		// losegameimpending
		finalperilstarted = true;
		// 5turns to fins game;
		finalperiltimer = 5;
	}

	private void startThirdPeril() {
		perilinterval = period3;
		smallperiltimer = period3;
		perilinterval = period3;

	}

	private void startSecondPeril() {
		// TODO Auto-generated method stub
		bigperilinterval = period2;
		bigperiltimer = period2;
		secondperilstarted = true;
		bigperilinterval = period2;
	}

	private void startFirstPeril() {
		perilinterval = period;
		smallperiltimer = period;
		firstperilstarted = true;
		perilinterval = period;
	}


	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		PerilTiming[] comboOptions = {PerilTiming.TEST,PerilTiming.LONG,PerilTiming.MEDIUM,PerilTiming.SHORT,PerilTiming.NONE};
		
		JLabel lab=new JLabel("Periltiming");
		lab.setText("Periltiming");
		JComboBox button =new JComboBox(comboOptions);
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTiming((PerilTiming) button.getSelectedItem());
		    }
		};
		button.addActionListener(listen);
		//button
		button.setSelectedItem(this.timing);
		
	
		//button.addActionListener(arg0);
		
		//adddropdownbutton for peril timing
		itemInfoText.addPreComboBox(lab, button);
	
	}
}
