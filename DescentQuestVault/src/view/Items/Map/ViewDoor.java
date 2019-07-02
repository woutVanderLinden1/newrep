package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import activation.OpenDoorActivation;
import activation.SearchTokenActivation;
import controller.UserInputController;
import controller.command.AddActivationToMapItemCommand;
import controller.command.RemoveActivationFromMapItemCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import frame.SubContainer;
import misc.ActivateAble;
import model.Activation;
import model.event.Event;
import model.event.MonsterTurnTrigger;
import model.event.OpenDoorTrigger;
import model.event.PlaceDoorEvent;
import model.event.PlaceTileEvent;
import model.event.RemoveDoorEvent;
import model.event.Trigger;
import model.event.Univent;
import model.event.UnlockDoorEvent;
import view.viewItems.DoorItem;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class ViewDoor extends MapItem implements SelectAble {

	private int scalefactor=260;
	private PlaceDoorEvent placeDoorEvent;
	private OpenDoorTrigger openDoorTrigger;
	private RemoveDoorEvent removeDoorEvent;
	private UnlockDoorEvent unlockDoorEvent;
	protected OpenDoorActivation openact;
	private ArrayList<Activation> activationList=new ArrayList<Activation>();
	
	private static int doornr=0;
	
	private static String giveDoorName() {
		doornr++;
		return "door"+doornr;
	}
	
	public PlaceDoorEvent getPlaceDoorEvent() {
		return placeDoorEvent;
	}

	public void setPlaceDoorEvent(PlaceDoorEvent placeDoorEvent) {
		this.placeDoorEvent = placeDoorEvent;
	}

	public OpenDoorTrigger getOpenDoorTrigger() {
		return openDoorTrigger;
	}

	public void setOpenDoorTrigger(OpenDoorTrigger openDoorTrigger) {
		this.openDoorTrigger = openDoorTrigger;
	}

	public RemoveDoorEvent getRemoveDoorEvent() {
		return removeDoorEvent;
	}

	public void setRemoveDoorEvent(RemoveDoorEvent removeDoorEvent) {
		this.removeDoorEvent = removeDoorEvent;
	}

	public ViewDoor(DoorItem door, ViewSquare baseSquare, int i, int j) {
		super(door,baseSquare,i,j);
		this.setName(giveDoorName());
		setPlaceDoorEvent(new PlaceDoorEvent(this));
		setOpenDoorTrigger(new OpenDoorTrigger(this));
		setRemoveDoorEvent(new RemoveDoorEvent(this));
		
		if(door.isClosedDoor()) {
			setUnlockDoorEvent(new UnlockDoorEvent(this));
			//no op
			//TODO: make addopendooractivationevent
			
		}
		else {
			UnlockDoor();
		}
	}


	public void UnlockDoor() {
		openact=new OpenDoorActivation(this);
		activations.add(openact);
	}



	public OpenDoorActivation getOpenact() {
		return openact;
	}

	public void setOpenact(OpenDoorActivation openact) {
		this.openact = openact;
	}

	protected void setUnlockDoorEvent(UnlockDoorEvent unlockDoorEvent2) {
		
		unlockDoorEvent=unlockDoorEvent2;
	}

	
	public UnlockDoorEvent getUnlockDoorEvent() {
		return unlockDoorEvent;
	}
	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWDOOR;
	}


	public int[][] getShape() {
		// TODO Auto-generated method stub
		return ((ShapeItem)this.getImageItem()).getShape();
	}
	public void setSquare(ViewSquare square) {
		System.out.println("square set "+square.getxID()+" "+square.getyID());
		baseSquare=square;
		point=new Point(square.getX(),square.getY());
	}

	public String getIDName() {
		// TODO Auto-generated method stub
		return item.getIDName();
	}



	
	public void setTriggers(ViewDoor toplace) {
		this.setOpenDoorTrigger(toplace.getOpenDoorTrigger());
		this.setPlaceDoorEvent(toplace.getPlaceDoorEvent());
		this.setRemoveDoorEvent(toplace.getRemoveDoorEvent());
		
	}


	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		ArrayList<Univent> toreturn=new ArrayList<Univent>();
		toreturn.add(placeDoorEvent);
		toreturn.add(removeDoorEvent);
		toreturn.add(this.openDoorTrigger);
		if(unlockDoorEvent!=null) {
			toreturn.add(this.getUnlockDoorEvent());
		}
		return toreturn; 
	}

	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		activations.remove(activation);
	}

	@Override
	public void InitialiseActivation( ItemInfoContainer itemInfoText) {
		addNewActivationCreator(itemInfoText);
		addActivationsShower(itemInfoText);
	}
	

	private void addActivationsShower(ItemInfoContainer itemInfoText) {
		for(Activation act:activations) {
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
		JButton button=new JButton("remove");
		ActivateAble hold=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new  RemoveActivationFromMapItemCommand(hold,act));
			
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

	public boolean isClosed() {
		// TODO Auto-generated method stub
		return ((DoorItem) item).isClosedDoor();
	}

	
	



	

}
