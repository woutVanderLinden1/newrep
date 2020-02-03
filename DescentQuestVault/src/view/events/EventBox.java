package view.events;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import StoryEditor.DraggAblePanel;
import controller.BaseEventController;
import controller.UserInputController;
import frame.SubContainer;
import frame.TemporaryAble;
import misc.Tools;
import misc.listeners.ItemRemoveListener;
import misc.listeners.MapItemPlaceListener;
import misc.listeners.RemoveTileListener;
import misc.listeners.TilePlaceListener;
import misc.listeners.TriggerFieldListener;
import misc.save.WorldSaveFile;
import model.ItemController;
import model.event.EndTurnTrigger;
import model.event.Event;
import model.event.GameLoseEvent;
import model.event.GameWonEvent;
import model.event.IfIntegerTrigger;
import model.event.MonsterTurnTrigger;
import model.event.StartUpTrigger;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.MultiTrigger;
import model.event.advancedevents.PerilEvent;
import model.event.advancedevents.PerilTiming;
import model.event.extraevents.TestOption;
import model.event.extraevents.TextStop;
import model.event.extraevents.TextTrigger;
import model.event.trigger.EndPhaseTrigger;
import model.values.CustomInteger;
import model.values.IntegerValueItem;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSearchToken;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.viewItems.ItemBox.SelectAble;

public class EventBox extends SubContainer implements ReleasAble,TilePlaceListener, RemoveTileListener,MapItemPlaceListener, ItemRemoveListener,Serializable,TemporaryAble{


	public Trigger basetrigger;
	public TriggerField baseTriggerField;
	

	public Trigger endtrigger;
	public TriggerField endTriggerField;
	
	private BaseField temporary;
	protected ArrayList<BaseField> fields;
	private EventBoxListener listen;
	
	public EventBox(Dimension defaultSize) {
		super(defaultSize);
	//	this.setLayout(null);
		//fields=new ArrayList<BaseField>();
		//this.setLayout(null);
	
		fields=new ArrayList<BaseField>();
		//do this only in a gamestart in a gameload 
		listen =new EventBoxListener(this);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
	
		refreshHeight();
	}

	public EventBox(int i, int height) {
		super(i,height);
	//	this.setLayout(null);
		fields=new ArrayList<BaseField>();
		//this.addTrigger(new StartUpTrigger());
	//	StartUpTrigger basetrigger=new StartUpTrigger();
		
		
		listen =new EventBoxListener(null);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		
		refreshHeight();
	}

	public TriggerField addTrigger(Trigger trig) {
		for(BaseField field2:fields) {
			if(field2.getUnivent()==trig) {
				return (TriggerField) field2;
			}
		}
		
			TriggerField field=null;
			if(trig.isMultiTrigger()) {
				if(trig.isStopEvent()) {
					field=new MultiTextTriggerField((TextTrigger) trig);
					 
				}
				else {
					field=new MultiTriggerField((MultiTrigger) trig,this.getWidth()-40,300);
				}
				 
			}
			else {
				field=new TriggerField(trig,this.getWidth()-40,300);
				
			}
			this.addTriggerField(field);
		
			
		
		//field.setLocation(0,200);
	//	field.setSize(this.getWidth()-5,190);
		
		//this.addTriggerField(field);
		System.out.println("new triggerfield add");
		
		return field;
	}
	private void addTriggerField(TriggerField field) {
		// TODO Auto-generated method stub
		this.add(field);
		addFieldToFields(field);
		field.setSize(this.getWidth(),field.getHeight());
		field.setPreferredSize(new Dimension(this.getWidth(),field.getHeight()));
		field.revalidate();
		
		//refreshHeight();
		field.setTriggerField(null);
		System.out.println("trigger added");
		refreshHeight();
	}

	@Override
	public void tilePlaced(ViewTile tile) {
		// TODO Auto-generated method stub
		//if tile placed create an event of tile placecommand after the initial tileplaced
		UserInputController control=UserInputController.getController();
		
		
		this.addEvent(tile.getPlaceTileEvent(),control.getSelected());
	}

