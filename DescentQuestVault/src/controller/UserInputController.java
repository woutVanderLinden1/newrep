package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import HeroPicker.HeroPicker;
import ItemEditor.ActionTaker;
import Shop.ItemShop.ItemShop;
import Shop.SkillShop.SkillShop;
import StoryEditor.AddEventToStoryElementPanelCommand;
import StoryEditor.Arrow;
import StoryEditor.CampaignSaveFile;
import StoryEditor.DragPanel;
import StoryEditor.DraggAblePanel;
import StoryEditor.FreeTimeEndListener;
import StoryEditor.MiniEventListener;
import StoryEditor.ProgressStatus;
import StoryEditor.StoryProgressController;
import StoryEditor.SubDragPanel;
import StoryEditor.ViewArrow;
import controller.analyzer.Analyzer;
import controller.analyzer.DragAnalyzer;
import controller.analyzer.MonsterPlaceAnalyzer;
import controller.analyzer.states.IAnalyzerState;
import controller.commands.AddDoorToViewSquareCommand;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.BasicCommand;
import controller.commands.CancelTileMoveCommand;
import controller.commands.ChangeTileColorCommand;
import controller.commands.ICommand;

import controller.commands.ShowOccupiedCommand;
import controller.commands.addTileToViewSquareCommand;
import controller.commands.select.AddSelectedToTriggerFieldCommand;
import controller.stack.MainStackManager;

import controller.stack.SideStackManager;
import controller.stack.StackManager;
import controller.stack.StackElements.ChangedColorStackElement;
import controller.stack.StackElements.IStackElement;
import controller.turns.GameController;
import frame.MainFrame;
import frame.SubContainer;
import misc.BaseFile;
import misc.CampaignFile;
import misc.DefaultCampaingFile;
import misc.SampleFile;
import misc.Tools;
import misc.listeners.ButtonPressedListener;
import misc.save.WorldSaveFile;
import model.IModel;
import model.ItemController;
import model.Monster.Monster;
import model.event.MonsterTurnTrigger;
import model.event.StartUpTrigger;
import model.event.Trigger;
import model.event.Univent;
import model.event.advancedevents.PerilEvent;
import model.event.extraevents.StopAble;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextStop;
import model.event.trigger.EndPhaseTrigger;
import model.values.CustomInteger;
import monsterEditor.MonsterEditorView;
import view.IDrawWindow;
import view.IView;
import view.LoadNewCampaignGameCommand;
import view.Items.Map.MapFrame;
import view.Items.Map.MapLocation;
import view.Items.Map.SubQuestFile;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.events.BaseField;
import view.events.EventField;
import view.events.MultiTriggerField;
import view.events.TriggerContainer;
import view.events.TriggerField;
import view.game.GameMonster;
import view.game.GameSquare;
import view.hero.GameHero;
import view.menu.CommandButton;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.QuestCreator;
import view.menu.QuestGame;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;




public class UserInputController implements MouseListener,KeyListener, ButtonPressedListener, IController {

	private MapFrame mapFrame;
	public boolean miniEventMode;
	private static UserInputController control;
	private ContinueElement toContinue;
	private GameController gamecontrol;
	private ArrayList<NextTextListeners> nexttextlistener=new ArrayList<NextTextListeners>();
	private ArrayList<FreeTimeEndListener> freetimeendlisteners=new ArrayList<FreeTimeEndListener>();
	private ArrayList<MiniEventListener> miniEventListeners=new ArrayList<MiniEventListener>();
	private boolean freeTimeMode;
	public static UserInputController getUserInputController() {
		return control;
	}
	private Stack<ChangedColorStackElement> colorStack =new Stack<ChangedColorStackElement>();
	private Stack<ChangedColorStackElement> monsterColorStack =new Stack<ChangedColorStackElement>();
	
	private DragStack draggingStack;
	
	private DragMouseMotionListener listen=new DragMouseMotionListener();
	private MainFrame testBox;
	
	private Analyzer analyze;
	protected StackManager stack;
	private IView view;
	private IModel mod;
	private Map<IDrawWindow,StackManager> stackMap;
	protected MainFrame theFrame;
	private boolean dragging;
	private boolean tileMoveing;
	private OldTilePlace oldTile;
	private CustomInteger hope;
	private boolean textstarted;
	private QuestText currentText;
	private ArrayList<EndGameListener> engamelisteners=new ArrayList<EndGameListener>();
	private ArrayList<EndHeroPickListener> endHeroPickListener=new ArrayList<EndHeroPickListener>();
	
	public QuestText getCurrentText() {
		return currentText;
	}

	public void setCurrentText(QuestText currentText) {
		this.currentText = currentText;
	}
	private BaseEventController baseEventControl;


	private boolean norefresh;


	private OldEventPlace oldevent;
	
