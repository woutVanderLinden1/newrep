package controller.analyzer;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import StoryEditor.AddEventToStoryElementPanelCommand;
import StoryEditor.DragPanel;
import StoryEditor.DraggAblePanel;
import StoryEditor.SubDragPanel;
import StoryEditor.ViewArrow;
import controller.KeyAction;
import controller.MouseAction;
import controller.ResetTileColorCommand;
import controller.UserInputController;
import controller.analyzer.states.IAnalyzerState;
import controller.command.game.ShowActivationsInGameCommand;
import controller.commands.BasicCommand;
import controller.commands.CancelTileMoveCommand;
import controller.commands.ChangeTileColorCommand;
import controller.commands.ICommand;

import controller.commands.ShowOccupiedCommand;
import controller.commands.ShowSelectedInTriggerContainerCommand;
import controller.commands.select.AddSelectedToTriggerFieldCommand;
import controller.commands.select.SelectCommand;
import controller.commands.select.StartDragCommand;
import controller.commands.select.StartDragElementCommand;
import controller.commands.select.StartDragEventCommand;
import controller.commands.select.StartDragTriggerCommand;
import controller.commands.select.StartTileDragCommand;
import controller.commands.select.UnselectTileCommand;
import model.event.Event;
import view.IDrawWindow;

import view.IView;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.BaseField;
import view.events.EventField;
import view.events.EventItem;
import view.events.MultiTriggerField;
import view.events.TriggerContainer;
import view.events.TriggerField;
import view.game.GameSquare;
import view.viewItems.DoorItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.SelectAble;
/**
 * tha analyzer, an analyzer can take elements of a mouseevent and bind them to an
 * appropriate command
 * @author r0454860
 *
 */
public class Analyzer {

	private IAnalyzerState state;
	
	/**
	 * starts the analyzer
	 *
	 */
	public Analyzer() {}
	
	/**
	 * makes a command dependent on the analyzerstate
	 * 
	 * @param x
	 * the x mouselocation
	 * @param y
	 * the y mouselocation
	 * @param act
	 * the mouseaction
	 * @param wind
	 * the window where the mouse acted
	 * @param view
	 * the view where the mouse acted
	 * @return
	 * a command that will be performed 
	 * determined depending on the state
	 */
	public ICommand makeCommand(int x, int y,MouseAction act, IDrawWindow wind, IView view){
		if(state==null){
			System.out.println("null returned");
			return null;
		}
		return state.makeCommand(x,y,act,wind,view);
	}

