package misc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.event.Trigger;
import model.event.extraevents.TextOption;
import view.events.BaseField;
import view.events.EventField;
import view.events.TriggerContainer;
import view.events.TriggerField;

public class Tools {
	
	private static int scale=64;
	private static Random random=new Random();

	public static BufferedImage resize (int width, int height, BufferedImage toRescale){
		BufferedImage myPicture=toRescale;
		//myPicture = ImageIO.read(new File("Images//texture.jpg"));
		Image img= myPicture.getScaledInstance(width, height, 0);
		 BufferedImage bimage = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();
		    myPicture=bimage;
		return myPicture;
	}
	


	public static BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    g.dispose();
	    return bi;
	}
	public static int getNonYEmpties(int[][] shape, int ystartoff) {
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


	public static int getNonXEmpties(int[][] shape, int xstartoff) {
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



	public static TriggerField CorrespondingTriggerField(Trigger trigger, ArrayList<BaseField> fields) {
		for(BaseField field:fields) {
			if(field.getUnivent().equals(trigger)) {
				return (TriggerField) field;
			}
			else {
				switch(field.getKind()) {
				
				case EVENT:
					
					break;
				case TRIGGER:
					TriggerField toreturn=CorrespondingTriggerField( trigger, ((TriggerField) field).getFields());
					if(toreturn!=null) {
						return toreturn;
					}
					break;
				case MODIFIER:
					System.out.println("error not yet implemented");
					break;
				default:
					break;
				
				}
		}
		}
		return null;
	}



	public static boolean containsWithSameTrigger(ArrayList<BaseField> fields, BaseField field) {
		for(BaseField newfield:fields) {
			System.out.println("containssamen");
			System.out.println(field.getUnivent());
			System.out.println(newfield.getUnivent());
			if(field.getUnivent().equals(newfield.getUnivent())) {
				return true;
			}
		}
		return false;
	}



	public static int getRandomInt(int size) {
		return random.nextInt(size);
		
	}



	public static TriggerContainer CorrespondingTriggerField(TextOption trigger, ArrayList<TriggerContainer> fields) {
		for(BaseField field:fields) {
			if(field.getUnivent().equals(trigger)) {
				return (TriggerContainer) field;
			}
		
		}
		return null;
	}
}
