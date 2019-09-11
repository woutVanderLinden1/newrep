package StoryEditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Arrow extends Line2D.Double implements DragPanelMoveListener,DeleteListener{

	AffineTransform tx = new AffineTransform();
	Line2D.Double line; 
	private DraggAblePanel origin;
	private DraggAblePanel target;
	private Color color=Color.black;
	private boolean finished=false;
	protected boolean selected=false;
	private SubDragPanel parent;
	
	Polygon arrowHead = new Polygon();  
	public Arrow(DraggAblePanel origin,int ex, int ey,SubDragPanel parent) {
		this.origin=origin;
		
		origin.addDragPanelMoveListener(this);
		//this.target=target;
		arrowHead.addPoint( 0,13);
		arrowHead.addPoint( -13, -13);
		Point p=origin.getLocation();
		line=new Line2D.Double(p.x+origin.getWidth()/2,p.y+origin.getHeight()/2,ex,ey);
		refreshStartLocation();
		this.parent=parent;
		
		
	}
	private void refreshStartLocation() {
		// TODO Auto-generated method stub
		Point p=origin.getLocation();
		
		line.setLine(new Point(p.x+origin.getWidth()/2,p.y+origin.getHeight()/2),line.getP2());
	}
	/*
	public Arrow(int sx,int sy,int ex,int ey) {
		arrowHead.addPoint( 0,13);
		arrowHead.addPoint( -13, -13);
		arrowHead.addPoint(13,-13);
		line=new Line2D.Double(sx,sy,ex,ey);
	}
	*/
	

	public void drawArrow(Graphics2D g2) {
		Stroke defaultStroke;
	   

	    defaultStroke = g2.getStroke();
	    //
	    //do your thing
	    //
	    //reset by
	     
		//g2.setStroke(new BasicStroke(10));
		Graphics2D graph=(Graphics2D) g2.create();
		graph.setColor(color);
		drawRectangle(graph);
		
		//this.drawArrow(g2);
		g2.setStroke(defaultStroke);
		this.drawArrowHead(g2);
 
	}
	private void drawRectangle(Graphics2D g2) {
		tx.setToIdentity();
	    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
	    int length=(int) Math.sqrt(Math.pow(line.y2-line.y1,2)+ Math.pow(line.x2-line.x1,2));
	    tx.translate(line.x1, line.y1);
	    tx.rotate((angle));
	    g2.setTransform(tx);
	    //g2.drawRect(0,0,length,10);
	    Rectangle rect=new Rectangle();
	    rect.x=-5;
	    rect.y=-5;
	    rect.width=length;
	    rect.height=10;
	    //g2.setBackground(color);
	    g2.setColor(color);
	    g2.fill(rect);
	    
	    //g2.dispose();
	    
		
	}
	
	public boolean contains(Point XY) {
		tx.setToIdentity();
	    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
	    int length=(int) Math.sqrt(Math.pow(line.y2-line.y1,2)+ Math.pow(line.x2-line.x1,2));
	    tx.translate(line.x1, line.y1);
	    tx.rotate((angle));
	    //g2.setTransform(tx);
		Rectangle rect=new Rectangle();
	    rect.x=-5;
	    rect.y=-5;
	    rect.width=length;
	    rect.height=10;
	    Shape newShape = tx.createTransformedShape(rect);
	    return newShape.contains(XY);
	    
		
	}


	// [...]
	private void drawArrowHead(Graphics2D g2d) {  
	    tx.setToIdentity();
	    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
	    tx.translate(line.x2, line.y2);
	    tx.rotate((angle-Math.PI/2d));  

	    Graphics2D g = (Graphics2D) g2d.create();
	    g.setTransform(tx);   
	    g.fill(arrowHead);
	    g.dispose();
	}


	public void setEndPosition(Point newLocation) {
		// TODO Auto-generated method stub
		//newLocation.setLocation(newLocation.x,newLocation.y);
		line.setLine(line.getP1(),newLocation);
		line =new Line2D.Double(line.x1,line.y1,line.x2-5,line.y2+20);
		System.out.println("arrow extended "+ line);
	}


	public void setEnd(DraggAblePanel c) {
		target=c;
		finished=true;
		target.addDeleteListener(this);
		target.addDragPanelMoveListener(this);
		origin.addDeleteListener(this);
		refreshEndLocation();
		
	}


	private void refreshEndLocation() {
		
		Point newLocation=new Point(target.getX()+target.getWidth()/2,target.getY()+5);
		line.setLine(line.getP1(),newLocation);
		line =new Line2D.Double(line.x1,line.y1,line.x2+5,line.y2+5);
		
	}


	@Override
	public void panelmoved(DraggAblePanel draggAblePanel) {
		refreshStartLocation();
		refreshEndLocation();
		
	}
	public void hoveredOver() {
		// TODO Auto-generated method stub
		System.out.println("hovered over");
		setColor(Color.blue);
	}
	public void hoveredStopped() {
		// TODO Auto-generated method stub
		setColor(Color.black);
	}
	public void clearFromListeners() {
		origin.removeMovePanelListener(this);
		if(target!=null) {
			target.removeMovePanelListener(this);
		}
		// TODO Auto-generated method stub
		
	}
	public boolean isFinsihed() {
		// TODO Auto-generated method stub
		return finished;
	}
	public void setColor(Color orange) {
		if(!selected) {
			color=orange;
		}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void panelDeleted() {
		// TODO Auto-generated method stub
		//
		delete();
		
	}
	private void triggerArrowDeleteListeners() {
		// TODO Auto-generated method stub
		
	}
	public void delete() {
		// TODO Auto-generated method stub
		triggerArrowDeleteListeners();
		parent.removeArrow(this);
	}
	
}
