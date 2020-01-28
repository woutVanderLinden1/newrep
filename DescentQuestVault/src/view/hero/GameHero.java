package view.hero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.command.AddActivationToMapItemCommand;
import controller.command.RemoveActivationFromMapItemCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.turns.TurnHolder;
import misc.ActivateAble;
import model.Activation;
import model.Hero.Hero;
import view.game.HeroPanel;
import view.viewItems.ItemBox.ItemInfoContainer;

public class GameHero extends ViewHero  implements ActivateAble,TurnHolder{

	
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	private Hero hero;
	private ArrayList<DefeatChangeListener> defeatedchangelisteners=new ArrayList<DefeatChangeListener>();
	private ArrayList<EndTurnListener> endTurnListeners=new ArrayList<EndTurnListener>();
	private boolean defeated;
	private EndTurnActivation endact;
	private DefeatActivation defeatact;
	private StandUpActivation standupact;
	private BuySkillActivation buyskillact;
	private boolean turnended;
	
	public boolean isTurnended() {
		return turnended;
	}
	public void setTurnended(boolean turnended) {
		this.turnended = turnended;
	}
	public GameHero(Hero hero) {
		super(hero);
		this.hero=hero;
		endact=new EndTurnActivation(this);
		defeatact=new DefeatActivation(this);
		buyskillact= new BuySkillActivation(this);
		standupact=new StandUpActivation(this);
		addBasicActivations();
	}
	public void addBasicActivations() {
		activationList.clear();
		if(!turnended) {
			activationList.add(endact);
		}
		
		activationList.add(defeatact);
		activationList.add(buyskillact);
	}
	public void addDefeatActivations() {
		activationList.clear();
		activationList.add(standupact);
	
	}
	

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}

	@Override
	public boolean isActivateAble() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void startTurn() {
		//do nothing a hero turn is normally processed.
		// TODO Auto-generated method stub
		
	}

	public void defeat() {
		defeated=true;
		notifyDefeatChangeListeners(defeated);
		addDefeatActivations();
	}
	private void notifyDefeatChangeListeners(boolean defeated2) {
		for(DefeatChangeListener listen:defeatedchangelisteners) {
			listen.defeated(defeated);
		}
		
	}

	public void standUp() {
		defeated=false;
		notifyDefeatChangeListeners(defeated);
		addBasicActivations();
	}

	public boolean isdefeated() {
		
		return defeated;
	}

	public void addDefeatChangeListener(DefeatChangeListener heroPanel) {
		// TODO Auto-generated method stub
		defeatedchangelisteners.add(heroPanel);
	}
	@Override
	public void endTurn() {
		turnended=true;
		notifyEndTurnListeners();
		addBasicActivations();
		
	}
	private void notifyEndTurnListeners() {
		for(EndTurnListener listen:endTurnListeners) {
			listen.TurnEnded();
		}
		
	}
	public void addTurnEndListener(EndTurnListener heroPanel) {
		// TODO Auto-generated method stub
		endTurnListeners.add(heroPanel);
	}
	@Override
	public void refreshTurn() {
		turnended=false;
		notifyEndTurnListeners();
		addBasicActivations();
		
	}
	@Override
	public void addActivation(Activation act) {
		activationList.add(act);
		
	}
	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		activationList.remove(activation);
	}
	@Override
	public void InitialiseActivation( ItemInfoContainer itemInfoText) {
		addNewActivationCreator(itemInfoText);
		addActivationsShower(itemInfoText);
	}
	

	private void addActivationsShower(ItemInfoContainer itemInfoText) {
		for(Activation act:activationList) {
				//add text for activatione
			//add removebutton
			//when added add nes trigger.
			addActivationTextChanger(act,itemInfoText);
			addActivationAddTriggerToField(act,itemInfoText);
			addActivationRemoveButton(act,itemInfoText);
		}
		
	}

	private void addActivationAddTriggerToField(Activation act, ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new  AddTriggerToTriggerFieldCommand(act.getTrigger(),null));
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Add trigger");
		
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

	private void addActivationRemoveButton(Activation act, ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new  RemoveActivationFromMapItemCommand(hold,act));
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("New Activation");
		
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

	private void addActivationTextChanger(Activation act, ItemInfoContainer itemInfoText) {
		// TODO Auto-generated method stub
		JLabel lab=new JLabel("name: ");
		 JTextField field = new JFormattedTextField();
		 
		 field.setName(act.getName());
		 field.setColumns(10);
		 field.setText(act.getName());
		 field.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  System.out.println("changedname "+field.getText());
					act.changeName((String)field.getText());
			  }
			});
		 field.addPropertyChangeListener("name",new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					System.out.println(field.getText());
					act.changeName((String)field.getText());
					
				}
	        	
	        });
		
     // itemInfoText.add(lab);
     // itemInfoText.add(field);
      int w=itemInfoText.getWidth();
      lab.setSize(new Dimension(w/2-20,25));
      field.setSize(new Dimension(w/2,25));
      lab.setPreferredSize(new Dimension((int)(w/2-20),25));
      field.setPreferredSize(new Dimension(w/2,25));
      lab.setHorizontalAlignment(SwingConstants.RIGHT);
      
      itemInfoText.addPreText(lab,field);
	}

	private void addNewActivationCreator(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("add");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new  AddActivationToMapItemCommand(hold));
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("New Activation");
		
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
	public String getImageString() {
		// TODO Auto-generated method stub
		return hero.getImageString();
	}
	public Hero getHero() {
		// TODO Auto-generated method stub
		return hero;
	}

}
