package view.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import MouseListeners.SelectEventListener;
import StoryEditor.DraggAblePanel;
import controller.UserInputController;
import frame.SubContainer;
import misc.Tools;
import misc.listeners.TriggerFieldListener;
import model.event.AddNewEventListener;
import model.event.Event;
import model.event.SingleMovementEvent;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.PerilEvent;
import model.event.advancedevents.peril.Peril;
import model.event.modifier.Modifier;
import view.viewItems.NameChangeListener;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class TriggerField extends BaseField implements NameChangeListener, AddNewEventListener{
	private SubContainer  subEvents;
	protected TriggerItem trigitem;
	protected Trigger trig;
	protected ArrayList<BaseField> fields=new ArrayList<BaseField>();
	//private triggeritem;
	protected BaseField temporaryfield;
	protected JTextField textLabel;
	protected boolean minimized;
	protected Color currentColor;
	protected TriggerFieldListener listen;
	protected Color textcolor=new Color(255,100,40);
	protected JButton butt;
	
	public SubContainer getSubEvents() {
		return subEvents;
	}

	public void setSubEvents(SubContainer subEvents) {
		this.subEvents = subEvents;
	}

	public Trigger getTrig() {
		return trig;
	}

	public void setTrig(Trigger trig) {
		this.trig = trig;
	}

	public TriggerField(Trigger trig, int i, int j) {
		super(trig.getName(),trig.isSelected());
		TriggerField field=this;
		minimized=false;
		subEvents=new SubContainer(this.getWidth()-25,80) {
			public void increaseHeight(int height){
				field.increaseHeight(height);
			}
			
			public void refreshHeight(){
				field.refreshHeight();
			}
		};
		textLabel=new JTextField();
		fields=new ArrayList<BaseField>();
		this.setPreferredSize(new Dimension(i,j));
		this.setSize(new Dimension(i,j));
		setTrig(trig);
		setTrigitem(new TriggerItem(trig));
		this.setLayout(null);
		event=trig;
		initialise();
		createBaseImage();

		refreshHeight();
		initialiseMouseListener();
		
		trig.addNameChangeListener(this);
		trig.addNewEventListener(this);
		
		//textLabel.addMouseListener(listen);
	}
	
	
	

	
	private void initialise() {
		for(Univent ev:trig.getUnivents()) {
			switch(ev.getKind()) {
			case EVENT:
				this.addEvent((Event) ev);
				break;
			case MODIFIER:	
				this.addModifier((Modifier)ev);
				break;
			case TRIGGER:
				this.addTrigger((Trigger) ev,true);
				break;
			case PERIL:
				this.addPeril((PerilEvent)ev);
				break;
	
			default:
				break;
			
			}
		}
		
		
	}

	

	private void addPeril(PerilEvent ev) {
		// TODO Auto-generated method stub
		PerilField field=new PerilField(ev,this.getWidth()-40,300);
		this.addTriggerField(field);
	}

	protected void initialiseMouseListener() {
		listen=new TriggerFieldListener(this);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		
		subEvents.addMouseListener(listen);
		subEvents.addMouseMotionListener(listen);
		textLabel.addMouseListener(listen);
		textLabel.addMouseMotionListener(listen);
		
	}

	public void createImage(Color col) {
		if(minimized) {
			this.initialiseMinimizedImage(col);
		}
		else {
			this.removeAll();
			this.revalidate();
			currentColor=col;
			// TODO Auto-generated constructor stub
			System.out.println("this is added");
		//	
			textLabel.setText(trig.getName());
			System.out.println("this is the name of the trigger "+trig.getIDName());
					//textLabel.setOpaque(false);
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
			
			 butt=new JButton("-");
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
		
			subEvents.setLocation(10,60);
			this.add(subEvents);
			//raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
			subEvents.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			//this.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			for(BaseField field:fields) {
				this.addBaseField(field);
			}
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

	public TriggerItem getTrigitem() {
		return trigitem;
	}

	public void setTrigitem(TriggerItem trigitem) {
		this.trigitem = trigitem;
	}

	public void addEvent(Event placeTileEvent) {
		// TODO Auto-generated method stub
		createEventField(placeTileEvent);
		
		
	}

	private void createEventField(Event placeTileEvent) {
		EventField field=new EventField(placeTileEvent,this.getWidth()-40);
		field.setSize(this.getWidth()-40,50);
		addEventField(field);
	}
	
	public void addEventField(EventField field) {
		//this.increaseHeight(field.getHeight());
		subEvents.add(field);
		if(!Tools.containsWithSameTrigger(fields, field)) {
			System.out.println("this does iet");
			fields.add(field);
			
		}
		field.setSize(new Dimension(this.getWidth()-60,field.getHeight()));
		refreshHeight();
	
		trig.addEvent(field.getEvent());
		field.setTriggerField(this);
		field.createBaseImage();
	}
	
	@Override
	public void increaseHeight(int height) {
		
		subEvents.setSize(subEvents.getHeight(), subEvents.getHeight()+height);
		
		this.setSize(this.getWidth(), this.getHeight()+height);
		((SubContainer) this.getParent()).increaseHeight(height);
		this.revalidate();
		this.repaint();
		
	}
	
	public void refreshHeight() {
		
		
		if(minimized) {
			this.setSize(new Dimension(this.getWidth(),50));
			this.setPreferredSize(new Dimension(this.getWidth(),50));
			butt.setLocation(this.getWidth()-40, 10);
			this.revalidate();
			this.repaint();
			
		}
		else {
			int length=60;
			for(BaseField field:fields) {
				
				length=length+field.getHeight()+10;
			}
		
			subEvents.setSize(new Dimension(subEvents.getWidth(),Math.max(80,length)));
			this.setSize(new Dimension(this.getWidth(),Math.max(145,length+65)));
			this.setPreferredSize(new Dimension(this.getWidth(),Math.max(145,length+65)));
			if(butt!=null) {
				butt.setLocation(this.getWidth()-40, 10);
			}
		
			this.revalidate();
			this.repaint();
			
		}
		if(this.getParent()!=null) {
			((SubContainer) this.getParent()).refreshHeight();
		}
		
	}

	@Override
	protected void removeEvent(Event placeTileEvent) {
		for(int i=0;i<fields.size();i++) {
			System.out.println("does not");
			BaseField toremove=fields.get(i);
			if(toremove.getUnivent()==placeTileEvent) {
				System.out.println("happens");
				subEvents.remove(toremove);
				fields.remove(i);
				i--;
				
			}
			
			toremove.removeEvent(placeTileEvent);
		}
		trig.removeEvent(placeTileEvent);
		// TODO Auto-generated method stub
		refreshHeight();
	}

	@Override
	public void removeTrigger(Trigger trigger) {
		// TODO Auto-generated method stub
		for(int i=0;i<fields.size();i++) {
			BaseField toremove=fields.get(i);
			if(toremove.getUnivent()==trigger) {
				this.remove(toremove);
				subEvents.remove(toremove);
				fields.remove(i);
				i--;
				
			}
			toremove.removeTrigger(trigger);
		}
		trig.removeTrigger(trigger);
		refreshHeight();
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.TRIGGER;
	}
	public void select() {
		super.select();
		createImage(new Color(255,240,170));
	}
	
	public void deselect() {
		super.deselect();
		createImage(new Color(255,230,120));
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return trigitem;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}


	
	@Override
	public void removeField(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		System.out.println("this shit");
		for(int i=0;i<fields.size();i++) {
			BaseField field=fields.get(i);
			field.removeField(todrag);
			trig.removeUnivent(((BaseField)todrag).getUnivent());
			if(field==todrag) {
				System.out.println("happens");
				fields.remove(i);
				subEvents.remove(todrag);
				this.remove(todrag);
				i--;
			}
		}
		refreshHeight();
	}

	public void addTriggerField(TriggerField field) {
		System.out.println("droppeda triggerfield in  a triggerfield" );
		subEvents.add(field);
		if(!Tools.containsWithSameTrigger(fields,field)) {
			System.out.println("added to fields");
			fields.add(field);
		}
		field.setSize(new Dimension(this.getWidth()-40,field.getHeight()));
	
		trig.addTrigger(field.getTrig());
		field.setTriggerField(this);
		refreshHeight();
	}

	public void showInTriggerField(SelectAble selected) {
		// TODO Auto-generated method stub
		System.out.println("going to show "+selected);
		switch(selected.getKind()) {
			
			case EVENT:
				temporaryfield=(EventField) selected;
				//temporary.createTransparent();
				temporaryfield.activateTemporary();
				
				addBaseField(temporaryfield);
				
				break;
			
			case MODIFIER:
			case TRIGGER:
				System.out.println("added temporaryfield to field");
				temporaryfield=(TriggerField) selected;
				//temporaryfield.createTransparent();
				temporaryfield.activateTemporary();
				addBaseField(temporaryfield);
				
				break;
			
			default:
				break;
			
		}
	}

	public void addBaseField(BaseField field) {
		
		subEvents.add(field);
		field.setPreferredSize(new Dimension(this.getWidth()-40,field.getHeight()));
		field.setSize(this.getWidth()-40,field.getHeight());
		
		
		refreshHeight();
		
	
	}

	@Override
	protected void createTransparent() {
		//TODO change here
		setColors(new Color(255,230,120,80));
	
		//createImage(new Color(255,230,120,80));
	}

	public void removeTemporary() {
		if(temporaryfield!=null) {
			if(temporaryfield.isTemporary()) {
				temporaryfield.deActivateTemporary();
				subEvents.remove(temporaryfield);
				this.removeField(temporaryfield);
				temporaryfield.createBaseImage();
				refreshHeight();
			}
		}
		
		
	}

	@Override
	protected void createBaseImage() {
		// TODO Auto-generated method stub
		createImage(new Color(255,230,120));
	}
	@Override
	public void sendEvent(MouseEvent e, Point point, SelectAble selectAble) {
		System.out.println("sendinhere "+this.minimized);
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
			if(SubContainer.isMouseWithinComponent(this)) {
				
				boolean found=false;
				switch(selectAble.getKind()) {
				
				case EVENT:
					break;
				case MODIFIER:
					System.out.println("modifierexited");
				case TRIGGER:
					break;
			
				default:
					return;
		
				
				}
				if(((BaseField)selectAble).isAncestorOf(subEvents)) {
					return;
				}
				if(!minimized) {
					for(int i=0;i<fields.size();i++) {
						if(i>fields.size()) {
							break;
						}
						BaseField field=fields.get(i);
						
					
						if(SubContainer.isMouseWithinComponent(field)) {
							System.out.println("insubarea");
							field.sendEvent(e, point, selectAble);
							 found=true;
						}
					
						
					}
				}
				
				if(!found) {
					super.sendEvent(e, point, selectAble);
					
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

	public void addTrigger(Trigger vent, boolean b) {
		TriggerField field=new TriggerField(vent,this.getWidth()-40,300);
		this.addTriggerField(field);
	}

	private void addModifier(Modifier ev) {
		// TODO Auto-generated method stub
		ModifierField field=new ModifierField(ev,this.getWidth()-40,300);
		this.addTriggerField(field);
		
	}
	public ArrayList<BaseField> getFields() {
		return new ArrayList<BaseField>(fields);
		
	}
	
	@Override
	public void nameChanged(String newname) {
		// TODO Auto-generated method stub
		textLabel.setText(newname);
	}


	private void setColors(Color color) {
		this.setBackground(color);
		textLabel.setBackground(color);
	}
	@Override
	public void setSize(Dimension w) {
		
		
		setSize((int)w.getWidth(),(int)w.getHeight());
	}
	@Override
	public void setSize(int w,int h) {
	
		if(textLabel!=null) {
			textLabel.setSize(w-80,30);
		}
		System.out.println("setsize "+w+" "+h);
		if(subEvents!=null) {
			subEvents.setSize(new Dimension(w-25,subEvents.getHeight()));
		}
		
		if(fields==null) {
			fields=new ArrayList<BaseField>();
		}
		for(BaseField field:fields) {
			field.setSize(w-40,field.getHeight());
		}
		super.setSize(w,h);
	}
	

	public boolean isMouseWithinComponent(int x, int y) {
		// TODO Auto-generated method stub
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
	    Rectangle bounds = this.getBounds();
	    bounds.setLocation(this.getLocationOnScreen());
	    return bounds.contains(mousePos);
	
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
	
	public void minimize() {
		
		minimized=!minimized;
		
		createImage(currentColor);
		refreshHeight();
	}
	
	protected void initialiseMinimizedImage(Color color) {
		this.removeAll();
		this.revalidate();
		
		currentColor=color;
		System.out.println("image made");
		//textLabel=new JTextField(selectedItem.getName());
		textLabel.setBackground(color);
		textLabel.setEnabled(false);
		//this.setSize(120,50);
		//textLabel.setDisabledTextColor(new Color(0,0,200));
		textLabel.setPreferredSize(new Dimension(100,25));
		
		this.setLayout(null);
	
		
		//textLabel.setOpaque(false);
		
		this.setBackground(color);
		Font font=new Font("Verdana", Font.BOLD, 15);
		textLabel.setFont(font);
		
		textLabel.setDisabledTextColor(textcolor);
		textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		



		textLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textLabel.setSize(this.getWidth()-80,this.getHeight()-20);
		//this.setPreferredSize(new Dimension(120,50));

		butt=new JButton("-");
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
	
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
	
		
	}

	public void released(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("draages");
		if(this.minimized) {
			System.out.println("stop here");
			super.released(arg0);
		
		}else {
			if(this.isTemporary()) {
				super.released(arg0);
			}
			else {
				listen.mouseReleased(arg0);
			}
		}
	
	}
	
	public void dragged(MouseEvent arg0) {
		System.out.println("draages");
		if(this.minimized) {
			System.out.println("stop here");
			super.dragged(arg0);
		
		}
		else {
			if(this.isTemporary()) {
				super.dragged(arg0);
			}
			else {
				listen.mouseDragged(arg0);
			}
		}
		
	}

	@Override
	public boolean canNotContain(SelectAble selected) {
		if(this.isAncestorOf((Component) selected)) {
			return true;
		}
		// TODO Auto-generated method stub
		if(selected==this) {
			return true;
		}
		for(BaseField field:fields) {
			if(field.canNotContain(selected)) {
				return true;
			}
		}
		return false;
	}


	public void activateTemporary() {
		//should deactivate mouselistener
		// TODO Auto-generated method stub
		System.out.println("becomes temporary");
		temporary=true;
		
		//this.createImage(Color.BLUE);
		/*
		this.removeMouseListener(listen);
		this.removeMouseMotionListener((MouseMotionListener) listen);
		*/
	}
	public void deActivateTemporary() {
		// TODO Auto-generated method stub
		temporary=false;
	
	//	this.createImage(Color.yellow);
		/*
		this.addMouseListener(listen);
		this.addMouseMotionListener((MouseMotionListener) listen);
		this.createImage(Color.BLUE);
		*/
	}
	@Override
	public void addMouseListener(MouseListener listen) {
		System.out.println("added listener");
		super.addMouseListener(listen);
	}

	@Override
	public void eventAdded(SingleMovementEvent singleMovementEvent) {
		this.addEvent(singleMovementEvent);
		
	}

	public boolean isMinimized() {
		// TODO Auto-generated method stub
		return this.minimized;
	}

	
	
}
