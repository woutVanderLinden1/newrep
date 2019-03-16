package view.Items.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import frame.SubContainer;
import misc.Tools;
import model.Activation;
import model.event.Event;
import model.event.PlaceTileEvent;
import model.event.RemoveTileEvent;
import model.event.Univent;
import view.viewItems.GridPanel;
import view.viewItems.TileItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;
import view.viewItems.ItemBox.ViewTileExit;

public class ViewTile extends MapItem implements SelectAble {

	private static int scalefactor=275;
	
	private ArrayList<ViewTile> connectedTiles=new ArrayList<ViewTile>();
	private ArrayList<ViewTileExit> exits;

	private Event placeTileEvent;
	private RemoveTileEvent removeTileEvent;
	ArrayList<Univent> eventlist=new ArrayList<Univent>();
	private static int tilenr=0;
	
	private static String giveTileName() {
		tilenr++;
		return "tile"+tilenr;
	}
	
	/*
	
	public ViewTile(String tileName, Point location) {
		try {
			item = ImageIO.read(new File(tileName));
			item=item.getScaledInstance( 290, 290,  java.awt.Image.SCALE_SMOOTH ) ;
			
			//backgroundImage=Tools.resize(squareWidth*mapLength,squareWidth*mapLength,(BufferedImage) backgroundImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		point=location;
	}

*/


	public Event getPlaceTileEvent() {
		return placeTileEvent;
	}

	public void setPlaceTileEvent(Event placeTileEvent) {
		this.placeTileEvent = placeTileEvent;
	}

	public ViewTile(TileItem image, ViewSquare square, int i, int j) {
		super(image,square,i,j);
		this.setName(giveTileName());
		setPlaceTileEvent(new PlaceTileEvent(this));
		setRemoveTileEvent(new RemoveTileEvent(this));
		eventlist.add(placeTileEvent);
		eventlist.add(removeTileEvent);
		
	}
	protected void setRemoveTileEvent(RemoveTileEvent removeTileEvent2) {
		removeTileEvent=removeTileEvent2;
		
	}

	public int[][] getShape() {
		// TODO Auto-generated method stub
		return this.getTileItem().getShape();
	}

	@Override
	public void draw(Graphics g, SubContainer jPanel) {
		Image todraw=null;
		switch (item.getAngle()) {
		case 0:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getLeftOff(),(int) point.getY()+item.getTopOff(),(ImageObserver) jPanel);
			break;
		case 90:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getBottomOff(),(int) point.getY()+item.getLeftOff(),(ImageObserver) jPanel);
			break;
		case 180:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getRightOff(),(int) point.getY()+item.getBottomOff(),(ImageObserver) jPanel);
		break;
		case 270:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getTopOff(),(int) point.getY()+item.getRightOff(),(ImageObserver) jPanel);
			break;
		
			
		}
		
		
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void connect(ViewTileExit exit2) {
		// TODO Auto-generated method stub
		connectedTiles.add(exit2.getOriginalTile());
	}

	public ArrayList<ViewTileExit> getViewExits() {
		// TODO Auto-generated method stub
		return exits;
	}

	public void setViewExits(ArrayList<ViewTileExit> exits) {
		this.exits = exits;
	}


	


	public int getAngle() {
		// TODO Auto-generated method stub
		return item.getAngle();
	}


	public Image getImage() {
		// TODO Auto-generated method stub
		return item.getImage();
	}


	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return item.getOption();
	}

	
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return item.getScaleWidth();
	}

	public int getLeftOff() {
		// TODO Auto-generated method stub
		return item.getLeftOff();
	}

	
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return item.getScaleHeight();
	}


	public int getTopOff() {
		// TODO Auto-generated method stub
		return item.getTopOff();
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return item.getImageItem();
	}

	
	public void setAngle(int i) {
	
		// TODO Auto-generated method stub
		if(item!=null) {
			this.getTileItem().setAngle(i);
		}
	
	}
	

	public ViewSquare getSquare() {
		// TODO Auto-generated method stub
		return baseSquare;
	}
	


	public void disconnect(ViewTileExit other) {
		exits.remove(other);
		
	}


	public TileItem getTileItem() {
		// TODO Auto-generated method stub
		return (TileItem) item;
	}



	@Override
	public void select() {
		selected=true;
		
	}

	@Override
	public void deselect() {
		selected=false;
		
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return point.x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return point.y;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return widthheight.x;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return widthheight.y;
	}
	


	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.VIEWTILE;
	}

	public String getIDName() {
		// TODO Auto-generated method stub
		return getTileItem().getIDName();
	}

	@Override
	public ArrayList<Univent> getEvents() {
		// TODO Auto-generated method stub
		
	 
		return eventlist; 
	}

	public RemoveTileEvent getRemoveTileEvent() {
		// TODO Auto-generated method stub
		return removeTileEvent;
	}

	@Override
	public void removeActivation(Activation activation) {
		// TODO Auto-generated method stub
		
	}

	





	
	

}