	private TriggerField temporaryLocation;
	private CityMenu men;


	
	public UserInputController(IView newview, IModel newmod, MainFrame theFrame){
		this.theFrame=theFrame;
		view=newview;
		mod=newmod;
		stackMap=new HashMap<IDrawWindow,StackManager>();
		analyze = new Analyzer();
		stack = new MainStackManager(view, mod);
		draggingStack=new DragStack();
		refreshState();
		Toolkit.getDefaultToolkit().addAWTEventListener(listen, AWTEvent.MOUSE_EVENT_MASK);
		Toolkit.getDefaultToolkit().addAWTEventListener(listen, AWTEvent.MOUSE_MOTION_EVENT_MASK);

	}
	
	public void initialiseBaseEventController(BaseEventController baseEventController) {
		// TODO Auto-generated method stub
		this.baseEventControl=baseEventController;
		view.initialiseBaseEvents(baseEventController);
		//eventPanel.setBaseTrigger(g.getBaseTrigger());
	}

	public IDrawWindow getActiveWindow(){
		return view.getActiveWindow();
	}
	
	
	
	/**
	 * handles a mouseevent by converting it to a mouseaction then analyzing it to a
	 * command using the analyzer and finally sending the command to the stack for
	 * it to execute afer every event the state of the analyzer is refreshed
	 * depending one the top of the stack
	 * 
	 * @param id
	 *            the id of the mouseaction
	 * @param x
	 *            the xlocation where is clicked
	 * @param y
	 *            the y location where is clicked
	 * @param clickCount
	 *            the number of clicks
	 */

	public void handleMouseEvent(int id, int x, int y, int clickCount) {
		MouseAction act = MouseAction.toMouseAction(id, clickCount);
		IDrawWindow wind = getActiveWindow();
		ICommand toExecute = analyze.makeCommand(x, y, act, wind, view);

		if(toExecute!=null){
			toExecute.setController(this);
			toExecute=stack.prepareCommand(toExecute);
			if(wind!=null){
				toExecute=prepareCommand(wind,toExecute);
			}
			
			toExecute.perform();
		}

		refreshState();

	}

	// the part used for the controller

	


	


	/**
	 * prepares a commmand whit the stack of the Subwindow.
	 * 
	 * @param wind
	 * the window where the command will be prepared from
	 * @param toExecute
	 * the command to adapt to the stack
	 * @return
	 * the adapted command
	 */
	public ICommand prepareCommand(IDrawWindow wind, ICommand toExecute) {
		//prepare the command (should be taken out of the window)
		StackManager stack2=stackMap.get(wind);
		toExecute = stack2.prepareCommand(toExecute);
		return toExecute;
	
	}

	/**
	 *
	 * handles a keyevent by converting it to a keyaction then analyzing it to a
	 * command using the analyzer and finally sending the command to the stack for
	 * it to execute afer every event the state of the analyzer is refreshed
	 * depending one the top of the stack
	 * 
	 * @param id
	 *            the id of the keyevent
	 * @param keyText
	 *            the text that is typed
	 * @param keyCode
	 *            the code of the key pressed
	 * @param modifier
	 *            the modifier denots wether shift/control is pressed
	 * @param keyChar
	 *            the pressed char

	*/
	public void handleKeyEvent(int id, String keyText, int keyCode, int modifier, char keyChar) {
		KeyAction act = KeyAction.toKeyAction(id, keyText, keyCode, modifier, keyChar);
		IDrawWindow wind=getActiveWindow();

		ICommand toExecute = analyze.makeCommand(act,wind, view);
		performCommand(toExecute);
		
	}
	
	

	/**
	 * updates the state so it corresponds with the top state of the stackmanager
	 */
	public void refreshState() {
	//	System.out.println("activewindow before refresh "+getActiveWindow());
		/*
		if(this.getActiveWindow()!=null){
			analyze.setState(toStackstate(this.getActiveWindow()));
		}
		else{
		*/
			if (stack.isTopChanged()) {
				analyze.setState(stack.getTopState());
				// System.out.println("refresh");
			}
			/*
		}

		System.out.println("after refresh"+ analyze.getState().toString());
*/
	}
	
	public IStackElement getCurrentElement(){
		if(this.getActiveWindow()!=null){
			return getWindowStackElement(this.getActiveWindow());
		}
		else{
			return stack.getTop(); 
		}
	}


	private IStackElement getWindowStackElement(IDrawWindow activeWindow) {
		// TODO Auto-generated method stub
		return stackMap.get(activeWindow).getTop();
	}

	/**
	 * returns the analyzerstate correspondent to the stack of this window
	 * 
	 * @param activeWindow
	 * the window to get the state from
	 * @return
	 * the analyzerstate correspondent to this state
	 */
	private IAnalyzerState toStackstate(IDrawWindow activeWindow) {
		StackManager stack2=stackMap.get(activeWindow);
		return stack2.getTopState();
	}

	public void adaptTop() {
		((MainStackManager) stack).adaptTop();
		
	}

	/**
	 * gives an appropriate stack to the window
	 * @param window
	 */
	public void giveStackToSubwindow(IDrawWindow window) {
	
		
	}


	

