package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

import frame.MainFrame;
import frame.QuestCreatorFrame;

public class MainLoop {

	public static void main(String[] args){
		MainLoop loop=new MainLoop();
		loop.start();
	}

	private static boolean closeRequested=false;

	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 



	
	private void start() {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("sources//descentquestbuilderfont2.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
		setUIFont (new javax.swing.plaf.FontUIResource("descentquestbuilderfont",Font.PLAIN,12));
		QuestCreatorFrame frame=new QuestCreatorFrame();
		frame.setVisible(true);
		
		while(!frame.isCloseRequested()){
			frame.performLoop();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if(!frame.isShowing()){
				closeRequested=true;
			}
			*/
			//System.out.println("hi");
		}
		frame.setVisible(false);
	}
}
