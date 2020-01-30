package monsterEditor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import controller.UserInputController;
import frame.MainFrame;
import model.IModel;
import model.Monster.Monster;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import view.events.EventBox;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.ItemTabField;
import view.viewItems.ItemBox.MonsterInfoItemBox;
import view.viewItems.ItemBox.SelectAble;

/**
 * 
 * @author User
 * a frame with sole purpose to make monsteractions
 * 
 *
 */
public class MonsterEditor extends MainFrame {

	private MonsterInfoItemBox info;

	private Monster monster;

	private EventBox events;
	private MonsterOptions optionPanel;
	private MonsterTurnTrigger currentmovement;
	private IModel mod;
	private MonsterEditorView view;
	private UserInputController oldcontroller;
	private ItemTabField extraitems;
	//private FrameController control=new FrameController();
	
	public MonsterEditor() {
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1250,850);
		this.setMinimumSize(new Dimension(1250,850));
		this.setPreferredSize(new Dimension(1250,850));
		this.setVisible(true);
		this.setName("MonsterEditor");
		this.setTitle("MonsterEditor");
		info=new MonsterInfoItemBox(400,800,300);
		info.setMinimumSize(new Dimension(300,800));
		events=new EventBox(400,1600);
		events.setMinimumSize(new Dimension(350,800));
		view=new MonsterEditorView(this);
		initialiseNewController();
	
		optionPanel=new MonsterOptions(300,250,this);
		optionPanel.setMinimumSize(new Dimension(300,250));
		info.setController((MonsterEditorController) userInput);
		extraitems=new MonsterItemTabField(300,350);
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,optionPanel,extraitems);
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane0,info);
		JPanel pan2=new JPanel();
		pan2.add(splitPane1);
		JScrollPane pane=new JScrollPane(events);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pan2,pane);
		
		JPanel panel=new JPanel();
		panel.setPreferredSize(this.getSize());
		panel.setSize(this.getSize());
		panel.add(splitPane2);
		lpane.add(panel,2,2);
		lpane.moveToFront(panel);
		setAlwaysOnTop(true);
		MonsterEditor edit=this;
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        
		            returnOldController();
		        
		        
		    }
		});
		optionPanel.defaultSelect();
		//.setView(new MonsterEditorView(this));
		//MonsterEditorController.createMonsterEditorController();
	}
	
	protected void returnOldController() {
	
		UserInputController.setController(oldcontroller);
	}

	private void initialiseNewController() {
		userInput=MonsterEditorController.createMonsterEditorController(null, view,this);
		oldcontroller=UserInputController.getController();
		UserInputController.setController(userInput);
	}

	//basically open same as questeditor but without the gridpanel.
	public InfoItemBox getInfo() {
		return info;
	}

	public void setInfo(MonsterInfoItemBox info) {
		this.info = info;
	}

	public EventBox getEvents() {
		return events;
	}

	public void setEvents(EventBox events) {
		this.events = events;
	}
	@Override
	public void doDragListenerEvent(MouseEvent e, SelectAble selectAble) {
		//lpane.moveToBack(glasspane);
		//currentMenu.dispatchEvent(e);
		//lpane.moveToFront(glasspane);
		Point p=e.getLocationOnScreen();
		Point newLocation=new Point(p.x-this.getX(),p.y-this.getY());
		events.sendEvent(e, newLocation, selectAble);
		info.sendEvent(e, newLocation, selectAble);
		optionPanel.sendEvent(e,newLocation,selectAble);
	}

	public void saveMovement() {
		
		
	}

	public MonsterTurnTrigger getMonsterMovement() {
		// TODO Auto-generated method stub
		return currentmovement;
	}

	public MonsterTurnTrigger getCurrentmovement() {
		return currentmovement;
	}

	public void setCurrentmovement(MonsterTurnTrigger currentmovement) {
		this.currentmovement = currentmovement;
	}

	public Monster getMonster() {
		// TODO Auto-generated method stub
		return monster;
	}

	public void setMonster(Monster mon) {
		// TODO Auto-generated method stub
		monster=mon;
	}

	public void loadMovement(MonsterTurnTrigger g) {
		// TODO Auto-generated method stub
		currentmovement=g;
		System.out.println("momvement initialised from editor weird?");
		events.initialiseMovement(g);
	}

	
	
}
