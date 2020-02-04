package view.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import MouseListeners.SelectEventListener;
import StoryEditor.DraggAblePanel;
import controller.UserInputController;
import frame.SubContainer;
import misc.Tools;
import misc.listeners.TriggerFieldListener;
import model.event.Event;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.MultiTrigger;
import model.event.extraevents.TextOption;
import view.viewItems.ItemBox.SelectAble;

//Add listeners


//a triggerfield withe multiple areas to choose from
//this takes some time thinking for it to work
public class MultiTriggerField extends TriggerField {
	
	private static final long serialVersionUID = 2944084166067614602L;
	protected HashMap<TriggerContainer,JTextField> textmap=new HashMap<TriggerContainer,JTextField>();
	protected ArrayList<TriggerContainer> subEventlist=new ArrayList<TriggerContainer>();
	//private SelectEventListener eventlisten;
	protected TriggerFieldListener listen;
	protected MultiTrigger trige;
	
	public MultiTriggerField(MultiTrigger trig, int i, int j) {
		super(trig, i, j);
		this.trige=trig;
		this.addOptions();
		
		//ev.getFirstperil
		/*
		TriggerContainer contain=new TriggerContainer(ev.getFirstperil(),this.getWidth()-25,80,this);
		this.addTriggerContainer(contain);
		contain.setName("minor peril");
		TriggerContainer contain2=new TriggerContainer(ev.getSecondperil(),this.getWidth()-25,80,this);
		this.addTriggerContainer(contain2);
		contain2.setName("major peril");
		*/
		//this.addTriggerContainer(contain);
		this.minimize();
		//textTrigger=textOption;
		//add a field for each option
		//addTextOptions();
		//refreshHeight();
	
	}
	private void addOptions() {
		// TODO Auto-generated method stub
		
		for(Trigger option:trige.getTriggerchoices()) {
			this.initialiseTextOption(option);
		}
		
		
		
	}
	@Override
	public void setSize(Dimension w) {
		
		
		setSize((int)w.getWidth(),(int)w.getHeight());
		//this.createBaseImage();
	}

	private void initialiseTextOption(Trigger option) {
		//add new subeventfield
		TriggerContainer contain=new TriggerContainer(option,this.getWidth()-25,80,this);
		contain.setName(option.getName());
		addTriggerContainer(contain);
		for(Univent vent:option.getUnivents()) {
			
			switch(vent.getKind()) {
			case DOOR:
				break;
			case EVENT:
				
				contain.addEvent((Event) vent);
				
				break;
			case MODIFIER:
			case TRIGGER:
			
				contain.addTrigger((Trigger) vent,false);
				
				
				break;
		
		
			default:
				break;
			
			}
			
			
		}
		
		//add the mouselistener
		
	}
	
	protected void initialiseMouseListener() {
		this.removeMouseListener(listen);
		this.removeMouseMotionListener(listen);
		textLabel.removeMouseListener(listen);
		textLabel.removeMouseMotionListener(listen);
		listen=new TriggerFieldListener(this);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		/*
		subEvents.addMouseListener(listen);
		subEvents.addMouseMotionListener(listen);
		*/
		
		textLabel.addMouseListener(listen);
		textLabel.addMouseMotionListener(listen);
		this.createBaseImage();
		
	}
	

