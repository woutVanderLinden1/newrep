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
import controller.UserInputController;
import frame.SubContainer;

import misc.listeners.TriggerFieldListener;
import model.event.Trigger;
import model.event.extraevents.TextOption;
import view.viewItems.ItemBox.SelectAble;

//Add listeners


//a triggerfield withe multiple areas to choose from
//this takes some time thinking for it to work
public class MultiTriggerField extends TriggerField {
	
	protected HashMap<TriggerContainer,JTextField> textmap=new HashMap<TriggerContainer,JTextField>();
	protected ArrayList<TriggerContainer> subEventlist=new ArrayList<TriggerContainer>();
	private SelectEventListener eventlisten;
	
	public MultiTriggerField(Trigger trig, int i, int j) {
		super(trig, i, j);
		
	
	}
	
	protected void initialiseMouseListener() {
		
		eventlisten=new SelectEventListener(this);
		this.addMouseListener(eventlisten);
		this.addMouseMotionListener(eventlisten);
		/*
		subEvents.addMouseListener(listen);
		subEvents.addMouseMotionListener(listen);
		*/
		textLabel.addMouseListener(eventlisten);
		textLabel.addMouseMotionListener(eventlisten);
		
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
			
			this.add(textLabel);
			textLabel.setLocation(10,10);
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
					
					subEvents.setLocation(10,y);
					y=y+subEvents.getHeight()+40;
					this.add(subEvents);
					//raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
					subEvents.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
					//this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
					for(BaseField field:subEvents.getBaseFields()) {
						subEvents.addBaseField(field);
					}
					
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
	
	protected void refreshHeight() {
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
	
	protected void addTriggerContainer(TriggerContainer contain) {
		// TODO Auto-generated method stub
		subEventlist.add(contain);
		JTextField newfield=new JTextField();
		newfield.addMouseListener(eventlisten);
		newfield.addMouseMotionListener(eventlisten);
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
		
		if(this.isTemporary()) {
			super.released(arg0);
		}
	
		//super.released(arg0);
		/*
		else {
			listen.mouseReleased(arg0);
		}
		*/
	}
	
	@Override
	protected void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		
		
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
					}
					
				}
				if(!found) {
					System.out.println("given through");
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
					
					contain.dispatchEvent(convertMouseEvent);
					/*
					MouseEvent convertMouseEvent2 = SwingUtilities.convertMouseEvent(e.getComponent(), e,subEvents);
					
					subEvents.dispatchEvent(convertMouseEvent2);
					 */
				}
				
			}
		}
		
		UserInputController control=UserInputController.getController();
		if(control.isDragging()) {
			
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
}