	//those two are no longer needed remove them when not lazy and annoyed
	@Override
	public IView getTheView() {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public StackManager getStackManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void performCommand(ICommand toExecute) {
		norefresh=false;
		IDrawWindow wind = getActiveWindow();
		if(toExecute!=null){
			toExecute.setController(this);
			toExecute=stack.prepareCommand(toExecute);
			if(wind!=null){
				this.prepareCommand(wind, toExecute);
			}
		
				toExecute.perform();
				
			}
		if(!norefresh) {
			theFrame.refresh();
			
		}
		theFrame.refresh();
		
		refreshState();
	}

	@Override
	public IModel getTheModel() {
		
		return mod;
	}

	public static UserInputController getController() {
		/*
		if(control==null){
			control=new UserInputController();
		}
		*/
		return control;
	}
	
	public static UserInputController createUserInputController(IModel model,IView view,MainFrame frame){
		control=new UserInputController(view,model,frame);
		return control;
	}

	@Override
	public void buttonPressed(CommandButton commandButton) {
		this.performCommand(commandButton.getCommand());
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("hello: "+arg0.getKeyChar());
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("hello: "+arg0.getKeyChar());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("hello: "+arg0.getKeyChar());
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
		handleMouseEvent(arg0.getID(), arg0.getX(), arg0.getY(), arg0.getClickCount());

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
		
		handleMouseEvent(arg0.getID(), arg0.getX(), arg0.getY(), arg0.getClickCount());
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		handleMouseEvent(arg0.getID(), arg0.getX(), arg0.getY(), arg0.getClickCount());
		
		
	}

	
	
	public ICommand generateGridCommand(int x, int y,MouseEvent event,int mouseClicked, ViewSquare square) throws Exception {
		return analyze.makeGridCommand(x,y, event,mouseClicked,square,view,dragging);
	}


	public void resetColors() {
		if(!colorStack.isEmpty()) {
			colorStack.peek().reset();
			colorStack.pop();
		}
		if(!monsterColorStack.isEmpty()) {
			ChangedColorStackElement ment=monsterColorStack.peek();
			for(ViewSquare square:ment.getSquares()) {
				view.changeTileColor(square, new Color(255,255,255,95));
			}
			
		}
		
	}

	public void addColorStackElement(ChangedColorStackElement changedColorStackElement) {
		colorStack.add(changedColorStackElement);
		
	}

	public ICommand generateSelectCommand(int mousePressed,int x,int y,MouseEvent ev) {
		return analyze.generateSelectCommand(mousePressed,view,x,y,ev);
		
	}

	public void startDrag(SelectAble toselect,int x,int y) {
		
		dragging=true;
		System.out.println("drag started");
	//	System.out.println("started dragging");
		draggingStack.setTomove(toselect);
		//analyze=new DragAnalyzer();
		activateAWTEventListeners();
		//theFrame.addMouseMotionListener(new DragMouseMotionListener());
		//theFrame.addMouseListener(new DragMouseMotionListener());
		theFrame.activateGlassPane();
		//norefresh=true;
		dragMouseEvent(x,y);
	}

	public void dragMouseEvent(int x, int y) {
		
		SelectAble selected=draggingStack.getTomove();
		
		ImageItem item=selected.getImageItem();
		
		//Image img=item.getImage();
		//Image newimg = img.getScaledInstance( (int)(70*(item.getScaleWidth())),(int)( 70*(item.getScaleHeight())),  java.awt.Image.SCALE_SMOOTH ) ;
		
		//Image ima=
		
		theFrame.setGlassPaneImage(item,x,y);
		
		
	}

	public void releaseDragEvent(int x, int y) {
		System.out.println("drag ended");
		theFrame.deactivateGlassPane();
		deactivateAWTEventListeners();
	
		
	}

	public void activateAWTEventListeners() {
		listen.setOn();
		
	}
	public void sendDragListenerEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		theFrame.doDragListenerEvent(e,view.getSelected());
	}


	public void deactivateAWTEventListeners() {
		//Toolkit.getDefaultToolkit().removeAWTEventListener(listen);
		//Toolkit.getDefaultToolkit().removeAWTEventListener(listen);
		listen.setOff();
		/*
		for(AWTEventListener listen:Toolkit.getDefaultToolkit().getAWTEventListeners()) {
			Toolkit.getDefaultToolkit().removeAWTEventListener(listen);
		}
		*/
		
	}

	public void stopDragging() {
		System.out.println("dragging ended");
		dragging=false;
	
		
	}

	public boolean tileMoving() {
		
		return tileMoveing;
	}

	public void keepTileMoveChange(ViewSquare square, SelectAble toselect) {
		
		tileMoveing=true;
		oldTile=new OldTilePlace(square,toselect);
		
	}
	public void keepEventMoveChange(TriggerField baseTrigger, BaseField todrag) {
		tileMoveing=true;
		oldevent=new OldEventPlace(baseTrigger,todrag);
	}


