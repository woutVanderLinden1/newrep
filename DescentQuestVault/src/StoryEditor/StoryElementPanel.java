package StoryEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controller.UserInputController;
import controller.commands.ICommand;
import frame.SubContainer;
import model.event.Univent;
import view.events.BaseField;
import view.events.EventField;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class StoryElementPanel extends DragPanel {
	
	private StartElementPanel startPanel;
	private EndElementPanel endPanel;

	public StoryElementPanel(Dimension size) {
		super(size);
		initialise();
	}

	private void initialise() {
		//
		startPanel=new StartElementPanel();
		endPanel=new EndElementPanel();
	}

	
	@Override
	public void sendEvent(MouseEvent e,Point p,SelectAble selectable) {
		System.out.println("instoryelementpanel");
		
		
		defaultpanel.sendEvent(e,p,selectable);
		
		MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e,this);
		
		this.dispatchEvent(convertMouseEvent);
		if( glasspane!=null&& arrowdrawing) {
			MouseEvent convertMouseEvent2 = SwingUtilities.convertMouseEvent(e.getComponent(), e,glasspane);
			glasspane.dispatchEvent(convertMouseEvent2);
		}
		
		
	}

	/*
	public void sendEvent(MouseEvent e, Point newLocation, SelectAble selectAble) {
		MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
		System.out.println(e.getID() +" hi "+MouseEvent.MOUSE_RELEASED);
		switch(e.getID()) {
		case MouseEvent.MOUSE_RELEASED:
			//System.out.println("it works");
			
			mouseReleased((MouseEvent) convertMouseEvent,selectAble);
			break;
		case MouseEvent.MOUSE_DRAGGED:
			
			//System.out.println("it works");
			mouseDragged((MouseEvent) convertMouseEvent,selectAble);
			break;
		case MouseEvent.MOUSE_MOVED:
			//System.out.println("it works");
			//mouseDragged((MouseEvent) arg0);
			break;
			
		}
		
	}
	*/

	private void mouseDragged(MouseEvent convertMouseEvent, SelectAble selectAble) {
		// TODO Auto-generated method stub
		
	}

	private void mouseReleased(MouseEvent convertMouseEvent, SelectAble selectAble) {
		System.out.println("released");
		switch(selectAble.getKind()) {
		case EVENT:
			//AddEventToStoryElementPanelCommand addcommand=new AddEventToStoryElementPanelCommand(this,convertMouseEvent.getPoint(),(DraggAblePanel) selectAble);
			UserInputController control=UserInputController.getController();
			//control.performCommand(addcommand);
			break;
		
			
		case STORYELEMENT:
			addStoryPanelToStoryElementPanel(convertMouseEvent.getPoint(),(StoryProgressPanel) selectAble);
			break;
		
		
		
		
		}
	}

	public void addStoryPanelToStoryElementPanel(Point point, StoryProgressPanel selectAble) {
		this.addDraggAblePanel(selectAble, point.x, point.y);
		
	}
	

	public void addEventPanelToStoryElementPanel(Point point, EventField selectAble) {
		this.addDraggAblePanel(selectAble, point.x, point.y);
		
	}

	/*
	private JScrollPane listScroller;
	private SubContainer container;
	
	private ArrayList<StoryElement> storyElements;
	public StoryElementPanel(int i, int j) {
		this.setSize(new Dimension(i,j));
		container=new SubContainer(i-50,1000);
		listScroller = new JScrollPane(container,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listScroller.setPreferredSize(new Dimension(i-30, j-100));
		container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
		storyElements=new ArrayList<StoryElement>();
		container.setBackground(Color.orange);
		this.add(listScroller);
		
		
	}
	public void addTextElement() {
		TextElement ment=new TextElement();
		storyElements.add(ment);
		
		
		//add new txtpanel to storyeditor)
		StoryTextPanel panel =new StoryTextPanel(ment);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//container.add(Box.createRigidArea(new Dimension(0,1)));
		//container.add(Box.createHorizontalStrut(10));
		container.add(Box.createRigidArea(new Dimension(0,1)));
		container.add(panel);
		refreshSize();
		this.revalidate();
		this.repaint();
		panel.setMaximumSize(new Dimension(this.getWidth()-40,400));
		panel.setPreferredSize(new Dimension(this.getWidth()-40,400));
		panel.setSize(new Dimension(this.getWidth()-40,400));
		container.add(Box.createRigidArea(new Dimension(0,1)));
	}
	private void refreshSize() {
		
		container.setPreferredSize(new Dimension(container.getSize().width,container.getSize().height+400));
		container.setSize(new Dimension(container.getSize().width,container.getSize().height+400));
		
	}
	public void addDialogElement() {
		// TODO Auto-generated method stub
		
	}
	public void addOptionElement() {
		// TODO Auto-generated method stub
		
	}
	public void addSoundElement() {
		// TODO Auto-generated method stub
		
	}
	*/
	
	public void addDraggAblePanel(DraggAblePanel pan,int x,int y) {
		pan.setLocation(new Point(x,y));
		pan.removeListeners();
		
		
		pan.giveMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
			
			}
			
		});
		pan.giveMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			
				System.out.println("pressed accepted");
				UserInputController control=UserInputController.getController();
			
				ICommand command=null;
				try {
					
					
					 
					
				} catch (Exception except) {
				// TODO Auto-generated catch block
					except.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					arrowdrawing=true;
					//System.out.println("arrow drawing started");
					Point p=e.getLocationOnScreen();
					startGlassPaneArrowDraw(pan,e);
				}
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			
				
			}
			
		});
			
			
		
		defaultpanel.add(pan);
		
		this.refresh();
	}

	public void startDragEvent(BaseField todrag) {
		// TODO Auto-generated method stub
		defaultpanel.removeDraggAblePanel(todrag);
	}

	public void startDragPanel(DraggAblePanel todrag) {
		// TODO Auto-generated method stub
		defaultpanel.removeDraggAblePanel(todrag);
		
	}

	public void deleteSelected(SelectAble selected) {
		// TODO Auto-generated method stub
		defaultpanel.deleteSelected(selected);
	}

}
