package frame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.UserInputController;
import misc.listeners.IResizeListeners;
import model.Monster.Monster;
import model.event.StartGameListener;
import monsterEditor.MonsterEditor;
import view.ViewManager;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.Menus;
import view.menu.QuestCreator;
import view.menu.QuestGame;
import view.viewItems.ShapeItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;

public class MainFrame extends JFrame{
	protected ViewManager viewMan;
	private boolean closerequested=false;

	private ArrayList<SubContainer> containers; 
	//private JLayeredPane mainPanel;
	private ArrayList<IResizeListeners> resizeListeners=new ArrayList<IResizeListeners>();
	private boolean changed=false;
	private SubContainer currentMenu;
	protected UserInputController userInput;
	private Dimension defaultSize=new Dimension(1600,1000); 
	private SubContainer glasspane;
	protected JLayeredPane lpane = new JLayeredPane();
	private QuestGame currentGame;
	
	private ArrayList<StartGameListener> gamestartlisteners=new ArrayList<StartGameListener>();
	
	public MainFrame(){
	
		
		this.addComponentListener(new ComponentAdapter() 
		{  
		        public void componentResized(ComponentEvent evt) {
		            resizeFrame(evt.getComponent().getWidth(),evt.getComponent().getHeight());
		            
		        }

				
		});
		
		
	
		
		
		
		
		
		this.setSize(defaultSize);
		this.setMinimumSize(new Dimension(400,500));
		this.setName("Descent Quest Builder");
		this.setTitle("Descent Quest Builder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.add(mainPanel);
	
		viewMan=new ViewManager(this);
		
		this.addMouseListener(userInput);
		this.addKeyListener(userInput);
	  //  this.requestFocus();
		  glasspane=new GlassContainer(defaultSize) ;
		    //this.setGlassPane(glasspane);
		    lpane.setSize(defaultSize);
		
	    this.requestFocus();
	    
	    this.setFocusable(true);
	  
	    this.add(lpane);
	    //activateGlassPane();
	    refresh();
	    JPanel panel=new JPanel();
	    panel.setSize(400,400);
	    panel.setBackground(Color.cyan);
	    lpane.add(panel,2,2);
	    lpane.moveToFront(glasspane);
	}
	
	protected void resizeFrame(int width, int height) {
		for(IResizeListeners listen:resizeListeners){
			listen.resizePart(width,height);
		}
		
	}


	public void performLoop() {
		if(changed){
			repaint();
			changed=false;
		}
		
	}
	
	public void addResizeListener(IResizeListeners newListener){
		resizeListeners.add(newListener);
	}

	public void paint(Graphics g){
		super.paint(g);
		
	
	}

	//@SuppressWarnings("deprecation")
	public void initialiseMenu(Menu mainMenu) {
		
		resizeListeners.clear();
		System.out.println("removed all gone back to menu");
		lpane.removeAll();
		lpane.revalidate();
	
		/*
		if(currentMenu!=null){
			this.remove(lpane);
			lpane.remove(currentMenu);
		    //activateGlassPane();
		}
		*/
		//lpane=createNewPanel();
		//
		//lpane.setLayout(new BoxLayout(lpane,BoxLayout.Y_AXIS));
		lpane.setLayout(null);
		addResizeListener( mainMenu);
		//lpane.add(mainMenu);
		mainMenu.setComponentSizes(getWidth(),getHeight());
		lpane.add(mainMenu, 2,2);
		//mainMenu.setComponentSizes(getWidth(),getHeight());
		
		currentMenu=mainMenu;
		lpane.moveToFront(mainMenu);
		//this.resize(400, 300);
		//changed=true;
		//activateGlassPane();
		//this.add(lpane);
		revalidate();
		repaint();
	     this.requestFocus();
	}

	private JLayeredPane createNewPanel() {
		JLayeredPane newPanel=new JLayeredPane();
		
		//newPanel.setLayout(new BoxLayout(lpane,BoxLayout.Y_AXIS));// TODO Auto-generated method stub
		newPanel.setLayout(null);
		newPanel.setSize(this.getWidth(),this.getHeight());
		newPanel.setBackground(Color.BLACK);
		return newPanel;
	}

	public void exit() {
		System.out.println("exitted");
		closerequested=true;
	}

	public boolean isCloseRequested() {
		// TODO Auto-generated method stub
		return closerequested;
	}

	public void startQuestEditor(QuestCreator quester) {
		resizeListeners.clear();
		System.out.println("removed all");
		lpane.removeAll();
		lpane.revalidate();
		/*
		if(currentMenu!=null){
			this.remove(lpane);
			lpane.remove(currentMenu);
		}
		*/
		
		//lpane.setLayout(new BoxLayout(lpane,BoxLayout.Y_AXIS));
		lpane.setLayout(null);
		addResizeListener( quester);
		//lpane.add(mainMenu);
		//mainMenu.setComponentSizes(getWidth(),getHeight());
		lpane.add(quester, 2,2);
		//mainMenu.setComponentSizes(getWidth(),getHeight());
		
		//currentMenu=mainMenu;
	
		//this.resize(400, 300);
		//changed=true;
		
	//	this.add(lpane);
		revalidate();
		repaint();
	
	//	this.addMouseListener(getUserInputController());
	//	lpane.addMouseListener(getUserInputController());
	//	quester.addMouseListener(userInput);
		currentMenu=quester;
		lpane.moveToFront(quester);
		this.requestFocus();
	}

	public UserInputController getUserInputController() {
		// TODO Auto-generated method stub
		return userInput;
	}

	public void refresh() {
		this.revalidate();
		this.repaint();
		
	}
	

	public void activateGlassPane() {
		
		
		//glasspane.removeAll();
		//glasspane.revalidate();
		System.out.println("glassPane activated");
	
		//refresh();

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
		glasspane.addMouseMotionListener(new Listentest());
		//lpane.addMouseMotionListener(new Listentest());

		
		this.requestFocus();
		
	}
	

	public void deactivateGlassPane() {
		// TODO Auto-generated method stub
		//glasspane.removeAll();
		
		glasspane.revalidate();
		//lpane.moveToBack(glasspane);
		lpane.remove(glasspane);
		
		refresh();
	}

	public void setGlassPaneImage(ImageItem item, int x, int y) {
		glasspane.setSize(this.getSize());
		glasspane.removeAll();
		glasspane.revalidate();
		
	
		
		Image img = item.getImage();
		Image newimg=item.getScaleImage(70);
		int xoff=x;
		int yoff=y;		
		GlassLabel label=new GlassLabel(new ImageIcon(newimg));
		if(item.hasShape()) {
			ShapeItem item2=(ShapeItem) item;
			Point poin=item2.getPointOff();
			xoff=xoff-poin.x*15;
			yoff=yoff-poin.y*15;
			
			//label.setLocation(xoff,yoff);
			
		}
		label.setLocation(xoff-20,yoff-45);
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
		
		glasspane.add(label);
		glasspane.addMouseMotionListener(new Listentest());
		//glasspane.setVisible(false);
		//glasspane.setVisible(true);
		//glasspane.setImage(newimg);
		//glasspane.setX(x-this.getX()-20);
		//glasspane.setY(y-this.getY()-40);
		refresh();
		
		this.requestFocus();
		
	}

	public void doDragListenerEvent(MouseEvent e, SelectAble selectAble) {
		//lpane.moveToBack(glasspane);
		//currentMenu.dispatchEvent(e);
		//lpane.moveToFront(glasspane);
		Point p=e.getLocationOnScreen();
		Point newLocation=new Point(p.x-this.getX(),p.y-this.getY());
		currentMenu.sendEvent(e,newLocation,selectAble);
	}
	
	public void startTestGame(QuestGame gamemenu) {
		//resizeListeners.clear();
		//System.out.println("removed all");
		//lpane.removeAll();
		lpane.revalidate();
		/*
		if(currentMenu!=null){
			this.remove(lpane);
			lpane.remove(currentMenu);
		}
		*/
		
		//lpane.setLayout(new BoxLayout(lpane,BoxLayout.Y_AXIS));
		lpane.setLayout(null);
		addResizeListener(gamemenu);
		//lpane.add(mainMenu);
		//mainMenu.setComponentSizes(getWidth(),getHeight());
		lpane.add(gamemenu, 2,2);
		lpane.moveToFront(gamemenu);
		//mainMenu.setComponentSizes(getWidth(),getHeight());
		
		//currentMenu=mainMenu;
	
		//this.resize(400, 300);
		//changed=true;
		
	//	this.add(lpane);
		viewMan.startGame(gamemenu);
		notifyGameStartListeners();
		revalidate();
		repaint();
		
	//	this.addMouseListener(getUserInputController());
	//	lpane.addMouseListener(getUserInputController());
	//	quester.addMouseListener(userInput);
		currentGame=gamemenu;
		//lpane.moveToFront(quester);
		
		this.requestFocus();
	}

	public void endTestGame() {
		// TODO Auto-generated method stub
		lpane.remove(currentGame);
		lpane.moveToBack(currentGame);
		revalidate();
		repaint();
	}

	public void addGameStartListener(StartGameListener listen) {
		gamestartlisteners.add(listen);
	}
	
	public void notifyGameStartListeners() {
		
		for(StartGameListener listen:gamestartlisteners) {
			 Thread thread = new Thread(){
				    public void run(){
				    	listen.gameStarted();
				 }
			 };

			 thread.start();
			
		}
	}

	public void openMonsterEditor() {
		System.out.println("monstereditor opened");
		MonsterEditor edit=new MonsterEditor();
		edit.setVisible(true);
		
	}

	public void openMonsterEditor(Monster mon) {
	
		
	}



}