	public void cancelTileMove() {
		
		switch(oldTile.getToselect().getKind()) {
		case EVENT:
			break;
		case TILEITEM:
			addTileToViewSquareCommand comm=new addTileToViewSquareCommand((TileItem) oldTile.getToselect(), oldTile.getSquare());
			this.performCommand(comm);
			endTileMove();
			break;
		case VIEWTILE:
			addTileToViewSquareCommand comm2=new addTileToViewSquareCommand(((ViewTile) oldTile.getToselect()), oldTile.getSquare());
			this.performCommand(comm2);
			endTileMove();
			break;
		case VIEWDOOR:
			System.out.println("oldsquares "+oldTile.getSquare());
			AddDoorToViewSquareCommand doorcom=new AddDoorToViewSquareCommand(((ViewDoor) oldTile.getToselect()), oldTile.getSquare());
			this.performCommand(doorcom);
			endTileMove();
		default:
			break;
		
		}
	

		
		
		
	}

	private void cancelEventMove() {
		
		BaseField field=oldevent.getField();
		field.activateTextLabelMouselistener();
		if(field.isPlaced()) {
			
			ICommand comm2=new AddSelectedToTriggerFieldCommand(/*oldevent.getField(),*/oldevent.getBaseTrigger());
			performCommand(comm2);
	
			//this.endEventMove(field);
			
			/*
			switch(oldevent.getField().getKind()) {
		
			case EVENT:
				ICommand comm=new AddEventToTriggerFieldCommand(oldevent.getField(),oldevent.getBaseTrigger());
				performCommand(comm);
				break;
			
			case TRIGGER:
				ICommand comm2=new AddEventToTriggerFieldCommand(oldevent.getField(),oldevent.getBaseTrigger());
				performCommand(comm2);
				break;
		
		
			default:
				break;
			
			}
			*/
		}
		
	}



	public void endTileMove() {
		System.out.println("ended tile move");
		dragging=false;
		tileMoveing=false;
		oldTile=null;
		oldevent=null;
	}

	/*
	public void addStartGameListener(StartUpTrigger basetrigger) {
		
		//theFrame.addGameStartListener(basetrigger);
		
	}
	*/
	public void beginStoryText() {
		nexttextlistener.clear();
		gamecontrol=null;
		//theFrame.removeAll();
		//first remove everything
		//now start a text interface
		if(testBox==null) {
			testBox=new MainFrame();
		}
		
		  testBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textstarted=true;
		QuestText text=new QuestText(theFrame.getWidth(),theFrame.getHeight(),theFrame.getUserInputController());
		this.setCurrentText(text);
		testBox.startQuestText(text);
		testBox.setVisible(true);
		testBox.revalidate();
		testBox.refresh();
	}
	public void StartHeroPicker(BaseFile generateBaseFile) {
		HeroPicker picker=new HeroPicker(generateBaseFile);
		picker.setVisible(true);
		
	}


	public void endStoryText() {
		textstarted=false;
		
		// TODO Auto-generated method stub
		
	}
	public void startGame(WorldSaveFile g) {
		nexttextlistener.clear();
		gamecontrol=new GameController();
		if(testBox==null) {
			testBox=new MainFrame();
		}
		initialiseBaseEventController(g.getControl());
		//addGameStartListener(g.getControl().getStartuptrigger());
		//addEndPhaseListener(g.getControl().getEndtrigger());
		//ItemController controller=ItemController.getItemController();
		ItemController control=ItemController.getItemController();
		control.addStartingValues(new DefaultCampaingFile());
		QuestGame game=new QuestGame(theFrame.getWidth(),theFrame.getHeight(),theFrame.getUserInputController());
		view.setGame(game);
		game.addGameStartListener(baseEventControl.getStartuptrigger());
		game.addEndPhaseListener(baseEventControl.getEndtrigger());
		game.addMonsterPlaceListener(gamecontrol);
		game.addHeroPlaceListener(gamecontrol);
		game.setGameController(gamecontrol);
		game.initialiseGameMap(new SampleFile());
		testBox.startQuestGame(game);	
		testBox.setVisible(true);
		
	}
	public void startGame(WorldSaveFile g,CampaignFile file) {
		UserInputController control=UserInputController.getController();
		int added=0;
		ArrayList<CampaignSaveFile> possibleevents=new ArrayList<CampaignSaveFile>();
		String directoryPath= System.getProperty("user.dir")+"//TravelEvent";
		File dir = new File(directoryPath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		     Optional<String> answer=this.getExtensionByStringHandling(child.getName());
		      System.out.println();
		      if(answer.get().equals("ser")){
		    	  added++;
		    	  CampaignSaveFile i=null;
					 FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(child);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						i = (CampaignSaveFile) in.readObject();
					    in.close();
					    fileIn.close();
					    System.out.println("the read file is " +i);
					    possibleevents.add(i);
					    	
					    
					
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		      }
		    }
		  }
		int p=Tools.getRandomInt(possibleevents.size());
		CampaignSaveFile tohappen=possibleevents.get(p);
		
		
		