	/**
	 * makes a command dependent on the analyzerstate
	 * @param view 
	 * @param square 
	 * @param mouseClicked 
	 * @param y 
	 * @param x 
	 * 
	 * @param act
	 * a keyaction
	 * @param wind
	 * the active window
	 * @param view
	 * the view 
	 * @return
	 * a command that can be performed
	 * determined depending on the state
	 */
	public ICommand makeGridCommand(int x, int y,MouseEvent event, int mouseClicked, ViewSquare square, IView view, boolean dragging) {
			UserInputController control=UserInputController.getController();
			//make into an arraylist
			ICommand command=null;

			switch(mouseClicked) {
				case MouseEvent.MOUSE_CLICKED:
					if(square.hasElement()) {
						command=new SelectCommand(square.getTopItem());
					}
					else {
						if(square.hasTile()){
							System.out.println("new tile selected correct");
							command=new SelectCommand(square.getContainingTile());
						}
						else {
							command=new UnselectTileCommand();
						}
					}
					/*
					else {
						if(view.hasSelected()) {
							//show the squares that the selected will take
							if(view.isLegalOccupied(square,view.getSelected())) {
								command=view.getPlaceSelectedCommand(square);
					
							}
						
						}
					}
					*/
					
					
					break;
				case MouseEvent.MOUSE_RELEASED:
					if(!dragging) {
						return null;
					}
					if(square==null) {
						return null;
					}
					if(view.hasSelected()) {
						
						//show the squares that the selected will take
						if(view.isLegalOccupied(square,view.getSelected())) {
							
							command=view.getPlaceSelectedCommand(square);
				
						}
						/*
						else {
							//if not legal occupied and it's a moved tile you must cancel the action
							if(control.tileMoving()) {
								command=new CancelTileMoveCommand();
							}
							/*
							if(control.eventMoving()) {
								CancelEventMove();
							}
							*/
						
					
					
					}
					break;
				case MouseEvent.MOUSE_MOVED:
					/*
					System.out.println("command received");
					if(view.hasSelected()) {
						//show the squares that the selected will take
						if(view.isLegalOccupied(square,view.getSelected())) {
							System.out.println("connectionfun");
							if(view.isConnected(square,view.getSelected())) {
								command=new ShowOccupiedCommand(square,view.getSelected(),new Color(0,255,0,97));
								
							}
							else {
								command=new ShowOccupiedCommand(square,view.getSelected(),new Color(255,255,255,97));
							}
						}
						else {
							command=new ShowOccupiedCommand(square,view.getSelected(),new Color(240,0,0,97));
							
						}
					}
					else {
						System.out.println("has no selected");
						*/
					if(square==null) {
						return null;
					}
					command=new ChangeTileColorCommand(square,new Color(10,10,240,97));
					//}
				
					break;
				case MouseEvent.MOUSE_DRAGGED:
				
					
					if(!dragging) {
						return null;
					}
					/*
					if(square==null) {
						return null;
					}
					*/
					
					System.out.println("command received");
					SelectAble selected=view.getSelected();
					if(view.hasSelected()) {
						switch(view.getSelected().getKind()) {
						case MONSTER:
						case TOKEN:
						case DOOR:
							ShapeItem dooritem1=(ShapeItem)selected;
							if(view.isLegalOccupied(square,selected)) {
								command=new ShowOccupiedCommand(square,dooritem1,new Color(255,255,255,97));
								
							}
							else {
								command=new ShowOccupiedCommand(square,dooritem1,new Color(240,0,0,97));
								
							}
							break;
					
							
						case EVENT:
							break;
						case TILEITEM:
							TileItem item=(TileItem)selected;
							//show the squares that the selected will take
							System.out.println("islegal "+view.isLegalOccupied(square,selected));
							if(view.isLegalOccupied(square,selected)) {
							
								if(view.isConnected(square,item)) {
									command=new ShowOccupiedCommand(square,item,new Color(0,255,0,97));
									
								}
								else {
									command=new ShowOccupiedCommand(square,item,new Color(255,255,255,97));
								}
							}
							else {
								command=new ShowOccupiedCommand(square,item,new Color(240,0,0,97));
								
							}
							break;
						case VIEWTILE:
							ViewTile tile=(ViewTile)selected;
							TileItem item2=tile.getTileItem();
							//show the squares that the selected will take
							if(view.isLegalOccupied(square,view.getSelected())) {
								System.out.println("connectionfun");
								if(view.isConnected(square,((ViewTile)view.getSelected()).getTileItem())) {
									command=new ShowOccupiedCommand(square,item2,new Color(0,255,0,97));
									
								}
								else {
									command=new ShowOccupiedCommand(square,item2,new Color(255,255,255,97));
								}
							}
							else {
								command=new ShowOccupiedCommand(square,item2,new Color(240,0,0,97));
								
							}
						
							break;
						case VIEWTOKEN:
						case VIEWMONSTER:
						case VIEWDOOR:
							MapItem vdoor=(MapItem)selected;
							ShapeItem vdooritem=(ShapeItem) vdoor.getImageItem();
							if(view.isLegalOccupied(square,selected)) {
								command=new ShowOccupiedCommand(square,vdooritem,new Color(255,255,255,97));
								
							}
							else {
								command=new ShowOccupiedCommand(square,vdooritem,new Color(240,0,0,97));
								
							}
							break;
						default:
							break;
						
						}
						
					}
					else {
						System.out.println("has no selected");
						command=new ChangeTileColorCommand(square,new Color(10,10,240,97));
					}
				
					break;
					
				case MouseEvent.MOUSE_EXITED:
					command=new ResetTileColorCommand();
					break;
				case MouseEvent.MOUSE_PRESSED:
					if(square.hasElement()) {
						command=new StartDragElementCommand(square.getTopItem(),x,y,event.getXOnScreen(),event.getYOnScreen(),square);
					}
					else {
						if(square.hasTile()){
							System.out.println("new tile selected correct");
							command=new StartTileDragCommand(square.getContainingTile(),x,y,event.getXOnScreen(),event.getYOnScreen(),square);
						//	command=new SelectTileCommand(square.getContainingTile());
						}
					}
				
					break;
				default:
					break;
			}
			return command;
		}
	

	public IAnalyzerState getState() {
		return state;
	}

