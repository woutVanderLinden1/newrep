package view.events;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import StoryEditor.DraggAblePanel;
import frame.TemporaryAble;
import model.event.Event;
import model.event.Trigger;
import model.event.Univent;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public abstract class BaseField extends DraggAblePanel implements SelectAble,Serializable,ReleasAble,TemporaryAble{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	


	private boolean placed=false;

	protected Univent event;
	protected boolean temporary;

	protected boolean selected;
	protected TriggerField triggerfield;

	

	protected abstract void removeEvent(Event placeTileEvent);


	public abstract  void removeTrigger(Trigger trigger) ;
	public boolean isPlaced() {
		return placed;
	}
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}




	public BaseField(String text) {
		super(text);
		
	}
	@Override
	public String getIDName() {
		// TODO Auto-generated method stub
		return event.getIDName();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return event.getName();
	}



	

	public Univent getUnivent() {
		// TODO Auto-generated method stub
		return event;
	}

	



	public Trigger getBaseTrigger() {
		// TODO Auto-generated method stub
		return triggerfield.getTrig();
	}
	

	@Override
	public void select() {
		System.out.println("event selected");
		// TODO Auto-generated method stub
		selected=true;
		event.select();
		//selectedItem.select();
		
	}

	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		selected=false;
		event.deselect();
		//selectedItem.deselect();
	}

	@Override
	public boolean isSelected() {
		
		return selected;
	}


	public void removeField(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		
	}


	public TriggerField getTriggerField() {
		return triggerfield;
	}


	public void setTriggerField(TriggerField triggerfield) {
		this.triggerfield = triggerfield;
	}



	protected abstract void createTransparent();



	protected abstract void createBaseImage();



	

	public void setName(String newname) {
		event.setName(newname);
		triggerNameChangeListeners(newname);
	}
	
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	
	public void addNameChangeListener(NameChangeListener listen) {
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}
	

	
	public void changeName(String newname) {
		this.setName(newname);
	}



	public void activateTemporary() {
		// TODO Auto-generated method stub
		temporary=true;
	}
	public void deActivateTemporary() {
		// TODO Auto-generated method stub
		temporary=false;
	}



	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//if(this.getTriggerField()!=null) {
			( (ReleasAble) this.getParent()).released(arg0);
		//}
	}
	public void dragged(MouseEvent arg0) {
	
		// TODO Auto-generated method stub
		//if(this.getTriggerField()!=null) {
			( (ReleasAble) this.getParent()).dragged(arg0);
		//}
	}

	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return temporary;
	}
	
	public void makeInvisible() {
		event.makeInvisible();
	}
	public void makeVisible() {
		event.makeVisible();
	}



	public boolean canNotContain(SelectAble selected2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void activateTextLabelMouselistener() {
		
	}
	public void deactivateTextLabelMouselistener() {
		// TODO Auto-generated method stub
		
	}

	



}
