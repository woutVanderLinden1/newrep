package view.events;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import StoryEditor.DraggAblePanel;
import controller.UserInputController;
import frame.SubContainer;
import misc.Tools;
import misc.listeners.TriggerFieldListener;
import model.event.Event;
import model.event.Trigger;
import view.viewItems.ItemBox.SelectAble;

public class TriggerContainer extends TriggerField {

	
	private MultiTriggerField field;
	
	public TriggerContainer(Trigger trig, int i, int j,MultiTriggerField superfield) {
		super(trig, i, j);
		this.setLayout(new FlowLayout());
		fields=new ArrayList<BaseField>();
		field=superfield;
		refreshHeight();
		
		
	}
	public void nameChanged(String newname) {
		super.nameChanged(newname);
		field.changeNameofContainer(this,newname);
	}
	
	@Override
	protected void initialiseMouseListener() {
		listen=new TriggerFieldListener(this,false);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		
	
		textLabel.addMouseListener(listen);
		textLabel.addMouseMotionListener(listen);
		
	}

	
	
	public ArrayList<BaseField> getBasefields() {
		return fields;
	}

	public void setBasefields(ArrayList<BaseField> basefields) {
		this.fields = basefields;
	}

	
	public TriggerContainer(Trigger option,Dimension defaultSize) {
		super(option, (int)defaultSize.getWidth(),(int) defaultSize.getHeight());
		fields=new ArrayList<BaseField>();
		refreshHeight();
	
	}


	public ArrayList<BaseField> getBaseFields() {
		// TODO Auto-generated method stub
		return fields;
	}

	@Override
	public void addBaseField(BaseField field) {
		System.out.println("temporary was added in triggercontainer");
		this.add(field);
		
		field.setPreferredSize(new Dimension(this.getWidth()-40,field.getHeight()));
		field.setSize(this.getWidth()-40,field.getHeight());
		
		//field.createBaseImage();
		//field.refreshHeight();
		
	
	}
	
	@Override
	public void refreshHeight() {
			System.out.println("in refreshheight "+fields);
			int length=60;
			if(fields!=null) {
				for(BaseField field:fields) {
					System.out.println("fieldsize refresh "+field.getHeight() );
					length=length+field.getHeight()+10;
				}
			}
			
			
			this.setSize(new Dimension(this.getWidth(),Math.max(80,length)));
			this.setPreferredSize(new Dimension(this.getWidth(),Math.max(80,length)));
			
			/*
			if(field!=null) {
				field.refreshHeight();
			}
			*/
			
			if(this.getParent()!=null) {
				((SubContainer) this.getParent()).refreshHeight();
			}
			this.revalidate();
			this.repaint();
			
			
		
		
	}

	@Override
	public void createImage(Color col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		super.addEvent(placeTileEvent);
		
		
	}
	


	@Override
	public void addEventField(EventField field) {
		
		this.add(field);
		if(!Tools.containsWithSameTrigger(fields, field)) {
			System.out.println("this does iet");
			fields.add(field);
			
		}
		field.setSize(new Dimension(this.getWidth()-60,field.getHeight()));
		
		trig.addEvent(field.getEvent());
		field.setTriggerField(this);
		field.createBaseImage();
		field.refreshHeight();
	
	}

	@Override
	protected void removeEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		super.removeEvent(placeTileEvent);
	}

	@Override
	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		super.removeTrigger(trigger);
		
		//this.remove(trigger);
	}

	@Override
	public void removeField(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		//super.removeField(todrag);
		this.remove(todrag);
		fields.remove(todrag);
	}

	@Override
	public void addTriggerField(TriggerField field) {
		// TODO Auto-generated method stub
		this.add(field);
		if(!Tools.containsWithSameTrigger(fields,field)) {
			System.out.println("added to fields");
			fields.add(field);
		}
		field.setSize(new Dimension(this.getWidth()-40,field.getHeight()));
		
		trig.addTrigger(field.getTrig());
		field.setTriggerField(this);
		field.refreshHeight();
	}

	@Override
	public void removeTemporary() {
		// TODO Auto-generated method stub
		super.removeTemporary();
	}

	@Override
	public void addTrigger(Trigger vent, boolean b) {
		// TODO Auto-generated method stub
		super.addTrigger(vent, b);
	}

	@Override
	public void setSize(Dimension w) {
		System.out.println("conatinersize set to "+w.getWidth()+" and "+w.getHeight());
		// TODO Auto-generated method stub
		super.setSize(w);
	}

	@Override
	public void setSize(int w, int h) {
		// TODO Auto-generated method stub
		super.setSize(w, h);
		for(BaseField field:fields) {
			field.setSize(this.getWidth()-40,field.getHeight());
		}
	}
	
	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.isTemporary()) {
			field.released(arg0);
		}
		else {
			listen.mouseReleased(arg0);
		}
	}
	
	public void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		
		
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
		
			if(SubContainer.isMouseWithinComponent(this)) {
				
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
				if(((BaseField)selectAble).isAncestorOf(this)) {
					return;
				}
				ArrayList<BaseField> fieldlist=fields;
				
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
					
					this.dispatchEvent(convertMouseEvent);
					/*
					MouseEvent convertMouseEvent2 = SwingUtilities.convertMouseEvent(e.getComponent(), e,subEvents);
					
					subEvents.dispatchEvent(convertMouseEvent2);
					 */
				}
				
			
		}
		
		UserInputController control=UserInputController.getController();
		if(control.isDragging()) {
			
		}
	}
	

}
