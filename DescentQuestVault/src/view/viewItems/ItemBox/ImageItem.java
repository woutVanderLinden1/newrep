package view.viewItems.ItemBox;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import org.apache.commons.io.FilenameUtils;

import controller.IController;
import frame.SubContainer;
import model.Item;
import model.Hero.Hero;
import model.Monster.Monster;
import model.Tile.Direction;
import model.Tile.TileExit;
import model.Tile.tilesets.OrginalTile;
import model.door.Door;
import model.generators.Generator;
import model.generators.ModifierGenerator;
import model.search.BasicToken;
import model.values.CustomValue;
import view.viewItems.NameChangeListener;


public abstract class ImageItem <P> implements SelectAble,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<NameChangeListener> namechangelisteners=new ArrayList<NameChangeListener>();
	private String name;
	private String IDName;

	protected String path;

	//todo move exits to tile only knowledge
	protected Item item;
	protected transient Image image;
	protected int angle;
	
	//private OrginalTile tile;

	protected transient Image lastImage;
	protected boolean selected;
	

	public int getAngle() {
		return angle;
	}
	

	
	public Item getItem() {
		return item;
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
	public void setItem(Item item) {
		this.item = item;
	}

	public Image getImage() {
		return lastImage;
		
	}

	public static Image copyImage(Image image2) {
		BufferedImage copyOfImage = 
				   new BufferedImage(image2.getWidth(null), image2.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
				Graphics g = copyOfImage.createGraphics();
				g.drawImage(image2, 0, 0, null);
		return copyOfImage;
	}

	public void setImage(Image image) {
		this.image = image;
		lastImage=image;
	}

	public String getIDName() {
		return IDName;
	}

	public void setIDName(String IDName) {
		this.IDName = IDName;
	}



	public ImageItem(Path a) {
		
		String newIDName=a.toString();	
		try {
			image=ImageIO.read(new File(newIDName));
			newIDName= FilenameUtils.removeExtension(newIDName);
			newIDName = FilenameUtils.getBaseName(newIDName);
			IDName=newIDName;
			//item=new Item(IDName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	public ImageItem(Hero hero) {
	//	this.tile=tile;
		IDName=hero.getName();
		path="Images/Hero/"+IDName+".png";
		item=hero;
		this.reinitialise();
		//name=IDName;
	}

	public ImageItem(OrginalTile tile) {
	//	this.tile=tile;
		IDName=tile.getName();
		path="Images/Tiles/"+IDName+".png";
		item=tile;
		this.reinitialise();
		//name=IDName;
	}
	public ImageItem(Door tile) {
	//	this.tile=tile;
		IDName=tile.getName();
		path="Images/Doors/"+IDName+".png";
		item=tile;
		this.reinitialise();
		
	}
	public ImageItem(BasicToken token) {
		IDName=token.getName();
		path="Images/token/"+IDName+".png";
		item=token;
		this.setName(token.getName());
		this.setIDName(token.getName());
		this.reinitialise();
	}

	public ImageItem() {
		// TODO Auto-generated constructor stub
	}

	public ImageItem(Monster token) {
		IDName=token.getName();

		 path="Images/monsters/"+IDName+".png";
		item=token;
		this.reinitialise();
	}

	public ImageItem(CustomValue val) {
		IDName=val.getName();
		name=val.getName();
		item=val;
		//this.reinitialise();
		// TODO Auto-generated constructor stub
	}



	public ImageItem(Generator val) {
		IDName=val.getName();
		name=val.getName();
		item=val;
	}







	public boolean getItemOption() {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract ItemOptions getOption();

	





	private Collection<? extends TileExit> rotateExits(int i) {
		// TODO Auto-generated method stub
		return null;
	}




	public BufferedImage rotateImageByDegrees(BufferedImage img, double degrees) {
	        double rads = Math.toRadians(degrees);
	        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
	        int w = img.getWidth();
	        int h = img.getHeight();
	        int newWidth = (int) Math.floor(w * cos + h * sin);
	        int newHeight = (int) Math.floor(h * cos + w * sin);

	        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = rotated.createGraphics();
	        AffineTransform at = new AffineTransform();
	        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

	        //int x = clickPoint == null ? w / 2 : clickPoint.x;
	       // int y = clickPoint == null ? h / 2 : clickPoint.y;

	        at.rotate(rads, w/2, h/2);
	        g2d.setTransform(at);
	        g2d.drawImage(img, 0, 0, null);
	        //g2d.setColor(Color.RED);
	        //g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
	        g2d.dispose();

	        return rotated;
	  }

	
	
	 public abstract ImageItem clone();

	 
	 
	
	
	 protected static void displayMatrix( int mat[][]) 
	    { 
		 int N=mat.length;
	        for (int i = 0; i < N; i++) 
	        { 
	            for (int j = 0; j < N; j++) 
	                System.out.print(" " + mat[i][j]); 
	       
	            System.out.print("\n"); 
	        } 
	        System.out.print("\n"); 
	    } 
	       
	
	  // An Inplace function to rotate a N x N matrix 
    // by 90 degrees in anti-clockwise direction 
    protected static void rotateMatrix(int amount, int mat[][]) 
    { 
    	int N=mat.length;
    	while(amount>0) {
    		  for (int x = 0; x < N / 2; x++) 
    	        { 
    	            // Consider elements in group of 4 in  
    	            // current square 
    	            for (int y = x; y < N-x-1; y++) 
    	            { 
    	                // store current cell in temp variable 
    	                int temp = mat[x][y]; 
    	       
    	                // move values from right to top 
    	                mat[x][y] = mat[y][N-1-x]; 
    	       
    	                // move values from bottom to right 
    	                mat[y][N-1-x] = mat[N-1-x][N-1-y]; 
    	       
    	                // move values from left to bottom 
    	                mat[N-1-x][N-1-y] = mat[N-1-y][x]; 
    	       
    	                // assign temp to left 
    	                mat[N-1-y][x] = temp; 
    	            } 
    	        } 
    		  amount--;
    	}
    	
        // Consider all squares one by one 
      
    }





	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return item.getScaleWidth();
	}

	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return item.getScaleHeight();
	}



	public int getLeftOff() {
		// TODO Auto-generated method stub
		return item.getLeftOff();
	}

	public int getTopOff() {
		// TODO Auto-generated method stub
		return item.getTopOff();
	}
	public int getRightOff() {
		// TODO Auto-generated method stub
		return item.getRightOff();
	}

	public int getBottomOff() {
		// TODO Auto-generated method stub
		return item.getBottomOff();
	}


	public Image getPreciseImage(int i,int j) {
		if(lastImage==null) {
			reinitialise();
		}
		Image newimg =null;
		switch (angle) {
		case 0:
		case 180:
		 newimg = lastImage.getScaledInstance( (int)(i),(int) (j),  java.awt.Image.SCALE_SMOOTH ) ;
		 break;
		case 90:
		case 270:
		 newimg = lastImage.getScaledInstance( (int)(i),(int) (j),  java.awt.Image.SCALE_SMOOTH ) ;
		 break;	
		}
		// TODO Auto-generated method stub
		return newimg;
	}

	public Image getScaleImage(int i) {
		if(lastImage==null) {
			reinitialise();
		}
		Image newimg =null;
		switch (angle) {
		case 0:
		case 180:
		 newimg = lastImage.getScaledInstance( (int)(i*item.getScaleWidth()),(int) (i*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
		 break;
		case 90:
		case 270:
		 newimg = lastImage.getScaledInstance( (int)(i*item.getScaleHeight()),(int) (i*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
		 break;	
		}
		// TODO Auto-generated method stub
		return newimg;
	}
	public boolean hasShape() {
		return false;
	}

	@Override
	public boolean isMapItem() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void reinitialise() {
		
		try {
			System.out.println(path);
			setImage(ImageIO.read(new File(path)));
			if(image==null) {
				System.out.println("thepath " +path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setName(String newname) {
		name=newname;
		triggerNameChangeListeners(newname);
	}
	

	
	public void addNameChangeListener(NameChangeListener listen) {
		namechangelisteners.add(listen);
	}
	public void triggerNameChangeListeners(String newname) {
		for(NameChangeListener listen:namechangelisteners) {
			listen.nameChanged(newname);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void changeName(String newname) {
		this.setName(newname);
	}
	
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
		item.decreaseAvailability();
	}



	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return item.isAvailable();
	}
	
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		System.out.println("added item specifics of" +this);
		if(item!=null) {
			item.addEventSpecifics(itemInfoText);
		}
		
		
	}



	public void setVisible() {
		
	}

}
