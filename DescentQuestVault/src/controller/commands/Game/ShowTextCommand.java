package controller.commands.Game;

import java.util.ArrayList;

import controller.commands.BasicCommand;
import controller.commands.ICommand;
import model.event.extraevents.TextOption;

public class ShowTextCommand extends BasicCommand implements ICommand {
	
	private String text;
	private ArrayList<TextOption> newoptions;

	public ShowTextCommand(String thetext) {
		// TODO Auto-generated constructor stub
		text=thetext;
	}

	public ShowTextCommand(String thetext, ArrayList<TextOption> options) {
		// TODO Auto-generated constructor stub
		text=thetext;
		newoptions=options;
	}

	@Override
	public void perform() {
		if(newoptions!=null) {
			view.showTextDialog(text,newoptions);
		}
		else {
			view.showTextDialog(text);
		}
		// TODO Auto-generated method stub
		
	}

}
