package StoryEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import frame.GlassContainer;
import frame.GlassLabel;
import frame.SubContainer;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class DragPanel extends SubContainer{

	//a test of a panel where jcomponents can be freely dragged and dropped 
	//to new locations
	private Dimension defaultSize=new Dimension(1600,1000);
	protected GlassContainer glasspane;
	protected JLayeredPane lpane = new JLayeredPane();
	private DraggAblePanel moving;
	protected SubDragPanel defaultpanel;
	private boolean dragging;
	protected boolean arrowdrawing;
	private ViewArrow arrowdrawn;
	private boolean glasspaneactivated=false;
	
	//you can drag elements in dragging zone 2 they will be diplayed in layer abovee when draggging and dropped back
	//on layer 2;
	
	public DragPanel(Dimension size) {
		super(size);
		defaultSize=size;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(defaultSize);
		this.setSize(defaultSize);
		this.setBackground(new Color(0,0,0,0));
		
		defaultpanel=new SubDragPanel(this);
		defaultpanel.setLayout(null);
		defaultpanel.setSize(defaultSize);
		defaultpanel.setPreferredSize(defaultSize);
		//defaultpanel.setBackground(Color.orange);
		lpane.add(defaultpanel,2,2);
		//this.addNewPanel();
		//this.addNewPanel();
		this.add(lpane);
		lpane.setSize(defaultSize);
		lpane.setPreferredSize(defaultSize);
		initialise();
	}

	private void initialise() {
		// TODO Auto-generated method stub
		//this.addNewPanel();
	}

	public void activateGlassPane() {
		if(!glasspaneactivated) {
		
		
		
		
			//glasspane.removeAll();
			//glasspane.revalidate();
			//System.out.println("glassPane activated");
			
		
			//refresh();
			System.out.println("glasspane activated");
		    glasspane=new GlassContainer(defaultSize)/* {
		    	 @Override
		            protected void paintComponent(Graphics g) {
		    		 /*
		                if (g instanceof Graphics2D) {
		                    final int R = 240;
		                    final int G = 240;
		                    final int B = 240;
	
		                    Paint p =
		                        new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
		                            0.0f, getHeight(), new Color(R, G, B, 0), true);
		                    Graphics2D g2d = (Graphics2D)g;
		                    g2d.setPaint(p);
		                    g2d.fillRect(0, 0, getWidth(), getHeight());
		                }
		            }
		            
		    }*/;
		    glasspane.setSize(this.getSize());
		   // glasspane.setLocation(10, 10);
		   // glasspane.setBackground(Color.PINK);
		    lpane.setBackground(Color.BLACK);
		    glasspane.setPreferredSize(this.getSize());
		    glasspane.setOpaque(false);
		    //this.setGlassPane(glasspane);
		    //glasspane.setAlwaysOnTop(true);
		    glasspane.setLayout(null);
		    glasspane.setBackground(new Color(0, 0, 0, 0));
		    lpane.add(glasspane, 2,2);
		    lpane.moveToFront(glasspane);
			glasspane.addMouseMotionListener(new Listentester());
			//lpane.addMouseMotionListener(new Listentest());
	
			
			this.requestFocus();
			glasspaneactivated=true;
		}
			
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
		if(glasspane!=null) {
			glasspane.revalidate();
			glasspane.repaint();
		}
		
		defaultpanel.revalidate();
		defaultpanel.repaint();
		this.requestFocus();
	}

	public void deactivateGlassPane() {
		if(glasspaneactivated) {
			System.out.println("glasspane deactivated");
			defaultpanel.add(moving);
			// TODO Auto-generated method stub
			//glasspane.removeAll();
			glasspane.removeArrows();
			glasspane.revalidate();
			arrowdrawn=null;
			arrowdrawing=false;
			dragging=false;
			defaultpanel.recolorPanels();
			
			//lpane.moveToBack(glasspane);
			lpane.remove(glasspane);
			
			refresh();
			this.requestFocus();
			glasspaneactivated=false;
		}
	}
	protected void startGlassPaneArrowDraw(DraggAblePanel pan, MouseEvent e) {
		MouseEvent e2 = SwingUtilities.convertMouseEvent(e.getComponent(), e, this);
		
		int x=e2.getX();
		int y=e2.getY();
		arrowdrawing=true;
		this.activateGlassPane();
		//JPanel origin=moving;
		moving=pan;
		glasspane.setSize(this.getSize());
		glasspane.removeAll();
		glasspane.revalidate();
		glasspane.setBackground(new Color(0,0,0,0));
		arrowdrawn=new ViewArrow(moving,x,y,defaultpanel);
		glasspane.AddArrow(arrowdrawn);
		glasspane.add(pan);
		glasspane.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(dragging|arrowdrawing) {
					doDragListenerEvent(e);
				}
				System.out.println("this still triggers");
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
				Component c=defaultpanel.getComponentAt(e.getPoint());
				//System.out.println("component"+c);
				
				
				if(c!=defaultpanel&& c!=moving&&arrowdrawing&&!dragging) {
					c.setBackground(Color.orange);
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				System.out.println("mouse move still triggers");
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
				
			}
			
		});
		glasspane.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				// TODO Auto-generated method stub
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				/*
				if(dragging) {
					dragging=false;
					addPanelToDefault(arg0,moving);
					moving=null;
				}
				if(arrowdrawing) {
					defaultpanel.add(moving);
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
					defaultpanel.dispatchEvent(convertMouseEvent);
					Component c=(Component) defaultpanel.getComponentAt(arg0.getPoint());
					if(c!=defaultpanel&&c!=null)  {
						DraggAblePanel a= (DraggAblePanel) c;
						addArrowToDefault(arg0, a);
						
					}
				}
				deactivateGlassPane();
				*/
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(dragging) {
					dragging=false;
					addPanelToDefault(arg0,moving);
					moving=null;
				}
				if(arrowdrawing) {
					
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
					defaultpanel.dispatchEvent(convertMouseEvent);
					Component c=(Component) defaultpanel.getComponentAt(arg0.getPoint());
					if(c!=defaultpanel)  {
						DraggAblePanel a= (DraggAblePanel) c;
						addArrowToDefault( a);
						
					}
				}
				deactivateGlassPane();
				
				
			}

		
			
		});
		//glasspane.setVisible(false);
		//glasspane.setVisible(true);
		//glasspane.setImage(newimg);
		//glasspane.setX(x-this.getX()-20);
		//glasspane.setY(y-this.getY()-40);
		refresh();
		
		this.requestFocus();
	}

	public void setGlassPaneImage(DraggAblePanel item, int x, int y) {
		this.activateGlassPane();
		glasspane.setSize(this.getSize());
		glasspane.removeAll();
		glasspane.revalidate();
		
	
		
		//Image img = item.getImage();
		//Image newimg=item.getScaleImage(70);
		int xoff=x;
		int yoff=y;		
		//GlassLabel label=new GlassLabel(new ImageIcon(newimg));
		DraggAblePanel label=item;
		moving=label;
		/*
		if(item.hasShape()) {
			ShapeItem item2=(ShapeItem) item;
			Point poin=item2.getPointOff();
			xoff=xoff-poin.x*15;
			yoff=yoff-poin.y*15;
			
			//label.setLocation(xoff,yoff);
			
		}
		*/
		label.setLocation(xoff-20,yoff-45);
		/*
		switch(item.getAngle()){
			case 0:
			case 180:
				//glasspane.setSize((int)(70*item.getScaleWidth()),(int)(70*item.getScaleHeight()));
				label.setSize((int)(70*item.getScaleWidth()),(int)(70*item.getScaleHeight()));
			
				break;
			case 90:
			case 270:
				//glasspane.setSize((int)(70*item.getScaleHeight()),(int)(70*item.getScaleWidth()));
				label.setSize((int)(70*item.getScaleHeight()),(int)(70*item.getScaleWidth()));
				break;
		}
		*/
	
		glasspane.setImage(label.getImage());
		//glasspane.add(label);
		glasspane.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if(dragging|arrowdrawing) {
					doDragListenerEvent(e);
				}
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
				Component c=defaultpanel.getComponentAt(e.getPoint());
				//System.out.println(c);
				if(c!=defaultpanel)  {
					c.setBackground(Color.orange);
					
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
			
			}
			
		});
		glasspane.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
				defaultpanel.dispatchEvent(convertMouseEvent);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(dragging) {
					dragging=false;
					addPanelToDefault(arg0,moving);
					moving=null;
				}
				if(arrowdrawing) {
					
					defaultpanel.add(moving);
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, defaultpanel);
					defaultpanel.dispatchEvent(convertMouseEvent);
					Component c=(Component) defaultpanel.getComponentAt(arg0.getPoint());
					//System.out.println(c);
					if(c!=defaultpanel)  {
						DraggAblePanel a=(DraggAblePanel) c;
						addArrowToDefault(a);
						
					}
				}
				deactivateGlassPane();
				
				
			}

		
			
		});
		//glasspane.setVisible(false);
		//glasspane.setVisible(true);
		//glasspane.setImage(newimg);
		//glasspane.setX(x-this.getX()-20);
		//glasspane.setY(y-this.getY()-40);
		refresh();
		
		this.requestFocus();
		
	}
	
	public void addArrowToDefault(DraggAblePanel c) {
	
		arrowdrawn.setEnd(c);
		System.out.println(arrowdrawn.line.getP1());
		System.out.println(arrowdrawn.line.getP2());
		defaultpanel.AddArrow(arrowdrawn);
		System.out.println("added arrow to default");
		arrowdrawing=false;
		deactivateGlassPane();
	}

	
	public void addDraggAblePanel(DraggAblePanel pan,int x,int y) {
		pan.setLocation(new Point(x,y));
		pan.setSize(150,50);
		pan.setMinimumSize(new Dimension(100,50));
		pan.setMaximumSize(new Dimension(100,50));
		pan.setPreferredSize(new Dimension(100,50));
		
		
		pan.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(dragging|arrowdrawing) {
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan.getParent());
					pan.getParent().dispatchEvent(convertMouseEvent);
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(dragging|arrowdrawing) {
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(arg0.getComponent(), arg0, pan.getParent());
					pan.getParent().dispatchEvent(convertMouseEvent);
				}
			}
			
		});
		pan.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					dragging=true;
					Point p=e.getLocationOnScreen();
					setGlassPaneImage(pan,p.x,p.y);
			      }
				if(e.getButton() == MouseEvent.BUTTON3) {
					arrowdrawing=true;
					//System.out.println("arrow drawing started");
					Point p=e.getLocationOnScreen();
					startGlassPaneArrowDraw(pan,e);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pan.setBackground(Color.green);
				if(arrowdrawing) {
					pan.setBackground(Color.orange);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pan.setBackground(Color.yellow);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					dragging=true;
					Point p=e.getLocationOnScreen();
					setGlassPaneImage(pan,p.x,p.y);
			      }
				if(e.getButton() == MouseEvent.BUTTON3) {
					arrowdrawing=true;
					Point p=e.getLocationOnScreen();
					//System.out.println("arrow drawing started");
					startGlassPaneArrowDraw(pan,e);
				}
				MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, pan.getParent());
				pan.getParent().dispatchEvent(convertMouseEvent);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(dragging|arrowdrawing) {
					MouseEvent convertMouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, pan.getParent());
					pan.dispatchEvent(convertMouseEvent);
				}
				
			}
			
		});
			
		
		defaultpanel.addDraggablePanel(pan);
	}
	public void addNewPanel() {
		DraggAblePanel pan =new DraggAblePanel("test") {

			@Override
			public SelectKind getKind() {
				// TODO Auto-generated method stub
				return null;
			}

			
			
		};
		pan.setName("new");
		//pan.setBackground(Color.yellow);
		//pan.setSize(new Dimension(100,100));
		//pan.setPreferredSize(new Dimension(100,100));
		this.addDraggAblePanel(pan, 0, 0);
		
	}
	
	
	


	private void addPanelToDefault(MouseEvent arg0, JPanel moving) {
		glasspane.remove(moving);
		defaultpanel.add(moving);
		Point p=arg0.getPoint();
		Point newLocation=new Point(p.x-5,p.y-5);
		moving.setLocation(newLocation);
		//deactivateGlassPane();
		//System.out.println("added panel to default");
	}

	public void doDragListenerEvent(MouseEvent e) {
		//lpane.moveToBack(glasspane);
		//currentMenu.dispatchEvent(e);
		//lpane.moveToFront(glasspane);
		Point p=e.getPoint();
		Point newLocation=new Point(p.x+5,p.y+5);
		if(dragging) {
			moving.setLocation(newLocation);
			glasspane.setPoint(newLocation);
		}
		System.out.println("the arrowpoint is "+p.x+" "+p.y);
		if(arrowdrawing) {
			arrowdrawn.setEndPosition(newLocation);
			Component c=defaultpanel.getComponentAt(e.getPoint());
			if(c!=defaultpanel&& c!=moving&&arrowdrawing&&!dragging) {
				c.setBackground(Color.orange);
			}
			
		}
		this.refresh();
	}
	
	public static void main(String[] args) {
		DragPanel pan=new DragPanel(new Dimension(1600,1000));
		//pan.setVisible(true);
		JFrame frame=new JFrame();
		frame.setPreferredSize(new Dimension(1600,1000));
		frame.setSize(new Dimension(1600,1000));
		frame.add(pan);
		
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void released(MouseEvent e) {
	}

	public boolean isArrowDragging() {
		// TODO Auto-generated method stub
		return arrowdrawing;
	}

	public boolean isMouseInComponent(MouseEvent e) {
		Component c=defaultpanel.getComponentAt(e.getPoint());
		//System.out.println("component"+c);
		
		
		if(c!=defaultpanel&& c!=moving&&arrowdrawing&&!dragging) {
			return true;
		}
		return false;
	}

	public DraggAblePanel getHovered(MouseEvent arg0) {
		Component c=defaultpanel.getComponentAt(arg0.getPoint());
		//System.out.println("component"+c);
		
		
		if(c!=defaultpanel&& c!=moving&&arrowdrawing&&!dragging) {
			return (DraggAblePanel) c;
		}
		return null;
	}

	
}