		control.performTravelEvent(tohappen, new EndTravelEventListener() {

			@Override
			public void travelEnded() {
				control.startFullGame(g,file);
			}
			
		});
	
	}
		
		
		
		
	public void startFullGame(WorldSaveFile g,CampaignFile file) {
		nexttextlistener.clear();
		gamecontrol=new GameController();
		if(testBox==null) {
			testBox=new MainFrame();
		}
		initialiseBaseEventController(g.getControl());
		//addGameStartListener(g.getControl().getStartuptrigger());
		//addEndPhaseListener(g.getControl().getEndtrigger());
		//ItemController controller=ItemController.getItemController();
		ItemController control=ItemController.getItemController();
		control.addStartingValues(new DefaultCampaingFile());
		QuestGame game=new QuestGame(theFrame.getWidth(),theFrame.getHeight(),theFrame.getUserInputController());
		view.setGame(game);
		game.addGameStartListener(baseEventControl.getStartuptrigger());
		game.addEndPhaseListener(baseEventControl.getEndtrigger());
		game.addMonsterPlaceListener(gamecontrol);
		game.addHeroPlaceListener(gamecontrol);
		game.setGameController(gamecontrol);
		game.initialiseGameMap(file.getBaseFile());
		testBox.startQuestGame(game);	
		testBox.setVisible(true);
		
	}

	public void startGame() {
		nexttextlistener.clear();
		gamecontrol=new GameController();
		QuestGame game=new QuestGame(theFrame.getSize(),new SampleFile());
		view.setGame(game);
		game.addGameStartListener(baseEventControl.getStartuptrigger());
		game.addEndPhaseListener(baseEventControl.getEndtrigger());
		game.addMonsterPlaceListener(gamecontrol);
		game.addHeroPlaceListener(gamecontrol);
		game.setGameController(gamecontrol);
		game.initialiseGameMap(new SampleFile());
		theFrame.startTestGame(game);
		
		
		// TODO Auto-generated method stub
		
	}
	public void endGame() {
		
		// TODO Auto-generated method stub
			theFrame.endTestGame();
			if(testBox!=null) {
				testBox.endTestGame();
			}
			
			textstarted=false;
			triggerEndGameListeners();
		
		
	}
	



	private void triggerEndGameListeners() {
		if(this.miniEventMode) {
			System.out.println("minieventlisteners warned");
			StoryProgressController control2=StoryProgressController.getController();
			control2.StoryMiniEventEnded();
		}
		else {
			if(this.freeTimeMode) {
				for(FreeTimeEndListener listen:freetimeendlisteners) {
					listen.FreeTimeEnded();
				}
			}
			else {
				StoryProgressController control2=StoryProgressController.getController();
				control2.StoryEventEnded();
			}
		}
		
	}

	public ICommand generateTriggerFieldCommand(int x, int y, MouseEvent arg0, int mouseEntered,
			TriggerField thefield) {
		// TODO Auto-generated method stub
		return analyze.makeTriggerFieldCommand(x,y,view,arg0,mouseEntered,thefield,dragging);
	}
	public BasicCommand generateReleasedOnSubDragPanelCommand(int x, int y, MouseEvent arg0,
			int mouseReleased, SubDragPanel panel, DragPanel parent) {
		// TODO Auto-generated method stub
		
		return analyze.makeEventToStoryElementPanelCommand(x,y,arg0,mouseReleased,panel,view.getSelected(),dragging,parent);
	}

	public void cancelMoves() {
		System.out.println("canceled "+tileMoving());
		if(tileMoving()) {
		
			ICommand command=new CancelTileMoveCommand();
			performCommand(command);
		}
		
	}

	public void cancelMove() {
		if(oldTile!=null) {
			cancelTileMove();
		}
		if(oldevent!=null) {
			cancelEventMove();
		}
	}

	
	public ICommand generateSelectFieldCommand(MouseEvent arg0, BaseField pan) {
		// TODO Auto-generated method stub
		return analyze.generateSelectFieldCommand(arg0,pan);
	}

	public ICommand generateSelectDragPanelCommand(MouseEvent e, DraggAblePanel pan) {
		// TODO Auto-generated method stub
		return analyze.generateSelectDragPanelCommand(e,pan);
	}

	public void endEventMove(BaseField field) {
		// TODO Auto-generated method stub
		this.endTileMove();
		
	}

	public boolean isDragging() {
		// TODO Auto-generated method stub
		return dragging;
	}

	public ICommand generateGameGridCommand(int x, int y, MouseEvent e, int mouseClicked, GameSquare square) {
		// TODO Auto-generated method stub
			return analyze.makeGameGridCommand(x,y, e,mouseClicked,square,view,dragging);

	}

	/*
	 * starts the mosnter placement
	 * the user can click squares in wich the monster will spawn including the first one
	 * monsters will spawn randomly on the selected squares.
	 * Stop by clicking something else (that isn't a button)
	 * right click will remove a square
	 */
	public void startMonsterPlacement(ArrayList<ViewSquare> tile,ViewMonster monster) {
		// TODO Auto-generated method stub
		System.out.println("monster placed");
		if(tile==null) {
			tile=new ArrayList<ViewSquare>();
		}
		analyze=new MonsterPlaceAnalyzer(monster,tile);
		ArrayList<ViewSquare> tileTotal=new ArrayList<ViewSquare>();
		
		for(ViewSquare square:tile) {
			tileTotal.addAll(view.changeShapeColor((ShapeItem) monster.getImageItem(),square,new Color(255,255,255,95)));
		}
		
		control.addMonsterColorStackElement(new ChangedColorStackElement(tileTotal));
	//	analyzer=new MonsterSelectionAnalyzer();
	}

	public void endMonsterPlacement() {
		ViewMonster monster=((MonsterPlaceAnalyzer) analyze).getMonsterplaced();
		
		analyze=new Analyzer();
		ChangedColorStackElement ment=monsterColorStack.pop();
		ment.reset();
		resetColors();
		for(ViewSquare square:ment.getSquares()) {
			view.changeTileColor(square, new Color(245,0,0,95));
		}
	
		colorStack.add(ment);
		
		
	
	}

	public BaseEventController getBaseEventControl() {
		return baseEventControl;
	}

	public void setBaseEventControl(BaseEventController baseEventControl) {
		this.baseEventControl = baseEventControl;
	}

	public void addToMarkedSquares(ViewSquare square) {
		
		monsterColorStack.peek().addtoViewSquares(square);
		view.changeTileColor(square, new Color(255,255,255,95));
	}

	public void addMonsterColorStackElement(ChangedColorStackElement changedColorStackElement) {
		// TODO Auto-generated method stub
		monsterColorStack.add(changedColorStackElement);
		//changedColorStack
		//view.changeTileColor(changedColorStackElement., color);
	}

	public void addToMarkedSquares(ArrayList<ViewSquare> square) {
		// TODO Auto-generated method stub
		monsterColorStack.peek().addtoViewSquares(square);
	
		
	}

	public void removeSquaresFromStack(ArrayList<ViewSquare> squares) {
		monsterColorStack.peek().removeViewSquares(squares);
		
	}

	public void keepToContinue(StopAble trigger) {
		// TODO Auto-generated method stub
		toContinue=new ContinueElement(trigger);
	}

	public void startTurns() {
		// TODO Auto-generated method stub
		gamecontrol.startNextTurn();
	}

	public void endTurn() {
		// TODO Auto-generated method stub
		gamecontrol.startNextTurn();
	}

	public void removeOldTemporary() {
		if( oldevent!=null) {
		
			BaseField field=oldevent.getField();
			field.setVisible();
			
			field.activateTextLabelMouselistener();
			oldevent=null;
		}
		
		view.removeShownInField(temporaryLocation);
		
	}

	public void keepNewTemporary(TriggerField whereshow) {
		// TODO Auto-generated method stub
		temporaryLocation=whereshow;
	}

	public void endOldMove(DraggAblePanel select) {
		
		select.setVisible();
		view.endDragEvent(select);
	}

	public void openMonsterEditor() {
		theFrame.openMonsterEditor();
		
	}
	public void openItemEditor() {
		// TODO Auto-generated method stub
		theFrame.openItemEditor();
	}
	public void openHeroEditor() {
		// TODO Auto-generated method stub
		theFrame.openHeroEditor();
	}

	public void openSkillEditor() {
		// TODO Auto-generated method stub
		theFrame.openSkillEditor();
	}

	

	public void openMonsterEditor(Monster mon) {
		theFrame.openMonsterEditor(mon);
		
	}
	
	public void openStoryEditor() {
		theFrame.openStoryEditor();
		
	}

	public void setView(IView newview) {
		// TODO Auto-generated method stub
		view=newview;
	}

	public static void setController(UserInputController userInput) {
		// TODO Auto-generated method stub
		control=userInput;
	}

	public void saveMovement() {
		// TODO Auto-generated method stub
		
	}

	public MonsterTurnTrigger getMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	public void openMainMenu() {
	
		theFrame.initialiseMenu(new MainMenu(theFrame.getWidth(),theFrame.getHeight()));
		ItemController.reset();
		//control=null;
		// TODO Auto-generated method stub
		
	}


	public void removeMonster(GameMonster monster) {
		gamecontrol.removeMonster(monster);
	}

	public void defeatHero(GameHero hero) {
		System.out.println("hero defeated");
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		CustomInteger hope=control.getHope();
		
		System.out.println("hope instance 2 "+hope);
		control.setHopeValue(hope.getTheInteger()-1);
		//hope.setTheInteger(hope.getTheInteger()-1);
		System.out.println("hope changed to "+hope.getTheInteger());
	}

	public void addEndPhaseListener(EndPhaseListener trig) {
		// TODO Auto-generated method stub
		theFrame.addEndPhaseListener(trig);
	}

	public void saveThis(WorldSaveFile thefile) {
		thefile.setBaseEventControl(this.baseEventControl);
		
	}

	public void startEditor() {
		initialiseBaseEventController(new BaseEventController(new StartUpTrigger(),new EndPhaseTrigger()));
		// TODO Auto-generated method stub
		
	}

	public BasicCommand generateDragPanelCommand(MouseEvent arg0, DraggAblePanel pan) {
		// TODO Auto-generated method stub
		return analyze.generateDragPanelCommand(arg0,pan);
	}

	public BasicCommand generateSelectArrowCommand(int x, int y, MouseEvent arg0, int mousePressed, ViewArrow arrow,
			SubDragPanel panel, DragPanel parent) {
		
		return analyze.generateSelectArrowCommand(x,y,arg0,mousePressed,arrow,panel,parent);
	}

	public Component getMainFrame() {
		// TODO Auto-generated method stub
		return theFrame;
	}

	public Boolean showNextText() {
		Boolean bool=false;
		if(textstarted) {
			for(int i=0;i<nexttextlistener.size();i++) {
				NextTextListeners listener = nexttextlistener.get(i);
				bool=listener.nextText();
			}
		}
		return bool;
	}

	public void addNextTextListener(NextTextListeners listener) {
		nexttextlistener.add(listener);
	}

	public void showTextDialog(String text, ArrayList<TextOption> newoptions) {
		currentText.showTextDialog(text, newoptions);
		currentText.revalidate();
		currentText.repaint();
	}

	public void endCampaign() {
		if(testBox==null) {
			testBox=new MainFrame();
		}
	

		if(miniEventMode) {
			System.out.println("ended minievent");
			notifyMiniEventModeListeners();
		}
		else {
			testBox.removeAll();
			nexttextlistener.clear();
			testBox.setVisible(false);
			// TODO Auto-generated method stub
		}
		
		
	}
	private void notifyMiniEventModeListeners() {
		while(!miniEventListeners.isEmpty()) {
			MiniEventListener listent = miniEventListeners.get(0);
			listent.miniEventEnded();
		}
		
	}

	public void loadConstantfile(WorldSaveFile g) {
		ItemController control=ItemController.getItemController();
		control.addAllValues(g.getCustomValues());
		
	}

	public void loadAndStartGame(String currentAdventure, CampaignFile file) {
		//first do travel event
		
		//then start it for real
		ItemController control=ItemController.getItemController();
		control.setCampaignFile(file);
		File workingDirectory = new File(System.getProperty("user.dir")+"/SavedWorlds");
		
		File f=new File(currentAdventure);
		if(f!=null) {
			
			WorldSaveFile g=null;
			 FileInputStream fileIn;
			try {
				fileIn = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				g = (WorldSaveFile) in.readObject();
			    in.close();
			    fileIn.close();
			    System.out.println("the read file is " +g);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//view.startQuestEditor();
			//view.loadGame(g);
			this.startGame(g,file);
		}
	}

	public static void renew() {
		// TODO Auto-generated method stub
		control=control.createUserInputController(null, control.getTheView(), control.theFrame);
	}

	public void endHeroPicking() {
		//testBox.setVisible(false);
		// TODO Auto-generated method stub
		
		for(int i=0;i<endHeroPickListener.size();i++) {
			endHeroPickListener.get(i).HeroesPicked();
			i--;
		}
	}


	public void addEndHeroPickerListener(EndHeroPickListener loadNewCampaignGameCommand) {
		// TODO Auto-generated method stub
		endHeroPickListener.add(loadNewCampaignGameCommand);
	}

	public void removeEndHeroPickerListener(EndHeroPickListener loadNewCampaignGameCommand) {
		// TODO Auto-generated method stub
		endHeroPickListener.remove(loadNewCampaignGameCommand);
	}

	public void startSkillShop(GameHero hero) {
		SkillShop shop=new SkillShop(testBox.getSize(), testBox, hero.getHero());
		shop.setVisible(true);
		
	}

	public void startCityShop() {
		// TODO Auto-generated method stub
		ItemController control=ItemController.getItemController();
		ItemShop shop=new ItemShop(testBox.getSize(), testBox);
		shop.setVisible(true);
	}
	

	public void addNextFreeTimeListener(FreeTimeEndListener freeTimeEndListener) {
		
		freetimeendlisteners.add(freeTimeEndListener);
	}

	public void beginFreeTimeEvent() {
		// TODO Auto-generated method stub
		freeTimeMode=true;
	}

	public void endFreeTimeEvent() {
		// TODO Auto-generated method stub
		freeTimeMode=false;
		freetimeendlisteners.clear();
	}

	public void nextFreeTime(int currentamount) {
		// TODO Auto-generated method stub
		ArrayList<SubQuestFile> availableSubQuests=new ArrayList<SubQuestFile>();
		String directoryPath= System.getProperty("user.dir")+"/SubQuests";
		File dir = new File(directoryPath);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      Optional<String> answer=this.getExtensionByStringHandling(child.getName());
		      System.out.println();
		      if(answer.get().equals("ser")){
		    	  
		    	 SubQuestFile i=null;
					 FileInputStream fileIn;
					try {
						fileIn = new FileInputStream(child);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						i = (SubQuestFile) in.readObject();
					    in.close();
					    fileIn.close();
					    System.out.println("the read file is " +i);
					    availableSubQuests.add(i);
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		      }
		    }
		  }
		  ItemController itcontrol=ItemController.getItemController();
		  CampaignFile file=itcontrol.getCampaignFile();
		  availableSubQuests.removeAll(file.getDoneSubQuests());
		  HashMap<MapLocation,SubQuestFile> currentSubQuests=new HashMap<MapLocation,SubQuestFile>();
		  while (currentSubQuests.size()<3&&availableSubQuests.size()!=0) {
			 int p= Tools.getRandomInt(availableSubQuests.size());
			 SubQuestFile fil=availableSubQuests.get(p);
			
			 MapLocation l=fil.getLoc();
			 currentSubQuests.put(l,fil);
			 for(int i=0; i<availableSubQuests.size();i++) {
				if(l==availableSubQuests.get(i).getLoc()) {
					availableSubQuests.remove(i);
					i--;
				}
			 }
			 
		  }
		  boolean tonext=false;
		  
		
		  mapFrame=new MapFrame(currentamount,currentSubQuests.keySet(),new ActionTaker<MapLocation>() {

			@Override
			public void perform(MapLocation value) {
				if(value!=MapLocation.CITY) {
					ItemController control=ItemController.getItemController();
					SubQuestFile file=currentSubQuests.get(value);
					boolean bool=SubQuestInfoDialog(mapFrame,file);
					if(bool) {
						closeMapFrame();
						
						startGame(file.getFile(),control.getCampaignFile());
					}
				}
				else {
					closeMapFrame();
					startCityMenu();
					
				}
			
				
			}
		  }
		  );
		  mapFrame.setVisible(true);
		  
		  
		  
		  
		 
		  
		  
		  
	}
	public void showCityMenu() {
		testBox.setTitle("City menu");
		testBox.initialiseMenu(men);
		testBox.bringToFront(men);
		testBox.setVisible(true);
		
	}
	
	public void startCityMenu() {
		
		//testBox.
		men=new CityMenu(testBox.getWidth(),testBox.getHeight());
		testBox.initialiseMenu(men);
		testBox.setVisible(true);
	}
	
	public static boolean SubQuestInfoDialog(Object obj,SubQuestFile file) {
	  
	    System.out.println("subquest name"+file.getName());
	    JPanel textPanel=new JPanel();
	    textPanel.setFont(new Font("Arial", Font.BOLD, 20)); 
	    textPanel.setLayout(new GridBagLayout());
	    JLabel name=new JLabel(file.getName());
	    name.setText(file.getName());
	    name.setMinimumSize(new Dimension(100,25));
	    name.setPreferredSize(new Dimension(100,25));
	   
	    JTextArea textArea = new JTextArea(file.getDescription());
	    textArea.setEditable(false);
	    textArea.setColumns(30);
	    textArea.setRows(10);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setSize(textArea.getPreferredSize().width, textArea.getPreferredSize().height);
	    JScrollPane pane=new JScrollPane(textArea);
	    

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
	    textPanel.add(name,c);
	    c.gridx = 0;
        c.gridy = 1;
	    textPanel.add(textArea,c);
	
	    textPanel.add(pane,c);
	   
	    int ret = JOptionPane.showConfirmDialog((Component) obj, textPanel, "SubQuest Info", JOptionPane.OK_OPTION);
	    if (ret == 0) {
	    
	        return	true;
	    } else {
	       // MyDialogs.Toast("Canceled by user\nChanges not saved", "Your choise");
	    }
	    return false;
	} 
	public void closeMapFrame() {
		mapFrame.dispose();
	}
	public Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	public void startRest() {
		ItemController control=ItemController.getItemController();
		control.setHopeValue(Math.max(control.getHope().getTheInteger(), 2));
		this.endGame();
		
	}

	public void startMiniEvent() {
		// TODO Auto-generated method stub
		this.miniEventMode=true;
	}
	public void endMiniEvent() {
		// TODO Auto-generated method stub
		this.miniEventMode=false;
		miniEventListeners.clear();
	}

	public void addEndMiniEventListener(MiniEventListener miniEventListener) {
		// TODO Auto-generated method stub
		miniEventListeners.add(miniEventListener);
	}

	public void performTravelEvent(CampaignSaveFile g,EndTravelEventListener listen) {
		UnNamedActionTaker take=new UnNamedActionTaker(g,listen);
		take.perform(null);
	}



	




	





	







	









	
}
