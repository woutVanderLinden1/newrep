package model.event;

import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public interface EventBase {

	SelectKind getKind();

	ImageItem getImageItem();

	String getIDName();

	//public void setName(String newname);
	//public String getName();
}
