package view.viewItems.ItemBox;

import java.awt.Image;
import java.awt.datatransfer.Transferable;

import misc.NameHolder;

public interface SelectAble extends NameHolder {


	/*

	public void rotate();

	public int getAngle();

	public Image getImage();

	public ItemOptions getOption();

	public double getScaleWidth();

	public int getLeftOff();

	public double getScaleHeight();

	public int getTopOff();

	public ImageItem getImageItem();

	public void setAngle(int i);

	public TileItem getTileItem();
	*/
	public SelectKind getKind();
	
	public void select();
	public void deselect();
	public boolean isSelected();

	public ImageItem getImageItem();

	public boolean isMapItem();

	public void makeVisible();

	public void makeInvisible();

	public void setVisible();

}
