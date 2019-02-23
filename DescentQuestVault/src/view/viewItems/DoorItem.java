package view.viewItems;

import model.door.Door;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class DoorItem extends ShapeItem {

	public DoorDirection direct;
	

	public DoorItem(Door tile) {
		super(tile);
		direct=DoorDirection.VERT;
		// TODO Auto-generated constructor stub
	}

	public DoorDirection getDirect() {
		return direct;
	}

	public void setDirect(DoorDirection direct) {
		this.direct = direct;
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.DOOR;
	}



	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		DoorItem toreturn=new DoorItem((Door) this.getItem());
		toreturn.setAngle(angle);
		return toreturn;
	}

	@Override
	public void setAngle(int newAngle) {
		switch(newAngle) {
		case 0:
		case 180:
			setDirect(DoorDirection.VERT);
			break;
		case 90:
		case 270:
			setDirect(DoorDirection.HORIZON);
			break;
		}
		super.setAngle(newAngle);
	}
	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Token;
	}


}