	public void setState(IAnalyzerState state) {
		this.state = state;
	}

	public ICommand makeCommand(KeyAction act, IDrawWindow wind, IView view) {
		// TODO Auto-generated method stub
		return state.makeCommand(act, wind, view);
	}

	public ICommand generateSelectCommand(int mousePressed,IView view ,int x,int y,MouseEvent ev) {
		//should initialise selectcommand if selected area is not empty
		SelectAble selected=view.getSelected();
		System.out.println(selected.getKind());
		if(view.hasSelected()) {
			switch(selected.getKind()) {
			case TRIGGER:
				BaseField field2=(BaseField) selected;
				return new StartDragEventCommand(field2,ev.getX(),ev.getY(),ev.getXOnScreen(),ev.getYOnScreen());
				
			
			case EVENT:
				/*
				Event event=(Event) ((EventField) selected).getEv().copy();
				BaseField field=new EventField(event,100);
				return new StartDragEventCommand(field,ev.getX(),ev.getY(),ev.getXOnScreen(),ev.getYOnScreen());
				*/
				BaseField field=(BaseField) selected;
				return new StartDragEventCommand(field,ev.getX(),ev.getY(),ev.getXOnScreen(),ev.getYOnScreen());
				
			
			case TILEITEM:
				return new StartDragCommand(selected,x,y);
			
			case VIEWTILE:
				ViewTile tile=(ViewTile) selected;
				return new StartTileDragCommand(tile,x,y,ev.getXOnScreen(),ev.getYOnScreen());
			case VIEWTOKEN:
				ViewToken token=(ViewToken) selected;
				return new StartDragElementCommand(token,x,y,ev.getXOnScreen(),ev.getYOnScreen(),null);
				
				//return new StartViewItemDragCommand(token,x,y,ev.getXOnScreen(),ev.getYOnScreen());
		
			case DOOR:
			case TOKEN:
				
			case MONSTER:
				return new StartDragCommand(selected,x,y);
			case STORYELEMENT:
				DraggAblePanel panel=(DraggAblePanel) selected;
				return new StartDragPanelCommand(panel,ev.getX(),ev.getY(),ev.getXOnScreen(),ev.getYOnScreen());
			
				
			default:
				break;
			
			}
			
		}
		return null;
	}

	public ICommand makeTriggerFieldCommand(int x, int y, IView view, MouseEvent arg0, int mouseEntered,
			TriggerField thefield,boolean dragging) {
		UserInputController control=UserInputController.getController();

		switch(mouseEntered) {
			case MouseEvent.MOUSE_PRESSED:
				
				return new StartDragEventCommand(thefield,arg0.getX(),arg0.getY(),arg0.getXOnScreen(),arg0.getYOnScreen());
			
			
			case MouseEvent.MOUSE_DRAGGED:
				if(view.hasSelected()) {
					
					if(thefield!=null) {
						
						if(thefield.isTemporary()) {
							return null;
						}
						
						if(((BaseField) view.getSelected()).canNotContain(thefield)) {
							return null;
						}
						
					}
					
					//only if in
					TriggerField activefield=thefield;
							//view.getFieldAt(x,y);
					
					System.out.println(activefield);
					if(activefield!=null) {
						
						return new ShowSelectedInTriggerFieldCommand(activefield);
					}
					if(view.eventBoxContains(x,y)) {
						System.out.println("is in eventbox");
						return new ShowSelectedInTriggerFieldCommand(null);
					}
					return null;
					
					
				}
				break;
			case MouseEvent.MOUSE_EXITED:
				
				
				if(!dragging) {
					
					return null;
				}
				//do rollovers

				if(view.hasSelected()) {
					SelectAble selected=view.getSelected();
					
					switch(selected.getKind()) {
					
					case TRIGGER:
					case EVENT:
					
						return new RemoveShowInTriggerFieldCommand(thefield);
				
				
					default:
						break;
					
					}
				}
				break;
			case MouseEvent.MOUSE_ENTERED:
				
				if(!dragging) {
					return null;
				}
			
				if(view.hasSelected()) {
					SelectAble selected=view.getSelected();
					switch(selected.getKind()) {
				
					case TRIGGER:
					case EVENT:
						return new ShowSelectedInTriggerFieldCommand(thefield);
				
				
					default:
						break;
					
					}
				}
				break;
			case MouseEvent.MOUSE_RELEASED:
				System.out.println("arrived here released");
				if(!dragging) {
					return null;
				}
				if(view.hasSelected()) {

					SelectAble selected=view.getSelected();
					switch(selected.getKind()) {
					case MODIFIER:
					case TRIGGER:
						control.performCommand( new RemoveShowInTriggerFieldCommand(thefield));
						if(thefield!=null) {
							if(thefield.isTemporary()) {
								return new AddSelectedToTriggerFieldCommand(thefield.getTriggerField());
							}
							if(((BaseField) view.getSelected()).canNotContain(thefield)) {
								return new CancelTileMoveCommand();
							}
						}
						
							
						
					case EVENT:
						control.performCommand( new RemoveShowInTriggerFieldCommand(thefield));
						return new AddSelectedToTriggerFieldCommand(thefield);
				
				
					default:
						break;
					
					}
				}
			case MouseEvent.MOUSE_MOVED:
				
				break;
					
			
		}
		//make into an arraylist
		return null;
	}

