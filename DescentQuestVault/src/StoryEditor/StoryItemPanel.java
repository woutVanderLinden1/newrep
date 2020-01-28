package StoryEditor;

import java.awt.event.MouseListener;

import MouseListeners.SelectEventListener;
import view.events.StoryElement;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectKind;

public class StoryItemPanel extends DraggAblePanel {
	
	private StoryElementItem item;
	

	private SelectStoryItemListener listen;
	
	public StoryElement getItem() {
		// TODO Auto-generated method stub
		return item;
	}

	public StoryItemPanel(String text) {
		super(text);
		listen=new SelectStoryItemListener(this);
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		item=new StoryElementItem();
	
		item.setImage(image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return item;
	}

}
