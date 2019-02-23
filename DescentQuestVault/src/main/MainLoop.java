package main;

import frame.MainFrame;
import frame.QuestCreatorFrame;

public class MainLoop {

	public static void main(String[] args){
		MainLoop loop=new MainLoop();
		loop.start();
	}

	private static boolean closeRequested=false;

	private void start() {
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
