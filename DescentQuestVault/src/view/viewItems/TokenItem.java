package view.viewItems;

import model.search.BasicToken;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class TokenItem extends ShapeItem {

	public TokenItem(BasicToken token) {
		super(token);
	}
	
	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.TOKEN;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new TokenItem((BasicToken) item);
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Token;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return true;
	}
}
