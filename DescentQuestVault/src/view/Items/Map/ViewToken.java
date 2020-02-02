package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

import activation.SearchTokenActivation;
import controller.UserInputController;
import controller.command.AddActivationToMapItemCommand;
import controller.command.RemoveActivationFromMapItemCommand;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import frame.SubContainer;
import misc.ActivateAble;
import model.Activation;
import model.Monster.TokenMonster;
import model.event.Event;
import model.event.PlaceMonsterEvent;
import model.event.PlaceSearchTokenEvent;
import model.event.RemoveSearchTokenEvent;
import model.event.SearchTokenTrigger;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.PlaceSpecialMonsterEvent;
import model.event.advancedevents.SearchEffectEvent;
import model.search.BasicToken;
import view.game.MonsterActivation;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;

public class ViewToken extends MapItem {
	protected PlaceSearchTokenEvent placeevent;
	

	protected RemoveSearchTokenEvent removeevent;
	protected static int tokennr=0;
	protected ArrayList<Activation> activationList=new ArrayList<Activation>();
	protected TokenMonster asmonster;
	private TokenItem token;
	protected PlaceSpecialMonsterEvent placeMonsterEv;
	protected boolean isMonster=false;
	
	
	public TokenMonster getAsmonster() {
		return asmonster;
	}


	public void setAsmonster(TokenMonster asmonster) {
		this.asmonster = asmonster;
	}


	private static String giveTokenName() {
		tokennr++;
		return "token"+tokennr;
	}
	

	public ViewToken(TokenItem image, ViewSquare square, int i, int j) {
		super(image, square, i, j);
		token=image;
		this.setName(giveTokenName());
		placeevent=new PlaceSearchTokenEvent(this);
		removeevent=new RemoveSearchTokenEvent(this);
		
		


		
		// TODO Auto-generated constructor stub
	}
	
	public void addTokenMonster() {
		TokenMonster tokenmon=new TokenMonster(token.getName());
		this.setAsmonster(tokenmon);
		//add tokenmonster to view
		//PlaceSpecialMonsterEvent ev:new
	}

	public ViewToken(BasicToken basicToken) {
		super(basicToken);
		token=new TokenItem(basicToken);
		this.setName(giveTokenName());
		placeevent=new PlaceSearchTokenEvent(this);
		removeevent=new RemoveSearchTokenEvent(this);
		//searchtrigger=new SearchTokenTrigger(this);
		

		// TODO Auto-generated constructor stub
	}



	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWTOKEN;
	}

	public PlaceSearchTokenEvent getPlaceSearchTokenEvent() {
		// TODO Auto-generated method stub
		return placeevent;
	}



	public RemoveSearchTokenEvent getRemoveSearchTokenEvent() {
		// TODO Auto-generated method stub
		return removeevent;
	}

	


	public void setTriggers(ViewToken toplace) {
		//this.setOpenSearchTokenTrigger(toplace.getSearchTokenTrigger());
		this.setPlaceSearchTokenEvent(toplace.getPlaceSearchTokenEvent());
		this.setRemoveSearchTokenEvent(toplace.getRemoveSearchTokenEvent());
	
		
	}

	protected void setRemoveSearchTokenEvent(RemoveSearchTokenEvent removeSearchTokenEvent) {
		removeevent=removeSearchTokenEvent;
		
	}

	protected void setPlaceSearchTokenEvent(PlaceSearchTokenEvent placeSearchTokenEvent) {
		// TODO Auto-generated method stub
		placeevent=placeSearchTokenEvent;
	}

	

	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return item.getIDName();
	}

	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		ArrayList<Univent> toreturn=new ArrayList<Univent>();
		toreturn.add(placeevent);
		toreturn.add(removeevent);
	
		//toreturn.add(effect);
		return toreturn; 
	}


	@Override
	public void removeActivation(Activation activation) {
	
		activations.remove(activation);
		activationList.remove(activation);
	}

	
	@Override
	public void InitialiseActivation( ItemInfoContainer itemInfoText) {
		addNewActivationCreator(itemInfoText);
		addActivationsShower(itemInfoText);
	}
	

	private void addActivationsShower(ItemInfoContainer itemInfoText) {
		for(Activation act:activations)  {
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
				itemInfoText.refreshImage();
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
		JButton button=new JButton("remove");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new  RemoveActivationFromMapItemCommand(hold,act));
				itemInfoText.refreshImage();
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText(act.getName());
		
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
					//itemInfoText.refreshImage();
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
				itemInfoText.refreshImage();
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


	public boolean isSearch() {
		// TODO Auto-generated method stub
		return ((TokenItem)item).isSearch();
	}
	
	public void generateAsMonster() {
		asmonster=new TokenMonster(token.getName());
		placeMonsterEv= new PlaceSpecialMonsterEvent(asmonster,token);
		
		AddEventToTriggerFieldCommand comm =new AddEventToTriggerFieldCommand(placeMonsterEv,null);
		UserInputController controller=UserInputController.getController();
		controller.performCommand(comm);
		//add eventbox
		//set
		
		
	}


	public void addTokenSpecifics(ItemInfoContainer itemInfoText) {
		
		JButton button=new JButton("add");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				generateAsMonster();
			
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("MonsterActivation");
		
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


	
}
