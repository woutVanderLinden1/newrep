package StoryEditor;

import view.events.StoryElement;
import view.viewItems.ItemBox.SelectKind;

public class StartElementPanel extends StoryItemPanel {

	
	
	public StartElementPanel() {
		super("start");
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.STORYELEMENT;
	}

	
	
	
}
