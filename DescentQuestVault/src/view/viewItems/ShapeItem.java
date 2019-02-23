package view.viewItems;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.Monster.Monster;
import model.Tile.tilesets.OrginalTile;
import model.door.Door;
import model.search.BasicToken;
import view.Items.Map.ViewSquare;
import view.viewItems.ItemBox.ImageItem;

public abstract class ShapeItem extends  ImageItem{

	protected int[][] lastShape;
	
	
	public ShapeItem(Door tile) {
		super(tile);
		// TODO Auto-generated constructor stub
		setAngle(0);
	}

	public ShapeItem(OrginalTile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
		setAngle(0);
	}

	public ShapeItem(BasicToken token) {
		super(token);
		setAngle(0);
	}

	public ShapeItem(Monster token) {
		super(token);
		setAngle(0);
	}

	public boolean hasShape() {
		return true;
	}
	public int[][] getShape() {
		return lastShape;
	
		
	}

	public void rotate() {
	
		setAngle(angle+90);
	}
	
	 public void setAngle(int angle) {
			// TODO Auto-generated method stub
		 while(angle>=360) {
				angle=angle-360;
			} 
		 this.angle=angle;	
		
		prepareAngle(angle);
	}
	protected void prepareAngle(int angle2) {
		lastImage=ImageItem.copyImage(image);
		lastImage=rotateImageByDegrees((BufferedImage) lastImage,angle);
	
		int[][] shape=item.getShape();
		displayMatrix(shape);
		
		
		lastShape=shape;

		//displayMatrix(lastShape);
		//place exits correctly
		
		
	
		
	}
	public int getPointValue(Point point, int x, int y) {
		int firstX=point.x-x;
		int firstY=point.y-y;
		if(firstY>=0&&firstY<lastShape.length) {
			if(firstX>=0&&firstX<lastShape[0].length) {
				return lastShape[firstY][firstX];
				
			}
		}
		return 0;
	
	}
	public boolean containsPoint(Point point, int x, int y) {
		int firstX=point.x-x;
		int firstY=point.y-y;
		if(firstY>=0&&firstY<lastShape.length) {
			if(firstX>=0&&firstX<lastShape[0].length) {
				if(lastShape[firstY][firstX]!=0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Point getPointOff() {
		int xstartoff=0;
		int ystartoff=0;
		while(lastShape[ystartoff][xstartoff]==0) {
    		xstartoff++;
    		if(xstartoff>lastShape[ystartoff].length-1) {
    			ystartoff++;
    			xstartoff=0;
    		}
    	}
		return new Point(xstartoff,ystartoff);
	}
	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return true;
	}




}
