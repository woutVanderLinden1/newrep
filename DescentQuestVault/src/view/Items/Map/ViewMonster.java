package view.Items.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controller.UserInputController;
import controller.command.AddActivationToMapItemCommand;
import controller.command.RemoveActivationFromMapItemCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.ICommand;
import frame.SubContainer;
import misc.ActivateAble;
import model.Activation;
import model.Monster.Monster;
import model.event.DefeatMonsterTrigger;
import model.event.Event;
import model.event.MonsterTurnTrigger;
import model.event.PlaceMonsterEvent;
import model.event.Trigger;
import model.event.Univent;
import model.values.Comparison;
import monstercreator.MonsterMovement;
import view.events.RemoveMonsterEvent;
import view.game.DefeatMonsterActivation;
import view.game.MonsterActivation;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;

public class ViewMonster extends MapItem implements ActivateAble{



	private PlaceMonsterEvent placeMonsterEvent;
	private RemoveMonsterEvent removeMonsterEvent;
	private DefeatMonsterTrigger defeatTrigger;
	private ArrayList<ViewSquare> placementSquares=new ArrayList<ViewSquare>();
	private MonsterMovement set; 
	private MonsterTurnTrigger turnTrigger;
	protected ArrayList<MonsterActivation> activationList=new ArrayList<MonsterActivation>();
	
	public ViewMonster(MonsterItem image, ViewSquare square, int i, int j)  {
		super(image, square, i, j);
		
		setName(image.getName());
		initialiseTurnTrigger();
		
		placeMonsterEvent=new PlaceMonsterEvent(this);
		removeMonsterEvent=new RemoveMonsterEvent(this);
		defeatTrigger=new DefeatMonsterTrigger(this);
		activationList.add(new DefeatMonsterActivation());
		//setIDName(image.getIDName());
		// TODO Auto-generated constructor stub
	}
	
	public void setMonsterActivations(ArrayList<MonsterActivation> activations) {
		// TODO Auto-generated method stub
		this.activationList=activations;
	}
	public ArrayList<MonsterActivation> getMonsterActivations() {
		// TODO Auto-generated method stub
		return activationList;
	}

	
	public ArrayList<ViewSquare> getPlacementSquares() {
		return placementSquares;
	}

	public void setPlacementSquares(ArrayList<ViewSquare> placementSquares) {
		this.placementSquares = placementSquares;
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
		toreturn.add(defeatTrigger);
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

	public Image getScaleImage(int i) {
		// TODO Auto-generated method stub
		return item.getScaleImage(i) ;
	}
	



	public DefeatMonsterTrigger getDefeatTrigger() {
		return defeatTrigger;
	}

	public void setDefeatTrigger(DefeatMonsterTrigger defeatTrigger) {
		this.defeatTrigger = defeatTrigger;
	}

	@Override
	public ArrayList<Activation> getActivations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addActivation(Activation act) {
		// TODO Auto-generated method stub
		activations.add(act);
	}

	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		activationList.remove(activation);
	}
	
	
	
}
