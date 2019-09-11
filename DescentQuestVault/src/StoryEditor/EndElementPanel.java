package StoryEditor;

import view.viewItems.ItemBox.SelectKind;

public class EndElementPanel extends DraggAblePanel {

	public EndElementPanel() {
		super("end");
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.STORYELEMENT;
	}

}
