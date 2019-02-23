package view.viewItems.ItemBox;

import javax.swing.JButton;

public class ItemButton extends JButton  {

	private ImageItem item;
	
	public ItemButton(ImageItem item, String idName) {
		super(idName);
		this.item=item;
	}

	public ImageItem getItem() {
		return item;
	}

	public void setItem(ImageItem item) {
		this.item = item;
	}
	
	//let the button listen to the item and remove itself when not available
	
}
