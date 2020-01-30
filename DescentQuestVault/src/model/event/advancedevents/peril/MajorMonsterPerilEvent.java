package model.event.advancedevents.peril;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import controller.commands.ICommand;
import controller.commands.Game.PlaceGameMonsterCommand;
import controller.commands.Game.ShowTextCommand;
import model.Item;
import model.Resources;
import model.Monster.Monster;
import model.event.Event;
import model.event.EventEndListener;
import model.event.Univent;
import model.event.advancedevents.PlaceSpecialMonsterEvent;
import view.Items.Map.ViewMonster;
import view.menu.QuestCreator;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class MajorMonsterPerilEvent extends Event {


	private ViewMonster viewmonster;
	private ICommand command;
	private boolean namebased=true;
	private int periliteration=0;
	
	public  MajorMonsterPerilEvent (ViewMonster viewMonster) {
		viewmonster=viewMonster;
		setCommand(new PlaceGameMonsterCommand(viewMonster));
		commands.add(command);
		setIDName("placemonster "+ viewmonster.getIDName());
		setName("place monster "+ viewmonster.getName());
		//viewMonster.addNameChangeListener(this);
	}
	
	public void initialiseMonset(ViewMonster viewMonster) {
		viewmonster=viewMonster;
		setCommand(new PlaceGameMonsterCommand(viewMonster));
		commands.add(command);
		setIDName("placemonster "+ viewmonster.getIDName());
		setName("place monster "+ viewmonster.getName());
		
	}
	@Override
	public void trigger() {
		periliteration++;
		double val=Math.min(viewmonster.getMonsterLimit(),periliteration);
		commands.add(new ShowTextCommand("Place "+val+" "+ viewmonster.getIDName()+ " on the entrance of the map (ignore group limits)",new EventEndListener() {

			@Override
			public void eventEnded() {
				triggerEventEndListeners();
			}
			
		}));
		super.trigger();
		
	}
	public boolean isStopEvent() {
		return true;
	}
	
	public MajorMonsterPerilEvent() {
		setIDName("Major placemonster ");
		setName("Major placemonster");
		// TODO Auto-generated constructor stub
	}

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}
	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub

	}

	@Override
	public Univent copy() {
		
		return new MajorMonsterPerilEvent(viewmonster);
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		ArrayList<Item>  comboOptions = Resources.getAvailableMonsters();
		
		Monster[] monsters= comboOptions.toArray(new Monster[comboOptions.size()]);
		String[] strings=new String[comboOptions.size()+1];
		strings[0]="none";
		for(int i=0;i<comboOptions.size();i++) {
			strings[i+1]=comboOptions.get(i).getName();
		}
		//comboOptions.forEach(a->a.getName());
		
		JLabel lab=new JLabel("Monster");
		lab.setText("Monster");
		JComboBox<String> button =new JComboBox<String>(strings);
		ActionListener listen=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=button.getSelectedIndex();
				if (j==0){
					deletedcommand();
				}
				else {
				
					Monster mon=monsters[j-1];
					ViewMonster monster=new ViewMonster(new MonsterItem(mon),null,0,0);
					initialiseMonset(monster);
				}
		    }
		};
		button.addActionListener(listen);
		//button
		
	
		//button.addActionListener(arg0);
		
		//adddropdownbutton for peril timing
		itemInfoText.addPreComboBox(lab, button);
	
	}
	
	public void deletedcommand() {
		commands.clear();
	}


}
