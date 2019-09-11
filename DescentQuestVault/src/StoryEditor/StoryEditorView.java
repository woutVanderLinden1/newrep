package StoryEditor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.BaseEventController;
import controller.commands.ICommand;
import frame.MainFrame;
import misc.ActivateAble;
import misc.save.WorldSaveFile;
import model.Activation;
import model.event.MonsterTurnTrigger;
import model.event.MovementString;
import model.event.extraevents.TextOption;
import monsterEditor.MonsterEditor;
import view.IDrawWindow;
import view.IView;
import view.ViewManager;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.BaseField;
import view.events.EventBox;
import view.events.TriggerField;
import view.game.GameDoor;
import view.game.GameMonster;
import view.game.GameTile;
import view.game.GameToken;
import view.game.MonsterKind;
import view.hero.GameHero;
import view.menu.Menus;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.ShapeItem;
import view.viewItems.TileItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.Connection;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.ViewTileExit;

public class StoryEditorView extends ViewManager implements IView {

	private StoryEditor frame;
	private InfoItemBox info;
	private EventBox events;
	private StoryTextFrame storyframe;
	
	public StoryEditorView(StoryEditor frame) {
		super(frame);
		this.frame = frame;
		info=frame.getInfobox();
		events=frame.getEventsOf();
		storyframe=frame.getStoryframe();
	}


	

	@Override
	public ICommand getPlaceSelectedCommand(ViewSquare tile) {
		// TODO Auto-generated method stub
		return super.getPlaceSelectedCommand(tile);
	}

	@Override
	public void setSelected(SelectAble item) {
		info.setSelected(item);
	}

	@Override
	public void deleteSelected() {
		SelectAble selected=info.getSelected();
		//rotate selected in the  gridpanel if it's there
		
		info.removeSelected();
		events.deleteSelected(selected);
		storyframe.deleteSelected(selected);
	}

	@Override
	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return info.hasSelected();
	}

	@Override
	public SelectAble getSelected() {
		// TODO Auto-generated method stub
		return info.getSelected();
	}

	@Override
	public void addSelectedToTriggerField(TriggerField field) {
		//super.addSelectedToTriggerField(field);
		events.addEventToTriggerField(info.getSelected(), field);
	}

	@Override
	public void addEventToTriggerField(BaseField field, TriggerField basetrigger) {
		// TODO Auto-generated method stub
		//events.add
		events.addEventToTriggerField(field, basetrigger);
	}

	@Override
	public void endDragEvent(DraggAblePanel todrag) {
		events.endDragEvent(todrag);
		//storyframe.endDragEvent(todrag);
	//	super.endDragEvent(todrag);
		
	}
	@Override
	public void startDragItem(BaseField todrag) {
		events.startDragEvent(todrag);
		storyframe.startDragEvent(todrag);
		
	}
	@Override
	public void ShowSelectedInTriggerField(TriggerField whereshow) {
		// TODO Auto-generated method stub
		events.showInTriggerField(getSelected(),whereshow);
		//super.ShowSelectedInTriggerField(whereshow);
	}

	@Override
	public void removeShownInField(TriggerField thefield) {
		// TODO Auto-generated method stub
		events.removeTemporaryShown(thefield);
		//super.removeShownInField(thefield);
	}


	@Override
	public TriggerField getFieldAt(int x, int y) {
	
		return events.getFieldAt(x, y);
	}
	@Override
	public boolean eventBoxContains(int x, int y) {
		// TODO Auto-generated method stub
		return events.containsPoint(x,y);
	}


	@Override
	public void refreshSelected() {
		info.refreshSelected();
		
	}

	@Override
	public void clearEventBox() {
		// TODO Auto-generated method stub
		events.clearEventBox();
	}

	@Override
	public void startDragDragPanel(DraggAblePanel todrag) {
		storyframe.startDragPanel(todrag);
		
	}

	


}
