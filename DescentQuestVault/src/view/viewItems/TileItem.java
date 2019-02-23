package view.viewItems;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import model.Tile.TileExit;
import model.Tile.tilesets.OrginalTile;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class TileItem extends ShapeItem {

	protected boolean selected;
	protected ArrayList<TileExit> exits;
	
	public TileItem(OrginalTile tile) {
		super(tile);

	}
	
	@Override
	public ItemOptions getOption() {
		return ItemOptions.Tile;
	}

	public ViewTile getTile(ViewSquare square, int x, int y) {
		// TODO Auto-generated method stub
		
		return new ViewTile(this,square,x,y);
	}

	@Override
	public TileItem clone() {
		// TODO Auto-generated method stub
		TileItem toreturn=new TileItem((OrginalTile) this.getItem());
		toreturn.setAngle(angle);
		return toreturn;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}



	public TileItem getTileItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		selected=true;
	}

	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		selected=false;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.TILEITEM;
	}

	public OrginalTile getOrginalTile() {
		// TODO Auto-generated method stub
		return (OrginalTile) super.getItem();
	}
	protected void prepareAngle(int angle2) {
		if(exits==null) {
			exits=new ArrayList<TileExit>();
		}
		int[][] shape=item.getShape();
		super.prepareAngle(angle2);
		int maxX=shape[0].length-1;
		int maxY=shape.length-1;
		
		exits.clear();
		switch(angle2) {
			case 0:
				for(TileExit exit:((OrginalTile) item).getExits()) {
					
					exits.add(exit);
				}
				break;
			
			case 90:
				rotateMatrix(3,shape);
				System.out.println("rotating");
				for(TileExit exit:((OrginalTile) item).getExits()) {
				
					Point point3=exit.getSquare3ShapeLocation();
					Point point2=exit.getSquare2ShapeLocation();
					Point newpoint3=new Point((int)( maxX-point3.getY()),(int)point3.getX());
					Point newpoint2=new Point((int)( maxX-point2.getY()),(int)point2.getX());
					exit.setSquare2ShapeLocation(newpoint2);
					exit.setSquare3ShapeLocation(newpoint3);
					exit.setDirect(exit.getDirect().nextDirection(3));
					exits.add(exit);
		
				}
				break;
			case 180:
				rotateMatrix(2,shape);
			
				for(TileExit exit:((OrginalTile) item).getExits()) {
					Point point3=exit.getSquare3ShapeLocation();
					Point point2=exit.getSquare2ShapeLocation();
					Point newpoint3=new Point((int)( maxX-point3.getX()),(int)(maxY-point3.getY()));
					Point newpoint2=new Point((int)( maxX-point2.getX()),(int)(maxY-point2.getY()));
					exit.setSquare2ShapeLocation(newpoint2);
					exit.setSquare3ShapeLocation(newpoint3);
					exit.setDirect(exit.getDirect().nextDirection(2));
					exits.add(exit);
					
				}
				break;
			case 270:
				rotateMatrix(1,shape);
				
				for(TileExit exit:((OrginalTile) item).getExits()) {
					Point point3=exit.getSquare3ShapeLocation();
					Point point2=exit.getSquare2ShapeLocation();
					Point newpoint3=new Point((int)( point3.getY()),(int)(maxY-point3.getX()));
					Point newpoint2=new Point((int)( point2.getY()),(int)(maxY-point2.getX()));
					exit.setSquare2ShapeLocation(newpoint2);
					exit.setSquare3ShapeLocation(newpoint3);
					exit.setDirect(exit.getDirect().nextDirection(1));
					exits.add(exit);
			
				}
				break;
			
			default:
				break;
		}
		
		lastShape=shape;

		//displayMatrix(lastShape);
		//place exits correctly
		
		
	
		
	}
	public ArrayList<TileExit> getExits() {
		return exits;
	}

	public void setExits(ArrayList<TileExit> exits) {
		this.exits = exits;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return true;
	}


	

}
