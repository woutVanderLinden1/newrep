package monsterEditor;

import StoryEditor.DraggAblePanel;
import controller.commands.ICommand;
import frame.MainFrame;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import view.ViewManager;
import view.Items.Map.ViewSquare;
import view.events.BaseField;
import view.events.EventBox;
import view.events.TriggerField;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.SelectAble;

public class MonsterEditorView extends ViewManager {
	
	private InfoItemBox info;
	private EventBox events;

	public MonsterEditorView(MonsterEditor frame) {
		super(frame);
		info=frame.getInfo();
		events=frame.getEvents();
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
	//	super.endDragEvent(todrag);
		
	}
	@Override
	public void startDragItem(BaseField todrag) {
		events.startDragEvent(todrag);
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
}
