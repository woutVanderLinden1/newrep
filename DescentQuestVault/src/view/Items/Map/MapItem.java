package view.Items.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import frame.SubContainer;
import misc.ActivateAble;
import model.Activation;
import model.event.Univent;
import model.search.BasicToken;
import view.viewItems.GridPanel;
import view.viewItems.NameChangeListener;
import view.viewItems.ShapeItem;
import view.viewItems.TokenItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.SelectAble;
import view.viewItems.ItemBox.SelectKind;

public abstract class MapItem implements SelectAble, Serializable, EventHolder,ActivateAble{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ShapeItem item;
	protected Point point;
	protected ViewSquare baseSquare;
	protected boolean selected=false;
	protected Point widthheight;
	protected static int scalefactor=275;

	protected String name;
	protected ArrayList<ViewSquare> occupyingSquares;
	protected ArrayList<Activation> activations=new ArrayList<Activation>();

	
	
	
	protected MapItem(ImageItem image, ViewSquare square, int i, int j) {
		item =(ShapeItem) image.clone();
		widthheight=new Point((int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()));
		
		 baseSquare=square;
		//backgroundImage=Tools.resize(squareWidth*mapLength,squareWidth*mapLength,(BufferedImage) backgroundImage);
	
		point=new Point(i,j);
		occupyingSquares=new ArrayList<ViewSquare>();
	}
	
	public MapItem(BasicToken basicToken) {
		item =new TokenItem(basicToken);
		widthheight=new Point((int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()));
		occupyingSquares=new ArrayList<ViewSquare>();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Activation> getActivations(){
		return (ArrayList<Activation>) activations.clone();
	}
	
	public void addActivation(Activation act) {
		activations.add(act);
	}
	
	
	public ArrayList<ViewSquare> getOccupyingSquares() {
		return occupyingSquares;
	}

	

	public void setOccupyingSquares(ArrayList<ViewSquare> occupyingSquares) {
		this.occupyingSquares = occupyingSquares;
	}


	public ImageItem getTileImage() {
		return item;
	}
	public void setTileImage(ShapeItem tileImage) {
		this.item = tileImage;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public ViewSquare getBaseSquare() {
		return baseSquare;
	}
	public void setBaseSquare(ViewSquare square,int i, int j) {
		baseSquare=square;
		point=new Point(i,j);
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Point getWidthheight() {
		return widthheight;
	}
	public void setWidthheight(Point widthheight) {
		this.widthheight = widthheight;
	}

	@Override
	public void select() {
		setSelected(true);
		
	}
	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		setSelected(false);
	}
	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return item;
	}
	
	public void addSquare(ViewSquare square) {
		occupyingSquares.add(square);
	}
	public boolean containsSameSquares(ArrayList<ViewSquare> squareList) {
		for(ViewSquare square:squareList) {
			if(!this.occupyingSquares.contains(square)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean containsaSameSquare(ArrayList<ViewSquare> squareList) {
		for(ViewSquare square:squareList) {
			if(this.occupyingSquares.contains(square)) {
				return true;
			}
		}
		return false;
	}
	public void drawShape(Graphics g) {
		ShapeItem shapeit=(ShapeItem) this.getImageItem();
		Point off=shapeit.getPointOff();
		g.setColor(new Color(0,0,240,95));
		int scale=GridPanel.squareWidth;
		int[][] shape=shapeit.getShape();
		int xval=getNonXEmpties(shape,off.x);
		xval=off.x-xval;
		int yval=getNonYEmpties(shape,off.y);
		yval=off.y-yval;
		for(int i=0;i<shape.length;i++) {
			for(int j=0;j<shape[i].length;j++) {
				if(shape[i][j]!=0) {
					g.fillRect(point.x+(j-xval)*scale,point.y+(i-yval)*scale,scale,scale);
				}
			}
		}
		
		
	}
	


	private int getNonYEmpties(int[][] shape, int ystartoff) {
		int p=0;
		boolean val=false;
		for(int i=0; i<ystartoff;i++) {
			for(int j=0;j<shape[i].length;j++) {
				val=false;
				if(shape[i][j]!=0) {
					if(!val) {
						p++;
						val=true;
					}
				
				}
			}
		}
		return p;
		
	}


	private int getNonXEmpties(int[][] shape, int xstartoff) {
		int p=0;
		boolean val=false;
		for(int j=0; j<xstartoff;j++) {
			val=false;
			for(int i=0;i<shape.length;i++) {
			
			
				if(shape[i][j]!=0) {
					if(!val) {
						p++;
						val=true;
					}
				}
			}
		}
		return p;
		
	}
	
	public int getAngle() {
		return item.getAngle();
		
	}
	public void rotate() {
		// TODO Auto-generated method stub
		this.item.rotate();
		switch (item.getAngle()) {
		case 0:
		widthheight=new Point((int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()));
			break;
		case 90:
			widthheight=new Point((int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()));
			break;
		case 180:
			widthheight=new Point((int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()));
			break;
		case 270:
			widthheight=new Point((int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()));
			break;
		
			
		}
	}

	public boolean isMapItem() {
		return true;
	}


	public int[][] getShape() {
		// TODO Auto-generated method stub
		return item.getShape();
	}
	
	public void draw(Graphics g, SubContainer jPanel) {
		
		Image todraw=null;
		int sw=GridPanel.squareWidth;
		switch (item.getAngle()) {
		case 0:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,sw-todraw.getWidth(null)/2+(int) point.getX()+item.getLeftOff(),(int) point.getY()+item.getTopOff(),(ImageObserver) jPanel);
			break;
		case 90:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getBottomOff(),sw-todraw.getHeight(null)/2+(int) point.getY()+item.getLeftOff(),(ImageObserver) jPanel);
			break;
		case 180:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleWidth()),(int)( scalefactor*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,sw-todraw.getWidth(null)/2+(int) point.getX()+item.getRightOff(),(int) point.getY()+item.getBottomOff(),(ImageObserver) jPanel);
		break;
		case 270:
			todraw=item.getImage().getScaledInstance( (int)(scalefactor*item.getScaleHeight()),(int)( scalefactor*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
			g.drawImage(todraw,(int) point.getX()+item.getTopOff(),sw-todraw.getHeight(null)/2+(int) point.getY()+item.getRightOff(),(ImageObserver) jPanel);
			break;
		
			
		}
	}

	public Point getPointOff() {
		// TODO Auto-generated method stub
		return ((ShapeItem)this.getImageItem()).getPointOff();
	}


	public boolean isActivateAble() {
		// TODO Auto-generated method stub
		return false;
	}


	public void initialise() {
		// TODO Auto-generated method stub
		item.reinitialise();
		item.setAngle(this.getAngle());
		item.deselect();
	
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newname) {
		System.out.println("name changed "+newname);
		name=newname;
		
	}
	
	@Override
	public void changeName(String newname) {
		System.out.println("name changed");
		this.setName(newname);
		triggerNameChangeListeners(newname);
	}
	
	
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	
	public void addNameChangeListener(NameChangeListener listen) {
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}


	public abstract ArrayList<Univent> getEvents();
	
	public void makeInvisible() {
		
	}
	public void makeVisible() {
		
	}
	
	public void increaseAvailability() {
		// TODO Auto-generated method stub
		item.increaseAvailability();
	}

	public void reduceAvailability() {
		// TODO Auto-generated method stub
		item.reduceAvailability();
	}
	
	public void setVisible() {
		
	}
}