	private void addEvent(Event placeTileEvent, TriggerField baseTriggerField2) {
		// TODO Auto-generated method stub
		baseTriggerField2.addEvent(placeTileEvent);
		refreshHeight();
	}
	
	public void refreshHeight() {
		
		int lenght=0;
		for(BaseField field:fields) {
			
			lenght=lenght+field.getHeight()+30;
		}
		this.setSize(this.getWidth(),Math.max(lenght,this.getHeight()));
		this.setPreferredSize(new Dimension(this.getWidth(),Math.max(lenght,this.getHeight())));
		this.revalidate();
		this.repaint();
	}

	@Override
	public void TileRemoved(ViewTile tile) {
		//remove the corresponding tile from the fields
		this.removeEvent(tile.getPlaceTileEvent());
		
		
	}

	protected void removeEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		for(int i=0;i<fields.size();i++) {
			BaseField field=fields.get(i);
			
			
			field.removeEvent(placeTileEvent);
			if(field.getUnivent()==placeTileEvent) {
				this.remove(field);
				fields.remove(field);
				i--;
				
			}
		}
		refreshHeight();
	}

	@Override
	public void notify(MapItem door) {
		// TODO Auto-generated method stub
		switch(door.getKind()) {
			case VIEWMONSTER:
				addMonster((ViewMonster) door);
				break;
			case VIEWTOKEN:
				addTokenEvents((ViewToken) door);
				break;
			case VIEWDOOR:
				//only possible currently
				addDoorEvents((ViewDoor) door);
				break;
		
			
		default:
			break;
		
		}
	}

	private void addMonster(ViewMonster door) {
		// TODO Auto-generated method stub
		baseTriggerField=UserInputController.getController().getSelected();
		this.addEvent(door.getPlaceMonsterEvent(),baseTriggerField);
	}

	
	private void addTokenEvents(ViewToken door) {
		baseTriggerField=UserInputController.getController().getSelected();
		
		this.addEvent(door.getPlaceSearchTokenEvent(),baseTriggerField);
		if(door.isSearch()) {
			TriggerField trig=this.addTrigger(((ViewSearchToken) door).getSearchTokenTrigger());
			if(door.isSearch()) {
				this.addEvent(((ViewSearchToken)door).getEffect(),trig);
			}
			//
			this.addEvent(door.getRemoveSearchTokenEvent(), trig);
		}
		
	
		
	}

	private void addDoorEvents(ViewDoor door) {
		baseTriggerField=UserInputController.getController().getSelected();
		
		this.addEvent(door.getPlaceDoorEvent(),baseTriggerField);
		TriggerField trig=this.addTrigger(door.getOpenDoorTrigger());

		this.addEvent(door.getRemoveDoorEvent(), trig);
		if(door.isClosed()) {
			this.addEvent(door.getUnlockDoorEvent());
		}
		
		
	}

	@Override
	public void itemRemoved(MapItem item) {
		System.out.println("itemremoved "+ item);
		// TODO Auto-generated method stub
				switch(item.getKind()) {
				case VIEWDOOR:
					//only possible currently
					removeDoorEvents((ViewDoor) item);
					break;
				case VIEWMONSTER:
					removeMonsterEvents((ViewMonster) item);
					break;
				case VIEWTOKEN:	
					removeTokenEvents((ViewToken) item);
					break;
				default:
					break;
				
				}
	}

	private void removeTokenEvents(ViewToken item) {
		this.removeEvent(item.getPlaceSearchTokenEvent());
		this.removeEvent(item.getRemoveSearchTokenEvent());
		if(item.isSearch()) {
			this.removeTrigger(((ViewSearchToken) item).getSearchTokenTrigger());
		}
		
	}

	private void removeMonsterEvents(ViewMonster item) {
		System.out.println("removed monster events");
		this.removeEvent(item.getPlaceMonsterEvent());
		
	}

	private void removeDoorEvents(ViewDoor item) {
		this.removeEvent(item.getPlaceDoorEvent());
		this.removeEvent(item.getRemoveDoorEvent());
		this.removeTrigger(item.getOpenDoorTrigger());
		// TODO Auto-generated method stub
		
	}

	public void removeTrigger(Trigger trigger) {
		for(int i=0;i<fields.size();i++) {
			BaseField field=fields.get(i);

			field.removeTrigger(trigger);
			if(field.getUnivent()==trigger) {
			
				this.remove(field);
				fields.remove(field);
				i--;
			}
		}
		refreshHeight();
		
	}

	public void endDragEvent(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		this.removeField(todrag);
		
	}
	
	public void startDragEvent(BaseField todrag) {
		// TODO Auto-generated method stub
		todrag.createTransparent();
		
		
	}

	private void removeField(DraggAblePanel todrag) {
		System.out.println("event removed");
		// TODO Auto-generated method stub
		System.out.println(fields.size());
		for(int i=0;i<fields.size();i++) {
			
			BaseField field=fields.get(i);
			System.out.println(field.getUnivent());
			field.removeField(todrag);
			if(field==todrag) {
				fields.remove(i);
				this.remove(field);
				i--;
			}
			
		}
		this.revalidate();
		this.repaint();
	}

	public void addEventToTriggerField(SelectAble selected, TriggerField field) {
		((BaseField) selected).setPlaced(true);
		//UserInputController control=UserInputController.getUserInputController();
		//if(field==null) {
			//if(control.getSelected()!=null) {
				
			//}
		//}
		
		switch(selected.getKind()) {
		case TRIGGER:
			if(field==null) {
				
				System.out.println("adding trigger field to eventbox");
				this.addTriggerField((TriggerField) selected);
			}
			else {
				System.out.println(selected+" "+field);
				System.out.println("adding trigger to triggerfield");
				field.addTriggerField((TriggerField) selected);

			}
	
			break;
		case EVENT:
			if(field==null) {
				this.addEventField((EventField) selected);
			}
			else {
				field.addEventField((EventField) selected);

			}
			break;
		case MODIFIER:
			if(field==null) {
				this.addTriggerField((ModifierField) selected);
			}
			else {
				field.addTriggerField((ModifierField) selected);

			}
		default:
			break;
		
		}
		
	}

	private void addEventField(EventField selected) {
		// TODO Auto-generated method stub
		this.add(selected);
		addFieldToFields(selected);
		selected.setSize(this.getWidth(),50);
		refreshHeight();
		
		selected.setTriggerField(null);
		//this.add(selected);
		System.out.println("event added to eventbox");
	}

	public void showInTriggerField(SelectAble selected, TriggerField whereshow) {
		// TODO Auto-generated method stub
		if(whereshow==null) {
			showHere(selected);
		}
		else {
			whereshow.showInTriggerField(selected);
		}
	}

	private void showHere(SelectAble selected) {
		System.out.println("it is shown here");
		/*
		if(true) {
			return;
		}
		*/
		switch(selected.getKind()) {
		
		case EVENT:
			temporary=(EventField) selected;
			temporary.createTransparent();
			temporary.activateTemporary();
			this.addBaseField(temporary);
			break;
		case MODIFIER:
		case TRIGGER:
			temporary=(TriggerField) selected;
			temporary.createTransparent();
			temporary.activateTemporary();
			this.addBaseField(temporary);
			
			break;
		
		default:
			break;
		
		}
		
	}

	private void addBaseField(BaseField selected) {
		// TODO Auto-generated method stub
		
		//selected.setSize(this.getWidth(),selected.getHeight());
		
		selected.setPreferredSize(new Dimension(this.getWidth(),selected.getHeight()));
		selected.setSize(this.getWidth(),selected.getHeight());
	
	
		this.add(selected);
		
		System.out.println("temporary added in eventbox");
		refreshHeight();
		
		
	}

	public void removeTemporaryShown(TriggerField whereshow) {
		// TODO Auto-generated method stub

			
		
			if(whereshow==null) {
				if(temporary!=null) {
					if(temporary.isTemporary()) {
							
						temporary.createBaseImage();
						temporary.deActivateTemporary();
						this.remove(temporary);
					}
				}
				
				
			}
			else {
				whereshow.removeTemporary();
			}
			this.revalidate();
			this.repaint();
		
	}

	public void deleteSelected(SelectAble selected) {
		System.out.println("deleted a "+selected.getKind());
		switch(selected.getKind()) {
		case DOOR:
			break;
		case EVENT:
			this.removeField((EventField) selected);
			break;
		case TILEITEM:
			break;
		case MODIFIER:
		case TRIGGER:
			this.removeField((TriggerField) selected);
			break;
		case VIEWDOOR:
			break;
		case VIEWTILE:
			break;
		default:
			break;
		
		}
	}
	@Override
	public void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		// TODO Auto-generated method stub
		if(SubContainer.isMouseWithinComponent(this)) {
			boolean found=false;
			/*
			if(((BaseField)selectAble).isAncestorOf(this)) {
				return;
			}
			*/
			point=new Point(point.x-this.getX(),point.y-this.getY());
			System.out.println("the fieldsize "+ fields.size());
			ArrayList<BaseField> newfields=new ArrayList<BaseField>(fields);
			for(int i =0;i<newfields.size();i++) {
				BaseField field=newfields.get(i);
				if(field!=null) {
					if(SubContainer.isMouseWithinComponent(field)) {
						System.out.println("field is found "+field);
						field.sendEvent(e,point,selectAble);
						found =true;
					}
				}
				
				
			}
			if(!found) {
				System.out.println("field is not found");
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
				
				this.dispatchEvent(convertMouseEvent);
				
			
				super.sendEvent(e, point, selectAble);
			}
			
		}
	}


	public void addUniventToTriggerField(Univent vent, TriggerField field) {
		
		
		switch(vent.getKind()) {
		case DOOR:
			break;
		case EVENT:
			if(field==null) {
				addEvent((Event) vent);
			}
			break;
		case MODIFIER:
		case TRIGGER:
			if(field==null) {
				addTrigger((Trigger) vent);
			}
			
			break;
	
	
		default:
			break;
		
		}
		
	}

	private void addEvent(Event vent) {
		EventField field=new EventField(vent,this.getWidth()-20);
		this.addEventField(field);
		
	}

	public void addUniventToTriggerField(Univent vent, Trigger trigger, boolean b) {
		// TODO Auto-generated method stub
		switch(vent.getKind()) {
		case EVENT:
			if(trigger==null) {
				addEvent((Event) vent);
			}
			else {
				TriggerField field=Tools.CorrespondingTriggerField(trigger,fields);
				field.addEvent((Event) vent);
			}
			
			break;
		case MODIFIER:
		case TRIGGER:
			if(trigger==null) {
				addTrigger((Trigger) vent);
			}
			else {
				TriggerField field2=Tools.CorrespondingTriggerField(trigger,fields);
				field2.addTrigger((Trigger) vent,b);
			}
			
			break;
		
		
		default:
			break;
		
		}
		
	}
	public void addbaseTrigger() {
	
	}

	public void setBaseBaseTrigger(StartUpTrigger baseTrigger2) {
		UserInputController control=UserInputController.getController();
		basetrigger=baseTrigger2;
		//baseTriggerField=this.addTrigger(baseTrigger2);
		//control.addStartGameListener((StartUpTrigger) baseTrigger2);
	}

	public TriggerField getFieldAt(int x, int y) {
		// TODO Auto-generated method stub
		TriggerField toreturn=null;
		for(BaseField field:fields) {
			switch(field.getKind()) {
			case MODIFIER:
			case TRIGGER:
				TriggerField got=((TriggerField) field).getFieldAt(x,y);
				if(got!=null) {
					return got;
				}
				if(((TriggerField) field).isMouseWithinComponent(x, y)) {
					return (TriggerField) field;
				}
				break;
		
			default:
				break;
			
			}
		}
		return toreturn;
	}

	public boolean containsPoint(int x, int y) {
		
		
		// TODO Auto-generated method stub
		return SubContainer.isMouseWithinComponent(this);
	}
	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
			listen.mouseReleased(arg0);
		
	}
	
	public void dragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
			listen.mouseDragged(arg0);
		
	}
	public void addFieldToFields(BaseField field) {
		if(!Tools.containsWithSameTrigger(fields, field)) {
			System.out.println("this does iet");
			fields.add(field);
			
		}
		//fields.add(field);
	}

	@Override
	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return false;
	}

	public void clearEventBox() {
		this.removeAll();
		fields.clear();
		
	}

	public ArrayList<BaseField> getFields() {
		// TODO Auto-generated method stub
		return fields;
	}

	public void initialiseMovement(MonsterTurnTrigger g) {
		removeAllFields();
		this.addTrigger(g);
	}

	private void removeAllFields() {
		removeAll();
		basetrigger=null;
		baseTriggerField=null;
		temporary=null;
		fields.clear();
		
	}

	public void saveThis(WorldSaveFile file) {
		// TODO Auto-generated method stub
		for(BaseField field:fields) {
			file.addUnivent(field.getUnivent());
		}
		//file.setBaseTrigger((StartUpTrigger)basetrigger);
		
	}


	public WorldSaveFile saveThis() {
		WorldSaveFile file=new WorldSaveFile();
		for(BaseField field:fields) {
			file.addUnivent(field.getUnivent());
		}
		ItemController itemcontrol=ItemController.getItemController();
		file.saveCustomValues(itemcontrol.getValues());
		//file.setBaseTrigger((StartUpTrigger)basetrigger);
		return file;
		//save the events to the file
	}

	public void addBaseTriggers() {
		//addBaseTrigger();
		addLoseEvent();
		addWinEvent();
		
		
		//we also need to add game won and game lost
		//and put game lost at when hope=0;
		
		
	}

	private void addWinEvent() {
		GameWonEvent ev=new GameWonEvent();
		this.addEvent(ev);
		
	}

	private void addLoseEvent() {
	
		GameLoseEvent lose=new GameLoseEvent();
		IntegerValueItem it=new IntegerValueItem(ItemController.getItemController().getHope());
		
		IfIntegerTrigger hopetrigger=(IfIntegerTrigger) it.getIftrigger().copy();
		hopetrigger.addEvent(lose);
		hopetrigger.setCompvalue(-1);
		this.addTrigger(hopetrigger);
		

		
	}

	private void addEndTrigger(EndPhaseTrigger endtrigger) {
		UserInputController control=UserInputController.getController();
		this.endtrigger=endtrigger;
		
		endTriggerField=this.addTrigger(endtrigger);
		
	}

	private void addBaseTrigger(StartUpTrigger trig) {
		UserInputController control=UserInputController.getController();
		basetrigger=trig;
		
		baseTriggerField=this.addTrigger(basetrigger);
		//baseTriggerField.addTrigger(new TestOption(),false);
		
	//	control.addStartGameListener((StartUpTrigger) basetrigger);
		//control.addEndPhaseListener(trig);
		
		//addendturntrigger
		//addperiltrigger
		//EndPhaseTrigger trig=new EndPhaseTrigger();
		//PerilEvent peril=new PerilEvent(PerilTiming.TEST);
		//control.addEndPhaseListener(trig);
		
	}

	public void setBaseTriggers(BaseEventController baseEventController) {
		System.out.println("bas triggers added");
		this.removeTrigger(basetrigger);
		this.removeTrigger(endtrigger);
		this.addEndTrigger(baseEventController.getEndtrigger());
		this.addBaseTrigger(baseEventController.getStartuptrigger());
		UserInputController control=UserInputController.getController();
		control.setSelected(baseTriggerField);
	}
	
	public BaseField getBaseFieldFromUnivent(Univent vent) {
		return null;
	}
	


}
