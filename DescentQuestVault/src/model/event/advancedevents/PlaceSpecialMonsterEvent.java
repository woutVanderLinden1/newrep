package model.event.advancedevents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.ContinueCommand;
import controller.commands.ICommand;
import controller.commands.Game.PlaceGameMonsterCommand;
import controller.commands.Game.ShowTextCommand;
import model.Item;
import model.Resources;
import model.Monster.Monster;
import model.Monster.TokenMonster;
import model.event.Event;
import model.event.MonsterTurnTrigger;
import model.event.Univent;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewToken;
import view.menu.QuestCreator;
import view.viewItems.MonsterItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ItemInfoContainer;

public class PlaceSpecialMonsterEvent extends Event implements StopAble {

	private ViewMonster viewmonster;
	private ICommand command;
	private boolean namebased=true;
	private String text;
	
	
	public PlaceSpecialMonsterEvent(ViewMonster viewMonster) {
		viewmonster=viewMonster;
		setCommand(new PlaceGameMonsterCommand(viewMonster));
		commands.add(command);
		setIDName("placemonster "+ viewmonster.getIDName());
		setName("place monster "+ viewmonster.getName());
		//viewMonster.addNameChangeListener(this);
		text="Place "+ viewmonster.getIDName()+ " on the map next  to the heroes equal to the number of heroes";
	}
	public PlaceSpecialMonsterEvent(ViewMonster viewMonster,ViewToken token) {
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
		ArrayList<TextOption> options=new ArrayList<TextOption>();
		options.add(new TextOption("continue",new ContinueCommand()));
		commands.add(new ShowTextCommand(text,options));
		
		super.trigger();
		
	}
	@Override
	public boolean isStopEvent() {
		return true;
	}
	
	public PlaceSpecialMonsterEvent(ViewToken token) {
		
		
	}

	public PlaceSpecialMonsterEvent(ViewMonster viewmonster,String text) {
		setIDName("placemonster ");
		setName("placemonster");
		this.viewmonster=viewmonster;
		this.text=text;
		
		// TODO Auto-generated constructor stub
	}

	public PlaceSpecialMonsterEvent(TokenMonster asmonster, TokenItem token) {
		viewmonster=new ViewMonster(new MonsterItem(asmonster),null,0,0);
		setCommand(new PlaceGameMonsterCommand(viewmonster));
		commands.add(command);
		setIDName("placemonster "+ viewmonster.getIDName());
		setName("place monster "+ viewmonster.getName());
		// TODO Auto-generated constructor stub
		text="The tokens are alive and move and attack and have specials like monsters";
	}
	public PlaceSpecialMonsterEvent() {
		setIDName("placemonster ");
		setName("placemonster");
		
		this.text="place monsters equal to the number of heroes next to the heroes respecting group limits";
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
		
		return new PlaceSpecialMonsterEvent(viewmonster,text);
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
	
		//choose moveset
		this.addMonsterMovementCombBox(itemInfoText);
		this.addMonsterMovementEventButton(itemInfoText);
		this.addTextChanger(itemInfoText);
	}
	
	private void addTextChanger(ItemInfoContainer itemInfoText) {
		JTextArea area=new JTextArea(200,400);
		area.setText(text);
		
		
		area.getDocument().addDocumentListener(new DocumentListener() {

		
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			private void warn() {
				text= area.getText();
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
		
	}
	/**
	 * adds a combobox to add a movement from the made movements
	 * @param itemInfoText
	 */
	private void addMonsterMovementCombBox(ItemInfoContainer itemInfoText) {
		ArrayList<MonsterTurnTrigger> possibleturns=new ArrayList<MonsterTurnTrigger>();
		File dir = new File(System.getProperty("user.dir")+"/Movement/zombie");
		System.out.println("MovementCombobox"+System.getProperty("user.dir")+"/Movement/zombie");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	MonsterTurnTrigger g=null;
				 FileInputStream fileIn;
				try {
					fileIn = new FileInputStream(child);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					g = (MonsterTurnTrigger) in.readObject();
				    in.close();
				    fileIn.close();
				    possibleturns.add(g);
				    System.out.println("the read file is " +g);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	
		    	
		    }
		  } else {
		  
		  }
		Map<String,MonsterTurnTrigger> trigmap=new HashMap<String,MonsterTurnTrigger>();
		String[] comboOptions = new String[possibleturns.size()];
		int k=0;
		for(MonsterTurnTrigger trig:possibleturns) {
			trigmap.put(trig.getName(),trig);
			comboOptions[k]=trig.getName();
			k++;
		}
		
		JComboBox<String> button=new JComboBox<String>(comboOptions);
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String comp=((String) button.getSelectedItem());
				MonsterTurnTrigger trig=trigmap.get(comp);
				viewmonster.setTurnTrigger(trig);
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("change setvalue");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        //button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreComboBox(field,button);
		
	}

	private void addMonsterMovementEventButton(ItemInfoContainer itemInfoText) {
		
			JButton button=new JButton("add");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserInputController control=UserInputController.getController();
					control.performCommand(new  AddTriggerToTriggerFieldCommand(viewmonster.getTurnTrigger(),null));
				
				}
				
			});
			 JLabel field = new JLabel();
			 field.setText("Monster Movement");
			
			 field.setEnabled(false);
			 field.setBackground(Color.yellow);
			
	       // itemInfoText.add(lab);
	       // itemInfoText.add(field);
	        int w=itemInfoText.getWidth();
	        field.setSize(new Dimension(w/2-20,25));
	        button.setSize(new Dimension(w/2,25));
	        button.setPreferredSize(new Dimension((int)(w/2-20),25));
	        field.setPreferredSize(new Dimension(w/2,25));
	        button.setHorizontalAlignment(SwingConstants.RIGHT);
			itemInfoText.addPreButton(field,button);
			
		
		
	}
	
	public void deletedcommand() {
		commands.clear();
	}
	@Override
	public void continueStop() {
		// TODO Auto-generated method stub
		
	}

}