	@Override
	public void createImage(Color col) {
		if(minimized) {
			this.initialiseMinimizedImage(col);
		}
		else {
			this.removeAll();
			this.revalidate();
			currentColor=col;
			if(this.textLabel!=null && trig!=null) {
				textLabel.setText(trig.getName());
				
				textLabel.setEnabled(false);
				textLabel.setDisabledTextColor(textcolor);
				this.setBackground(col);
				textLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
				textLabel.setForeground(new Color(255,100,40));
				textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
				textLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				textLabel.setBackground(col);
				textLabel.setRequestFocusEnabled(false);
				textLabel.setPreferredSize(new Dimension(this.getWidth(),25));
				textLabel.setSize(110,25);
				this.add(textLabel);
				textLabel.setLocation(10,10);
			}
			
			
			JButton butt=new JButton("-");
			butt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					minimize();
				}
				
			});
			this.add(butt);
			butt.setLocation(this.getWidth()-40, 10);
			butt.setSize(35,15);
			butt.setPreferredSize(new Dimension(35,15));
			
			
			int y=80;
			if(subEventlist!=null) {
				System.out.println("thistriggers "+subEventlist.size());
				for(TriggerContainer subEvents:subEventlist) {
					JTextField textLabel2= textmap.get(subEvents);
					textLabel2=textmap.get(subEvents);
					textLabel2.setText(subEvents.getName());
					
					textLabel2.setEnabled(false);
					textLabel2.setDisabledTextColor(textcolor);
					this.setBackground(col);
					textLabel2.setFont(new Font("TimesRoman", Font.BOLD, 20));
					textLabel2.setForeground(new Color(255,100,40));
					textLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
					textLabel2.setAlignmentY(Component.TOP_ALIGNMENT);
					textLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
					textLabel2.setBackground(col);
					textLabel2.setRequestFocusEnabled(false);
					textLabel2.setPreferredSize(new Dimension(this.getWidth(),25));
					textLabel2.setSize(110,25);
					textLabel2.setLocation(10, y-30);
					this.add(textLabel2);
					textLabel2.removeMouseListener(listen);
					textLabel2.removeMouseMotionListener(listen);
					textLabel2.addMouseListener(listen);
					textLabel2.addMouseMotionListener(listen);
					subEvents.setLocation(10,y);
					y=y+subEvents.getHeight()+40;
					this.add(subEvents);
					subEvents.createBaseImage();
					//subEvents.removeAll();
					//raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
					subEvents.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
					//this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
					
					/*
					for(BaseField field:subEvents.getBaseFields()) {
						subEvents.addBaseField(field,false);
					}
					*/
					//subEvents.refreshHeight();
					
				}
			}
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			refreshHeight();
			/*
			this.getModel().addChangeListener(new ChangeListener() {
	
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			*/
	
		}
	}
	public void refreshHeight(boolean b) {
		
		this.createImage(this.getBackground());
		this.refreshHeight();
		if(this.getParent()!=null) {
			((SubContainer) this.getParent()).refreshHeight(true);
		}
	}
	public void refreshHeight() {
	
		if(minimized) {
			this.setSize(new Dimension(this.getWidth(),50));
			this.setPreferredSize(new Dimension(this.getWidth(),50));
			this.revalidate();
			this.repaint();
		}
		else {
			int length=60;
			if(subEventlist!=null) {
				for(TriggerContainer field:subEventlist) {
					
					length=length+field.getHeight()+40;
				}
			
				//subEvents.setSize(new Dimension(subEvents.getWidth(),Math.max(80,length)));
				
			}
			this.setSize(new Dimension(this.getWidth(),Math.max(145,length+45)));
			this.setPreferredSize(new Dimension(this.getWidth(),Math.max(145,length+45)));
		
		
		}
		
		
		this.revalidate();
		this.repaint();
		if(this.getParent()!=null) {
			((SubContainer) this.getParent()).refreshHeight();
		}
	}
	
	@Override
	public void setSize(int w,int h) {
	
		if(textLabel!=null) {
			textLabel.setSize(w-80,30);
		}
		System.out.println("setsize "+w+" "+h);
		
		
		if(subEventlist!=null) {
			for(TriggerContainer contain:subEventlist) {
				contain.setSize(new Dimension(w-25,contain.getHeight()));
			}
			//for each basefield in the container instead
			//but also keep textfield in container
			for(BaseField field:fields) {
				field.setSize(w-40,field.getHeight());
			}
		}
		
		
		
		super.setSize(w,h);
	}
	public void addEventField(EventField field) {
		//this.increaseHeight(field.getHeight());
		//subEvents.add(field);
	
			System.out.println("this does iet");
			subEventlist.get(0).addEventField(field);
			this.createBaseImage();
	}
	public void addTriggerField(TriggerField field) {
		subEventlist.get(0).addTriggerField(field);
		System.out.println("huhuhuhuh");
		this.createBaseImage();
	}
			
		
	protected void addTriggerContainer(TriggerContainer contain) {
		// TODO Auto-generated method stub
		subEventlist.add(contain);
		JTextField newfield=new JTextField();
		newfield.addMouseListener(listen);
		newfield.addMouseMotionListener(listen);
		textmap.put(contain,newfield);
		refreshHeight();
		//trig.addNameChangeListener(this);
	}
	


	public void removeTriggerContainer(TriggerContainer contain) {
		subEventlist.remove(contain);
		textmap.remove(contain);
		refreshHeight();
		
	}

	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.isMinimized()) {
			super.released(arg0);
		}
		else {
			if(this.isTemporary()) {
				super.released(arg0);
			}
			else {
				listen.mouseReleased(arg0);
			}	
		}
		
		//super.released(arg0);
		/*
		else {
			listen.mouseReleased(arg0);
		}
		*/
	}
	
	@Override
	public void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		System.out.println("here");
		if(this.minimized) {
			super.sendEvent(e, point, selectAble);
		}
		else {
			
			
			/*
			if(this.contains(point)) {
				this.dispatchEvent(e);
			}
			*/
			if(selectAble==this) {
				System.out.println("issame");
				//
			
				switch(e.getID()) {
				case MouseEvent.MOUSE_RELEASED:
					((SubContainer) this.getParent()).released(e);
					break;
				}
			
				return;
			}
			boolean found2=false;
			for(TriggerContainer contain:subEventlist) {
				if(SubContainer.isMouseWithinComponent(contain)) {
					
					boolean found=false;
					switch(selectAble.getKind()) {
					
					case EVENT:
						break;
					case MODIFIER:
					case TRIGGER:
						break;
				
					default:
						return;
			
					
					}
					if(((BaseField)selectAble).isAncestorOf(contain)) {
						return;
					}
					ArrayList<BaseField> fieldlist=contain.getBasefields();
					
					for(int i=0;i<fieldlist.size();i++) {
						if(i>fieldlist.size()) {
							break;
						}
						BaseField field=fieldlist.get(i);
						
					
						if(SubContainer.isMouseWithinComponent(field)) {
							System.out.println("insubarea");
							field.sendEvent(e, point, selectAble);
							 found=true;
							 found2=true;
						}
						
					}
					if(!found) {
						System.out.println("given through");
						//MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
						//this.dispatchEvent(convertMouseEvent);
						found=true;
						found2=true;
						MouseEvent convertMouseEvent2 = SwingUtilities.convertMouseEvent(e.getComponent(), e,contain);
						
						contain.dispatchEvent(convertMouseEvent2);
						 
					}
					
					
				}
			
		
			}
			if(!found2) {
				if(e.getID()==MouseEvent.MOUSE_DRAGGED) {
					SubContainer contain=subEventlist.get(0);
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, contain);
					
					contain.dispatchEvent(convertMouseEvent);
					//MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
					
					//contain.dispatchEvent(convertMouseEvent);
				}
				if(e.getID()==MouseEvent.MOUSE_RELEASED) {
					SubContainer contain=subEventlist.get(0);
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, contain);
					
					contain.dispatchEvent(convertMouseEvent);
					//MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
					
					//contain.dispatchEvent(convertMouseEvent);
				}
			}
	
		}
		
	}

	public void changeNameofContainer(TriggerContainer triggerContainer,String newname) {
		if(textmap!=null) {
			JTextField field=textmap.get(triggerContainer);
					if(field!=null) {
						field.setText(newname);
					}
		}
		
		
	}
	@Override
	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		for(TriggerContainer contain:subEventlist) {
			contain.removeTrigger(trigger);
		}
	
	}
	@Override
	public void removeEvent(Event ev) {
		// TODO Auto-generated method stub
		for(TriggerContainer contain:subEventlist) {
			contain.removeEvent(ev);
		}
	
	}
	@Override
	public void removeField(DraggAblePanel todrag) {
		for(TriggerContainer contain:subEventlist) {
			contain.removeField(todrag);
		}
	}
}
