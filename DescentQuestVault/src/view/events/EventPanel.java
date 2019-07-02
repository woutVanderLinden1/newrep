package view.events;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.BaseEventController;
import frame.SubContainer;
import misc.save.WorldSaveFile;
import model.SelectedArea;
import model.event.StartUpTrigger;
import model.event.Trigger;
import model.event.Univent;
import model.values.CustomInteger;
import view.viewItems.TitleBox;
import view.viewItems.ItemBox.SelectAble;

public class EventPanel extends SubContainer implements Serializable {

	private JScrollPane eventScroller;
	private EventBox eventBox;
	
	public EventPanel(int width, int height) {
		super(width, height);
		this.setBackground(Color.BLACK);
		TitleBox pan=new TitleBox("Events", 80, 40);
		//pan.setFont(font);
		this.add(pan);
	
		eventBox=new EventBox(width-30,height);
	    eventScroller=new JScrollPane(eventBox,eventScroller.VERTICAL_SCROLLBAR_ALWAYS,
	        	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        eventScroller.setPreferredSize(new Dimension(width-10,height-50));
	       // scrollPane.setContent(layout);
	       

	       this.add(eventScroller);
	      
	}

	public EventBox getEventBox() {
		// TODO Auto-generated method stub
		return eventBox;
	}

	public void startDragEvent(BaseField todrag) {
		// TODO Auto-generated method stub
		eventBox.startDragEvent(todrag);
	}

	public void addEventToTriggerField(SelectAble selected, TriggerField field) {
		// TODO Auto-generated method stub
		eventBox.addEventToTriggerField(selected,field);
	}

	public void showInTriggerField(SelectAble selected, TriggerField whereshow) {
		// TODO Auto-generated method stub
		eventBox.showInTriggerField(selected,whereshow);
	}
	public void removeTemporaryShown(TriggerField whereshow) {
		eventBox.removeTemporaryShown(whereshow);
	}

	public void deleteSelected(SelectAble selected) {
		// TODO Auto-generated method stub
		eventBox.deleteSelected(selected);
	}

	public void endDragEvent(BaseField todrag) {
		// TODO Auto-generated method stub
		eventBox.endDragEvent(todrag);
	}

	@Override
	public void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		// TODO Auto-generated method stub
		point=new Point(point.x-this.getX(),point.y-this.getY());
		eventBox.sendEvent(e,point,selectAble);
	}

	public WorldSaveFile saveThis() {
		
		return eventBox.saveThis();
	}

	public void addUniventToTriggerField(Univent vent, TriggerField field) {
		eventBox.addUniventToTriggerField(vent,field);
		
	}

	public void addUniventToTrigger(Univent vent, Trigger trigger, boolean b) {
		// TODO Auto-generated method stub
		eventBox.addUniventToTriggerField(vent, trigger,b);
	}

	public void addStartTriggers(CustomInteger hope) {
		// TODO Auto-generated method stub
		eventBox.addBaseTriggers(hope);
	}

	public void setBaseTrigger(StartUpTrigger baseTrigger) {
		// TODO Auto-generated method stub
		eventBox.setBaseBaseTrigger(baseTrigger);
	}

	public TriggerField getFieldAt(int x, int y) {
		// TODO Auto-generated method stub
		return eventBox.getFieldAt(x,y);
	}

	public boolean eventBoxContains(int x, int y) {
		// TODO Auto-generated method stub
		return eventBox.containsPoint(x,y);
	}

	public void clearEventBox() {
		// TODO Auto-generated method stub
		eventBox.clearEventBox();
	}

	public void saveThis(WorldSaveFile thefile) {
		// TODO Auto-generated method stub
		eventBox.saveThis(thefile);
	}

	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		eventBox.removeTrigger(trigger);
	}

	public void setBaseTriggers(BaseEventController baseEventController) {
		// TODO Auto-generated method stub
		eventBox.setBaseTriggers(baseEventController);
	}

	

}
