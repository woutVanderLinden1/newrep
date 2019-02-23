package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.SwingConstants;

import controller.UserInputController;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.ICommand;
import frame.SubContainer;
import model.Activation;
import model.Monster.Monster;
import model.event.Event;
import model.event.MonsterTurnTrigger;
import model.event.PlaceMonsterEvent;
import model.event.Trigger;
import model.event.Univent;
import model.values.Comparison;
import monstercreator.MonsterMovement;
import view.events.RemoveMonsterEvent;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;

public class ViewMonster extends MapItem {

	public ArrayList<ViewSquare> getPlacementSquares() {
		return placementSquares;
	}

	public void setPlacementSquares(ArrayList<ViewSquare> placementSquares) {
		this.placementSquares = placementSquares;
	}

	private PlaceMonsterEvent placeMonsterEvent;
	private RemoveMonsterEvent removeMonsterEvent;
	private ArrayList<ViewSquare> placementSquares=new ArrayList<ViewSquare>();
	private MonsterMovement set; 
	private MonsterTurnTrigger turnTrigger;
	
	public ViewMonster(MonsterItem image, ViewSquare square, int i, int j)  {
		super(image, square, i, j);
		placeMonsterEvent=new PlaceMonsterEvent(this);
		setName(image.getName());
		initialiseTurnTrigger();
		placeMonsterEvent=new PlaceMonsterEvent(this);
		removeMonsterEvent=new RemoveMonsterEvent(this);
		//setIDName(image.getIDName());
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWMONSTER;
	}

	public Event getPlaceMonsterEvent() {
		// TODO Auto-generated method stub
		return placeMonsterEvent;
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
		toreturn.add(placeMonsterEvent);
		toreturn.add(removeMonsterEvent);
	//	toreturn.add(this.openDoorTrigger);
		return toreturn; 
	}

	public void setAsPlaceMentSquares(ArrayList<ViewSquare> squares) {
		// TODO Auto-generated method stub
		placementSquares=squares;
	}

	public ArrayList<ViewSquare> getPlaceMonsterSquares() {
		// TODO Auto-generated method stub
		return placementSquares;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return item.getName();
	}

	public Trigger getTurnTrigger() {
		// TODO Auto-generated method stub
		return turnTrigger;
	}
	private void initialiseTurnTrigger() {
		turnTrigger=((Monster)	this.getImageItem().getItem()).getDefaultTrigger();
		
	}

	public void addMonsterSpecifics(ItemInfoContainer itemInfoText) {
		addMonsterMovementEventButton(itemInfoText);
		addMonsterMovementCombBox(itemInfoText);
	}

	/**
	 * adds a combobox to add a movement from the made movements
	 * @param itemInfoText
	 */
	private void addMonsterMovementCombBox(ItemInfoContainer itemInfoText) {
		ArrayList<MonsterTurnTrigger> possibleturns=new ArrayList<MonsterTurnTrigger>();
		File dir = new File(System.getProperty("user.dir")+"/Movement/"+this.getImageItem().getItem().getName());
		System.out.println("MovementCombobox"+System.getProperty("user.dir")+"/Movement/"+this.getImageItem().getItem().getName());
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
				turnTrigger=trig;
			
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
					control.performCommand(new  AddTriggerToTriggerFieldCommand(turnTrigger,null));
				
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
	
	
	
	
	
}
