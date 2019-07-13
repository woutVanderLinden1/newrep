package view.viewItems;

import model.Monster.MonsterSet;
import model.search.BasicToken;
import view.Items.Map.MapItem;
import view.Items.Map.ViewToken;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class TokenItem extends ShapeItem {

	public TokenItem(BasicToken token) {
		super(token);
		this.setName(token.getName());
		
		this.setIDName(token.getName());
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
	public MapItem createViewItem() {
		return item.createItem();
		
		
	}

	public boolean isSearch() {
		// TODO Auto-generated method stub
		return ((BasicToken) item).isSearch();
	}
}