	public ICommand generateSelectFieldCommand(MouseEvent arg0, BaseField pan) {
		System.out.println("started dragging by the fieldlistener");
		return new StartDragEventCommand(pan,arg0.getX(),arg0.getY(),arg0.getXOnScreen(),arg0.getYOnScreen());
	}

	public ICommand makeGameGridCommand(int x, int y, MouseEvent e, int mouseClicked, GameSquare square, IView view,
			boolean dragging) {
		ICommand command=null;

		switch(mouseClicked) {
			case MouseEvent.MOUSE_CLICKED:
				if(square.hasActivateAble()) {
					return new ShowActivationsInGameCommand(new Point(x,y),square.getActivateAbles());
				}
				break;
		}
				
		// TODO Auto-generated method stub
		return null;
	}

	public ICommand generateSelectDragPanelCommand(MouseEvent arg0, DraggAblePanel pan) {
		// TODO Auto-generated method stub
		return new SelectCommand(pan);
		
	}

	public BasicCommand makeEventToStoryElementPanelCommand(int x, int y, MouseEvent arg0,
			int mouseReleased, SubDragPanel panel, SelectAble selectAble, boolean dragging, DragPanel parent) {
		// TODO Auto-generated method stub
		if(parent.isArrowDragging()) {
			if(parent.isMouseInComponent(arg0)) {
				DraggAblePanel hovered=parent.getHovered(arg0);
				return new addArrowToStoryElementPanelCommand(hovered,parent);
			}
			else {
				return new DeactivateGlassPaneCommand(parent);
			}
		}
		if(dragging) {
			return new AddEventToStoryElementPanelCommand(panel,new Point(x,y),(DraggAblePanel) selectAble);
		}
		
		return null;
		
		
	}

	public BasicCommand generateDragPanelCommand(MouseEvent arg0, DraggAblePanel pan) {
		// TODO hier was ik mee bezig
		SelectAble selected=pan;
		Point point=arg0.getPoint();
		
			switch(selected.getKind()) {
			case EVENT:
				Event event=(Event) ((EventField) selected).getEv().copy();
				BaseField field=new EventField(event,100);
				System.out.println("started dragging from analyzer");
				return new StartDragEventCommand(field,point.x,point.y,arg0.getXOnScreen(),arg0.getYOnScreen());
				
			
			case TILEITEM:
				return new StartDragCommand(selected,point.x,point.y);
			
			case VIEWTILE:
				ViewTile tile=(ViewTile) selected;
				return new StartTileDragCommand(tile,point.x,point.y,arg0.getXOnScreen(),arg0.getYOnScreen());
			case DOOR:
			case TOKEN:
			case MONSTER:
				return new StartDragCommand(selected,point.x,point.y);
			case STORYELEMENT:
				DraggAblePanel panel=(DraggAblePanel) selected;
				return new StartDragPanelCommand(panel,point.x,point.y,arg0.getXOnScreen(),arg0.getYOnScreen());
			
				
			default:
				break;
			
			}
			
		
		return null;
	}

	public BasicCommand generateSelectArrowCommand(int x, int y, MouseEvent arg0, int mousePressed, ViewArrow arrow,
			SubDragPanel panel, DragPanel parent) {
		// TODO Auto-generated method stub
		if(mousePressed==MouseEvent.MOUSE_PRESSED) {
			return new SelectCommand(arrow);
		}
		return null;
		
	}
}
