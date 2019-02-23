package view.events;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import model.event.Event;
import model.event.Trigger;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public class ModifierField extends TriggerField {

	
	
	public ModifierField(Trigger trig, int i, int j) {
		super(trig, i, j);
		setTrigitem(new ModifierItem(trig));
		createBaseImage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.MODIFIER;
	}

	
	@Override
	protected void createBaseImage() {
		// TODO Auto-generated method stub
		textcolor=new Color(0,255,0);
		createImage(new Color(255,250,250));

	}

	public void select() {
		super.select();
		createImage(new Color(225,220,220));
	}
	
	public void deselect() {
		super.deselect();
		createImage(new Color(255,250,250));
	}

}
