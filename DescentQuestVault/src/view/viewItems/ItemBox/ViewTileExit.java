package view.viewItems.ItemBox;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import model.Tile.Direction;
import model.Tile.Square;
import model.Tile.Theme;
import model.Tile.TileExit;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;

public class ViewTileExit implements Serializable{
	private TileExit exit;
	private boolean connected=false;
	private ViewTile originalTile;
	//the square th
	private ViewSquare location3;
	private ViewSquare location2;
	private ViewTileExit connectedexit;
	private Connection connection;
	
	
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public ViewTileExit getConnectedexit() {
		return connectedexit;
	}
	public void setConnectedexit(ViewTileExit connectedexit) {
		this.connectedexit = connectedexit;
	}

	
	public ViewTile getOriginalTile() {
		return originalTile;
	}
	public void setOriginalTile(ViewTile originalTile) {
		this.originalTile = originalTile;
	}

	public ViewTileExit(TileExit exit2, ViewSquare square2, ViewSquare square3,ViewTile origin) {
		exit=exit2;
		connected=false;
		location2=square2;
		location3=square3;
		originalTile=origin;
		
	}
	
	public ViewSquare getLocation3() {
		return location3;
	}

	public void setLocation3(ViewSquare location3) {
		this.location3 = location3;
	}

	public ViewSquare getLocation2() {
		return location2;
	}

	public void setLocation2(ViewSquare location2) {
		this.location2 = location2;
	}
	
	
	public Direction getDirect() {
		// TODO Auto-generated method stub
		return exit.getDirect();
	}
	
	public boolean isConnectedWith(ViewTileExit avexit) {
		if(this.getTheme()!=avexit.getTheme()) {
			return false;
		}


		if(getDirect().isOpposite(avexit.getDirect())) {
		
			switch(getDirect()) {
			case DOWN:
				
				if(location3.getxID()==avexit.getLocation2().getxID()&&location3.getyID()+1==avexit.getLocation2().getyID()) {
					if(location2.getxID()==avexit.getLocation3().getxID()&&location2.getyID()+1==avexit.getLocation3().getyID()) {
						return true;
					}
				}
				break;
			case LEFT:
				
				if(location3.getxID()-1==avexit.getLocation2().getxID()&&location3.getyID()==avexit.getLocation2().getyID()) {
					if(location2.getxID()-1==avexit.getLocation3().getxID()&&location2.getyID()==avexit.getLocation3().getyID()) {
						return true;
					}
				}
				break;
			case RIGHT:
			
				if(location3.getxID()+1==avexit.getLocation2().getxID()&&location3.getyID()==avexit.getLocation2().getyID()) {
					if(location2.getxID()+1==avexit.getLocation3().getxID()&&location2.getyID()==avexit.getLocation3().getyID()) {
						return true;
					}
				}
				break;
			case UP:
			
				if(location3.getxID()==avexit.getLocation2().getxID()&&location3.getyID()-1==avexit.getLocation2().getyID()) {
					if(location2.getxID()==avexit.getLocation3().getxID()&&location2.getyID()-1==avexit.getLocation3().getyID()) {
						return true;
					}
				}
				break;
			default:
				break;
			
			
			
			
			}
			
		}
		
		
		return connected;
		
	}

	public Theme getTheme() {
		// TODO Auto-generated method stub
		return exit.getTheme();
	}
	public ArrayList<Point> getNexts() {
		ArrayList<Point> toreturn=new ArrayList<Point>();
		switch(getDirect()) {
		
		case DOWN:
			toreturn.add(new Point(location3.getxID(),location3.getyID()+1));
			toreturn.add(new Point(location2.getxID(),location2.getyID()+1));
			break;
		case LEFT:
			toreturn.add(new Point(location3.getxID()-1,location3.getyID()));
			toreturn.add(new Point(location2.getxID()-1,location2.getyID()));
			break;
		case RIGHT:
			toreturn.add(new Point(location3.getxID()+1,location3.getyID()));
			toreturn.add(new Point(location2.getxID()+1,location2.getyID()));
			break;
		case UP:
			toreturn.add(new Point(location3.getxID(),location3.getyID()-1));
			toreturn.add(new Point(location2.getxID(),location2.getyID()-1));
			break;
		default:
			break;
		
		}
		return toreturn;
	}
	public void connect(ViewTileExit exit2) {
		if(exit2!=null) {
			connected=true;
			connectedexit=exit2;
			originalTile.connect(exit2);
		}
		
		
	}
	public void disconnect() {
		connected=false;
		originalTile.disconnect(connectedexit);
		connectedexit=null;
	
		
	}
	
}
