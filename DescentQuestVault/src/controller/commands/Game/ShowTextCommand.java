package controller.commands.Game;

import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import frame.MainFrame;
import model.event.EventEndListener;
import model.event.extraevents.TextOption;

public class ShowTextCommand extends BasicCommand implements ICommand {
	
	private String text;
	private ArrayList<TextOption> newoptions;
	private EventEndListener listen;
	/*

	public ShowTextCommand(String thetext) {
		// TODO Auto-generated constructor stub
		text=thetext;
	}

	public ShowTextCommand(String thetext, ArrayList<TextOption> options) {
		// TODO Auto-generated constructor stub
		text=thetext;
		newoptions=options;
	}
	*/

	public ShowTextCommand(String thetext, ArrayList<TextOption> options, EventEndListener eventEndListener) {
		// TODO Auto-generated constructor stub
		text=thetext;
		newoptions=options;
		listen=eventEndListener;
		
	}

	public ShowTextCommand(String string, EventEndListener eventEndListener) {
		text=string;
		listen=eventEndListener;
		
	}

	@Override
	public void perform() {
		if(newoptions!=null) {
			view.showTextDialog(text,newoptions, listen);
		}
		else {
			view.showTextDialog(text,listen);
		}
		// TODO Auto-generated method stub
		MainFrame.mainFrame.revalidate();
		MainFrame.mainFrame.repaint();
	}

}
