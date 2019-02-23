package view.Items.Map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

import misc.ActivateAble;
import misc.listeners.RemoveTileListener;

import java.awt.Color;
import model.Tile.Tile;
import view.viewItems.TileItem;
import view.viewItems.ItemBox.ImageItem;



public class ViewSquare extends JComponent implements Serializable {
	private int x;
	private int y;
	private int width;
	private int height;
	private TileItem tile;
	private int xID;
	private int yID;
	private ArrayList<MapItem> items=new ArrayList<MapItem>();
	
	private int occupationnr;
	private ViewTile containingTile;
	
	public TileItem getTile() {
		return tile;
	}

	public void setTile(TileItem tileName) {
		this.tile = tileName;
	}

	public int getOccupationnr() {
		return occupationnr;
	}

	public void setOccupationnr(int occupationnr) {
		this.occupationnr = occupationnr;
	}

	public ViewTile getContainingTile() {
		return containingTile;
	}

	public void setContainingTile(ViewTile containingTile) {
		this.containingTile = containingTile;
	}

	private Color color;
	
	
	public ViewSquare(int x, int y, int w, int h,int Xid,int Yid){
		this.x=x;
		this.y=y;
		this.width=w;
		this.height=h;
		xID=Xid;
		yID=Yid;
	}
	
	public int getxID() {
		return xID;
	}

	public void setxID(int xID) {
		this.xID = xID;
	}

	public int getyID() {
		return yID;
	}

	public void setyID(int yID) {
		this.yID = yID;
	}

	@Override
	public void paint(Graphics g){
		//System.out.println("drawn right");
		g.setColor(Color.BLACK);
		g.drawRect(x,y,width,height);
	
		if(color!=null) {
			 Graphics2D g2 = (Graphics2D) g;
			 g2.setColor(color);
			 g2.fillRect(x,y,width,height);
			 g2.setColor(Color.black);
		
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;

	}

	public boolean hasTile() {
		if(containingTile!=null) {
			return true;
		}
		return false;
	}

	public void removeTile() {
		containingTile=null;
		tile=null;
		occupationnr=0;
		
	}

	public void addElement(MapItem viewdoor) {
		// TODO Auto-generated method stub
		items.add(viewdoor);
	}

	public boolean hasElement() {
		// TODO Auto-generated method stub
		return !items.isEmpty();
	}

	public MapItem getTopItem() {
		// TODO Auto-generated method stub
		return items.get(items.size()-1);
	}

	public void removeItem(MapItem toselect) {
		items.remove(toselect);
		
	}

	public ArrayList<ActivateAble> getActivateAbles(){
		ArrayList<ActivateAble> resultlist=new ArrayList<ActivateAble>();
		for(MapItem it:items) {
			if(it.isActivateAble()) {
				resultlist.add((ActivateAble) it);
			}
		}
		
		return resultlist;
		
	}
	public boolean hasActivateAble() {
		for(MapItem it:items) {
			if(it.isActivateAble()) {
				System.out.println("has activatteable");
				return true;
			}
		}
		return false;
	}


	
}
