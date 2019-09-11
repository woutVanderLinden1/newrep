package StoryEditor;

import view.viewItems.ItemBox.SelectKind;

public class StartElementPanel extends DraggAblePanel {

	
	public StartElementPanel() {
		super("start");
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.STORYELEMENT;
	}
	
	
	
}
