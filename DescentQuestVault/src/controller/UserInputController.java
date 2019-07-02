package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import controller.analyzer.Analyzer;
import controller.analyzer.DragAnalyzer;
import controller.analyzer.MonsterPlaceAnalyzer;
import controller.analyzer.states.IAnalyzerState;
import controller.commands.AddDoorToViewSquareCommand;
import controller.commands.AddEventToTriggerFieldCommand;
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
import frame.MainFrame;
import frame.SubContainer;
import misc.SampleFile;
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
import model.event.extraevents.TextStop;
import model.event.trigger.EndPhaseTrigger;
import model.values.CustomInteger;
import monsterEditor.MonsterEditorView;
import view.IDrawWindow;
import view.IView;
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
import view.menu.QuestGame;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;

import java.awt.AWTEvent;
import java.awt.Color;
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




public class UserInputController implements MouseListener,KeyListener, ButtonPressedListener, IController {


	private static UserInputController control;
	private ContinueElement toContinue;
	private GameController gamecontrol;
	
	
	private static UserInputController getUserInputController() {
		return control;
	}
	private Stack<ChangedColorStackElement> colorStack =new Stack<ChangedColorStackElement>();
	private Stack<ChangedColorStackElement> monsterColorStack =new Stack<ChangedColorStackElement>();
	
	private DragStack draggingStack;
	
	private DragMouseMotionListener listen=new DragMouseMotionListener();
	
	
	private Analyzer analyze;
	protected StackManager stack;
	private IView view;
	private IModel mod;
	private Map<IDrawWindow,StackManager> stackMap;
	private MainFrame theFrame;
	private boolean dragging;
	private boolean tileMoveing;
	private OldTilePlace oldTile;
	private CustomInteger hope;
	
	private BaseEventController baseEventControl;


	private boolean norefresh;


	private OldEventPlace oldevent;
	
	private TriggerField temporaryLocation;
	
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

	public void startGame() {
		gamecontrol=new GameController();
		QuestGame game=new QuestGame(theFrame.getSize());
		
		game.addGameStartListener(baseEventControl.getStartuptrigger());
		game.addEndPhaseListener(baseEventControl.getEndtrigger());
		game.addMonsterPlaceListener(gamecontrol);
		game.addHeroPlaceListener(gamecontrol);
		game.setGameController(gamecontrol);
		game.initialiseGame(new SampleFile());
		theFrame.startTestGame(game);
		
		
		// TODO Auto-generated method stub
		
	}
	public void endGame() {
		// TODO Auto-generated method stub
		theFrame.endTestGame();
	}


	public ICommand generateTriggerFieldCommand(int x, int y, MouseEvent arg0, int mouseEntered,
			TriggerField thefield) {
		// TODO Auto-generated method stub
		return analyze.makeTriggerFieldCommand(x,y,view,arg0,mouseEntered,thefield,dragging);
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

	public ICommand generateSelectFieldCommand(MouseEvent arg0,EventField field) {
		// TODO Auto-generated method stub
		return analyze.generateSelectFieldCommand(arg0,field);
	}
	public ICommand generateSelectFieldCommand(MouseEvent arg0, BaseField field) {
		// TODO Auto-generated method stub
		return analyze.generateSelectFieldCommand(arg0,field);
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
		view.removeShownInField(temporaryLocation);
		
	}

	public void keepNewTemporary(TriggerField whereshow) {
		// TODO Auto-generated method stub
		temporaryLocation=whereshow;
	}

	public void endOldMove(BaseField field) {
		// TODO Auto-generated method stub
		view.endDragEvent(field);
	}

	public void openMonsterEditor() {
		theFrame.openMonsterEditor();
		
	}

	public void openMonsterEditor(Monster mon) {
		theFrame.openMonsterEditor(mon);
		
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



	









	
}
