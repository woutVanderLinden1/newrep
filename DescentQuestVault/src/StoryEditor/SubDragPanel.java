package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.UserInputController;
import controller.commands.BasicCommand;
import controller.commands.ICommand;
import controller.commands.select.SelectCommand;
import frame.SubContainer;
import view.events.BaseField;
import view.events.EventField;
import view.events.EventPanel;
import view.events.ReleasAble;
import view.viewItems.ItemBox.SelectAble;

public class SubDragPanel extends SubContainer implements ReleasAble {

	private ArrayList<ViewArrow> arrows=new ArrayList<ViewArrow>();
	private ArrayList<DraggAblePanel> dragablepanels;
	
	private DragPanel parent;
	
	public void AddArrow(ViewArrow arrow) {
		arrows.add(arrow);
	}
	
	public SubDragPanel(DragPanel parent) {
		super(new Dimension(0,0));
		this.setBackground(Color.blue);
		this.parent=parent;
		dragablepanels=new ArrayList<DraggAblePanel>();
		SubDragPanel panel=this;
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				UserInputController controller=UserInputController.getController();
				for(ViewArrow arrow:arrows) {
					if(arrow.contains(arg0.getPoint())){
						BasicCommand command = controller.generateSelectArrowCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_PRESSED,arrow, panel,parent);
						 
						controller.performCommand(command);
					}
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("this triggers released subdragpanel");
				UserInputController controller=UserInputController.getController();
				
				BasicCommand command = controller.generateReleasedOnSubDragPanelCommand(arg0.getX(), arg0.getY(),arg0, MouseEvent.MOUSE_RELEASED, panel,parent);
				 
				controller.performCommand(command);
			
				//parent.refresh();
				//revalidate();
				//repaint();
			}
			
		});
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				System.out.println("draggedhere");
				if(parent.isArrowDragging()) {
					parent.doDragListenerEvent(arg0);
				}
				
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				//System.out.println("listened");
				for(Arrow arrow:arrows) {
					if(arrow.contains(arg0.getPoint())){
						arrow.hoveredOver();
						
					}
					else {
						arrow.hoveredStopped();
					}
					
				}
				revalidate();
				repaint();
				
				
				
			}

			
			
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("this is painted");
		this.setBackground(Color.white);
		  Graphics2D g2d=(Graphics2D) g;
		  super.paintComponent(g2d);
		  for(Arrow arrow:arrows) {
			  System.out.println("arrow drawn");
			  arrow.drawArrow(g2d);
		  }
		  //i know this doesn't makes sense but it works :p
		  this.setBackground(Color.gray);
		  
	}
	public void removeArrows() {
		
		arrows.clear();
	}

	public void removeDraggAblePanel(DraggAblePanel todrag) {
		todrag.setInvisible();
		//this.remove(todrag);
		
		
	}

	@Override
	public void released(MouseEvent e) {
		
		MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
		this.dispatchEvent(convertMouseEvent);
	}

	@Override
	public void dragged(MouseEvent e) {
		
		MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
		this.dispatchEvent(convertMouseEvent);
	}

	public void sendEvent(MouseEvent e, Point p, SelectAble selectable) {
		System.out.println("heresended");
		if(selectable.isMapItem()) {
			System.out.println("returned here");
			return;
		}
		boolean insubcomp=false;
		for(int i=0;i<dragablepanels.size();i++) {
			DraggAblePanel panel=dragablepanels.get(i);
			System.out.println("in panel "+panel+ " "+panel.isMouseWithinComponent(panel));
			if(panel.isMouseWithinComponent(panel)) {
				insubcomp=true;
				panel.sendEvent(e, e.getPoint(), selectable);
				
			}
		}
		System.out.println( "checkininside");
		if(!insubcomp && this.isMouseWithinComponent(this)) {
			
		
			//only if holded is a mapitem do the thingie
			System.out.println("mouseevent got in subdragpanel");
			Point point= new Point(p.x-this.getX(),p.y-this.getY());
			
			MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
			
			this.dispatchEvent(convertMouseEvent);
			for(MouseListener listen:this.getMouseListeners()) {
				System.out.println(listen);
			}
			/*
			
				switch(e.getID()) {
				case MouseEvent.MOUSE_CLICKED:
					new GridMouseListener(this).mouseClicked(e,point);
					break;
				case MouseEvent.MOUSE_RELEASED:
					new GridMouseListener(this).mouseReleased(e,point);
					break;
				
				}
			
	
				switch(e.getID()) {
				case MouseEvent.MOUSE_MOVED:
					System.out.println("moouse moved");
					new GridMouseListener(this).mouseMoved(e,point);
					break;
				case MouseEvent.MOUSE_DRAGGED:
					System.out.println("moouse dragged");
					new GridMouseListener(this).mouseDragged(e,point);
					break;
				}
				
			*/
		}
		
	}

	public void addDraggablePanel(DraggAblePanel pan) {
		dragablepanels.add(pan);
		this.add(pan);
	}

	public void addDraggablePanel(Point point, DraggAblePanel pan) {
		pan.setLocation(point);
		pan.setSize(150,50);
		pan.setMinimumSize(new Dimension(100,50));
		pan.setMaximumSize(new Dimension(100,50));
		pan.setPreferredSize(new Dimension(100,50));
		dragablepanels.add(pan);
		SubDragPanel thispanel=this;
		pan.giveMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, thispanel);
				thispanel.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, thispanel);
				thispanel.dispatchEvent(convertMouseEvent);
				
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
					Point p=e.getLocationOnScreen();
					parent.startGlassPaneArrowDraw(pan, e);
					
					//System.out.println("arrow drawing started");
					
					
				}
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, thispanel);
				thispanel.dispatchEvent(convertMouseEvent);
				
			}
			
		});
		pan.placed();
		this.add(pan);
		
		parent.deactivateGlassPane();
	}

	public void recolorPanels() {
		for(DraggAblePanel panel:dragablepanels) {
			panel.setBackground(Color.green);
		}
		
	}

	public void deleteSelected(SelectAble selected) {
		switch(selected.getKind()) {
		case ARROW:
			ViewArrow arrow=(ViewArrow)selected;
			arrow.delete();
			arrows.remove(arrow);
			break;
		case EVENT:
			EventField evpanel=(EventField) selected;
			evpanel.delete();
			for(ViewArrow viewarrow:arrows) {
				
			}
			
			
			this.remove(evpanel);
			break;
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

	public void removeArrow(Arrow arrow) {
		
		arrows.remove(arrow);
	}


	

}
